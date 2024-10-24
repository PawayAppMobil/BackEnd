package com.paway.spring.data.kmoneta.invoice.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.paway.spring.data.kmoneta.inventory.model.Product;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;


@Document(collection = "invoices")
public class Invoice {
    @Setter
    @Getter
    @Id
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;
    @Setter
    @Getter
    private LocalDate date;
    @Setter
    @Getter
    private Double amount;
    @Setter
    private String status;
    private List<Product> items;

    @Setter
    @Getter
    private String userId;
    @Setter
    private LocalDate dueDate;
    private byte[] document; // Para almacenar la imagen o PDF

    // Métodos getter y setter

    public String getStatus() {
        return status;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }


    public LocalDate getDueDate() {
        return dueDate;
    }

    public byte[] getDocument() {
        return document;
    }

    public void setDocument(byte[] document) {
        this.document = document;
    }
}