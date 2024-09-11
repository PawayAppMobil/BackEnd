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
    private String customerId;
    private Date date;
    private Date dueDate;
    private String invoiceNumber;
    private List<InvoiceItem> items;
    private Date paidDate;
    private String status;
    private String templateId;
    private Double totalAmount;
    private String transactionId;
}

