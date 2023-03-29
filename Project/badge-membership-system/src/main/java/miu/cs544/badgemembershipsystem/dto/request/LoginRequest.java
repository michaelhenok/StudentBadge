package miu.cs544.badgemembershipsystem.dto.request;

import lombok.Data;

@Data
public class LoginRequest {
    private String username;
    private String password;
}
