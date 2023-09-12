package com.example.hhs.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Builder
@Entity
@Table(name="property_photo")
public class PropertyPhoto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String type;

    @Lob
    @Column(name = "photoData", length = 2097152) // Adjust length as needed
    private byte[] photoData;

    public PropertyPhoto(Company company, byte[] photoData) {
		// TODO Auto-generated constructor stub
	}

//	public void setCompany(Company existingCompany) {
//		// TODO Auto-generated method stub
//		
//	}
}
