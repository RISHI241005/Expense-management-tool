package com.example.expense.service;

import com.example.expense.dto.ExpenseRequestDto;
import com.example.expense.dto.ExpenseResponseDto;

import java.util.List;

public interface ExpenseService {

    ExpenseResponseDto createExpense(ExpenseRequestDto dto);

    List<ExpenseResponseDto> getAllExpenses();

    ExpenseResponseDto getExpenseById(Long id);

    ExpenseResponseDto updateExpense(Long id, ExpenseRequestDto dto);

    void deleteExpense(Long id);
}
