package com.example.expense.controller;

import com.example.expense.dto.ExpenseRequestDto;
import com.example.expense.dto.ExpenseResponseDto;
import com.example.expense.service.ExpenseService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
public class ExpenseController {

    private final ExpenseService expenseService;

    public ExpenseController(ExpenseService expenseService) {
        this.expenseService = expenseService;
    }

    // CREATE
    @PostMapping
    public ResponseEntity<ExpenseResponseDto> createExpense(
            @Valid @RequestBody ExpenseRequestDto dto) {

        return ResponseEntity.ok(expenseService.createExpense(dto));
    }

    // READ ALL
    @GetMapping
    public ResponseEntity<List<ExpenseResponseDto>> getAllExpenses() {
        return ResponseEntity.ok(expenseService.getAllExpenses());
    }

    // READ BY ID
    @GetMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> getExpenseById(
            @PathVariable Long id) {

        return ResponseEntity.ok(expenseService.getExpenseById(id));
    }

    // UPDATE
    @PutMapping("/{id}")
    public ResponseEntity<ExpenseResponseDto> updateExpense(
            @PathVariable Long id,
            @Valid @RequestBody ExpenseRequestDto dto) {

        return ResponseEntity.ok(expenseService.updateExpense(id, dto));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteExpense(@PathVariable Long id) {
        expenseService.deleteExpense(id);
        return ResponseEntity.noContent().build();
    }
}
