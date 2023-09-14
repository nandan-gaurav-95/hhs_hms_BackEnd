package com.example.hhs.service;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.hhs.model.ExpenseVoucher;
import com.example.hhs.repository.ExpenseVoucherRepository;

import java.util.List;

@Service
public class ExpenseVoucherService {

	@Autowired
    private  ExpenseVoucherRepository expenseVoucherRepository;

    
    

    // Create a new expense voucher
    public ExpenseVoucher createExpenseVoucher(ExpenseVoucher expenseVoucher) {
        return expenseVoucherRepository.save(expenseVoucher);
    }

    // Retrieve an expense voucher by ID
    public ExpenseVoucher getExpenseVoucherById(Long id) {
        return expenseVoucherRepository.findById(id).orElse(null);
    }

    // Retrieve all expense vouchers
    public List<ExpenseVoucher> getAllExpenseVouchers() {
        return expenseVoucherRepository.findAll();
    }

    // Update an existing expense voucher
    public ExpenseVoucher updateExpenseVoucher(Long id, ExpenseVoucher updatedExpenseVoucher) {
        ExpenseVoucher existingExpenseVoucher = expenseVoucherRepository.findById(id).orElse(null);
        if (existingExpenseVoucher != null) {
            // Update fields of the existing expense voucher with the new data
            // You can add logic here to update specific fields as needed
            existingExpenseVoucher.setVoucherNumber(updatedExpenseVoucher.getVoucherNumber());
            existingExpenseVoucher.setVoucherDate(updatedExpenseVoucher.getVoucherDate());
            existingExpenseVoucher.setAmount(updatedExpenseVoucher.getAmount());
            existingExpenseVoucher.setExpenseCategory(updatedExpenseVoucher.getExpenseCategory());
            existingExpenseVoucher.setRemarks(updatedExpenseVoucher.getRemarks());

            // Save the updated expense voucher
            return expenseVoucherRepository.save(existingExpenseVoucher);
        }
        return null; // Return null if the expense voucher with the given ID doesn't exist
    }

    // Delete an expense voucher by ID
    public void deleteExpenseVoucher(Long id) {
        expenseVoucherRepository.deleteById(id);
    }
}