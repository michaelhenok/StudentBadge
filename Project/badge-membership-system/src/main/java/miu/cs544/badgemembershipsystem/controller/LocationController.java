package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.LocationRequest;
import miu.cs544.badgemembershipsystem.dto.response.LocationResponse;
import miu.cs544.badgemembershipsystem.service.LocationService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/locations")
@RequiredArgsConstructor
public class LocationController {
    private final LocationService locationService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<LocationResponse> getAll() {
        return locationService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public LocationResponse getOne(@PathVariable long id) {
        return locationService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody LocationRequest locationRequest) {
        locationService.save(locationRequest);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody LocationRequest locationRequest) {
        locationService.update(id, locationRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        locationService.delete(id);
    }

}
