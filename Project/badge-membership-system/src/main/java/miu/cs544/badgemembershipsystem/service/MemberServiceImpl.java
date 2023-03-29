package miu.cs544.badgemembershipsystem.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Member;
import miu.cs544.badgemembershipsystem.dto.request.MemberRequest;
import miu.cs544.badgemembershipsystem.dto.request.UserRequest;
import miu.cs544.badgemembershipsystem.dto.response.MemberResponse;
import miu.cs544.badgemembershipsystem.repository.MemberRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberServiceImpl implements MemberService {

    private final MemberRepo memberRepo;
    private final ModelMapper modelMapper;
    private final UserService userService;
    @Override
    public List<MemberResponse> findAll() {
        List<Member> members = memberRepo.findAll();
        List<MemberResponse> result = members.stream().map(m -> modelMapper.map(m, MemberResponse.class)).toList();
        return result;
    }

    @Override
    public MemberResponse findById(long id) {
        Optional<Member> member = memberRepo.findById(id);

        if (member.isEmpty()) {
            throw new ResourceNotFoundException("Member Not Found");
        }
        MemberResponse memberResponse = modelMapper.map(member.get(), MemberResponse.class);

        return memberResponse;

    }

    @Override
    public void save(MemberRequest memberRequestDto) {
        Member member = memberRepo.save(modelMapper.map(memberRequestDto, Member.class));
        UserRequest userRequest = new UserRequest();
        userRequest.setUsername(member.getEmail());
        userRequest.setMemberId(member.getId());
        userRequest.setRole(memberRequestDto.getRole().name());
        userRequest.setPassword(memberRequestDto.getPassword());
        userService.save(userRequest);
    }

    @Override
    public void update(long id, MemberRequest memberRequestDto) {

        Optional<Member> memberDb = memberRepo.findById(id);
        if (memberDb.isEmpty()) {
            throw new ResourceNotFoundException("Member Not Found");
        }

        Member member = modelMapper.map(memberRequestDto, Member.class);
        member.setId(id);
        memberRepo.save(member);

    }

    @Override
    public void delete(long id) {
        Optional<Member> member = memberRepo.findById(id);
        if (member.isEmpty()) {
            throw new ResourceNotFoundException("Member Not Found");
        }
        memberRepo.deleteById(id);


    }
}
