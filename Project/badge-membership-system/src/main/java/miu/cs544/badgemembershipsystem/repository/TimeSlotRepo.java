package miu.cs544.badgemembershipsystem.repository;

import miu.cs544.badgemembershipsystem.domain.TimeSlot;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TimeSlotRepo extends JpaRepository<TimeSlot,Long> {
}
