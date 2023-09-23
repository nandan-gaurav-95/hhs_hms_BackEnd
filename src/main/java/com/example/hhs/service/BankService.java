package com.example.hhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.Bank;
import com.example.hhs.repository.BankRepository;

@Service
public class BankService {

    @Autowired
    private BankRepository bankRepository;


    public List<Bank> getAllBanks() {
        return bankRepository.findAll();
    }

    public Optional<Bank> getBankById(Long id) {
        return bankRepository.findById(id);
    }

    public Bank saveBank(Bank bank) {
        return bankRepository.save(bank);
    }

    public void deleteBank(Long id) {
        bankRepository.deleteById(id);
        
    }

    public Bank updateBank(Long id, Bank updatedBank) {
        Optional<Bank> existingBankOptional = bankRepository.findById(id);

        if (existingBankOptional.isPresent()) {
            Bank existingBank = existingBankOptional.get();
            // Update the fields of the existing bank with the values from updatedBank
            existingBank.setBankName(updatedBank.getBankName());
            existingBank.setAccountNo(updatedBank.getAccountNo());
            existingBank.setTdrNo(updatedBank.getTdrNo());
            existingBank.setInterestaccured(updatedBank.getInterestaccured());
            existingBank.setAmountutilized(updatedBank.getAmountutilized());
            existingBank.setBalance(updatedBank.getBalance());

            // Save the updated bank entity
            return bankRepository.save(existingBank);
        } else {
            // Bank not found
            return null;
        }
    }
}