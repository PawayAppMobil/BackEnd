package com.paway.spring.data.kmoneta.reports.service;
import com.paway.spring.data.kmoneta.reports.model.Report;

import com.paway.spring.data.kmoneta.reports.model.ReportRequest;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import com.paway.spring.data.kmoneta.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import java.util.Date;
import java.util.List;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Report generateReport(String userId, DateRange dateRange, String reportType) {
        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(userId, dateRange.getStartDate(), dateRange.getEndDate());

        Report report = new Report();
        report.setUserId(userId);
        report.setDateRange(dateRange);
        report.setGeneratedAt(new Date());
        report.setReportType(reportType);
        report.setTransactions(transactions);

        return report;
    }

    public List<Transaction> getTransactionsByDateRange(String userId, Date startDate, Date endDate) {
        return transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }


}

