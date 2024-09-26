package com.paway.spring.data.kmoneta.transaction.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private int amount;
    private Date date;
    private String details;
    private String invoiceId;
    private String transactionId;
    private String type;

    public Transaction() {
    }

    public Transaction(int amount, Date date, String details, String invoiceId, String transactionId, String type) {
        this.amount = amount;
        this.date = date;
        this.details = details;
        this.invoiceId = invoiceId;
        this.transactionId = transactionId;
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getInvoiceId() {
        return invoiceId;
    }

    public void setInvoiceId(String invoiceId) {
        this.invoiceId = invoiceId;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString() {
        return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", details=" + details + ", invoiceId=" + invoiceId + ", transactionId=" + transactionId + ", type=" + type + "]";
    }
}
