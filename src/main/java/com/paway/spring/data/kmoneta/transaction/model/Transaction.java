package com.paway.spring.data.kmoneta.transaction.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
@Getter
@Setter
@Document(collection = "transactions")
public class Transaction {

    @Id
    private String id;
    private int amount;
    private Date date;
    private String details;
    private String invoiceId;

    private boolean isIncome;
    private String userId;

    public Transaction() {
    }

    public Transaction(int amount, Date date, String details, String invoiceId, boolean isIncome) {
        this.amount = amount;
        this.date = date;
        this.details = details;
        this.invoiceId = invoiceId;
        this.isIncome = isIncome;
    }

    public String getType() {
        return isIncome ? "Income" : "Expense";
    }
    @Override
    public String toString() {
        return "Transaction [id=" + id + ", amount=" + amount + ", date=" + date + ", details=" + details + ", invoiceId=" + invoiceId + ", " + (isIncome ? "Es ingreso" : "Es gasto") + "]";
    }
}
