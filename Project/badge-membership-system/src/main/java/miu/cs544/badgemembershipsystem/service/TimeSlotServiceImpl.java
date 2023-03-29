package miu.cs544.badgemembershipsystem.service;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;
import miu.cs544.badgemembershipsystem.dto.request.TimeSlotRequest;
import miu.cs544.badgemembershipsystem.dto.response.TimeSlotResponse;
import miu.cs544.badgemembershipsystem.repository.TimeSlotRepo;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TimeSlotServiceImpl implements TimeSlotService {
    private final TimeSlotRepo timeSlotRepo;
    private final ModelMapper modelMapper;


    @Override
    public List<TimeSlotResponse> findAll() {
        return timeSlotRepo.findAll().stream().map(t ->
                modelMapper.map(t, TimeSlotResponse.class)).toList();
    }

    @Override
    public TimeSlotResponse findById(long id) {
        Optional<TimeSlot> timeSlot = timeSlotRepo.findById(id);
        if (timeSlot.isEmpty()) {
            throw new ResourceNotFoundException("Time  not available!");
        }
        return modelMapper.map(timeSlot, TimeSlotResponse.class);
    }

    @Override
    public void save(TimeSlotRequest timeSlotRequest) {
        timeSlotRepo.save(modelMapper.map(timeSlotRequest, TimeSlot.class));
    }

    @Override
    public void update(long id, TimeSlotRequest timeSlotRequest) {
        Optional<TimeSlot> timeSlotDB = timeSlotRepo.findById(id);
        if (timeSlotDB.isEmpty()) {
            throw new ResourceNotFoundException("Time not available!");
        }
        TimeSlot timeSlot = modelMapper.map(timeSlotRequest, TimeSlot.class);
        timeSlot.setId(id);
        timeSlotRepo.save(timeSlot);
    }

    @Override
    public void delete(long id) {
        Optional<TimeSlot> timeSlot = timeSlotRepo.findById(id);
        if (timeSlot.isEmpty()) {
            throw new ResourceNotFoundException("Time not available!");
        }
        timeSlotRepo.deleteById(id);
    }
}
