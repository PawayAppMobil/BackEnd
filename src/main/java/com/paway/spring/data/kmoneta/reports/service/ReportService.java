package com.paway.spring.data.kmoneta.reports.service;
import com.paway.spring.data.kmoneta.reports.model.Report;

import com.paway.spring.data.kmoneta.reports.model.ReportRequest;
import com.paway.spring.data.kmoneta.reports.repository.ReportRepository;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import com.paway.spring.data.kmoneta.transaction.repository.TransactionRepository;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ReportRepository reportRepository;

    public Report generateReport(String userId, DateRange dateRange, String reportType) {
        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, dateRange.getStartDate(), dateRange.getEndDate());

        Report report = new Report();
        report.setUserId(userId);
        report.setDateRange(dateRange);
        report.setGeneratedAt(LocalDate.now());
        report.setReportType(reportType);
        report.setTransactions(transactions);
        report.generateReport();

        return reportRepository.save(report);
    }
    public List<Report> getReportsByUserId(String userId) {
        List<Report> reports = reportRepository.findByUserId(userId);

        if (reports.isEmpty()) {
            throw new ReportNotFoundException("No reports found for user with id: " + userId);
        }

        return reports;
    }

    public List<Transaction> getTransactionsByDateRange(String userId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }


}

