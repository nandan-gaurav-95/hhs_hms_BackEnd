package com.example.hhs.controller;

//Jay Shree Ram

import com.example.hhs.model.DayBook;
import com.example.hhs.service.DayBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class DayBookController {

	 @Autowired
    private  DayBookService dayBookService;

    // Create a new day book entry
    @PostMapping("/daybook")
    public ResponseEntity<DayBook> createDayBookEntry(@RequestBody DayBook dayBookEntry) {
    
        DayBook createdEntry = dayBookService.createDayBookEntry(dayBookEntry);
        if(createdEntry !=null) {
			 return ResponseEntity.status(HttpStatus.CREATED)
					.body(createdEntry);
		}else {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(null);	
		}
//        return ResponseEntity.ok(createdEntry);
    
    }

    // Retrieve all day book entries
    @GetMapping("/daybook")
    public ResponseEntity<List<DayBook>> getAllDayBookEntries() {
        List<DayBook> dayBookEntries = dayBookService.getAllDayBookEntries();
        return ResponseEntity.ok(dayBookEntries);
    }

    // Retrieve a specific day book entry by ID
    @GetMapping("/daybook/{id}")
    public ResponseEntity<DayBook> getDayBookEntryById(@PathVariable Long id) {
        DayBook dayBookEntry = dayBookService.getDayBookEntryById(id);
        if (dayBookEntry != null) {
            return ResponseEntity.ok(dayBookEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Update an existing day book entry
    @PutMapping("/daybook/{id}")
    public ResponseEntity<DayBook> updateDayBookEntry(@PathVariable Long id, @RequestBody DayBook updatedEntry) {
        DayBook updatedDayBookEntry = dayBookService.updateDayBookEntry(id, updatedEntry);
        if (updatedDayBookEntry != null) {
            return ResponseEntity.ok(updatedDayBookEntry);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Delete a day book entry by ID
    @DeleteMapping("/daybook/{id}")
    public ResponseEntity<Void> deleteDayBookEntry(@PathVariable Long id) {
        dayBookService.deleteDayBookEntry(id);
        return ResponseEntity.noContent().build();
    }

    // Add other controller methods as needed
}
