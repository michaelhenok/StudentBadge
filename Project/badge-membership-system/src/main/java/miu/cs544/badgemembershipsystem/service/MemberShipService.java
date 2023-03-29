package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.domain.Membership;
import miu.cs544.badgemembershipsystem.dto.request.MembershipRequest;
import miu.cs544.badgemembershipsystem.dto.response.MembershipResponse;
import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;

import java.util.List;


public interface MemberShipService {

    MembershipResponse findMembershipById(Long id);

    List<MembershipResponse> findAllMembership();

    List<MembershipResponse> findAllMembershipsByMemberID(Long id);

    List<Membership> findActiveEnrolledPlan(long memberId, long planId);

    List<Membership> findActiveMembershipForGivenPlanAndMember(long planId, long memberId);

    List<MembershipResponse> findAllMembershipsByMemberIDAndType(Long id, MemberShipTypeEnum type);

    void saveMembership(MembershipRequest membership);

    void deleteMembership(Long id);

    void updateMembership(Long id, MembershipRequest membership);

}
