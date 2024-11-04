
package com.paway.spring.data.kmoneta.inventory.repository;

import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.model.ProviderStock;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductNameContaining(String productName);
    List<Product> findByProviderId(String providerId);
    List<Product> findByUserId(String userId);
    @Aggregation(pipeline = {
            "{ $group: { _id: '$providerId', totalStock: { $sum: '$stock' } } }",
            "{ $project: { providerId: '$_id', totalStock: 1, _id: 0 } }"
    })
    List<ProviderStock> getTotalStockByProvider();
}