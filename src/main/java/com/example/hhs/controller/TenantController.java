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

import com.example.hhs.model.Tenant;
import com.example.hhs.service.TenantService;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class TenantController {
	
	@Autowired
	private TenantService tenantService;
	
	@PostMapping("/tenants")
	public ResponseEntity<Tenant>createTenant(@RequestBody Tenant tenant){
		  // Set the date with only the date portion
		tenant.setDate(Date.valueOf(tenant.getDate().toLocalDate()));
		Tenant createTenant = tenantService.createTenant(tenant);
		
		if(createTenant !=null) {
			 return ResponseEntity.status(HttpStatus.CREATED)
					.body(createTenant);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);	
		}
//		return new ResponseEntity<>(createTenant, HttpStatus.CREATED);
	}
	
	@GetMapping("/tenants")
	public ResponseEntity<List<Tenant>>getAllTenants(){
		List<Tenant>tenants = tenantService.getAllTenants();
		return new ResponseEntity<>(tenants,HttpStatus.OK);
	}
	
	  @GetMapping("/tenants/{id}")
	    public ResponseEntity<Tenant> getTenantById(@PathVariable Long id) {
	        Optional<Tenant> tenant = tenantService.getTenantById(id);
	        return tenant.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
	                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
	    }
	  
	  @PutMapping("/tenants/{id}")
	    public ResponseEntity<Tenant> updateTenant(@PathVariable Long id, @RequestBody Tenant updatedTenant) {
	        Tenant tenant = tenantService.updateTenant(id, updatedTenant);
	        return new ResponseEntity<>(tenant, HttpStatus.OK);
	    }
	  

	    @DeleteMapping("/tenants/{id}")
	    public ResponseEntity<Void> deleteTenant(@PathVariable Long id) {
	        tenantService.deleteTenant(id);
	        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	    }

}
