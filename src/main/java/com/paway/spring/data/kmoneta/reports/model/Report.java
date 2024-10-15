package com.paway.spring.data.kmoneta.reports.model;

import com.paway.spring.data.kmoneta.reports.service.DateRange;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;



@Document(collection = "reports")
@Getter
@Setter
public class Report {
    @Id
    private String id;
    private String userId;
    private DateRange dateRange;
    private Date generatedAt;
    private String reportType;
    private double totalIncome;
    private double totalExpenses;
    private List<Transaction> transactions;

    // Método para calcular totales
    public void calculateTotals() {
        this.totalIncome = transactions.stream()
                .filter(Transaction::isIncome)
                .mapToDouble(Transaction::getAmount)
                .sum();

        this.totalExpenses = transactions.stream()
                .filter(transaction -> !transaction.isIncome())
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

}

