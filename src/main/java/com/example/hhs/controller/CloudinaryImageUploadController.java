package com.example.hhs.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.service.CloudinaryImageService;

@RestController
@RequestMapping("/api")
public class CloudinaryImageUploadController {
	@Autowired
	private CloudinaryImageService cloudinaryImgService;
	
	
	@PostMapping("/cloud-upload")
	public ResponseEntity<Map> uploadImage(@RequestParam("image")MultipartFile file  ){
		
		Map data = this.cloudinaryImgService.upload(file);
		return new ResponseEntity<>(data, HttpStatus.OK);
		
	}

}
