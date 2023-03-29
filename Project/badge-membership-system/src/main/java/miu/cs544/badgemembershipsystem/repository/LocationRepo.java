package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.Location;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LocationRepo extends JpaRepository<Location, Long> {

    List<Location> findAllByPlanId(long id);
}
