package miu.cs544.badgemembershipsystem.service;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ActionNotAllowedException;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Member;
import miu.cs544.badgemembershipsystem.domain.Membership;
import miu.cs544.badgemembershipsystem.domain.Plan;
import miu.cs544.badgemembershipsystem.dto.request.MembershipRequest;
import miu.cs544.badgemembershipsystem.dto.response.MembershipResponse;
import miu.cs544.badgemembershipsystem.repository.MemberRepo;
import miu.cs544.badgemembershipsystem.repository.MemberShipRepo;
import miu.cs544.badgemembershipsystem.repository.PlanRepo;
import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MembershipServiceImpl implements MemberShipService {
    private final MemberShipRepo memberShipRepo;
    private final PlanRepo planRepo;
    private final MemberRepo memberRepo;
    private final ModelMapper modelMapper;

    public MembershipResponse findMembershipById(Long id) {
        Optional<Membership> membershipDb = memberShipRepo.findById(id);
        if (membershipDb.isEmpty()) {
            throw new ResourceNotFoundException("MemberShip Not Found");
        }
        MembershipResponse membershipResponse = modelMapper.map(membershipDb.get(), MembershipResponse.class);
        return membershipResponse;
    }

    @Override
    public List<MembershipResponse> findAllMembership() {
        List<Membership> MembershipRepos = memberShipRepo.findAll();
        List<MembershipResponse> response = new ArrayList<>();
        for (Membership membership : MembershipRepos) {
            MembershipResponse membershipResponse = modelMapper.map(membership, MembershipResponse.class);
            response.add(membershipResponse);
        }
        return response;
    }

    @Override
    public List<MembershipResponse> findAllMembershipsByMemberID(Long id) {
        return memberShipRepo.findAllMembershipsByMemberID(id).stream()
                .filter(m -> m.getEndDate().isAfter(LocalDate.now())).
                map(m -> modelMapper.map(m, MembershipResponse.class)).toList();
    }

    @Override
    public List<MembershipResponse> findAllMembershipsByMemberIDAndType(Long id, MemberShipTypeEnum type) {
        return memberShipRepo.findAllMembershipsByMemberIDAndType(id, type).stream()
                .filter(m -> m.getEndDate().isAfter(LocalDate.now()))
                .map(m -> modelMapper.map(m, MembershipResponse.class)).toList();
    }


    @Override
    public void saveMembership(MembershipRequest membershipRequest) {

        if (membershipRequest.getMemberId() == null) {
            throw new ResourceNotFoundException("Member Not Provided");
        }

        if (membershipRequest.getPlanId() == null) {
            throw new ResourceNotFoundException("Plan Not Provided");
        }

        //Check in Database
        Optional<Plan> plan = planRepo.findById(membershipRequest.getPlanId());
        if (plan.isEmpty()) {
            throw new ResourceNotFoundException("Plan Not Found");
        }

        Optional<Member> member = memberRepo.findById(membershipRequest.getMemberId());
        if (member.isEmpty()) {
            throw new ResourceNotFoundException("Member Not Found");
        }

        List<Membership> enrolledActiveMemberships = findActiveEnrolledPlan(membershipRequest.getMemberId(), membershipRequest.getPlanId());
        //check if membership already exist of same kind
        if (enrolledActiveMemberships.stream().anyMatch(m -> m.getMemberShipType().getType() == membershipRequest.getMemberShipType().getType())) {
            throw new ActionNotAllowedException("Member already has a active membership of this type in this plan");
        }

        Membership membership = modelMapper.map(membershipRequest, Membership.class);

        membership.setPlan(plan.get());
        membership.setMember(member.get());
        memberShipRepo.save(membership);

    }


    public List<Membership> findActiveEnrolledPlan(long memberId, long planId) {
        List<Membership> mem = memberShipRepo.findMembershipByPlanIdAndMemberId(planId, memberId);
        List<Membership> memF = mem.stream()
                .filter(m -> m.getEndDate().isAfter(LocalDate.now())).toList();
        System.out.println("ok");
        return memF;
    }

    @Override
    public List<Membership> findActiveMembershipForGivenPlanAndMember(long planId, long memberId) {
        return memberShipRepo.findMembershipByPlanIdAndMemberId(planId, memberId).stream().filter(m -> m.getEndDate().isAfter(LocalDate.now())).toList();

    }


    @Override
    public void deleteMembership(Long id) {
        Optional<Membership> memberShip = memberShipRepo.findById(id);
        if (memberShip.isEmpty()) {
            throw new ResourceNotFoundException("MemberShip Not Found");
        }
        memberShipRepo.deleteById(id);

    }

    @Override
    public void updateMembership(Long id, MembershipRequest membershipRequest) {

        Optional<Membership> membershipDb = memberShipRepo.findById(id);
        if (membershipDb.isEmpty()) {
            throw new ResourceNotFoundException("MemberShip Not Found");
        }

        Membership memberShip = modelMapper.map(membershipRequest, Membership.class);
        memberShip.setId(id);
        memberShipRepo.save(memberShip);
    }
}
