package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;
import miu.cs544.badgemembershipsystem.dto.request.PlanRequest;
import miu.cs544.badgemembershipsystem.dto.response.LocationResponse;
import miu.cs544.badgemembershipsystem.dto.response.PlanResponse;

import java.util.List;


public interface PlanService {

    PlanResponse findPlanById(Long id);

    List<PlanResponse> findAllPlan();

    List<LocationResponse> findAllLocations(Long planId);

    List<PlanResponse> findAllPlansByMemberID(Long id);

    List<PlanResponse> findAllPlansByMemberIDAndType(Long id, MemberShipTypeEnum type);
    Boolean checkIfMemberIsCheckerInGivenPlan(Long memberId, Long planId);

    void savePlan(PlanRequest planRequest);

    void deletePlan(Long id);

    void updatePlan(Long id, PlanRequest planRequest);

}
