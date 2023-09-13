package com.example.hhs.model;

// Jay Shree Ram
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
public class Payroll {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
//	Employee Details
	private Long emp_id;
	private String emp_name;
	
//	Payroll Details
	private Date dateOfHiring;
	private Date dateOfLeaving;
	private Double basicSalary;
	private Double allowance;
	private Double deduction;
	private Double grossSalary;
	private Double netSalary;
	
//	PF Details
	private Double pfEmployeeContribution;
	private Double pfEmployerContribution;
	
//	Loan Details
	private Double loanAmount;
	private Double loanRepaymentAmount;

	
}
