package com.paway.spring.data.kmoneta.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
public class Product {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String description;
    private double price;
    private String productName;
    private int stock;
    private byte[] image;  // Cambiado a byte[]
    private String providerId;

    public Product() {
    }

    public Product(String description, double price, String productName, int stock, byte[] image, String providerId) {
        this.description = description;
        this.price = price;
        this.productName = productName;
        this.stock = stock;
        this.image = image;  // Cambiado a byte[]
        this.providerId = providerId;
    }

    public String getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public byte[] getImage() {  // Nuevo getter
        return image;
    }

    public void setImage(byte[] image) {  // Nuevo setter
        this.image = image;
    }
    public String getProviderId() {
        return providerId;
    }

    @Override
    public String toString() {
        return "Product [id=" + id + ", description=" + description + ", price=" + price + ", productName=" + productName + ", stock=" + stock + "]";
    }
}
