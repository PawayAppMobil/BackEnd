package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.Report;
import com.paway.spring.data.kmoneta.reports.repository.ReportRepository;
import com.paway.spring.data.kmoneta.reports.repository.ExpenseRepository;
import com.paway.spring.data.kmoneta.reports.model.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.Date;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    // Obtener todos los reportes
    @GetMapping
    public List<Report> getAllReports() {
        return reportRepository.findAll();
    }

    // Obtener un reporte por ID
    @GetMapping("/{id}")
    public ResponseEntity<Report> getReportById(@PathVariable String id) {
        Optional<Report> report = reportRepository.findById(id);
        return report.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Crear un nuevo reporte
    @PostMapping
    public Report createReport(@RequestBody Report report) {
        List<Expense> expenses = expenseRepository.findByUserId(report.getUserId());
        report.generateReport(expenses);
        report.setGeneratedAt(new Date()); // Establecer la fecha de generación en el backend
        return reportRepository.save(report);
    }

    // Actualizar un reporte existente
    @PutMapping("/{id}")
    public ResponseEntity<Report> updateReport(@PathVariable String id, @RequestBody Report reportDetails) {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (reportOptional.isPresent()) {
            Report report = reportOptional.get();
            report.setUserId(reportDetails.getUserId());
            report.setData(reportDetails.getData());
            report.setDateRange(reportDetails.getDateRange());
            report.setReportType(reportDetails.getReportType());
            List<Expense> expenses = expenseRepository.findByUserId(report.getUserId());
            report.generateReport(expenses);
            report.setGeneratedAt(new Date()); // Establecer la fecha de generación en el backend
            Report updatedReport = reportRepository.save(report);
            return ResponseEntity.ok(updatedReport);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // Eliminar un reporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        Optional<Report> report = reportRepository.findById(id);
        if (report.isPresent()) {
            reportRepository.delete(report.get());
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
