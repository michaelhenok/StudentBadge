package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.dto.request.TimeSlotRequest;
import miu.cs544.badgemembershipsystem.dto.response.TimeSlotResponse;

import java.util.List;

public interface TimeSoltService {
    List<TimeSlotResponse> findAll();

    TimeSlotResponse findById(long id);

    void save(TimeSlotRequest timeSlotRequest);

    void update(long id, TimeSlotRequest timeSlotRequest);

    void delete(long id);
}
