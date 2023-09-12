package com.example.hhs.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.hhs.model.Tenant;
import com.example.hhs.repository.TenantRepository;

@Service
public class TenantService {
	
	@Autowired
	private TenantRepository tenantRepo;
	
	
	public List <Tenant> getAllTenants(){
		return this.tenantRepo.findAll();
	}
	
	public Optional<Tenant> getTenantById(Long id){
		return this.tenantRepo.findById(id);
	}
	
	public Tenant createTenant(Tenant tenant) {
		return this.tenantRepo.save(tenant);
	}
	
	public Tenant updateTenant (Long id, Tenant updateTenant) {
		
		Optional<Tenant> existingTenantOptional = tenantRepo.findById(id);
		
		if(existingTenantOptional.isPresent()) {
			
			Tenant existingTenant = existingTenantOptional.get();
			existingTenant.setTenantName(updateTenant.getTenantName());
			existingTenant.setAddress(updateTenant.getAddress());
			existingTenant.setContactNum(updateTenant.getContactNum());
			existingTenant.setAllocatedShop(updateTenant.getAllocatedShop());
			existingTenant.setRentCollected(updateTenant.getRentCollected());
			existingTenant.setRentDue(updateTenant.getRentDue());
			existingTenant.setSecurityDeposit(updateTenant.getSecurityDeposit());
			existingTenant.setElectricityDue(updateTenant.getElectricityDue());
//			existingTenant.setAllocatedShop(updateTenant.getAllocatedShop());
			
			return this.tenantRepo.save(existingTenant);
			
		} else {
            throw new RuntimeException("Tenant not found with id: " + id);
        }
		
	}
	
	 public void deleteTenant(Long id) {
		 tenantRepo.deleteById(id);
	    }

}
