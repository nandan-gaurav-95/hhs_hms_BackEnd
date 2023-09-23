package com.example.hhs.controller;



import com.example.hhs.model.Bank;
import com.example.hhs.service.BankService;

import org.apache.el.stream.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class BankController {

    @Autowired
    private BankService bankService;
 
    @PostMapping("/bank")
    public ResponseEntity<Bank> saveBank(@RequestBody Bank bank) {
        Bank savedBank = bankService.saveBank(bank);
        if (savedBank != null) {
            return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedBank);
        } else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(null);
        }
    }
    
    @GetMapping("/bank")
    public ResponseEntity<List<Bank>> getAllBanks() {
        List<Bank> banks = bankService.getAllBanks();
        if (!banks.isEmpty()) {
            return ResponseEntity.ok(banks);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body(null);
        }
    }

    @GetMapping("/bank/{id}")
    public ResponseEntity<Bank> getBankById(@PathVariable Long id) {
        Bank bank = bankService.getBankById(id).orElse(null);
        if (bank != null) {
            return ResponseEntity.ok(bank);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    

    @DeleteMapping("/bank/{id}")
    public ResponseEntity<Void> deleteBank(@PathVariable Long id) {
        bankService.deleteBank(id);
        return ResponseEntity.noContent().build();
    }
    
    @PutMapping("/bank/{id}")
    public ResponseEntity<Bank> updateBank(@PathVariable Long id, @RequestBody Bank updatedBank) {
        Bank updatedBankItem = bankService.updateBank(id, updatedBank);
        if (updatedBankItem != null) {
            return ResponseEntity.ok(updatedBankItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
