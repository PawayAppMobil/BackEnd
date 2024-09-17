package com.paway.spring.data.kmoneta.reports.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;
import java.util.List;

@Document(collection = "reports")
public class Report {

    @Id
    private String id;
    private Data data;
    private DateRange dateRange;
    private Date generatedAt;
    private String reportType;

    // Clases anidadas para detalles de datos y rango de fechas
    public static class Data {
        private List<Detail> details;
        private double totalExpenses;
        private double totalIncome;

        // Getters y setters
        public List<Detail> getDetails() {
            return details;
        }

        public void setDetails(List<Detail> details) {
            this.details = details;
        }

        public double getTotalExpenses() {
            return totalExpenses;
        }

        public void setTotalExpenses(double totalExpenses) {
            this.totalExpenses = totalExpenses;
        }

        public double getTotalIncome() {
            return totalIncome;
        }

        public void setTotalIncome(double totalIncome) {
            this.totalIncome = totalIncome;
        }
    }

    public static class Detail {
        private String description;
        private double amount;

        // Getters y setters
        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public double getAmount() {
            return amount;
        }

        public void setAmount(double amount) {
            this.amount = amount;
        }
    }

    public static class DateRange {
        private Date startDate;
        private Date endDate;

        // Getters y setters
        public Date getStartDate() {
            return startDate;
        }

        public void setStartDate(Date startDate) {
            this.startDate = startDate;
        }

        public Date getEndDate() {
            return endDate;
        }

        public void setEndDate(Date endDate) {
            this.endDate = endDate;
        }
    }

    // Getters y setters del Report
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Data getData() {
        return data;
    }

    public void setData(Data data) {
        this.data = data;
    }

    public DateRange getDateRange() {
        return dateRange;
    }

    public void setDateRange(DateRange dateRange) {
        this.dateRange = dateRange;
    }

    public Date getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(Date generatedAt) {
        this.generatedAt = generatedAt;
    }

    public String getReportType() {
        return reportType;
    }

    public void setReportType(String reportType) {
        this.reportType = reportType;
    }
}
