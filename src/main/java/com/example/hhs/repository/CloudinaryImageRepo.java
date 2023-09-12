package com.example.hhs.repository;

import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

public interface CloudinaryImageRepo {
	
	

	public Map upload(MultipartFile file);

}
