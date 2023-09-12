package com.example.hhs.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.cloudinary.Cloudinary;

@Configuration
public class ProjectConfig {
	
	@Bean
	public Cloudinary getCloudinary() {
		Map config = new HashMap();
		config.put("cloud_name","dajmtt4iv");
		config.put("api_key","475451241414674");
		config.put("api_secret","zble1fzZmastEC2W_EXp6cMV6Eo");
		config.put("secure",true);
		return new Cloudinary (config);
	}
	
	
	

}
