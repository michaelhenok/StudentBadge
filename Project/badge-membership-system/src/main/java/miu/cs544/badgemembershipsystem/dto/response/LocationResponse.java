package miu.cs544.badgemembershipsystem.dto.response;



import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.LocationType;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationResponse {
    private long id;
    private String name;
    private String description;
    private short capacity;
    private LocationType locationType;
    private PlanResponse plan;
    private List<TimeSlot> slots;
}
