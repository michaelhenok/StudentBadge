package miu.cs544.badgemembershipsystem.controller;


import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.MembershipRequest;
import miu.cs544.badgemembershipsystem.dto.response.MembershipResponse;
import miu.cs544.badgemembershipsystem.service.MemberShipService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/memberships")
@RequiredArgsConstructor
public class MembershipController {
    private final MemberShipService membershipService;


    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public MembershipResponse getOne(@PathVariable("id") Long id) {
        return membershipService.findMembershipById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MembershipResponse> getAll() {
        return membershipService.findAllMembership();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody MembershipRequest membershipRequest) {
        membershipService.saveMembership(membershipRequest);

    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody MembershipRequest membershipRequest) {
        membershipService.updateMembership(id, membershipRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        membershipService.deleteMembership(id);
    }

}
