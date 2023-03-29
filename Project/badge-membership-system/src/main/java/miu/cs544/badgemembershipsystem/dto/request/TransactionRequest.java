package miu.cs544.badgemembershipsystem.dto.request;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.TransactionStatus;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionRequest {

    private String description;
    private long badgeId;
    private long locationId;
    private long planId;
    private long checkerId;

    private TransactionStatus status;

}
