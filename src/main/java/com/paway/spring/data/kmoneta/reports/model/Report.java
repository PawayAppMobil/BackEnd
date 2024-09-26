package com.paway.spring.data.kmoneta.reports.model;

import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "reports")
public class Report {

    @Getter
    @Setter
    @Id
    private String id;
    @Getter
    @Setter
    private String userId;
    @Getter
    @Setter
    private Data data;
    @Getter
    @Setter
    private DateRange dateRange;
    @Getter
    @Setter
    private Date generatedAt;
    @Getter
    @Setter
    private String reportType;

    // Método para generar el reporte
    public void generateReport(List<Expense> expenses, List<Transaction> transactions) {
        this.generatedAt = new Date();
        this.data.setTotalIncome(calculateTotalIncome(transactions));
        this.data.setTotalExpenses(calculateTotalExpenses(expenses));
    }

    // Método para calcular el total de ingresos
    private double calculateTotalIncome(List<Transaction> transactions) {
        return transactions.stream()
                .filter(transaction -> "income".equals(transaction.getType()))
                .mapToDouble(Transaction::getAmount)
                .sum();
    }

    // Método para calcular el total de gastos
    private double calculateTotalExpenses(List<Expense> expenses) {
        return expenses.stream()
                .mapToDouble(Expense::getAmount)
                .sum();
    }

    @Setter
    @Getter
    public static class Data {
        private List<Detail> details;
        private double totalExpenses;
        private double totalIncome;
    }

    @Setter
    @Getter
    public static class Detail {
        private String description;
        private double amount;
    }

    @Setter
    @Getter
    public static class DateRange {
        private Date startDate;
        private Date endDate;
    }
}
