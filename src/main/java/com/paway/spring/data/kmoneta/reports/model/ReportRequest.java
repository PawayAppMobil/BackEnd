package com.paway.spring.data.kmoneta.reports.model;

import com.paway.spring.data.kmoneta.reports.service.DateRange;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class ReportRequest {
    private String userId;
    private String reportType;
    private DateRange dateRange;


}

