package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.UserRequest;
import miu.cs544.badgemembershipsystem.dto.response.UserResponse;
import miu.cs544.badgemembershipsystem.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<UserResponse> getAll() {
        return userService.findAll();
    }

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(@RequestBody UserRequest userRequest) {
        userService.save(userRequest);
    }
}
