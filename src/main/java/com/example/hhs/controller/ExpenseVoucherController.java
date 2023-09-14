package com.example.hhs.controller;


import com.example.hhs.model.ExpenseVoucher;
import com.example.hhs.service.ExpenseVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ExpenseVoucherController {

    private final ExpenseVoucherService expenseVoucherService;

    @Autowired
    public ExpenseVoucherController(ExpenseVoucherService expenseVoucherService) {
        this.expenseVoucherService = expenseVoucherService;
    }

    // Create a new expense voucher
    @PostMapping("/expense")
    public ExpenseVoucher createExpenseVoucher(@RequestBody ExpenseVoucher expenseVoucher) {
        return expenseVoucherService.createExpenseVoucher(expenseVoucher);
    }

    // Retrieve an expense voucher by ID
    @GetMapping("/expense/{id}")
    public ExpenseVoucher getExpenseVoucherById(@PathVariable Long id) {
        return expenseVoucherService.getExpenseVoucherById(id);
    }

    // Retrieve all expense vouchers
    @GetMapping("/expense")
    public List<ExpenseVoucher> getAllExpenseVouchers() {
        return expenseVoucherService.getAllExpenseVouchers();
    }

    // Update an existing expense voucher by ID
    @PutMapping("/expense/{id}")
    public ExpenseVoucher updateExpenseVoucher(@PathVariable Long id, @RequestBody ExpenseVoucher updatedExpenseVoucher) {
        return expenseVoucherService.updateExpenseVoucher(id, updatedExpenseVoucher);
    }

    // Delete an expense voucher by ID
    @DeleteMapping("/expense/{id}")
    public void deleteExpenseVoucher(@PathVariable Long id) {
        expenseVoucherService.deleteExpenseVoucher(id);
    }
}
