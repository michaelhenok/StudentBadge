package miu.cs544.badgemembershipsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class BadgeResponse {
    private long id;
    private String badgeID;
    private String description;

}
