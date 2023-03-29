package miu.cs544.badgemembershipsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.MemberShipType;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MembershipRequest {
    private LocalDate startDate;//not null
    private LocalDate endDate;//not null
    private Long planId;//memberships has one plan
    private Long memberId;
    private MemberShipType memberShipType;// 1-> unlimited.  2->limited 3->Checker.


}
