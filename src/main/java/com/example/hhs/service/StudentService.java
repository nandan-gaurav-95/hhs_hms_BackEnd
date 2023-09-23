package com.example.hhs.service;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.Student;
import com.example.hhs.repository.StudentRepositiory;

@Service
public class StudentService {

    @Autowired
    private StudentRepositiory studentRepository;

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepository.findById(id);
    }

    public Student updateStudent(Long id, Student updatedStudent) {
        Optional<Student> existingStudentOptional = studentRepository.findById(id);

        if (existingStudentOptional.isPresent()) {
            Student existingStudent = existingStudentOptional.get();

            // Update the fields you want from the updatedStudent object
            existingStudent.setName(updatedStudent.getName());
            existingStudent.setFather_name(updatedStudent.getFather_name());
            existingStudent.setDateofbirth(updatedStudent.getDateofbirth());
            existingStudent.setGender(updatedStudent.getGender());
            existingStudent.setQualification(updatedStudent.getQualification());
            existingStudent.setTrade(updatedStudent.getTrade());
            existingStudent.setSession(updatedStudent.getSession());
            existingStudent.setMobile_no(updatedStudent.getMobile_no());
            existingStudent.setTotal_fees(updatedStudent.getTotal_fees());
            existingStudent.setAddress(updatedStudent.getAddress());

            // Save the updated student entity to the database
            return studentRepository.save(existingStudent);
        } else {
            // Student with the given ID not found
            return null;
        }
    }


    public void deleteStudent(Long id) {
        studentRepository.deleteById(id);
    }
}
