package miu.cs544.badgemembershipsystem.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Data

public class MemberResponse {
    private long id;
    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private String email;
    private String phone;
    private BadgeResponse activeBadge;

}


