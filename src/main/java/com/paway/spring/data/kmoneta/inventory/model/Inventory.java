package com.paway.spring.data.kmoneta.inventory.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Document(collection = "inventories")
public class Inventory {

    @Id
    private String id;
    private List<Product> products;
    private String customerId;

    public Inventory() {
    }

    public Inventory(String id, List<Product> products, String customerId) {
        this.id = id;
        this.products = products;
        this.customerId = customerId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProducts(List<Product> products) {
        this.products = products;
    }

    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    @Override
    public String toString() {
        return "Inventory [id=" + id + ", products=" + products + ", customerId=" + customerId + "]";
    }
}