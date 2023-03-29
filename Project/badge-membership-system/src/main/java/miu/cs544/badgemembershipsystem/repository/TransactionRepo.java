package miu.cs544.badgemembershipsystem.repository;


import miu.cs544.badgemembershipsystem.domain.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {

    @Query("select t from Transaction t where t.badge.id = :badgeId")
    public List<Transaction> findAllTransactionByBadgeId(@Param("badgeId") long badgeId);

    @Query("select t from Transaction t where t.badge.id = :badgeId and t.plan.id= :planId and t.entryDate >= :entryDate")
    public List<Transaction> findTransactionsOfMemberInGivenPlanWithInterval(@Param("badgeId") long badgeId, @Param("planId") long planId, @Param("entryDate") LocalDateTime entryDate);

    @Query("select l from Transaction l where l.location.id = :locationId")
    public List<Transaction> findAllTransactionByLocation(@Param("locationId") long locationId);
}
