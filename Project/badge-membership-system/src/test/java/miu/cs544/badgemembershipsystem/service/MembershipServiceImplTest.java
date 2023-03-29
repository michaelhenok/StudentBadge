package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.dto.request.MembershipRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class MembershipServiceImplTest {
    @Autowired
    private MembershipServiceImpl membershipServiceImpl;


    @Test
    void testFindMembershipById() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> membershipServiceImpl.findMembershipById(1L));
    }


    @Test
    void testFindAllMembership() {
        // Arrange, Act and Assert
        assertTrue(membershipServiceImpl.findAllMembership().isEmpty());
    }


    @Test
    void testFindAllMembershipsByMemberID() {
        // Arrange, Act and Assert
        assertTrue(membershipServiceImpl.findAllMembershipsByMemberID(1L).isEmpty());
        assertTrue(membershipServiceImpl.findAllMembershipsByMemberID(2L).isEmpty());
    }




    @Test
    void testSaveMembership() {
        // Arrange
        MembershipRequest membershipRequest = new MembershipRequest();
        membershipRequest.setMemberId(1L);

        // Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> membershipServiceImpl.saveMembership(membershipRequest));
    }


    @Test
    void testDeleteMembership() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> membershipServiceImpl.deleteMembership(1L));
        assertThrows(ResourceNotFoundException.class, () -> membershipServiceImpl.deleteMembership(2L));
    }


    @Test
    void testUpdateMembership() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class,
                () -> membershipServiceImpl.updateMembership(1L, new MembershipRequest()));
    }
}

