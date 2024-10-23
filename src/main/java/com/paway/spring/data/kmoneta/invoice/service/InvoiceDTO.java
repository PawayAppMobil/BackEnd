package com.paway.spring.data.kmoneta.invoice.service;

import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.model.ProductDTO;

import java.util.Date;
import java.util.List;
public class InvoiceDTO {

    private Date date;
    private Double amount;
    private String status;
    private List<String> productIds;  // Cambiado a lista de IDs de productos
    private String userId;
    private Date dueDate;
     // Para la imagen o PDF

    // Getters y Setters

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<String> getProductIds() {
        return productIds;
    }

    public void setProductIds(List<String> productIds) {
        this.productIds = productIds;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }


}