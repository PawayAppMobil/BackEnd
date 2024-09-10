package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.ExpenseCategory;
import com.paway.spring.data.kmoneta.reports.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/expense-categories")
public class ExpenseCategoryController {
    @Autowired
    private ExpenseCategoryRepository expenseCategoryRepository;

    @PostMapping
    public ResponseEntity<ExpenseCategory> crearExpenseCategory(@RequestBody ExpenseCategory expenseCategory) {
        ExpenseCategory nuevaCategoria = expenseCategoryRepository.save(expenseCategory);
        return ResponseEntity.ok(nuevaCategoria);
    }
}
