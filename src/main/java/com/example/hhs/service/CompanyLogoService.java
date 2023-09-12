package com.example.hhs.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.model.CompanyLogo;
import com.example.hhs.repository.CompanyLogoRepository;
import com.example.hhs.util.ImageUtil;

@Service
public class CompanyLogoService {
	
	@Autowired
	private CompanyLogoRepository companyLogoRepo;

//	  public CompanyLogo saveCompanyLogo(CompanyLogo companyLogo) {
//	        return companyLogoRepo.save(companyLogo);
//	    }
	
//	public CompanyLogo createAndSaveCompanyLogo(MultipartFile file) throws IOException {
//        // Handle the image upload and create a CompanyLogo entity
//        CompanyLogo companyLogo = new CompanyLogo();
//        companyLogo.setLogoData(file.getBytes());
//        companyLogo.setType(file.getContentType());
//        companyLogo.setName(file.getName());
//
//        // Save the CompanyLogo entity
//        return companyLogoRepo.save(companyLogo);
//    }
//	
//	
//	 public CompanyLogo getCompanyLogoById(Long id) {
//	        return companyLogoRepo.findById(id).orElse(null);
//	    }
	 
	 
	 
//	 public String uploadImage(MultipartFile file) throws IOException {
//
//		 CompanyLogo LogoData = companyLogoRepo.save(CompanyLogo.builder()
//	                .name(file.getOriginalFilename())
//	                .type(file.getContentType())
//	                .logoData(ImageUtil.compressImage(file.getBytes())).build());
//	        if (LogoData != null) {
//	            return "file uploaded successfully : " + file.getOriginalFilename();
//	        }
//	        return null;
//	    }
	
	public CompanyLogo uploadImage(MultipartFile file) throws IOException {
	    // Handle the image upload and create a CompanyLogo entity
	    CompanyLogo companyLogo = CompanyLogo.builder()
	        .name(file.getOriginalFilename())
	        .type(file.getContentType())
	        .logoData(ImageUtil.compressImage(file.getBytes()))
	        .build();

	    // Save the CompanyLogo entity
	    return companyLogoRepo.save(companyLogo);
	}
	 
	 
	 public byte[] downloadImage(String fileName){
	        Optional<CompanyLogo> dbLogoData = companyLogoRepo.findByName(fileName);
	        byte[] images=ImageUtil.decompressImage(dbLogoData.get().getLogoData());
	        return images;
	    }
	
}
