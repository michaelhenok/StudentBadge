package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.PlanRule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanRuleRepo extends JpaRepository<PlanRule,Long> {
}
