package com.paway.spring.data.kmoneta.reports.model;



import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Setter
@Getter
@Document(collection = "expenses")
public class Expense {

    @Id
    private String id;
    private String userId;
    private String categoryId;
    private double amount;
    private String description;
    private Date date;
}
