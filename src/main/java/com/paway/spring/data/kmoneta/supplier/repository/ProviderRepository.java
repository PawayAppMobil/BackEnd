package com.paway.spring.data.kmoneta.supplier.repository;

import com.paway.spring.data.kmoneta.inventory.model.ProviderProductCount;
import com.paway.spring.data.kmoneta.supplier.model.Provider;

import org.springframework.data.mongodb.repository.Aggregation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;


import java.util.List;

public interface ProviderRepository extends MongoRepository<Provider, String> {

    List<Provider> findByName(String name);


    List<Provider> findByAddress_City(String city);


    List<Provider> findByAddress_State(String state);

}
