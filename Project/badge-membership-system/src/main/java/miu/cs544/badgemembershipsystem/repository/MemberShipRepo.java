package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.Membership;
import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemberShipRepo extends JpaRepository<Membership, Long> {
    @Query("select m from Membership m where m.member.id=:memberId")
    List<Membership> findAllMembershipsByMemberID(@Param("memberId") long memberId);

    @Query("select m from Membership m where m.member.id=:memberId and m.plan.id=:planId")
    List<Membership> findMembershipByPlanIdAndMemberId(@Param("planId") long planId, @Param("memberId") long memberId);

    @Query("select m from Membership m where m.member.id=:memberId and m.memberShipType.type=:type")
    List<Membership> findAllMembershipsByMemberIDAndType(@Param("memberId") long memberId, @Param("type") MemberShipTypeEnum type);
}
