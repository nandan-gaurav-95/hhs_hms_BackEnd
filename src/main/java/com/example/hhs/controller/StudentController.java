package com.example.hhs.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.hhs.model.Student;
import com.example.hhs.service.StudentService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class StudentController {
	 @Autowired
	    private StudentService studentService;

	    // Create Student
	    @PostMapping("/students")
	    public ResponseEntity<Student> createStudent(@RequestBody Student student) {
	        Student createdStudent = studentService.createStudent(student);

	        if (createdStudent != null) {
	            return ResponseEntity.status(HttpStatus.CREATED)
	                    .body(createdStudent);
	        } else {
	            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
	                    .body(null);
	        }
	    }

	    // Get All students
	    @GetMapping("/students")
	    public ResponseEntity<List<Student>> getAllStudents() {
	        List<Student> students = studentService.getAllStudents();
	        return new ResponseEntity<>(students, HttpStatus.OK);
	    }

	    // Get student by Id
	    @GetMapping("/students/{id}")
	    public ResponseEntity<Student> getStudentById(@PathVariable Long id) {
	        Optional<Student> student = studentService.getStudentById(id);
	        return student.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }

	    // Update student by Id
	    @PutMapping("/students/{id}")
	    public ResponseEntity<Student> updateStudent(@PathVariable Long id, @RequestBody Student updatedStudent) {
	        Student student = studentService.updateStudent(id, updatedStudent);
	        return new ResponseEntity<>(student, HttpStatus.OK);
	    }

	    // Delete student by Id
	    @DeleteMapping("/students/{id}")
	    public ResponseEntity<Void> deleteStudent(@PathVariable Long id) {
	        studentService.deleteStudent(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }
	}
