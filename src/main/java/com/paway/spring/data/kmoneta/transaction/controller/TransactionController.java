package com.paway.spring.data.kmoneta.transaction.controller;

import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import com.paway.spring.data.kmoneta.transaction.repository.TransactionRepository;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

    @GetMapping("/{id}")
    public Transaction getTransactionById(@PathVariable String id) {
        return transactionRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Transaction createTransaction(@Valid @RequestBody Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    @PutMapping("/{id}")
    public Transaction updateTransaction(@PathVariable String id, @RequestBody Transaction transaction) {
        transaction.setId(id);
        return transactionRepository.save(transaction);
    }

    @DeleteMapping("/{id}")
    public void deleteTransaction(@PathVariable String id) {
        transactionRepository.deleteById(id);
    }
}
