package miu.cs544.badgemembershipsystem.service.security;

import miu.cs544.badgemembershipsystem.dto.request.LoginRequest;
import miu.cs544.badgemembershipsystem.dto.response.LoginResponse;

public interface AuthenticationService {
    LoginResponse login(LoginRequest loginRequest);
}
