package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.dto.request.TimeSlotRequest;
import miu.cs544.badgemembershipsystem.dto.response.TimeSlotResponse;
import java.util.List;

public interface TimeSlotService {
    List<TimeSlotResponse> findAll();

    TimeSlotResponse findById(long id);

    void save(TimeSlotRequest timeSoltRequest);

    void update(long id, TimeSlotRequest timeSoltRequest);

    void delete(long id);
}
