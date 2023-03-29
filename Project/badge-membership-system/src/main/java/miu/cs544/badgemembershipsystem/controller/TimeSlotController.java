package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.TimeSlotRequest;
import miu.cs544.badgemembershipsystem.dto.response.TimeSlotResponse;
import miu.cs544.badgemembershipsystem.service.TimeSlotService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/v1/timeslots")
@RequiredArgsConstructor
public class TimeSlotController {
    private final TimeSlotService timeSlotService;
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TimeSlotResponse> getAll() {

        return timeSlotService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TimeSlotResponse getOne(@PathVariable long id) {
        return timeSlotService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody TimeSlotRequest timeSoltRequest) {
        this.timeSlotService.save(timeSoltRequest);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody TimeSlotRequest timeSoltRequest) {
        this.timeSlotService.update(id, timeSoltRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {

        this.timeSlotService.delete(id);
    }

}
