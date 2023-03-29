package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeRequest {
    private String badgeID;
    private String description;

    private Long memberID;
}
