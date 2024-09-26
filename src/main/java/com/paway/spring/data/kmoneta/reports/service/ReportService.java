package com.paway.spring.data.kmoneta.reports.service;
import com.paway.spring.data.kmoneta.reports.model.Expense;
import com.paway.spring.data.kmoneta.reports.model.Report;
import com.paway.spring.data.kmoneta.reports.model.ReportRequestDTO;
import com.paway.spring.data.kmoneta.reports.model.ReportResponseDTO;
import com.paway.spring.data.kmoneta.reports.repository.ExpenseRepository;
import com.paway.spring.data.kmoneta.reports.repository.ReportRepository;
import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import com.paway.spring.data.kmoneta.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@Service
public class ReportService {

    @Autowired
    private ReportRepository reportRepository;

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public List<ReportResponseDTO> getAllReports() {
        List<Report> reports = reportRepository.findAll();
        return reports.stream().map(this::convertToDTO).collect(Collectors.toList());
    }

    public ReportResponseDTO getReportById(String id) {
        Optional<Report> report = reportRepository.findById(id);
        return report.map(this::convertToDTO).orElse(null);
    }

    public ReportResponseDTO createReport(ReportRequestDTO reportRequestDTO) {
        List<Expense> expenses = expenseRepository.findByUserId(reportRequestDTO.getUserId());
        List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(
                reportRequestDTO.getUserId(),
                reportRequestDTO.getDateRange().getStartDate(),
                reportRequestDTO.getDateRange().getEndDate()
        );

        Report report = new Report();
        report.setUserId(reportRequestDTO.getUserId());
        report.setReportType(reportRequestDTO.getReportType());
        report.setDateRange(convertToDateRange(reportRequestDTO.getDateRange()));
        report.generateReport(expenses, transactions);
        report.setGeneratedAt(new Date());

        Report savedReport = reportRepository.save(report);
        return convertToDTO(savedReport);
    }

    public ReportResponseDTO updateReport(String id, ReportRequestDTO reportRequestDTO) {
        Optional<Report> reportOptional = reportRepository.findById(id);
        if (reportOptional.isPresent()) {
            Report report = reportOptional.get();
            List<Expense> expenses = expenseRepository.findByUserId(reportRequestDTO.getUserId());
            List<Transaction> transactions = transactionRepository.findByUserIdAndDateBetween(
                    reportRequestDTO.getUserId(),
                    reportRequestDTO.getDateRange().getStartDate(),
                    reportRequestDTO.getDateRange().getEndDate()
            );

            report.setUserId(reportRequestDTO.getUserId());
            report.setReportType(reportRequestDTO.getReportType());
            report.setDateRange(convertToDateRange(reportRequestDTO.getDateRange()));
            report.generateReport(expenses, transactions);
            report.setGeneratedAt(new Date());

            Report updatedReport = reportRepository.save(report);
            return convertToDTO(updatedReport);
        } else {
            return null;
        }
    }

    public void deleteReport(String id) {
        reportRepository.deleteById(id);
    }

    private ReportResponseDTO convertToDTO(Report report) {
        ReportResponseDTO dto = new ReportResponseDTO();
        dto.setId(report.getId());
        dto.setUserId(report.getUserId());
        dto.setData(convertToDataDTO(report.getData()));
        dto.setDateRange(convertToDateRangeDTO(report.getDateRange()));
        dto.setGeneratedAt(report.getGeneratedAt());
        dto.setReportType(report.getReportType());
        return dto;
    }

    private ReportResponseDTO.DataDTO convertToDataDTO(Report.Data data) {
        ReportResponseDTO.DataDTO dto = new ReportResponseDTO.DataDTO();
        dto.setDetails(data.getDetails().stream().map(this::convertToDetailDTO).collect(Collectors.toList()));
        dto.setTotalExpenses(data.getTotalExpenses());
        dto.setTotalIncome(data.getTotalIncome());
        return dto;
    }

    private ReportResponseDTO.DetailDTO convertToDetailDTO(Report.Detail detail) {
        ReportResponseDTO.DetailDTO dto = new ReportResponseDTO.DetailDTO();
        dto.setDescription(detail.getDescription());
        dto.setAmount(detail.getAmount());
        return dto;
    }

    private ReportResponseDTO.DateRangeDTO convertToDateRangeDTO(Report.DateRange dateRange) {
        ReportResponseDTO.DateRangeDTO dto = new ReportResponseDTO.DateRangeDTO();
        dto.setStartDate(dateRange.getStartDate());
        dto.setEndDate(dateRange.getEndDate());
        return dto;
    }

    private Report.DateRange convertToDateRange(ReportRequestDTO.DateRangeDTO dateRangeDTO) {
        Report.DateRange dateRange = new Report.DateRange();
        dateRange.setStartDate(dateRangeDTO.getStartDate());
        dateRange.setEndDate(dateRangeDTO.getEndDate());
        return dateRange;
    }
}