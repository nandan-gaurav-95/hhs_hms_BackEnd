package com.example.hhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.Employee;
import com.example.hhs.repository.EmployeeRepository;

@Service
public class EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepo;
	
	  // Create a new employee
    public Employee createEmployee(Employee employee) {
        return employeeRepo.save(employee);
    }
	
 // Retrieve all day book entries
    public List<Employee> getAllEmployee() {
        return employeeRepo.findAll();
    }

    // Retrieve a specific day book entry by ID
    
    public Optional<Employee> getEmployeeById(Long id){
		return this.employeeRepo.findById(id);
	}

    // Update an existing day book entry
    public Employee updateEmployee(Long id, Employee updatedEmployee) {
    	 Optional<Employee> existingEmployee = employeeRepo.findById(id);
    	
    	
        if (!existingEmployee.isEmpty()) {
        	Employee  existingEmp = existingEmployee.get();
        	
        	existingEmp.setEmpName(updatedEmployee.getEmpName());
        	existingEmp.setDateOfHiring(updatedEmployee.getDateOfHiring());
        	existingEmp.setDateOfLeaving(updatedEmployee.getDateOfLeaving());
        	existingEmp.setAddress(updatedEmployee.getAddress());
        	existingEmp.setContactNumber(updatedEmployee.getContactNumber());
        	existingEmp.setSalary(updatedEmployee.getSalary());
        	existingEmp.setPfContribution(updatedEmployee.getPfContribution());
        	existingEmp.setLoanAmount(updatedEmployee.getLoanAmount());
        	
            return employeeRepo.save(existingEmp);
        }
        return null; // Entry not found
    }

    // Delete a day book entry by ID
    public void deleteEmployee(Long id) {
    	employeeRepo.deleteById(id);
    }
	
}
