package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.BadgeRequest;
import miu.cs544.badgemembershipsystem.dto.request.BadgeSwipeRequest;
import miu.cs544.badgemembershipsystem.dto.response.BadgeResponse;
import miu.cs544.badgemembershipsystem.dto.response.TransactionResponse;
import miu.cs544.badgemembershipsystem.service.BadgeService;
import miu.cs544.badgemembershipsystem.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/badges")
@RequiredArgsConstructor
public class BadgeController {
    private final BadgeService badgeService;
    private final TransactionService transactionService;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<BadgeResponse> getAll() {
        return badgeService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public BadgeResponse getOne(@PathVariable long id) {
        return badgeService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody BadgeRequest badgeRequest) {
        badgeService.save(badgeRequest);
    }

    @PostMapping("/{id}/swipe")
    @ResponseStatus(HttpStatus.CREATED)
    public void swipe(@PathVariable long id, @RequestBody BadgeSwipeRequest badgeSwipeRequest) {
        badgeService.swipe(id, badgeSwipeRequest);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody BadgeRequest badgeRequest) {
        badgeService.update(id, badgeRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        badgeService.delete(id);
    }

    @GetMapping("{id}/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> findAllTransactionByBadgeId(@PathVariable long id) {
        return transactionService.findAllTransactionsbyBadgeId(id);
    }
}
