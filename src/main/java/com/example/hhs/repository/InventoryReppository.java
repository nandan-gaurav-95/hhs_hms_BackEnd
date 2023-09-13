package com.example.hhs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.hhs.model.InventoryItem;



@Repository
public interface InventoryReppository extends JpaRepository<InventoryItem, Long> {

}
