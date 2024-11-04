package com.paway.spring.data.kmoneta.inventory.model;

public class ProviderStock {

    private String providerId;
    private int totalStock;
    private String name;

    public ProviderStock(String providerId, int totalStock, String name) {
        this.providerId = providerId;
        this.totalStock = totalStock;
        this.name = name;
    }

    // Getters and Setters
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}