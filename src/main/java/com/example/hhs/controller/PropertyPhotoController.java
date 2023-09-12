package com.example.hhs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.model.Company;
import com.example.hhs.service.PropertyPhotoService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyPhotoController {
	
	@Autowired
	private PropertyPhotoService propphotoservice;
//	uploading multiple Images  WIP
	
//	@PutMapping("/company-photo/{id}")
//	public ResponseEntity<String> updateCompanyPhotoById(
//	    @PathVariable Long id,
//	    @RequestParam("files") MultipartFile[] files) {  
//	    Company updatedCompany = propphotoservice.updateCompanyPhotoById(id, files);
//	    if (updatedCompany != null) {
//	        System.err.println(id);
//	        System.err.println(files.length);
//	        for (MultipartFile file : files) {
//	            System.out.println(file.getOriginalFilename());
//	        }
//	        return ResponseEntity.ok()
//	            .header("Message", "Company photos updated successfully")
//	            .body("Company photos updated successfully");
//	    } else {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//	            .header("Message", "Company not found or photo update failed")
//	            .body("Company not found or photo update failed");
//	    }
//	}

//	uploading single Image 
	@PutMapping("/property-photo/{id}")
	public ResponseEntity<String> updateCompanyPhotoById(
	    @PathVariable Long id,
	    @RequestParam("file") MultipartFile file) {  
	    Company updatedCompany = propphotoservice.updateCompanyPhotoById(id, file);
	    if (updatedCompany != null) {
	        System.err.println(id);
	        System.out.println(file);
	        return ResponseEntity.ok()
	            .header("Message", "Company photo updated successfully")
	            .body("Company photo updated successfully");
	    } else {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
	            .header("Message", "Company not found or photo update failed")
	            .body("Company not found or photo update failed");
	    }
	}

}
