package miu.cs544.badgemembershipsystem.dto.request;

import lombok.Data;

@Data
public class UserRequest {
    private String username;
    private String password;
    private String role;
    private long memberId;
}
