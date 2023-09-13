package com.example.hhs.service;

import com.example.hhs.model.InventoryItem;
import com.example.hhs.repository.InventoryReppository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

	   @Autowired
    private  InventoryReppository inventoryRepository;


    public List<InventoryItem> getAllInventoryItems() {
        return inventoryRepository.findAll();
    }

    public InventoryItem getInventoryItemById(Long id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    public InventoryItem createInventoryItem(InventoryItem item) {
        return inventoryRepository.save(item);
    }

    public InventoryItem updateInventory(Long id, InventoryItem updated) {
        if (inventoryRepository.existsById(id)) {
            updated.setId(id);
			
            // Set the ID of the updated item
            return inventoryRepository.save(updated);
        }
        return null; // Item not found
    }

    public void deleteInventoryItem(Long id) {
        inventoryRepository.deleteById(id);
    }

	public InventoryItem updateInventoryItem(Long id, InventoryItem updatedItem) {
		// TODO Auto-generated method stub
		return null;
	}
}