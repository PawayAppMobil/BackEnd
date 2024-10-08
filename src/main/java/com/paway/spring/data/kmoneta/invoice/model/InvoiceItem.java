package com.paway.spring.data.kmoneta.invoice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class InvoiceItem {
    private String id;
    private String description;
    private Integer quantity;
    private Double unitPrice;
    private String productId;
}
