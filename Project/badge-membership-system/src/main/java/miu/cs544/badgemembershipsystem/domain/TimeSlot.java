package miu.cs544.badgemembershipsystem.domain;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.Day;

import java.time.DayOfWeek;
import java.time.LocalTime;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class TimeSlot {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Enumerated(EnumType.STRING)
    private DayOfWeek day;
    @NotNull
    private LocalTime startTime;
    @NotNull
    private LocalTime endTime;
}