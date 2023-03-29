package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.Member;
import miu.cs544.badgemembershipsystem.dto.request.MemberRequest;
import miu.cs544.badgemembershipsystem.dto.response.MemberResponse;
import miu.cs544.badgemembershipsystem.repository.MemberRepo;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MemberServiceImplTest {
    @MockBean
    private MemberRepo memberRepo;

    @MockBean
    private ModelMapper modelMapper;

    @Autowired
    private MemberServiceImpl memberServiceImpl;


    @Test
    void testFindAll() {
        // Arrange
        ArrayList<Member> memberList = new ArrayList<>();
        memberList.add(new Member());
        when(memberRepo.findAll()).thenReturn(memberList);
        when(modelMapper.map((Object) any(), (Class<MemberResponse>) any()))
                .thenThrow(new ResourceNotFoundException("An error happened here"));

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> memberServiceImpl.findAll());
        verify(memberRepo).findAll();
        verify(modelMapper).map((Object) any(), (Class<MemberResponse>) any());
    }


    @Test
    void testFindById() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> memberServiceImpl.findById(1L));
    }


    @Test
    void testSave() {
        // Arrange
        when(memberRepo.save((Member) any())).thenReturn(new Member());
        when(modelMapper.map((Object) any(), (Class<Member>) any())).thenReturn(new Member());

        // Act
        memberServiceImpl.save(new MemberRequest());

        // Assert
        verify(memberRepo).save((Member) any());
        verify(modelMapper).map((Object) any(), (Class<Member>) any());
    }


    @Test
    void testDelete() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> memberServiceImpl.delete(1L));
        assertThrows(ResourceNotFoundException.class, () -> memberServiceImpl.delete(2L));
        assertThrows(ResourceNotFoundException.class, () -> memberServiceImpl.delete(3L));

    }

    @Test
    void testUpdate() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> memberServiceImpl.update(1L, new MemberRequest()));
    }


}

