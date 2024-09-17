
package com.paway.spring.data.kmoneta.inventory.repository;

import com.paway.spring.data.kmoneta.inventory.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductNameContaining(String productName);
}