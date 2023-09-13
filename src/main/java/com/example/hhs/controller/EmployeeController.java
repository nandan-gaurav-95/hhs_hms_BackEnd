package com.example.hhs.controller;

import java.sql.Date;
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

import com.example.hhs.model.Employee;
import com.example.hhs.service.EmployeeService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class EmployeeController {
	
	@Autowired
	private EmployeeService empService;
	
//	Create Employee
	@PostMapping("/employee")
	public ResponseEntity<Employee> createEmloyee(@RequestBody Employee emp){
		
		emp.setDateOfHiring(Date.valueOf(((Date) emp.getDateOfHiring()).toLocalDate()));
		emp.setDateOfLeaving(Date.valueOf(((Date) emp.getDateOfLeaving()).toLocalDate()));
		
		Employee empCreate=empService.createEmployee(emp);
		if(empCreate !=null) {
			 return ResponseEntity.status(HttpStatus.CREATED)
					.body(empCreate);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);	
		}
	}
	
//	Get All Employee
	@GetMapping("/employee")
	public ResponseEntity<List<Employee>>getAllEmployee(){
		List<Employee>employee = empService.getAllEmployee();
		return new ResponseEntity<>(employee,HttpStatus.OK);
	}
	
//	Get employee by Id
	  @GetMapping("/employee/{id}")
	    public ResponseEntity<Employee> getTenantById(@PathVariable Long id) {
	        Optional<Employee> employee = empService.getEmployeeById(id);
	        return employee.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	  
	  
//		Update employee by Id
	  @PutMapping("/employee/{id}")
	    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id, @RequestBody Employee updatedEmployee) {
		  Employee employee = empService.updateEmployee(id, updatedEmployee);
	        return new ResponseEntity<>(employee, HttpStatus.OK);
	    }
	  
//		Delete employee by Id
	    @DeleteMapping("/employee/{id}")
	    public ResponseEntity<Void> deleteEmployee(@PathVariable Long id) {
	    	empService.deleteEmployee(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
