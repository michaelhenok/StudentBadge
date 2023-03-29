package miu.cs544.badgemembershipsystem.service;


import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Location;
import miu.cs544.badgemembershipsystem.domain.Plan;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;
import miu.cs544.badgemembershipsystem.dto.request.LocationRequest;
import miu.cs544.badgemembershipsystem.dto.response.LocationResponse;
import miu.cs544.badgemembershipsystem.repository.LocationRepo;
import miu.cs544.badgemembershipsystem.repository.PlanRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class LocationServiceImpl implements LocationService {
    private final LocationRepo locationRepo;
    private final PlanRepo planRepo;
    private final ModelMapper modelMapper;

    @Override
    public List<LocationResponse> findAll() {
        return locationRepo.findAll().stream().map(l ->
                modelMapper.map(l, LocationResponse.class)).toList();
    }

    @Override
    public Boolean isOpen(long locationId) {
        Location location = locationRepo.findById(locationId).get();
        List<TimeSlot> slots = location.getSlots();
        DayOfWeek today = LocalDate.now().getDayOfWeek();
        Optional<TimeSlot> dayRule = slots.stream().filter(s -> s.getDay() == today).findFirst();

        if (dayRule.isPresent() && LocalTime.now().isAfter(dayRule.get().getStartTime()) && LocalTime.now().isBefore(dayRule.get().getEndTime())) {
            return true;
        }

        return false;

    }

    @Override
    public LocationResponse findById(long id) {
        Optional<Location> location = locationRepo.findById(id);
        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location doesn't exist!");
        }
        return modelMapper.map(location, LocationResponse.class);
    }

    @Override
    public void save(LocationRequest locationRequest) {

        if (locationRequest.getPlanId() == null) {
            throw new ResourceNotFoundException("Plan Not Provided");
        }
        Location location = modelMapper.map(locationRequest, Location.class);
        Optional<Plan> plan = planRepo.findById(locationRequest.getPlanId());
        if (plan.isEmpty()) {
            throw new ResourceNotFoundException("Plan Not Found");
        }

        location.setPlan(plan.get());
        locationRepo.save(location);

    }

    @Override
    public void update(long id, LocationRequest locationRequest) {

        Optional<Location> Location = locationRepo.findById(id);
        if (Location.isEmpty()) {
            throw new ResourceNotFoundException("Location doesn't exist!");
        }
        Location location = modelMapper.map(locationRequest, Location.class);
        location.setId(id);
        locationRepo.save(location);
    }

    @Override
    public void delete(long id) {
        Optional<Location> location = locationRepo.findById(id);
        if (location.isEmpty()) {
            throw new ResourceNotFoundException("Location doesn't exist!");
        }
        locationRepo.deleteById(id);
    }
}
