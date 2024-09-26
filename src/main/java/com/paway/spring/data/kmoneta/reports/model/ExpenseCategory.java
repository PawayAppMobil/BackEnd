package com.paway.spring.data.kmoneta.reports.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
@Setter
@Getter
@Document(collation = "expenseCategories")
public class ExpenseCategory {

    // Getters y setters

    @Id
    private String id;
    private String userID;
    private String categoryName;
    private String description;

}
