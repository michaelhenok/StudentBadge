package miu.cs544.badgemembershipsystem.controller;


import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.PlanRequest;
import miu.cs544.badgemembershipsystem.dto.response.LocationResponse;
import miu.cs544.badgemembershipsystem.dto.response.PlanResponse;
import miu.cs544.badgemembershipsystem.service.PlanService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/plans")

public class PlanController {

    private final PlanService planService;

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PlanResponse getOne(@PathVariable("id") Long id) {
        return planService.findPlanById(id);
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PlanResponse> getAll() {
        return planService.findAllPlan();
    }

    @GetMapping("{id}/locations")
    @ResponseStatus(HttpStatus.OK)
    public List<LocationResponse> getAllLocations(@PathVariable long id) {
        return planService.findAllLocations(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody PlanRequest planRequestDto) {
        planService.savePlan(planRequestDto);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable("id") Long id, @RequestBody PlanRequest planRequest) {
        planService.updatePlan(id, planRequest);

    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable Long id) {
        planService.deletePlan(id);

    }

}
