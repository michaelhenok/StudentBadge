package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.dto.request.LocationRequest;
import miu.cs544.badgemembershipsystem.dto.response.LocationResponse;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface LocationService {

    List<LocationResponse> findAll();

    Boolean isOpen(long locationId);

    LocationResponse findById(long id);

    void save(LocationRequest LocationRequest);

    void update(long id, LocationRequest LocationRequest);

    void delete(long id);
}
