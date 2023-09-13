package com.example.hhs.model;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class ExpenseVoucher {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	  // Voucher details
    private String voucherNumber;
    private Date voucherDate;
    private Double amount;
    private String expenseCategory; // Category of expense (e.g., Office Supplies, Utilities)
    private String remarks;

    // Employee or department related to this expense
    // You may need to establish relationships here

	
}
