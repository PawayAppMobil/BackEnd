package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.Report;
import com.paway.spring.data.kmoneta.reports.model.ReportRequest;
import com.paway.spring.data.kmoneta.reports.service.ReportService;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import org.springframework.http.HttpStatus;

import java.util.Date;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody ReportRequest request) {
        Report report = reportService.generateReport(request.getUserId(), request.getDateRange(), request.getReportType());
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(@RequestParam String userId, @RequestParam Date startDate, @RequestParam Date endDate) {
        List<Transaction> transactions = reportService.getTransactionsByDateRange(userId, startDate, endDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }
}
