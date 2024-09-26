package com.paway.spring.data.kmoneta.reports.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReportResponseDTO {
    private String id;
    private String userId;
    private DataDTO data;
    private DateRangeDTO dateRange;
    private Date generatedAt;
    private String reportType;

    @Getter
    @Setter
    public static class DataDTO {
        private List<DetailDTO> details;
        private double totalExpenses;
        private double totalIncome;
    }

    @Getter
    @Setter
    public static class DetailDTO {
        private String description;
        private double amount;
    }

    @Getter
    @Setter
    public static class DateRangeDTO {
        private Date startDate;
        private Date endDate;
    }
}
