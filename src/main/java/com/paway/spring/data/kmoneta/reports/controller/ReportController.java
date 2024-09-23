package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.Report;
import com.paway.spring.data.kmoneta.reports.repository.ReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportRepository reportRepository;

    @PostMapping
    public ResponseEntity<Report> crearReport(@RequestBody Report report) {
        Report nuevoReporte = reportRepository.save(report);
        return ResponseEntity.ok(nuevoReporte);
    }

    @GetMapping
    public ResponseEntity<List<Report>> obtenerTodosLosReportes() {
        List<Report> reportes = reportRepository.findAll();
        return ResponseEntity.ok(reportes);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Report> obtenerReportePorId(@PathVariable String id) {
        return reportRepository.findById(id)
                .map(reporte -> ResponseEntity.ok(reporte))
                .orElse(ResponseEntity.notFound().build());
    }
}
