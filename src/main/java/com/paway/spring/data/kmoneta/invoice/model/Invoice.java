package com.paway.spring.data.kmoneta.invoice.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;


@Document(collection = "invoices")
public class Invoice {
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    private LocalDate date;
    private Double amount;
    private String status;
    private List<InvoiceItem> items;

    private String userId;

    private byte[] document; // Para almacenar la imagen o PDF

    // Métodos getter y setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    @JsonFormat(pattern = "yyyy-MM-dd ")
    public LocalDate  getDate() {
        return date;
    }
    @JsonFormat(pattern = "yyyy-MM-dd ")
    public void setDate(LocalDate  date) {
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



    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }



    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}