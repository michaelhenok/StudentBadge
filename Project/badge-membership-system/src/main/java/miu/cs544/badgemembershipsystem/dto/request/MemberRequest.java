package miu.cs544.badgemembershipsystem.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import miu.cs544.badgemembershipsystem.domain.Role;

import java.time.LocalDate;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberRequest {

    private String firstName;
    private String lastName;
    private LocalDate DOB;
    private String email;
    private String phone;
    private String password;
    private Role role;
}
