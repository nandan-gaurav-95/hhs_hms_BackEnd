package com.example.hhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.Student;

@Repository
public interface StudentRepositiory extends JpaRepository<Student, Long>{

}
