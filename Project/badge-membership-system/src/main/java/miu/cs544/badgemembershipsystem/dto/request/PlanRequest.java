package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanRequest {
    private String name;
    private String description;
    private List<PlanRuleRequest> rules;

}
