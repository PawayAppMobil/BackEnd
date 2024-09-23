package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.ExpenseCategory;
import com.paway.spring.data.kmoneta.reports.repository.ExpenseCategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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

    @GetMapping
    public ResponseEntity<List<ExpenseCategory>> obtenerTodasLasCategorias() {
        List<ExpenseCategory> categorias = expenseCategoryRepository.findAll();
        return ResponseEntity.ok(categorias);
    }


    @GetMapping("/{id}")
    public ResponseEntity<ExpenseCategory> obtenerCategoriaPorId(@PathVariable String id) {
        return expenseCategoryRepository.findById(id)
                .map(categoria -> ResponseEntity.ok(categoria))
                .orElse(ResponseEntity.notFound().build());
    }

}
