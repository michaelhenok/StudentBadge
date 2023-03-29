package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotRequest {
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
}
