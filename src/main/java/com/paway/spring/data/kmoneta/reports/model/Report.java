package com.paway.spring.data.kmoneta.reports.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.paway.spring.data.kmoneta.reports.service.DateRange;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Document(collection = "reports")
@Getter
@Setter
public class Report {
    @Id
    private String id; // ID generado automáticamente
    private String userId;
    private DateRange dateRange;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    private LocalDate generatedAt;
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

    // Método para generar un ID único
    public void generateId() {
        this.id = UUID.randomUUID().toString();
    }

    public void generateReport() {
        this.generateId();
        this.generatedAt = LocalDate.now();
        this.calculateTotals();
    }
}
