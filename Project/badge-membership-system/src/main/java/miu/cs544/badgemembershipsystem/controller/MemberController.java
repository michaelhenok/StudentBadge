package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.utils.enums.MemberShipTypeEnum;
import miu.cs544.badgemembershipsystem.dto.request.MemberRequest;
import miu.cs544.badgemembershipsystem.dto.response.BadgeResponse;
import miu.cs544.badgemembershipsystem.dto.response.MemberResponse;
import miu.cs544.badgemembershipsystem.dto.response.MembershipResponse;
import miu.cs544.badgemembershipsystem.dto.response.PlanResponse;
import miu.cs544.badgemembershipsystem.service.BadgeService;
import miu.cs544.badgemembershipsystem.service.MemberService;
import miu.cs544.badgemembershipsystem.service.MemberShipService;
import miu.cs544.badgemembershipsystem.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/members")
@RequiredArgsConstructor
public class MemberController {
    private final MemberService memberService;
    private final PlanService planService;
    private final MemberShipService memberShipService;
    private final BadgeService badgeService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<MemberResponse> getAll() {
        return memberService.findAll();
    }

    @GetMapping("/{id}/memberships")
    @ResponseStatus(HttpStatus.OK)
    public List<MembershipResponse> getAllMemberShip(@PathVariable long id, @RequestParam(value = "type", required = false) MemberShipTypeEnum type) {
        if (type != null) {
            return memberShipService.findAllMembershipsByMemberIDAndType(id, type);
        }
        return memberShipService.findAllMembershipsByMemberID(id);
    }

    @GetMapping("/{id}/plans")
    @ResponseStatus(HttpStatus.OK)
    public List<PlanResponse> getAllPlans(@PathVariable long id, @RequestParam(value = "type", required = false) MemberShipTypeEnum type) {
        if (type != null) {
            return planService.findAllPlansByMemberIDAndType(id, type);
        }
        return planService.findAllPlansByMemberID(id);
    }

    @GetMapping("/{id}/badges")
    @ResponseStatus(HttpStatus.OK)
    public List<BadgeResponse> getAllBadges(@PathVariable long id) {
        return badgeService.findBadgesByMemberID(id);
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public MemberResponse getOne(@PathVariable long id) {
        return memberService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody MemberRequest memberRequestDto) {
        memberService.save(memberRequestDto);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody MemberRequest memberRequestDto) {
        memberService.update(id, memberRequestDto);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        memberService.delete(id);
    }


}
