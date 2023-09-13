package com.example.hhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.Payroll;
import com.example.hhs.repository.PayrollRepository;

@Service
public class PayrollService {
	
	@Autowired
	public PayrollRepository payrollRepository;
	
	 // Create a new payroll entry
    public Payroll createPayroll(Payroll payroll) {
        return payrollRepository.save(payroll);
    }

    // Retrieve all payroll entries
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // Retrieve a specific payroll entry by ID
    public Optional<Payroll> getPayrollById(Long id) {
        return payrollRepository.findById(id);
    }

    // Update an existing payroll entry
    public Payroll updatePayroll(Long id, Payroll updatedPayroll) {
        if (payrollRepository.existsById(id)) {
            updatedPayroll.setId(id); // Set the ID of the updated payroll
            return payrollRepository.save(updatedPayroll);
        }
        return null; // Payroll entry not found
    }

    // Delete a payroll entry by ID
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }

    // Other business logic methods can be added here

}
