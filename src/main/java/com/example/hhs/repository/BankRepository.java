package com.example.hhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.Bank;
@Repository
public interface BankRepository extends JpaRepository<Bank, Long>{

}
