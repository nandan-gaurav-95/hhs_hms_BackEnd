package com.example.hhs.controller;




import com.example.hhs.model.InventoryItem;
import com.example.hhs.service.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class InventoryController {

	 @Autowired
    private  InventoryService inventoryService;

    // Create a new inventory item
    @PostMapping("/inventory")
    public ResponseEntity<InventoryItem> createInventoryItem(@RequestBody InventoryItem item) {
    	InventoryItem createdItem = inventoryService.createInventoryItem(item);
    	if(createdItem !=null) {
			 return ResponseEntity.status(HttpStatus.CREATED)
					.body(createdItem);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);	
		}
    	
//    	return ResponseEntity.ok(createdItem);
    }

    // Retrieve all inventory items
    @GetMapping("/inventory")
    public ResponseEntity<List<InventoryItem>> getAllInventoryItems() {
        List<InventoryItem> inventoryItems = inventoryService.getAllInventoryItems();
        return ResponseEntity.ok(inventoryItems);
    }

    // Retrieve a specific inventory item by ID
    @GetMapping("/inventory/{id}")
    public ResponseEntity<InventoryItem> getInventoryItemById(@PathVariable Long id) {
    	InventoryItem inventoryItem = inventoryService.getInventoryItemById(id);
        if (inventoryItem != null) {
            return ResponseEntity.ok(inventoryItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing inventory item
    @PutMapping("/inventory/{id}")
    public ResponseEntity<InventoryItem> updateInventoryItem(@PathVariable Long id, @RequestBody InventoryItem updatedItem) {
    	InventoryItem updatedInventoryItem = inventoryService.updateInventory(id, updatedItem);
        if (updatedInventoryItem != null) {
            return ResponseEntity.ok(updatedInventoryItem);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete an inventory item by ID
    @DeleteMapping("/inventory/{id}")
    public ResponseEntity<Void> deleteInventoryItem(@PathVariable Long id) {
        inventoryService.deleteInventoryItem(id);
        return ResponseEntity.noContent().build();
    }
    
    // Add other controller methods as needed
}
