package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.LocationType;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class LocationRequest {
    private String name;
    private String description;
    private short capacity;
    private LocationType locationType;
    private Long planId;
    private List<TimeSlot> slots;

}
