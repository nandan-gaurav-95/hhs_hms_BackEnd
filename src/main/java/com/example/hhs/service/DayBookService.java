package com.example.hhs.service;

//Jay Shree Ram
import com.example.hhs.model.DayBook;
import com.example.hhs.repository.DayBookRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class DayBookService {

	@Autowired
    private  DayBookRepository dayBookRepository; // Assuming you have a DayBookRepository

   

    // Create a new day book entry
    public DayBook createDayBookEntry(DayBook dayBookEntry) {
        return dayBookRepository.save(dayBookEntry);
    }

    // Retrieve all day book entries
    public List<DayBook> getAllDayBookEntries() {
        return dayBookRepository.findAll();
    }

    // Retrieve a specific day book entry by ID
    public DayBook getDayBookEntryById(Long id) {
        return dayBookRepository.findById(id).orElse(null);
    }

    // Update an existing day book entry
    public DayBook updateDayBookEntry(Long id, DayBook updatedEntry) {
        if (dayBookRepository.existsById(id)) {
            updatedEntry.setId(id); // Set the ID of the updated entry
            return dayBookRepository.save(updatedEntry);
        }
        return null; // Entry not found
    }

    // Delete a day book entry by ID
    public void deleteDayBookEntry(Long id) {
        dayBookRepository.deleteById(id);
    }

    // Other business logic methods can be added here

}
