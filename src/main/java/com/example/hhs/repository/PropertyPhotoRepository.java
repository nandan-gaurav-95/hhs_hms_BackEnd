package com.example.hhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.hhs.model.PropertyPhoto;

public interface PropertyPhotoRepository extends JpaRepository<PropertyPhoto, Long> {

//	 PropertyPhoto findByCompanyCompanyNm(String name);
//	 PropertyPhoto findByName(String name);
}