package com.paway.spring.data.kmoneta.inventory.model;

public class ProviderProductCount {
    private String providerId;
    private long productCount;

    public ProviderProductCount(String providerId, long productCount) {
        this.providerId = providerId;
        this.productCount = productCount;
    }

    public String getProviderId() {
        return providerId;
    }

    public void setProviderId(String providerId) {
        this.providerId = providerId;
    }

    public long getProductCount() {
        return productCount;
    }

    public void setProductCount(long productCount) {
        this.productCount = productCount;
    }
}