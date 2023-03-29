package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Plan;
import miu.cs544.badgemembershipsystem.dto.request.PlanRequest;
import miu.cs544.badgemembershipsystem.repository.PlanRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class PlanServiceImplTest {
    @MockBean
    private ModelMapper modelMapper;

    @MockBean
    private PlanRepo planRepo;

    @Autowired
    private PlanServiceImpl planServiceImpl;


    @Test
    void testFindPlanById() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> planServiceImpl.findPlanById(1L));
    }


    @Test
    void testFindAllPlan() {
        // Arrange, Act and Assert
        assertTrue(planServiceImpl.findAllPlan().isEmpty());
    }


    @Test
    void testSavePlan() {
        // Arrange
        when(planRepo.save((Plan) any())).thenReturn(new Plan());
        when(modelMapper.map((Object) any(), (Class<Plan>) any())).thenReturn(new Plan());

        // Act
        planServiceImpl.savePlan(new PlanRequest());

        // Assert
        verify(planRepo).save((Plan) any());
        verify(modelMapper).map((Object) any(), (Class<Plan>) any());
    }



    @Test
    void testDeletePlan() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> planServiceImpl.deletePlan(1L));
        assertThrows(ResourceNotFoundException.class, () -> planServiceImpl.deletePlan(2L));
    }



}

