package com.example.hhs.model;



import java.sql.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
@Table(name="tenant")
public class Tenant {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	private String tenantName;
	private String address;
	private Long contactNum;

	@ManyToOne
	@JoinColumn(name="company_id")
	private Company company;
	
	private String allocatedShop;
    private Double rentCollected;
    private Double rentDue;
    private Double securityDeposit;
    private Double electricityDue;
    private Date date;
//    private String electricityCollectionDetails;
}
