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
import java.util.Optional;

@Service
public class ReportService {

    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ReportRepository reportRepository;

    // 1. Generar reporte basado en un rango de fechas
    public Report generateReport(String userId, DateRange dateRange, String reportType) {
        List<Transaction> transactions = transactionRepository
                .findByUserIdAndDateBetween(userId, dateRange.getStartDate(), dateRange.getEndDate());

        Report report = new Report();
        report.setUserId(userId);
        report.setDateRange(dateRange);
        report.setGeneratedAt(LocalDate.now());
        report.setReportType(reportType);
        report.setTransactions(transactions);
        report.calculateTotals();  // Calcula ingresos y gastos

        return reportRepository.save(report);
    }

    // 2. Obtener todas las transacciones en un rango de fechas
    public List<Transaction> getTransactionsByDateRange(String userId, LocalDate startDate, LocalDate endDate) {
        return transactionRepository.findByUserIdAndDateBetween(userId, startDate, endDate);
    }

    // 3. Obtener todos los reportes de un usuario
    public List<Report> getReportsByUserId(String userId) {
        return reportRepository.findByUserId(userId);
    }

    // 4. Obtener un reporte por ID y validar que sea del usuario correcto
    public Report getReportById(String userId, String reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        return report.filter(r -> r.getUserId().equals(userId)).orElse(null);
    }

    // 5. Eliminar un reporte específico
    public boolean deleteReportById(String userId, String reportId) {
        Optional<Report> report = reportRepository.findById(reportId);
        if (report.isPresent() && report.get().getUserId().equals(userId)) {
            reportRepository.deleteById(reportId);
            return true;
        }
        return false;
    }
}

