package miu.cs544.badgemembershipsystem.dto.response;

import lombok.Data;

@Data
public class UserResponse {
    private long id;
    private String username;
    private String role;
    private MemberResponse member;
}
