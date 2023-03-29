package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.Intervals;
import miu.cs544.badgemembershipsystem.domain.Role;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRuleRequest {
    private int numberOfEntry;
    private Intervals intervals;
    private Role role;
}

