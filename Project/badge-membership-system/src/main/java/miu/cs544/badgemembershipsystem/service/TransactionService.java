package miu.cs544.badgemembershipsystem.service;

import miu.cs544.badgemembershipsystem.domain.Transaction;
import miu.cs544.badgemembershipsystem.dto.request.TransactionRequest;
import miu.cs544.badgemembershipsystem.dto.response.TransactionResponse;
import miu.cs544.badgemembershipsystem.utils.enums.Intervals;

import java.util.List;

public interface TransactionService {
    List<TransactionResponse> findAll();

    TransactionResponse findById(long id);

    void save(TransactionRequest transactionRequest);

    List<Transaction> findTransactionsOfMemberInGivenPlanWithInterval(Long badgeId, Long PlanId, Intervals interval);

    void update(long id, TransactionRequest transactionRequest);

    void delete(long id);

    List<TransactionResponse> findAllTransactionsbyBadgeId(long badgeId);

    List<Transaction> findAllTransactionByLocation(long id);
}
