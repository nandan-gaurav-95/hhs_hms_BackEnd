package com.example.hhs.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.model.Company;
import com.example.hhs.model.CompanyLogo;
import com.example.hhs.model.CompanyWithImage;
import com.example.hhs.service.CompanyLogoService;
import com.example.hhs.service.CompanyService;
import com.example.hhs.util.ImageUtil;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/api")
public class CompanyController {


	@Autowired
    private  CompanyService companyService;
	

//Register Company
	
	 @PostMapping("/create")
		public ResponseEntity<?> uploadImage(
				@RequestParam("image")MultipartFile file,
			    @RequestParam("companyData") String companyData
			    ) throws IOException {
	    	  // Deserialize the JSON data into a Company object
	    	  ObjectMapper objectMapper = new ObjectMapper();
	    	    Company company = objectMapper.readValue(companyData, Company.class);

	    	    // Handle the image upload and create a CompanyLogo entity
	    	    CompanyLogo companyLogo = CompanyLogo.builder()
	    	        .name(file.getOriginalFilename())
	    	        .type(file.getContentType())
	    	        .logoData(ImageUtil.compressImage(file.getBytes()))
	    	        .build();
	    	    
	    	    // Set the CompanyLogo entity in the Company object
	    	    company.setLogo(companyLogo);
	    	    
	    	 // Save the Company object to your database
	    	    Company savedCompany = companyService.createCompany(company);
	    	    if (savedCompany != null) {
	    	        return ResponseEntity.status(HttpStatus.OK)
	    	        		//"Image and Company data uploaded successfully"
	    	            .body(savedCompany);
	    	    } else {
	    	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	    	        		// "Failed to upload image and Company data"
	    	            .body(null);
	    	    }
		}

// Get all data 
    @GetMapping("/getall")
    public ResponseEntity<List<Company>> getAllCompanies() {
        List<Company> companies = companyService.getAllCompanies();
        return ResponseEntity.ok(companies);
    }
    
    
    // Get company property details by Id 
    @GetMapping("/companiesById/{id}")
    public ResponseEntity<CompanyWithImage> getCompanyById(@PathVariable Long id) {
        Company company = companyService.getCompanyById(id);

        if (company != null) {
            CompanyLogo logo = company.getLogo();
            byte[] imageData = ImageUtil.decompressImage(logo.getLogoData());

            // Debug logging
            System.out.println("Company Name: " + company.getCompanyNm());
            System.out.println("Image Data Length: " + imageData.length);

            CompanyWithImage companyWithImage = new CompanyWithImage();
            companyWithImage.setCompany(company);
            companyWithImage.setImageData(imageData);

            return ResponseEntity.ok()
                .header("Message", "Company found with the given ID")
                .body(companyWithImage);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .header("Message", "No company found with the given ID")
                .build();
        }
    }

    //Update company detail by id
    @PutMapping("/company/{id}")
    public ResponseEntity<String> updateCompanyByName(
    		@PathVariable Long id, 
    		@RequestBody Company updatedCompany)
    {
    	Company  isUpdated = companyService.updateCompanyById(id, updatedCompany);
        
        if (isUpdated != null) {
            return ResponseEntity.ok()
                                 .header("Message", "Company updated successfully")
                                 .body("Company updated successfully");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                                 .header("Message", "Company not found or update failed")
                                 .body("Company not found or update failed");
        }
    }
    
    // Delete company with all details
    @DeleteMapping("/company/{id}")
    public ResponseEntity<Void> deleteCompany(@PathVariable Long id) {
        companyService.deleteCompany(id);
        return ResponseEntity.ok().build();
    }
}
