package com.paway.spring.data.kmoneta.inventory.controller;

import com.paway.spring.data.kmoneta.inventory.model.Inventory;
import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.repository.InventoryRepository;
import com.paway.spring.data.kmoneta.inventory.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/inventories")
public class InventoryController {

    @Autowired
    private InventoryRepository inventoryRepository;

    @Autowired
    private ProductRepository productRepository;

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

    @PostMapping("/{inventoryId}/add-product/{productId}")
    public Inventory addProductToInventory(@PathVariable String inventoryId, @PathVariable String productId) {
        Optional<Inventory> inventoryOpt = inventoryRepository.findById(inventoryId);
        Optional<Product> productOpt = productRepository.findById(productId);

        if (inventoryOpt.isPresent() && productOpt.isPresent()) {
            Inventory inventory = inventoryOpt.get();
            Product product = productOpt.get();
            inventory.getProducts().add(product);
            return inventoryRepository.save(inventory);
        } else {
            throw new RuntimeException("Inventory or Product not found");
        }
    }
}
