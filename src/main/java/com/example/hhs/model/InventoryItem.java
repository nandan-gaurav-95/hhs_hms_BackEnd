package com.example.hhs.model;

//Jay Shree Ram
import jakarta.persistence.Entity;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
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

public class InventoryItem {
	
	 @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
	 
//   Inventory Item details
	 private String itemName;
	 private String itemDescription;
	 private Double unitPrice;
	 private int quantityAvailable;
	 
	// Inventory category or type (e.g., Consumable, Non-Consumable)
	    private String category;
	    
	    // Employee responsible for the item
//	    @ManyToOne
//	    private Employee responsibleEmployee; // You may need to define the Employee entity
	    
	 
	    // Other relevant fields for tracking inventory
	 
}
