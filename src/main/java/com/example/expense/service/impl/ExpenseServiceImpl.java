package com.example.expense.service.impl;

import com.example.expense.dto.ExpenseRequestDto;
import com.example.expense.dto.ExpenseResponseDto;
import com.example.expense.exception.ResourceNotFoundException;
import com.example.expense.mapper.ExpenseMapper;
import com.example.expense.model.Expense;
import com.example.expense.repository.ExpenseRepository;
import com.example.expense.service.ExpenseService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    private final ExpenseRepository expenseRepository;

    public ExpenseServiceImpl(ExpenseRepository expenseRepository) {
        this.expenseRepository = expenseRepository;
    }

    // ---------------- CREATE ----------------
    @Override
    public ExpenseResponseDto createExpense(ExpenseRequestDto dto) {

        Expense expense = ExpenseMapper.toEntity(dto);
        Expense savedExpense = expenseRepository.save(expense);

        return ExpenseMapper.toDto(savedExpense);
    }

    // ---------------- GET ALL ----------------
    @Override
    public List<ExpenseResponseDto> getAllExpenses() {

        return expenseRepository.findAll()
                .stream()
                .map(ExpenseMapper::toDto)
                .toList();
    }

    // ---------------- GET BY ID ----------------
    @Override
    public ExpenseResponseDto getExpenseById(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense", "id", id));

        return ExpenseMapper.toDto(expense);
    }

    // ---------------- UPDATE ----------------
    @Override
    public ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto dto) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense", "id", id));

        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());

        Expense updatedExpense = expenseRepository.save(expense);

        return ExpenseMapper.toDto(updatedExpense);
    }

    // ---------------- DELETE ----------------
    @Override
    public void deleteExpense(Long id) {

        Expense expense = expenseRepository.findById(id)
                .orElseThrow(() ->
                        new ResourceNotFoundException("Expense", "id", id));

        expenseRepository.delete(expense);
    }
}
