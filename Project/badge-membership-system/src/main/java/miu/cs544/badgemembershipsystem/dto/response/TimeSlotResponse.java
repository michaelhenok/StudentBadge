package miu.cs544.badgemembershipsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.DayOfWeek;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TimeSlotResponse {
    private long id;
    private DayOfWeek day;
    private LocalTime startTime;
    private LocalTime endTime;
}
