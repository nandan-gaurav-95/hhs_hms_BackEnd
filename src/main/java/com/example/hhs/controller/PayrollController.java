package com.example.hhs.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.hhs.model.Payroll;
import com.example.hhs.service.PayrollService;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PayrollController {

	  @Autowired
    private  PayrollService payrollService;

    // Create a new payroll entry
    @PostMapping("/payroll")
    public ResponseEntity<Payroll> createPayroll(@RequestBody Payroll payroll) {
        Payroll createdPayroll = payrollService.createPayroll(payroll);
    	if(createdPayroll !=null) {
			 return ResponseEntity.status(HttpStatus.CREATED)
					.body(createdPayroll);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);	
		} 
//        return ResponseEntity.ok(createdPayroll);
    }

    // Retrieve all payroll entries
    @GetMapping("/payroll")
    public ResponseEntity<List<Payroll>> getAllPayrolls() {
        List<Payroll> payrollEntries = payrollService.getAllPayrolls();
        return ResponseEntity.ok(payrollEntries);
    }

    // Retrieve a specific payroll entry by ID
    @GetMapping("payroll/{id}")
    public ResponseEntity<Payroll> getPayrollById(@PathVariable Long id) {
        Optional<Payroll> payrollEntry = payrollService.getPayrollById(id);
        if (payrollEntry.isPresent()) {
            return ResponseEntity.ok(payrollEntry.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing payroll entry
    @PutMapping("payroll/{id}")
    public ResponseEntity<Payroll> updatePayroll(@PathVariable Long id, @RequestBody Payroll updatedPayroll) {
        Payroll updatedPayrollEntry = payrollService.updatePayroll(id, updatedPayroll);
        if (updatedPayrollEntry != null) {
            return ResponseEntity.ok(updatedPayrollEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a payroll entry by ID
    @DeleteMapping("payroll/{id}")
    public ResponseEntity<Void> deletePayroll(@PathVariable Long id) {
        payrollService.deletePayroll(id);
        return ResponseEntity.noContent().build();
    }
}
