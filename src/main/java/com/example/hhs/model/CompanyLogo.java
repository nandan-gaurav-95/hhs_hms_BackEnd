package com.example.hhs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
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
@Entity
@Builder
@Table(name="company_logo")
public class CompanyLogo {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long l_id;

    private String name;
    private String type;
    @Lob
    @Column(name = "logoData",length = 2097152)
    private byte[] logoData;
    
    
    public CompanyLogo(Company company, byte[] logoImage) {
		// TODO Auto-generated constructor stub
	}
   
}
