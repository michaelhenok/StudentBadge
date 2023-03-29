package miu.cs544.badgemembershipsystem.dto.response;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.MemberShipType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipResponse {
    private long id;//Auto Generated
    private LocalDate startDate;//not null
    private LocalDate endDate;//not null
    private MemberShipType memberShipType;// 1-> unlimited.  2->limited 3->Checker.
    private MemberResponse member;
    private PlanResponse plan;//memberships has one plan

}
