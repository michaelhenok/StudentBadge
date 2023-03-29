package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.Badge;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BadgeRepo extends JpaRepository<Badge, Long> {

    @Query("select b from Badge b where b.member.id=:memberId")
    List<Badge> findBadgesByMemberID(@Param("memberId") long memberId);
}
