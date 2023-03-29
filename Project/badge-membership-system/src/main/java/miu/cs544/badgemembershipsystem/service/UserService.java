package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.dto.request.MemberRequest;
import miu.cs544.badgemembershipsystem.dto.request.UserRequest;
import miu.cs544.badgemembershipsystem.dto.response.MemberResponse;
import miu.cs544.badgemembershipsystem.dto.response.UserResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {
    List<UserResponse> findAll();

    UserResponse findById(long id);

    void save(UserRequest userRequestDto);
}
