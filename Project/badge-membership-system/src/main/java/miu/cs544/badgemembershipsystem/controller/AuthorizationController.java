package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.LoginRequest;
import miu.cs544.badgemembershipsystem.dto.response.LoginResponse;
import miu.cs544.badgemembershipsystem.service.security.AuthenticationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthorizationController {
    private final AuthenticationService authenticationService;
    @PostMapping("/login")
    @ResponseStatus(HttpStatus.CREATED)
    public LoginResponse login(@RequestBody LoginRequest loginRequest) {
        return authenticationService.login(loginRequest);
    }
}
