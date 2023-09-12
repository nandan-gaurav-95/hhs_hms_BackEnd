package com.example.hhs.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.Company;

@Repository
public interface CompanyRepository  extends JpaRepository<Company, Long>{

	 // Define a custom query to fetch a company by name
//    @Query("SELECT c FROM Company c WHERE c.companyNm = :name")
//    Company findByCompanyNm(String name);
//	 List<Company> findByCompanyNm(String name);

}
