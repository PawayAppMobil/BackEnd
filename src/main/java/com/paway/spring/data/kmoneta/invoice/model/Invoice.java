package com.paway.spring.data.kmoneta.invoice.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Document(collection = "invoices")
public class Invoice {
    @Id
    private String id;
    private Date date;
    private Double amount;
    private String status;
    private List<InvoiceItem> items;
    private String transactionId;
    private String customerId;
    private Date dueDate;
}