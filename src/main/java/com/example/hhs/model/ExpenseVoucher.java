package com.example.hhs.model;

import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
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
