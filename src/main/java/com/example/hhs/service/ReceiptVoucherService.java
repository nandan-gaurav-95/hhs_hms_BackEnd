package com.example.hhs.service;


import com.example.hhs.model.ReceiptVoucher;
import com.example.hhs.repository.ReceiptVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReceiptVoucherService {

	  @Autowired
    private  ReceiptVoucherRepository receiptVoucherRepository;

    // Create a new receipt voucher
    public ReceiptVoucher createReceiptVoucher(ReceiptVoucher receiptVoucher) {
        return receiptVoucherRepository.save(receiptVoucher);
    }

    // Update an existing receipt voucher
    public ReceiptVoucher updateReceiptVoucher(ReceiptVoucher receiptVoucher) {
        return receiptVoucherRepository.save(receiptVoucher);
    }

    // Retrieve all receipt vouchers
    public List<ReceiptVoucher> getAllReceiptVouchers() {
        return receiptVoucherRepository.findAll();
    }

    // Retrieve a receipt voucher by ID
    public Optional<ReceiptVoucher> getReceiptVoucherById(Long id) {
        return receiptVoucherRepository.findById(id);
    }

    // Delete a receipt voucher by ID
    public void deleteReceiptVoucher(Long id) {
        receiptVoucherRepository.deleteById(id);
    }
}
