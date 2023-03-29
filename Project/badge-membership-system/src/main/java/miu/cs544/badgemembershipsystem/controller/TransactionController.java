package miu.cs544.badgemembershipsystem.controller;

import lombok.RequiredArgsConstructor;
import miu.cs544.badgemembershipsystem.dto.request.TransactionRequest;
import miu.cs544.badgemembershipsystem.dto.response.TransactionResponse;
import miu.cs544.badgemembershipsystem.service.TransactionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionServiceImpl transactionService;


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionResponse> getAll() {
        return transactionService.findAll();
    }

    @GetMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionResponse getById(@PathVariable long id) {
        return transactionService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody TransactionRequest transactionRequest) {
        transactionService.save(transactionRequest);
    }


    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void update(@PathVariable long id, @RequestBody TransactionRequest transactionRequest) {
        transactionService.update(id, transactionRequest);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public void delete(@PathVariable long id) {
        transactionService.delete(id);

    }


}
