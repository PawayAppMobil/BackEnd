package com.paway.spring.data.kmoneta.inventory.repository;

import com.paway.spring.data.kmoneta.inventory.model.Inventory;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface InventoryRepository extends MongoRepository<Inventory, String> {
}
