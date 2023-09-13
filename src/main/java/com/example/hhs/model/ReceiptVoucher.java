package com.example.hhs.model;

//Jay Shree Ram
import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Entity

public class ReceiptVoucher {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	voucher Details 
	private String voucherNum;
	private Date voucherDate;
	private Double amount;
	private String paymentMethod; //Cash Or Cheque
	private String remark;
	
	 // Employee or Tenant related to this payment
    // You may need to establish relationships here
	
}
