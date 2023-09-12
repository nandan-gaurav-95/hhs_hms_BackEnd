package com.example.hhs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.CompanyLogo;

@Repository
public interface CompanyLogoRepository  extends JpaRepository<CompanyLogo, Long>{
	
	Optional<CompanyLogo> findByName(String name);

}
