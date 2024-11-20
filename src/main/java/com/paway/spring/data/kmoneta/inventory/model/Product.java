package com.paway.spring.data.kmoneta.inventory.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "products")
@Getter
@Setter
public class Product {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    @JsonProperty(access = JsonProperty.Access.READ_ONLY)
    private String id;
    private String userId;
    private String description;
    private double price;
    private String productName;
    private int stock;
    private byte[] image;  // Cambiado a byte[]
    private String providerId;

    public Product() {
    }

    public Product(String description, String userId,double price, String productName, int stock, byte[] image, String providerId) {
        this.userId = userId;
        this.description = description;
        this.price = price;
        this.productName = productName;
        this.stock = stock;
        this.image = image;  // Cambiado a byte[]
        this.providerId = providerId;
    }


    @Override
    public String toString() {
        return "Product [id=" + id + ", description=" + description + ", price=" + price + ", productName=" + productName + ", stock=" + stock + "]";
    }
}
