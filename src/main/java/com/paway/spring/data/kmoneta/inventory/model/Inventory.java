package com.paway.spring.data.kmoneta.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "inventories")
public class Inventory {

    @Id
    private String id;
    private List<String> productIds;
    private String customerId;

    public Inventory() {
    }

    public Inventory(String id, List<String> productIds, String customerId) {
        this.id = id;
        this.productIds = productIds;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<String> getProducts() {
        return productIds;
    }

    public void setProducts(List<String> productIds) {
        this.productIds = productIds;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", products=" + productIds + ", customerId=" + customerId + "]";
    }
}
