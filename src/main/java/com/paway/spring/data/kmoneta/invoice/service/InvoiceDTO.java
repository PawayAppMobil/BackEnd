package com.paway.spring.data.kmoneta.invoice.service;

import com.paway.spring.data.kmoneta.invoice.model.InvoiceItem;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;
public class InvoiceDTO {
    private Date date;
    private Double amount;
    private String status;
    private List<InvoiceItem> items;
    private String transactionId;
    private String userId;
    private Date dueDate;
    private MultipartFile document; // Para la imagen o PDF

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

    public List<InvoiceItem> getItems() {
        return items;
    }

    public void setItems(List<InvoiceItem> items) {
        this.items = items;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
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

    public MultipartFile getDocument() {
        return document;
    }

    public void setDocument(MultipartFile document) {
        this.document = document;
    }
}