package com.paway.spring.data.kmoneta.invoice.service;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paway.spring.data.kmoneta.invoice.model.InvoiceItem;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
public class InvoiceDTO {
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate date;
    private Double amount;
    private String status;
    private List<InvoiceItem> items;

    private String userId;

     // Para la imagen o PDF

    // Getters y Setters

    public LocalDate  getDate() {
        return date;
    }

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




}