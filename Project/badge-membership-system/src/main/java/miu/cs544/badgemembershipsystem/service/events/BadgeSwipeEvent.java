package miu.cs544.badgemembershipsystem.service.events;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.TransactionStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BadgeSwipeEvent {

    private long badgeId;
    private long locationId;
    private long planId;
    private TransactionStatus status;
    private long checkerId;

}