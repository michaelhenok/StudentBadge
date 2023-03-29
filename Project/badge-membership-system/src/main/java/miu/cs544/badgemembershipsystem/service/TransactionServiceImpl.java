package miu.cs544.badgemembershipsystem.service;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.aspect.exceptionHandling.ResourceNotFoundException;
import miu.cs544.badgemembershipsystem.domain.*;
import miu.cs544.badgemembershipsystem.dto.request.TransactionRequest;
import miu.cs544.badgemembershipsystem.dto.response.TransactionResponse;
import miu.cs544.badgemembershipsystem.repository.*;
import miu.cs544.badgemembershipsystem.utils.enums.Intervals;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {


    private final BadgeRepo badgeRepo;

    private final LocationRepo locationRepo;

    private final PlanRepo planRepo;
    private final MemberRepo memberRepo;
    private final ModelMapper modelMapper;

    private final TransactionRepo transactionRepo;


    @Override
    public List<TransactionResponse> findAll() {
        return transactionRepo.findAll().stream()
                .map(t -> modelMapper.map(t, TransactionResponse.class)).toList();
    }

    @Override
    public TransactionResponse findById(long id) {
        Optional<Transaction> transaction = transactionRepo.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction Not Found");
        }
        return modelMapper.map(transaction, TransactionResponse.class);
    }

    @Override
    public void save(TransactionRequest transactionRequest) {

        if (transactionRequest.getBadgeId() == 0 || transactionRequest.getLocationId() == 0
                || transactionRequest.getPlanId() == 0) {
            throw new ResourceNotFoundException("Badge or Location or Plan is not provided");
        }


        Optional<Badge> badge = badgeRepo.findById(transactionRequest.getBadgeId());

        Optional<Location> location = locationRepo.findById(transactionRequest.getLocationId());
        Optional<Member> checker = memberRepo.findById(transactionRequest.getCheckerId());

        Optional<Plan> plan = planRepo.findById(transactionRequest.getPlanId());

        if (badge.isEmpty() || location.isEmpty() || plan.isEmpty()|| checker.isEmpty()){
            throw new ResourceNotFoundException("Badge or location or Plan or Checker is not found");
        }

        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        transaction.setBadge(badge.get());
        transaction.setLocation(location.get());
        transaction.setPlan(plan.get());
        transaction.setChecker(checker.get());

        transaction.setEntryDate(LocalDateTime.now());

        transactionRepo.save(transaction);
    }

    @Override
    public List<Transaction> findTransactionsOfMemberInGivenPlanWithInterval(Long badgeId, Long planId, Intervals interval) {
        LocalDateTime startTime = calculateStartTime(interval);
        return transactionRepo.findTransactionsOfMemberInGivenPlanWithInterval(badgeId, planId, startTime);
    }


    private LocalDateTime calculateStartTime(Intervals intervals) {
        LocalDate localDate = LocalDate.now();
        switch (intervals) {
            case MONTH:
                localDate.withDayOfMonth(1);
            case YEAR:
                localDate.withDayOfYear(1);
            case WEEK:
                localDate.minusWeeks(1);
            case DAY:
                localDate.now();
        }
        return LocalDateTime.of(localDate, LocalTime.MIDNIGHT);
    }

    @Override
    public void update(long id, TransactionRequest transactionRequest) {

        Optional<Transaction> transactionDb = transactionRepo.findById(id);
        if (transactionDb.isEmpty()) {
            throw new ResourceNotFoundException("Transaction Not Found");
        }
        Transaction transaction = modelMapper.map(transactionRequest, Transaction.class);
        transaction.setId(id);
        transactionRepo.save(transaction);
    }

    @Override
    public void delete(long id) {
        Optional<Transaction> transaction = transactionRepo.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Transaction Not Found");
        }
        transactionRepo.deleteById(id);
    }

    @Override
    public List<TransactionResponse> findAllTransactionsbyBadgeId(long badgeId) {
        Optional<Badge> badge = badgeRepo.findById(badgeId);
        if (badge.isEmpty()) {
            throw new ResourceNotFoundException("Badge not found");
        }
        return transactionRepo.findAllTransactionByBadgeId(badgeId).stream().map(t -> modelMapper.map(t, TransactionResponse.class)).toList();
    }

    @Override
    public List<Transaction> findAllTransactionByLocation(long id) {

        Optional<Transaction> transaction = transactionRepo.findById(id);
        if (transaction.isEmpty()) {
            throw new ResourceNotFoundException("Location is not found");
        }
        return transactionRepo.findAllTransactionByLocation(id);
    }


}
