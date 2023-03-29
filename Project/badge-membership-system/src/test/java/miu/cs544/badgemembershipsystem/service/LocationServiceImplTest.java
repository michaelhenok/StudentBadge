package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.LocationType;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;
import miu.cs544.badgemembershipsystem.dto.request.LocationRequest;
import miu.cs544.badgemembershipsystem.utils.enums.LocationTypeEnum;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class LocationServiceImplTest {
    @Autowired
    private LocationServiceImpl locationServiceImpl;


    @Test
    void testFindAll() {
        // Arrange, Act and Assert
        Assertions.assertTrue(locationServiceImpl.findAll().isEmpty());
    }


    @Test
    void testFindById() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> locationServiceImpl.findById(1L));
    }



    @Test
    void testSave() {
        // Arrange
        LocationType locationType = new LocationType(1L, LocationTypeEnum.DINING_HALL);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> locationServiceImpl.save(new LocationRequest("Plan Not Provided",
                        "The characteristics of someone ", (short) 1, locationType, 1L, new ArrayList<>())));
    }




    @Test
    void testSave2() {
        // Arrange
        ArrayList<TimeSlot> timeSlotList = new ArrayList<>();
        timeSlotList.add(new TimeSlot());

        // Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> locationServiceImpl
                        .save(new LocationRequest("Plan Not Provided", "The characteristics of something", (short) 1,
                                new LocationType(1L, LocationTypeEnum.DINING_HALL), 1L, timeSlotList)));
    }


    @Test
    void testUpdate() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> locationServiceImpl.update(1L, new LocationRequest()));
    }


    @Test
    void testDelete() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> locationServiceImpl.delete(1L));
        assertThrows(ResourceNotFoundException.class, () -> locationServiceImpl.delete(2L));
    }
}

