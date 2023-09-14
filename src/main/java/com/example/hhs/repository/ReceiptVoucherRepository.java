package com.example.hhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.ReceiptVoucher;

@Repository
public interface ReceiptVoucherRepository  extends JpaRepository<ReceiptVoucher, Long>  {

}
