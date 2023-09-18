package com.example.hhs.model;

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
public class CompanyWithImage {
	
//	private Company company;
	private Long id;
	 private String companyNm;
	    private String email;
	    private String gstNo;
	    private Long mobNo;
	    
    private byte[] imageData;
    
    private String villageNm;
    private String ctsNo;
    private Double extentAcres;
    private String boundries;
    private Double taxAmt;
    private String accountNm;
    private Double annualIncome;
    private String address;
    private int registrationNo;
    private String gazzetNo;
    private String message; // Add a message field
}
