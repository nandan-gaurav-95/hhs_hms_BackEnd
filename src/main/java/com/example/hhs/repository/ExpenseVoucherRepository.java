package com.example.hhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.ExpenseVoucher;

@Repository
public interface ExpenseVoucherRepository extends JpaRepository<ExpenseVoucher, Long>  {

}
