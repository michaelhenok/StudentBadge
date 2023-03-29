package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.dto.request.TransactionRequest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@ExtendWith(SpringExtension.class)
class TransactionServiceImplTest {
    @Autowired
    private TransactionServiceImpl transactionServiceImpl;


    @Test
    void testFindAll() {
        // Arrange, Act and Assert
        assertTrue(transactionServiceImpl.findAll().isEmpty());
    }


    @Test
    void testFindById() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> transactionServiceImpl.findById(1L));
    }


    @Test
    void testSave() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> transactionServiceImpl.save(new TransactionRequest()));

        assertThrows(ResourceNotFoundException.class,() -> transactionServiceImpl.save(new TransactionRequest(LocalDateTime.of
                (1, 1, 1, 1, 1),
                        "The characteristics ", 1L, 1L, 1L)));

        assertThrows(ResourceNotFoundException.class,
                () -> transactionServiceImpl.save(new TransactionRequest(LocalDateTime.of(1, 1, 1, 1, 1),
                        " someone or something", 1L, 1L, 0L)));
    }



    @Test
    void testDelete() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> transactionServiceImpl.delete(1L));
        assertThrows(ResourceNotFoundException.class, () -> transactionServiceImpl.delete(2L));
    }


    @Test
    void testFindAllTransactionsbyBadgeId() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> transactionServiceImpl.findAllTransactionsbyBadgeId(1L));
    }


    @Test
    void testFindAllTransactionByLocation() {
        // Arrange, Act and Assert
        assertThrows(ResourceNotFoundException.class, () -> transactionServiceImpl.findAllTransactionByLocation(1L));
    }
}

