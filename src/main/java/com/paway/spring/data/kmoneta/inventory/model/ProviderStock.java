package com.paway.spring.data.kmoneta.inventory.model;

import org.springframework.data.mongodb.core.mapping.Field;

public class ProviderStock {

    private String providerId;
    private int totalStock;

    public ProviderStock(String providerId, int totalStock) {
        this.providerId = providerId;
        this.totalStock = totalStock;
    }

    // Getters y Setters
    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public int getTotalStock() {
        return totalStock;
    }

    public void setTotalStock(int totalStock) {
        this.totalStock = totalStock;
    }
}
