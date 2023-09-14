package com.example.hhs.controller;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhs.model.PaymentVoucher;
import com.example.hhs.service.PaymentVoucherService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PaymentVoucherController {
	@Autowired
	 private  PaymentVoucherService paymentVoucherService;

	    
	    // Create a new payment record
	    @PostMapping("/payment")
	    public ResponseEntity<PaymentVoucher> createPaymentVoucher(@RequestBody PaymentVoucher paymentVoucher) {
	        // Optionally, set the date fields with only the date portion
	        paymentVoucher.setVoucherDate(Date.valueOf(paymentVoucher.getVoucherDate().toLocalDate()));

	        PaymentVoucher createdPaymentVoucher = paymentVoucherService.createPaymentVoucher(paymentVoucher);

	        if (createdPaymentVoucher != null) {
	            return ResponseEntity.status(HttpStatus.CREATED)
	                .body(createdPaymentVoucher);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                .body(null);
	        }
	    }
	    // Retrieve all payment entries
	    @GetMapping("/payment")
	    public ResponseEntity<List<PaymentVoucher>> getAllPaymentVouchers() {
	        List<PaymentVoucher> paymentVouchers = paymentVoucherService.getAllPaymentVouchers();

	        if (!paymentVouchers.isEmpty()) {
	            return ResponseEntity.ok(paymentVouchers);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	 // Retrieve a specific payment entry by ID
	    @GetMapping("/payment/{id}")
	    public ResponseEntity<PaymentVoucher> getPaymentVoucherById(@PathVariable Long id) {
	        PaymentVoucher paymentVoucher = paymentVoucherService.getPaymentVoucherById(id);

	        if (paymentVoucher != null) {
	            return ResponseEntity.ok(paymentVoucher);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	 // Update an existing payment entry
	    @PutMapping("/payment/{id}")
	    public ResponseEntity<PaymentVoucher> updatePaymentVoucher(@PathVariable Long id, @RequestBody PaymentVoucher updatedPaymentVoucher) {
	        PaymentVoucher updatedVoucher = paymentVoucherService.updatePaymentVoucher(id, updatedPaymentVoucher);

	        if (updatedVoucher != null) {
	            return ResponseEntity.ok(updatedVoucher);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
	    // Delete a payment entry by ID
	    @DeleteMapping("/payment/{id}")
	    public ResponseEntity<Void> deletePaymentVoucher(@PathVariable Long id) {
	        boolean deleted = paymentVoucherService.deletePaymentVoucher(id);

	        if (deleted) {
	            return ResponseEntity.noContent().build();
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
	        }
	    }
}
