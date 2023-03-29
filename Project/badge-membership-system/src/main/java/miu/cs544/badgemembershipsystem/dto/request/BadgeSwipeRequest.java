package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class BadgeSwipeRequest {
    private Long locationId;
    private Long planId;
    private Long checkerId;

}
