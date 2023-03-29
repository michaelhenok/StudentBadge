package miu.cs544.badgemembershipsystem.service;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ActionNotAllowedException;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.*;
import miu.cs544.badgemembershipsystem.dto.request.BadgeRequest;
import miu.cs544.badgemembershipsystem.dto.request.BadgeSwipeRequest;
import miu.cs544.badgemembershipsystem.dto.response.BadgeResponse;
import miu.cs544.badgemembershipsystem.repository.BadgeRepo;
import miu.cs544.badgemembershipsystem.repository.LocationRepo;
import miu.cs544.badgemembershipsystem.repository.MemberRepo;
import miu.cs544.badgemembershipsystem.repository.PlanRepo;
import miu.cs544.badgemembershipsystem.service.events.BadgeSwipeEvent;
import miu.cs544.badgemembershipsystem.utils.enums.Intervals;
import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;
import miu.cs544.badgemembershipsystem.utils.enums.TransactionStatus;
import org.modelmapper.ModelMapper;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class BadgeServiceImpl implements BadgeService {

    private final BadgeRepo badgeRepo;
    private final PlanRepo planRepo;
    private final LocationRepo locationRepo;
    private final PlanService planService;
    private final LocationService locationService;
    private final TransactionService transactionService;
    private final MemberShipService memberShipService;
    private final MemberRepo memberRepo;
    private final ModelMapper modelMapper;

    private final ApplicationEventPublisher publisher;

    @Override
    public List<BadgeResponse> findAll() {
        return badgeRepo.findAll().stream().map(m -> modelMapper.map(m, BadgeResponse.class)).toList();
    }

    @Override
    public BadgeResponse findById(long id) {
        Optional<Badge> badge = badgeRepo.findById(id);
        if (badge.isEmpty()) {
            throw new ResourceNotFoundException("Badge Not Found");
        }
        return modelMapper.map(badge, BadgeResponse.class);
    }

    @Override
    public List<BadgeResponse> findBadgesByMemberID(long memberId) {
        return badgeRepo.findBadgesByMemberID(memberId).stream().map(m -> modelMapper.map(m, BadgeResponse.class)).toList();
    }

    @Override
    public void save(BadgeRequest badgeRequest) {
        if (badgeRequest.getMemberID() == null) {
            throw new ResourceNotFoundException("Member Not Provided");
        }
        Badge badge = modelMapper.map(badgeRequest, Badge.class);
        Optional<Member> member = memberRepo.findById(badgeRequest.getMemberID());
        if (member.isEmpty()) {
            throw new ResourceNotFoundException("Member Not Found");
        }
        if (member.get().getBadges().size() > 0) {
            //find active badge and update active to false
            Badge activeBadge = member.get().getActiveBadge();
            activeBadge.setActive(false);
            badgeRepo.save(activeBadge);
        }
        badge.setMember(member.get());
        badgeRepo.save(badge);
    }

    @Override
    public void swipe(long id, BadgeSwipeRequest badgeSwipeRequest) {

        if (badgeSwipeRequest.getLocationId() == null || badgeSwipeRequest.getPlanId() ==
                null || badgeSwipeRequest.getCheckerId() == null) {
            throw new ResourceNotFoundException("Location OR Plan OR Checker Not Provided");
        }

        Optional<Badge> badgeDb = badgeRepo.findById(id);

        if (badgeDb.isEmpty()) {
            throw new ResourceNotFoundException("Badge Not Found");
        }

        Optional<Plan> planDb = planRepo.findById(badgeSwipeRequest.getPlanId());

        if (planDb.isEmpty()) {
            throw new ResourceNotFoundException("Plan Not Found");
        }

        Optional<Location> locationDb = locationRepo.findById(badgeSwipeRequest.getLocationId());

        if (locationDb.isEmpty()) {
            throw new ResourceNotFoundException("Location Not Found");
        }

        Optional<Member> memberDb = memberRepo.findById(badgeSwipeRequest.getCheckerId());

        if (memberDb.isEmpty()) {
            throw new ResourceNotFoundException("Checker Not Found");
        }

        //check if the location is open
        if (!locationService.isOpen(badgeSwipeRequest.getLocationId())) {
            throw new ActionNotAllowedException("The given location is closed currently");

        }


        //check if member has membership on given plan; not checker membership
        List<Membership> memberships = memberShipService.findActiveMembershipForGivenPlanAndMember(badgeSwipeRequest.getPlanId(), badgeDb.get().getMember().getId());
        Optional<Membership> membership = memberships.stream().filter(m -> m.getMemberShipType().getType() != MemberShipTypeEnum.CHECKER).findFirst();
        if (membership.isEmpty()) {
            throw new ActionNotAllowedException("Member have no membership to this plan");
        }

        // check if the checker is valid person
        if (!planService.checkIfMemberIsCheckerInGivenPlan(badgeDb.get().getMember().getId(), badgeSwipeRequest.getPlanId())) {
            throw new ActionNotAllowedException("The given member cannot perform check operation");
        }

        //ALL GOOD TO PROCEED SWIPE OPERATION

        MemberShipTypeEnum type = membership.get().getMemberShipType().getType();
        TransactionStatus status = TransactionStatus.ALLOWED;

        //So we Allow the member to pass

        if (type == MemberShipTypeEnum.LIMITED) {
            List<PlanRule> rules = planDb.get().getRules();

            Role memberRole = memberDb.get().getUser().getRole();
            Optional<PlanRule> rule = rules.stream().filter(r -> r.getRole() == memberRole).findFirst();
            if (rule.isPresent()) {
                // find previous entry records of a given user for a given plan
                Intervals interval = rule.get().getIntervals();
                long noOfEntryAllowed = rule.get().getNumberOfEntry();
                long noOfEntryUsed = transactionService.findTransactionsOfMemberInGivenPlanWithInterval(id, badgeSwipeRequest.getPlanId(), interval).size();

                if (noOfEntryAllowed <= noOfEntryUsed) {
                    status = TransactionStatus.DECLINED;
                    publishTransactionEvent(badgeSwipeRequest, id, status);
                    throw new ActionNotAllowedException("This user has no balance left to use this plan");
                }
            }
        }
        // PUBLISH EVENT
        publishTransactionEvent(badgeSwipeRequest, id, status);


    }

    private void publishTransactionEvent(BadgeSwipeRequest badgeSwipeRequest, long id, TransactionStatus status) {
        publisher.publishEvent(BadgeSwipeEvent.builder().badgeId(id).planId(badgeSwipeRequest.getPlanId()).checkerId(badgeSwipeRequest.getCheckerId()).locationId(badgeSwipeRequest.getLocationId()).status(status).build());
    }


    @Override
    public void update(long id, BadgeRequest badgeRequest) {
        Optional<Badge> badgeDb = badgeRepo.findById(id);
        if (badgeDb.isEmpty()) {
            throw new ResourceNotFoundException("Badge Not Found");
        }

        Badge badge = modelMapper.map(badgeRequest, Badge.class);
        badge.setId(id);
        badgeRepo.save(badge);

    }

    @Override
    public void delete(long id) {
        Optional<Badge> badge = badgeRepo.findById(id);
        if (badge.isEmpty()) {
            throw new ResourceNotFoundException("Badge Not Found");
        }
        badgeRepo.deleteById(id);


    }
}
