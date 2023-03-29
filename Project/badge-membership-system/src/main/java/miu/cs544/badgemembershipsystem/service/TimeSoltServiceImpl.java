package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;
import miu.cs544.badgemembershipsystem.dto.request.TimeSlotRequest;
import miu.cs544.badgemembershipsystem.dto.response.TimeSlotResponse;
import miu.cs544.badgemembershipsystem.repository.TimeSlotRepo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TimeSoltServiceImpl implements TimeSoltService {
    TimeSlotRepo timeSlotRepo;
    ModelMapper modelMapper;

    @Autowired
    public TimeSoltServiceImpl(TimeSlotRepo timeSlotRepo, ModelMapper modelMapper) {
        this.timeSlotRepo = timeSlotRepo;
        this.modelMapper = modelMapper;
    }

    @Override
    public List<TimeSlotResponse> findAll() {
        return timeSlotRepo.findAll().stream().map(t ->
                modelMapper.map(t, TimeSlotResponse.class)).toList();
    }

    @Override
    public TimeSlotResponse findById(long id) {
        Optional<TimeSlot> timeSolt = timeSlotRepo.findById(id);
        if (timeSolt.isEmpty()) {
            throw new ResourceNotFoundException("Time  not available!");
        }
        return modelMapper.map(timeSolt, TimeSlotResponse.class);
    }

    @Override
    public void save(TimeSlotRequest timeSlotRequest) {
        timeSlotRepo.save(modelMapper.map(timeSlotRequest, TimeSlot.class));
    }

    @Override
    public void update(long id, TimeSlotRequest timeSlotRequest) {
        Optional<TimeSlot> timeSolt = timeSlotRepo.findById(id);
        if (timeSolt.isEmpty()) {
            throw new ResourceNotFoundException("Time  not available!");
        }
        TimeSlot timeSlot = modelMapper.map(timeSlotRequest, TimeSlot.class);
        timeSlot.setId(id);
        timeSlotRepo.save(timeSlot);
    }

    @Override
    public void delete(long id) {
        Optional<TimeSlot> timeSolt = timeSlotRepo.findById(id);
        if (timeSolt.isEmpty()) {
            throw new ResourceNotFoundException("Time  not available!");
        }
        timeSlotRepo.deleteById(id);
    }
}
