package miu.cs544.badgemembershipsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.Role;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlanResponse {
    private long id;
    private String name;
    private String description;
    private List<PlanRuleResponse> rules;

}
