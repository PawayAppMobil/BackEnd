package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.Report;
import com.paway.spring.data.kmoneta.reports.model.ReportRequest;
import com.paway.spring.data.kmoneta.reports.service.ReportService;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import lombok.val;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

import org.springframework.http.HttpStatus;

import java.util.Date;

@RestController
@RequestMapping("/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // 1. Crear un reporte basado en un rango de fechas
    @PostMapping
    public ResponseEntity<Report> createReport(@RequestBody ReportRequest request) {
        Report report = reportService.generateReport(
                request.getUserId(),
                request.getDateRange(),
                request.getReportType());
        return new ResponseEntity<>(report, HttpStatus.CREATED);
    }

    // 2. Obtener todas las transacciones en un rango de fechas para un usuario
    @GetMapping("/transactions")
    public ResponseEntity<List<Transaction>> getTransactionsByDateRange(
            @RequestParam String userId,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate) {

        List<Transaction> transactions = reportService.getTransactionsByDateRange(userId, startDate, endDate);
        return new ResponseEntity<>(transactions, HttpStatus.OK);
    }

    // 3. Obtener todos los reportes de un usuario
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<Report>> getReportsByUserId(@PathVariable String userId) {
        List<Report> reports = reportService.getReportsByUserId(userId);
        return new ResponseEntity<>(reports, HttpStatus.OK);
    }

    // 4. Obtener un reporte específico de un usuario por reportId
    @GetMapping("/user/{userId}/{reportId}")
    public ResponseEntity<Report> getReportById(
            @PathVariable String userId,
            @PathVariable String reportId) {

        Report report = reportService.getReportById(userId, reportId);
        if (report == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(report, HttpStatus.OK);
    }

    // 5. Borrar un reporte específico por reportId
    @DeleteMapping("/{userId}/{reportId}")
    public ResponseEntity<Void> deleteReportById(
            @PathVariable String userId,
            @PathVariable String reportId) {

        boolean deleted = reportService.deleteReportById(userId, reportId);
        if (deleted) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
}