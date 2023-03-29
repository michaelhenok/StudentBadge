package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.dto.request.MemberRequest;
import miu.cs544.badgemembershipsystem.dto.response.MemberResponse;

import java.util.List;


public interface MemberService {
    List<MemberResponse> findAll();

    MemberResponse findById(long id);

    void save(MemberRequest memberRequestDto);

    void update(long id, MemberRequest memberRequestDto);

    void delete(long id);
}
