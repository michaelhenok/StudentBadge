package miu.cs544.badgemembershipsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.TransactionStatus;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionResponse {

    private long id;
    private LocalDateTime entryDate;
    private String description;
    private BadgeResponse badge;
    private MemberResponse checker;
    private LocationResponse location;
    private TransactionStatus status;
    private PlanResponse plan;
}
