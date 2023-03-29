package miu.cs544.badgemembershipsystem.service;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Member;
import miu.cs544.badgemembershipsystem.domain.User;
import miu.cs544.badgemembershipsystem.dto.request.UserRequest;
import miu.cs544.badgemembershipsystem.dto.response.UserResponse;
import miu.cs544.badgemembershipsystem.repository.MemberRepo;
import miu.cs544.badgemembershipsystem.repository.UserRepo;
import org.modelmapper.ModelMapper;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepo userRepo;
    private final MemberRepo memberRepo;
    private final ModelMapper modelMapper;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public List<UserResponse> findAll() {
        List<User> users = userRepo.findAll();
        List<UserResponse> result = users.stream().map(m -> modelMapper.map(m, UserResponse.class)).toList();
        return result;
    }

    @Override
    public UserResponse findById(long id) {
        Optional<User> user = userRepo.findById(id);

        if (user.isEmpty()) {
            throw new ResourceNotFoundException("User Not Found");
        }
        UserResponse userResponse = modelMapper.map(user.get(), UserResponse.class);

        return userResponse;
    }

    @Override
    public void save(UserRequest userRequestDto) {
        String encryptedPassword = bCryptPasswordEncoder.encode(userRequestDto.getPassword());
        userRequestDto.setPassword(encryptedPassword);
        User user = modelMapper.map(userRequestDto, User.class);
        Optional<Member> member;
        if (userRequestDto.getMemberId() != 0) {
            member = memberRepo.findById(userRequestDto.getMemberId());
            if (member.isEmpty()) {
                throw new ResourceNotFoundException("Given Member is not in database");
            }
            user.setMember(member.get());
        }
        userRepo.save(user);

    }
}
