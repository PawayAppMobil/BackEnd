
package com.paway.spring.data.kmoneta.inventory.repository;

import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.model.ProviderStock;
import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Optional;

public interface ProductRepository extends MongoRepository<Product, String> {
    List<Product> findByProductNameContaining(String productName);
    List<Product> findByProviderId(String providerId);
    List<Product> findByUserId(String userId);
    @Aggregation(pipeline = {
            "{ $group: { _id: '$providerId', totalStock: { $sum: '$stock' } } }",
            "{ $lookup: { from: 'provider', localField: '_id', foreignField: '_id', as: 'provider' } }",
            "{ $unwind: '$provider' }",
            "{ $project: { providerId: '$_id', totalStock: 1, name: '$provider.name', _id: 0 } }"
    })
    List<ProviderStock> getTotalStockByProvider();

}