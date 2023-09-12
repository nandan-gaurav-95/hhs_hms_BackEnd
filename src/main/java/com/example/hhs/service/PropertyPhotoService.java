package com.example.hhs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.hhs.model.Company;
import com.example.hhs.model.PropertyPhoto;
import com.example.hhs.repository.CompanyRepository;
import com.example.hhs.repository.PropertyPhotoRepository;

import java.io.IOException;
import java.util.Optional;

@Service
public class PropertyPhotoService {
    @Autowired
    private CompanyRepository companyRepo;
    @Autowired
    private PropertyPhotoRepository propertyPhotoRepo;
    
//    public Company updateCompanyPhotoById(Long id, MultipartFile[] files) {
//        // First, check if a company with the given ID exists
//        Optional<Company> optionalCompany = companyRepo.findById(id);
//
//        if (optionalCompany.isPresent()) {
//            Company existingCompany = optionalCompany.get();
//
//            try {
//                for (MultipartFile file : files) {
//                    PropertyPhoto propertyPhoto = existingCompany.getPropertyPhoto();
//
//                    if (propertyPhoto == null) {
//                        // Create a new PropertyPhoto object if it doesn't exist
//                        propertyPhoto = new PropertyPhoto();
//                    }
//
//                    // Set properties of the PropertyPhoto
//                    propertyPhoto.setName(file.getOriginalFilename());
//                    propertyPhoto.setType(file.getContentType());
//                    propertyPhoto.setPhotoData(file.getBytes());
//
//                    // Set the PropertyPhoto in the Company entity
//                    existingCompany.setPropertyPhoto(propertyPhoto);
//
//                    // Save the PropertyPhoto entity
//                    propertyPhotoRepo.save(propertyPhoto);
//                }
//
//                // Save the updated Company entity
//                companyRepo.save(existingCompany);
//
//                return existingCompany;
//            } catch (IOException e) {
//                // Handle the IO exception or log the error as needed
//                e.printStackTrace();
//                return null; // Return null to indicate the update failed
//            }
//        } else {
//            return null; // Company with the given ID not found
//        }
//    }

    
    
    public Company updateCompanyPhotoById(Long id, MultipartFile file) {
        // First, check if a company with the given ID exists
        Optional<Company> optionalCompany = companyRepo.findById(id);

        if (optionalCompany.isPresent()) {
            Company existingCompany = optionalCompany.get();

            try {
                PropertyPhoto propertyPhoto = existingCompany.getPropertyPhoto();

                if (propertyPhoto == null) {
                    // Create a new PropertyPhoto object if it doesn't exist
                    propertyPhoto = new PropertyPhoto();
                }

                // Set properties of the PropertyPhoto
                propertyPhoto.setName(file.getOriginalFilename());
                propertyPhoto.setType(file.getContentType());
                propertyPhoto.setPhotoData(file.getBytes());

                // Set the PropertyPhoto in the Company entity
                existingCompany.setPropertyPhoto(propertyPhoto);

                // Save the PropertyPhoto entity
                propertyPhotoRepo.save(propertyPhoto);

                // Save the updated Company entity
                companyRepo.save(existingCompany);

                return existingCompany;
            } catch (IOException e) {
                // Handle the IO exception or log the error as needed
                e.printStackTrace();
                return null; // Return null to indicate the update failed
            }
        } else {
            return null; // Company with the given ID not found
        }
    }
    
    
}


