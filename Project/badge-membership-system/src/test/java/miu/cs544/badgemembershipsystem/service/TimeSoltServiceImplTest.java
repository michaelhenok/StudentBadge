package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Day;
import miu.cs544.badgemembershipsystem.domain.TimeSlot;
import miu.cs544.badgemembershipsystem.dto.request.TimeSlotRequest;
import miu.cs544.badgemembershipsystem.dto.response.TimeSlotResponse;
import miu.cs544.badgemembershipsystem.repository.TimeSlotRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TimeSoltServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private TimeSlotRepo timeSlotRepo;

    @Autowired
    private TimeSoltServiceImpl timeSoltServiceImpl;


    @Test
    void testFindAll() {
        // Arrange and Act
        List<TimeSlotResponse> actualFindAllResult = timeSoltServiceImpl.findAll();

        // Assert
        assertEquals(58, actualFindAllResult.size());
        TimeSlotResponse getResult = actualFindAllResult.get(52);
        assertEquals(63L, getResult.getId());
        assertEquals(Day.SATURDAY, getResult.getDay());
        TimeSlotResponse getResult1 = actualFindAllResult.get(5);
        assertEquals(16L, getResult1.getId());
        assertEquals(Day.SUNDAY, getResult1.getDay());
        TimeSlotResponse getResult2 = actualFindAllResult.get(2);
        assertEquals(13L, getResult2.getId());
        assertEquals(Day.SUNDAY, getResult2.getDay());


    }


    @Test
    void testFindById() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> timeSoltServiceImpl.findById(1L));
    }


    @Test
    void testSave() {
        // Arrange
        when(timeSlotRepo.save((TimeSlot) any())).thenReturn(new TimeSlot());
        when(modelMapper.map((Object) any(), (Class<TimeSlot>) any())).thenReturn(new TimeSlot());

        // Act
        timeSoltServiceImpl.save(new TimeSlotRequest());

        // Assert
        verify(timeSlotRepo).save((TimeSlot) any());
        verify(modelMapper).map((Object) any(), (Class<TimeSlot>) any());
    }



    @Test
    void testDelete() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> timeSoltServiceImpl.delete(1L));
        assertThrows(ResourceNotFoundException.class, () -> timeSoltServiceImpl.delete(2L));
        assertThrows(ResourceNotFoundException.class, () -> timeSoltServiceImpl.delete(123L));
    }
}

