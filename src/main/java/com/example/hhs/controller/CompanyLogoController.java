package com.example.hhs.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhs.service.CompanyLogoService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class CompanyLogoController {
	
	  @Autowired
	    private CompanyLogoService companyLogoService;
	  
	  
//	  get logo by image name 
		@GetMapping("/imagenm/{fileName}")
		public ResponseEntity<?> downloadImage(@PathVariable String fileName){
			byte[] imageData=companyLogoService.downloadImage(fileName);
			return ResponseEntity.status(HttpStatus.OK)
					.contentType(MediaType.valueOf("image/png"))
					.body(imageData);
		}
		
		
	
	    


}
