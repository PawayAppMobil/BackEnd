package com.paway.spring.data.kmoneta.invoice.service;

import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.model.ProductDTO;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
public class InvoiceDTO {

    private LocalDate date;
    private Double amount;
    private String status;
    private List<String> productIds;  // Cambiado a lista de IDs de productos
    private String userId;
    private LocalDate dueDate;
    // Para la imagen o PDF

    // Getters y Setters

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
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

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }


}