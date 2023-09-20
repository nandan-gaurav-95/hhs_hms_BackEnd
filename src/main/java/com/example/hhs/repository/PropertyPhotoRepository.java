package com.example.hhs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhs.model.PropertyPhoto;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {
	 List<PropertyPhoto> findByCompany_Id(Long company_id);
//	 PropertyPhoto findByCompanyCompanyNm(String name);
//	 PropertyPhoto findByName(String name);
//	 void deleteByCompanyId(Long companyId);
}