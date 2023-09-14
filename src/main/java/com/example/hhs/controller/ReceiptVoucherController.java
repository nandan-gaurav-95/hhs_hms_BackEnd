package com.example.hhs.controller;



import com.example.hhs.model.ReceiptVoucher;
import com.example.hhs.service.ReceiptVoucherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ReceiptVoucherController {

    private final ReceiptVoucherService receiptVoucherService;

    @Autowired
    public ReceiptVoucherController(ReceiptVoucherService receiptVoucherService) {
        this.receiptVoucherService = receiptVoucherService;
    }

    // Create a new receipt voucher
    @PostMapping("/receipt")
    public ResponseEntity<ReceiptVoucher> createReceiptVoucher(@RequestBody ReceiptVoucher receiptVoucher) {
        ReceiptVoucher createdVoucher = receiptVoucherService.createReceiptVoucher(receiptVoucher);
        return new ResponseEntity<>(createdVoucher, HttpStatus.CREATED);
    }

    // Retrieve all receipt vouchers
    @GetMapping("/receipt")
    public ResponseEntity<List<ReceiptVoucher>> getAllReceiptVouchers() {
        List<ReceiptVoucher> receiptVouchers = receiptVoucherService.getAllReceiptVouchers();
        return new ResponseEntity<>(receiptVouchers, HttpStatus.OK);
    }

    // Retrieve a receipt voucher by ID
    @GetMapping("/receipt/{id}")
    public ResponseEntity<ReceiptVoucher> getReceiptVoucherById(@PathVariable Long id) {
        Optional<ReceiptVoucher> receiptVoucher = receiptVoucherService.getReceiptVoucherById(id);
        return receiptVoucher.map(voucher -> new ResponseEntity<>(voucher, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    
    // Update an existing receipt voucher
    @PutMapping("/receipt/{id}")
    public ResponseEntity<ReceiptVoucher> updateReceiptVoucher(@PathVariable Long id, @RequestBody ReceiptVoucher receiptVoucher) {
        Optional<ReceiptVoucher> existingVoucher = receiptVoucherService.getReceiptVoucherById(id);
        if (existingVoucher.isPresent()) {
            receiptVoucher.setId(id); // Ensure the ID is set for the update
            ReceiptVoucher updatedVoucher = receiptVoucherService.updateReceiptVoucher(receiptVoucher);
            return new ResponseEntity<>(updatedVoucher, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    // Delete a receipt voucher by ID
    @DeleteMapping("/receipt/{id}")
    public ResponseEntity<Void> deleteReceiptVoucher(@PathVariable Long id) {
        Optional<ReceiptVoucher> existingVoucher = receiptVoucherService.getReceiptVoucherById(id);
        if (existingVoucher.isPresent()) {
            receiptVoucherService.deleteReceiptVoucher(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
