package com.paway.spring.data.kmoneta.reports.controller;

import com.paway.spring.data.kmoneta.reports.model.ReportRequestDTO;
import com.paway.spring.data.kmoneta.reports.model.ReportResponseDTO;
import com.paway.spring.data.kmoneta.reports.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/reports")
public class ReportController {

    @Autowired
    private ReportService reportService;

    // Obtener todos los reportes
    @GetMapping
    public List<ReportResponseDTO> getAllReports() {
        return reportService.getAllReports();
    }

    // Obtener un reporte por ID
    @GetMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> getReportById(@PathVariable String id) {
        ReportResponseDTO report = reportService.getReportById(id);
        return report != null ? ResponseEntity.ok(report) : ResponseEntity.notFound().build();
    }

    // Crear un nuevo reporte
    @PostMapping
    public ReportResponseDTO createReport(@RequestBody ReportRequestDTO reportRequestDTO) {
        return reportService.createReport(reportRequestDTO);
    }

    // Actualizar un reporte existente
    @PutMapping("/{id}")
    public ResponseEntity<ReportResponseDTO> updateReport(@PathVariable String id, @RequestBody ReportRequestDTO reportRequestDTO) {
        ReportResponseDTO updatedReport = reportService.updateReport(id, reportRequestDTO);
        return updatedReport != null ? ResponseEntity.ok(updatedReport) : ResponseEntity.notFound().build();
    }

    // Eliminar un reporte
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReport(@PathVariable String id) {
        reportService.deleteReport(id);
        return ResponseEntity.noContent().build();
    }
}
