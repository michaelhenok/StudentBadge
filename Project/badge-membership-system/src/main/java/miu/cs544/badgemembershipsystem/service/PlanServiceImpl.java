package miu.cs544.badgemembershipsystem.service;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Membership;
import miu.cs544.badgemembershipsystem.domain.Plan;
import miu.cs544.badgemembershipsystem.dto.request.PlanRequest;
import miu.cs544.badgemembershipsystem.dto.response.LocationResponse;
import miu.cs544.badgemembershipsystem.dto.response.PlanResponse;
import miu.cs544.badgemembershipsystem.repository.LocationRepo;
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
public class PlanServiceImpl implements PlanService {

    private final PlanRepo planRepo;
    private final LocationRepo locationRepo;
    private final MemberShipRepo memberShipRepo;
    private final ModelMapper modelMapper;

    public PlanResponse findPlanById(Long id) {
        Optional<Plan> planDb = planRepo.findById(id);
        if (planDb.isEmpty()) {
            throw new ResourceNotFoundException("Plan Not Found");
        }
        return modelMapper.map(planDb.get(), PlanResponse.class);
    }

    @Override
    public List<PlanResponse> findAllPlan() {
        List<Plan> plans = planRepo.findAll();
        List<PlanResponse> planResponse = new ArrayList<>();
        for (Plan plan : plans) {
            PlanResponse planResponseDto = modelMapper.map(plan, PlanResponse.class);
            planResponse.add(planResponseDto);
        }
        return planResponse;
    }

    @Override
    public List<LocationResponse> findAllLocations(Long planId) {
        return locationRepo.findAllByPlanId(planId).stream().map(l -> modelMapper.map(l, LocationResponse.class)).toList();
    }

    @Override
    public List<PlanResponse> findAllPlansByMemberID(Long id) {
        return memberShipRepo.findAllMembershipsByMemberID(id).stream()
                .filter(m -> m.getEndDate().isAfter(LocalDate.now()))
                .map(m -> modelMapper.map(m.getPlan(), PlanResponse.class)).toList();
    }

    @Override
    public List<PlanResponse> findAllPlansByMemberIDAndType(Long id, MemberShipTypeEnum type) {
        return memberShipRepo.findAllMembershipsByMemberIDAndType(id, type).stream()
                .filter(m -> m.getEndDate().isAfter(LocalDate.now()))
                .map(m -> modelMapper.map(m.getPlan(), PlanResponse.class)).toList();
    }

    @Override
    public Boolean checkIfMemberIsCheckerInGivenPlan(Long memberId, Long planId) {
        List<Membership> memberships = memberShipRepo.findAllMembershipsByMemberIDAndType(memberId, MemberShipTypeEnum.CHECKER);
        return memberships.stream().anyMatch(m -> m.getPlan().getId() == planId);
    }


    @Override
    public void savePlan(PlanRequest planRequestDto) {
        Plan plan = modelMapper.map(planRequestDto, Plan.class);
        planRepo.save(plan);
    }

    @Override
    public void deletePlan(Long id) {
        Optional<Plan> planDb = planRepo.findById(id);
        if (planDb.isEmpty()) {
            throw new ResourceNotFoundException("Plan Not Found");
        }
        planRepo.deleteById(id);

    }

    @Override
    public void updatePlan(Long id, PlanRequest planRequestDto) {
        Optional<Plan> planDb = planRepo.findById(id);
        if (planDb.isEmpty()) {
            throw new ResourceNotFoundException("Plan Not Found");
        }
        Plan plan = modelMapper.map(planRequestDto, Plan.class);
        plan.setId(id);
        planRepo.save(plan);
    }

}
