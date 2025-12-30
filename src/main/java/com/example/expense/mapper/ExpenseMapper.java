package com.example.expense.mapper;

import com.example.expense.dto.ExpenseRequestDto;
import com.example.expense.dto.ExpenseResponseDto;
import com.example.expense.model.Expense;

public class ExpenseMapper {

    // Request DTO → Entity
    public static Expense toEntity(ExpenseRequestDto dto) {

        Expense expense = new Expense();
        expense.setTitle(dto.getTitle());
        expense.setAmount(dto.getAmount());
        expense.setCategory(dto.getCategory());
        expense.setExpenseDate(dto.getExpenseDate());

        return expense;
    }

    // Entity → Response DTO
    public static ExpenseResponseDto toDto(Expense expense) {

        ExpenseResponseDto dto = new ExpenseResponseDto();
        dto.setId(expense.getId());
        dto.setTitle(expense.getTitle());
        dto.setAmount(expense.getAmount());
        dto.setCategory(expense.getCategory());
        dto.setExpenseDate(expense.getExpenseDate());
        dto.setCreatedAt(expense.getCreatedAt());
        dto.setUpdatedAt(expense.getUpdatedAt());

        return dto;
    }
}
