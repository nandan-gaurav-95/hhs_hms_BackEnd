package com.example.hhs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.model.Company;
import com.example.hhs.model.PropertyPhoto;
import com.example.hhs.service.CompanyService;
import com.example.hhs.service.PropertyPhotoService;
import com.example.hhs.util.ImageUtil;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class PropertyPhotoController {
	
	@Autowired
	private PropertyPhotoService propphotoservice;
	@Autowired
    private  CompanyService companyService;
	
//	uploading multiple Images  
	
	  @PostMapping("/company-photo/{id}")
	public ResponseEntity<String> CompanyPhotoById(
	    @PathVariable Long id,
	    @RequestParam("files")  MultipartFile[]  files) {  
		  String message = "";
		  try {
		        Company updatedCompany = propphotoservice.CompanyPhotosById(id, Arrays.asList(files));

		        if (updatedCompany != null) {
		            List<String> fileNames = new ArrayList<>();

		            for (MultipartFile file : files) {
		            	System.err.println(id);
			            System.err.println(file.getOriginalFilename());
		                fileNames.add(file.getOriginalFilename());
		            }

		            message = "Company photos upload successfully: " + fileNames;
		            return ResponseEntity.status(HttpStatus.OK).body(message);
		        } else {
		            message = "Company not found or photo update failed";
		            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
		        }
		    } catch (Exception e) {
		        message = "Fail to upload company photos!";
		        return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
		    }
	}

	  
	  @GetMapping("/company-photo/{id}")
	  public ResponseEntity<List<byte[]>> getCompanyPhotosById(@PathVariable Long id) {
	      try {
	          // Retrieve the list of photo file names for the specified company ID
	          List<byte[]> photoDataList = propphotoservice.getCompanyPhotoDataById(id);

	          if (photoDataList != null && !photoDataList.isEmpty()) {
	              return ResponseEntity.status(HttpStatus.OK).body(photoDataList);
	          } else {
	              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Collections.emptyList());
	          }
	      } catch (Exception e) {
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Collections.emptyList());
	      }
	  }
	  
	  //Delete particular photo with photo-id 
//	  @DeleteMapping("/company-photo/{p_id}")
//	  public ResponseEntity<String> deleteCompanyPhotosById(@PathVariable Long p_id) {
//	      String message = "";
//	      try {
//	          boolean deleted = propphotoservice.deletePropertyPhotoById(p_id);
//	          if (deleted) {
//	        	  System.err.println(p_id); 
//	        	  message = "Company photos deleted successfully";
//	              return ResponseEntity.status(HttpStatus.OK).body(message);
//	          } else {
//	              message = "Company not found or photo deletion failed";
//	              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
//	          }
//	      } catch (Exception e) {
//	          message = "Failed to delete company photos!";
//	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
//	      }
//	  }
	  


//	uploading single Image 
//	@PutMapping("/property-photo/{id}")
//	public ResponseEntity<String> updateCompanyPhotoById(
//	    @PathVariable Long id,
//	    @RequestParam("file") MultipartFile file) {  
//	    Company updatedCompany = propphotoservice.updateCompanyPhotoById(id, file);
//	    if (updatedCompany != null) {
//	        System.err.println(id);
//	        System.out.println(file);
//	        return ResponseEntity.ok()
//	            .header("Message", "Company photo updated successfully")
//	            .body("Company photo updated successfully");
//	    } else {
//	        return ResponseEntity.status(HttpStatus.NOT_FOUND)
//	            .header("Message", "Company not found or photo update failed")
//	            .body("Company not found or photo update failed");
//	    }
//	}
	  
	  
	  @DeleteMapping("/company-photos/{company_id}")
	  public ResponseEntity<String> deleteCompanyPhotosByCompanyId(@PathVariable Long company_id) {
	      String message = "";
	      try {
	          boolean deleted = propphotoservice.deletePropertyPhotosByCompanyId(company_id);
	          if (deleted) {
	              System.err.println(company_id); 
	              message = "Company photos deleted successfullly";
	              return ResponseEntity.status(HttpStatus.OK).body(message);
	          } else {
	              message = "Company not found or photo deletion failed";
	              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(message);
	          }
	      } catch (Exception e) {
	          message = "Failed to delete company photos!";
	          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(message);
	      }
	  }

	  
	  
	  
	  
	


}
