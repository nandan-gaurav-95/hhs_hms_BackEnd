package com.example.hhs.model;

import java.sql.Date;

import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

public class PettyCashBook {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
    private Date date;
    private String description;
    private Double pettyCashInflow;
    private Double pettyCashOutflow;
	
	 // Other relevant fields for the Petty Cash Book

}
