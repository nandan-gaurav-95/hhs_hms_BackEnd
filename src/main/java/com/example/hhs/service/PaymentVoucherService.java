package com.example.hhs.service;



import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.PaymentVoucher;
import com.example.hhs.repository.PaymentVoucherRepository;

@Service
public class PaymentVoucherService {
	
	 @Autowired
	 private  PaymentVoucherRepository paymentVoucherRepository;

	  

	    public PaymentVoucher createPaymentVoucher(PaymentVoucher paymentVoucher) {
	        paymentVoucher.setVoucherDate(Date.valueOf(paymentVoucher.getVoucherDate().toLocalDate()));
	        PaymentVoucher createdPaymentVoucher = paymentVoucherRepository.save(paymentVoucher);

	        // Return the created PaymentVoucher
	        return createdPaymentVoucher;
	    }
	    public PaymentVoucher getPaymentVoucherById(Long id) {
	        // Implement your logic to retrieve a PaymentVoucher record by ID
	        Optional<PaymentVoucher> paymentVoucherOptional = paymentVoucherRepository.findById(id);

	        return paymentVoucherOptional.orElse(null);
	    }
	    public List<PaymentVoucher> getAllPaymentVouchers() {
	        // Implement your logic to retrieve all PaymentVoucher records
	        List<PaymentVoucher> paymentVouchers = paymentVoucherRepository.findAll();

	        // Return the list of PaymentVoucher records
	        return paymentVouchers;
	    }
	    
	    
	    public PaymentVoucher updatePaymentVoucher(Long id, PaymentVoucher updatedPaymentVoucher) {
	        // Retrieve the existing PaymentVoucher record by ID
	        Optional<PaymentVoucher> existingPaymentVoucherOptional = paymentVoucherRepository.findById(id);

	        if (existingPaymentVoucherOptional.isPresent()) {
	            PaymentVoucher existingPaymentVoucher = existingPaymentVoucherOptional.get();

	            // Update the fields of the existing PaymentVoucher with the values from updatedPaymentVoucher
	            existingPaymentVoucher.setVoucherNum(updatedPaymentVoucher.getVoucherNum());
	            existingPaymentVoucher.setVoucherDate(updatedPaymentVoucher.getVoucherDate());
	            existingPaymentVoucher.setAmount(updatedPaymentVoucher.getAmount());
	            existingPaymentVoucher.setPaymentMethod(updatedPaymentVoucher.getPaymentMethod());
	            existingPaymentVoucher.setRemark(updatedPaymentVoucher.getRemark());

	            // Save the updated PaymentVoucher record
	            PaymentVoucher updatedVoucher = paymentVoucherRepository.save(existingPaymentVoucher);

	            return updatedVoucher;
	        } else {
	            // PaymentVoucher record not found, return null or handle the situation as needed
	            return null;
	        }
	    }
	    
	    
	        public boolean deletePaymentVoucher(Long id) {
	           
	            if (paymentVoucherRepository.existsById(id)) {
	                paymentVoucherRepository.deleteById(id);
	                return true; // Record was successfully deleted
	            } else {
	                return false; // Record not found or an error occurred
	            }
	        
	        
	    }
}
