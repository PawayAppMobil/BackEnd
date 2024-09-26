package com.paway.spring.data.kmoneta.reports.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

@Getter
@Setter
public class ReportRequestDTO {
    private String userId;
    private String reportType;
    private DateRangeDTO dateRange;

    @Getter
    @Setter
    public static class DateRangeDTO {
        private Date startDate;
        private Date endDate;
    }
}

