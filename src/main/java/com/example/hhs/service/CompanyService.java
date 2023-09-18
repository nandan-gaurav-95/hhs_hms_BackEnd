package com.example.hhs.service;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.model.Company;
import com.example.hhs.model.CompanyLogo;
import com.example.hhs.model.CompanyWithImage;
import com.example.hhs.model.PropertyPhoto;
import com.example.hhs.repository.CompanyLogoRepository;
import com.example.hhs.repository.CompanyRepository;
import com.example.hhs.repository.PropertyPhotoRepository;
import com.example.hhs.util.ImageUtil;


@Service
public class CompanyService {
	
	@Autowired
	private CompanyRepository companyRepo;
	@Autowired
	private CompanyLogoRepository companyLogoRepo;

	
	
	 public Company createCompany(Company company) {
	        return companyRepo.save(company);
	    }
	 
	 //For photo uploading 
	 public Company findById(Long id) {
	        // You can implement this method using your repository
	        Optional<Company> optionalCompany = companyRepo.findById(id);
	        return optionalCompany.orElse(null);
	    }
	 	
	

//	    public Company getCompanyById(Long id) {
//	        return companyRepo.findById(id).orElse(null);
//	    }
	 public CompanyWithImage getCompanyWithLogoById(Long id) {
		    Company company = companyRepo.findById(id).orElse(null);

		    if (company != null) {
		        CompanyLogo logo = company.getLogo();
		        byte[] imageData = ImageUtil.decompressImage(logo.getLogoData());

		        CompanyWithImage companyWithImage = new CompanyWithImage();
		        companyWithImage.setId(company.getId());
		        companyWithImage.setImageData(imageData);
		        companyWithImage.setCompanyNm(company.getCompanyNm());
		        companyWithImage.setEmail(company.getEmail());
		        companyWithImage.setGstNo(company.getGstNo());
		        companyWithImage.setMobNo(company.getMobNo());
		        companyWithImage.setVillageNm(company.getVillageNm());
		        companyWithImage.setCtsNo(company.getCtsNo());
		        companyWithImage.setExtentAcres(company.getExtentAcres());
		        companyWithImage.setBoundries(company.getBoundries());
		        companyWithImage.setTaxAmt(company.getTaxAmt());
		        companyWithImage.setAccountNm(company.getAccountNm());
		        companyWithImage.setAnnualIncome(company.getAnnualIncome());
		        companyWithImage.setRegistrationNo(company.getRegistrationNo());
		        companyWithImage.setAddress(company.getAddress());
		        companyWithImage.setGazzetNo(company.getGazzetNo());

		        return companyWithImage;
		    } else {
		        return null; // Or handle the case where the company is not found
		    }
		}

	    public List<Company> getAllCompanies() {
	        return companyRepo.findAll();
	    }
	    
	    public Company updateCompanyById(Long id, Company updatedCompany) {
	    	 Optional<Company> existingCompaniesOptional = companyRepo.findById(id);
	         
	        if (!existingCompaniesOptional.isEmpty()) {
	            Company existingCompany = existingCompaniesOptional.get(); 
	            existingCompany.setVillageNm(updatedCompany.getVillageNm());
	            existingCompany.setCtsNo(updatedCompany.getCtsNo());
	            existingCompany.setExtentAcres(updatedCompany.getExtentAcres());
	            existingCompany.setBoundries(updatedCompany.getBoundries());
	            existingCompany.setTaxAmt(updatedCompany.getTaxAmt());
	            existingCompany.setAccountNm(updatedCompany.getAccountNm());
	            existingCompany.setAnnualIncome(updatedCompany.getAnnualIncome());
	            existingCompany.setAddress(updatedCompany.getAddress());
	            existingCompany.setRegistrationNo(updatedCompany.getRegistrationNo());
	            existingCompany.setGazzetNo(updatedCompany.getGazzetNo());
	           
	            
	            return companyRepo.save(existingCompany);
	        }
	        return null;
	    }
	    
	    


	    public void deleteCompany(Long id) {
	    	companyRepo.deleteById(id);
	    }

	    
	    
		  public CompanyLogo getCompanyByName(String companyName) {
		        return companyLogoRepo.findByName(companyName).orElse(null);
		    }

}
