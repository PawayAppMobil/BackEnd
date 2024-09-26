package com.paway.spring.data.kmoneta.inventory.controller;

import com.paway.spring.data.kmoneta.inventory.model.Inventory;
import com.paway.spring.data.kmoneta.inventory.repository.InventoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/inventories")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @GetMapping
    public List<Inventory> getAllInventories() {
        return inventoryRepository.findAll();
    }

    @GetMapping("/{id}")
    public Inventory getInventoryById(@PathVariable String id) {
        return inventoryRepository.findById(id).orElse(null);
    }

    @PostMapping
    public Inventory createInventory(@RequestBody Inventory inventory) {
        return inventoryRepository.save(inventory);
    }

    @PutMapping("/{id}")
    public Inventory updateInventory(@PathVariable String id, @RequestBody Inventory inventory) {
        inventory.setId(id);
        return inventoryRepository.save(inventory);
    }

    @DeleteMapping("/{id}")
    public void deleteInventory(@PathVariable String id) {
        inventoryRepository.deleteById(id);
    }
}
