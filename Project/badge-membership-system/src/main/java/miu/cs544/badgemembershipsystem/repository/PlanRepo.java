package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.Plan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanRepo extends JpaRepository<Plan, Long> {


}
