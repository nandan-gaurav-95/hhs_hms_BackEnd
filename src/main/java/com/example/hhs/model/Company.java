/*
  @author Jay Shree Ram
 * 
 */
package com.example.hhs.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
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
@Entity
@Builder
public class Company {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 private String companyNm;
	    private String email;
	    private String gstNo;
	    private Long mobNo;
	    
	    @OneToOne(cascade = CascadeType.ALL)
	    @JoinColumn(name = "logo_id") // This will create a foreign key to link the image
	    private CompanyLogo logo;
	    
//	    @OneToMany(cascade = CascadeType.ALL)
//	    @JoinColumn(name = "photo_id")
//	    private PropertyPhoto propertyPhoto; // Add a relationship to the Photo entity
	   
	    @OneToMany(cascade = CascadeType.ALL, mappedBy = "company")
	    private List<PropertyPhoto> propertyPhotos = new ArrayList<>();

	    
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
	    
}