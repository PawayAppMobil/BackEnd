package com.paway.spring.data.kmoneta.invoice.controller;

import com.paway.spring.data.kmoneta.invoice.model.Invoice;
import com.paway.spring.data.kmoneta.invoice.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Date;
import java.util.Optional;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @PostMapping
    public ResponseEntity<Invoice> createInvoice(@RequestBody Invoice invoice) {
        Invoice createdInvoice = invoiceRepository.save(invoice);
        return new ResponseEntity<>(createdInvoice, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String id) {
        Optional<Invoice> invoice = invoiceRepository.findById(id);
        return invoice.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceRepository.findAll();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestBody Invoice invoice) {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);
        if (invoiceData.isPresent()) {
            Invoice updatedInvoice = invoiceData.get();
            updatedInvoice.setCustomerId(invoice.getCustomerId());
            updatedInvoice.setDate(invoice.getDate());
            updatedInvoice.setDueDate(invoice.getDueDate());
            updatedInvoice.setInvoiceNumber(invoice.getInvoiceNumber());
            updatedInvoice.setItems(invoice.getItems());
            updatedInvoice.setPaidDate(invoice.getPaidDate());
            updatedInvoice.setStatus(invoice.getStatus());
            updatedInvoice.setTemplateId(invoice.getTemplateId());
            updatedInvoice.setTotalAmount(invoice.getTotalAmount());
            updatedInvoice.setTransactionId(invoice.getTransactionId());
            return new ResponseEntity<>(invoiceRepository.save(updatedInvoice), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteInvoice(@PathVariable String id) {
        try {
            invoiceRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/customer/{customerId}")
    public ResponseEntity<List<Invoice>> getInvoicesByCustomer(@PathVariable String customerId) {
        List<Invoice> invoices = invoiceRepository.findByCustomerId(customerId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Invoice>> getInvoicesByStatus(@PathVariable String status) {
        List<Invoice> invoices = invoiceRepository.findByStatus(status);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/due-date-range")
    public ResponseEntity<List<Invoice>> getInvoicesNearDueDate(
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date startDate,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date endDate) {
        List<Invoice> invoices = invoiceRepository.findByDueDateBetween(startDate, endDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PutMapping("/{id}/mark-as-paid")
    public ResponseEntity<Invoice> markInvoiceAsPaid(@PathVariable String id) {
        Optional<Invoice> invoiceData = invoiceRepository.findById(id);
        if (invoiceData.isPresent()) {
            Invoice paidInvoice = invoiceData.get();
            paidInvoice.setStatus("paid");
            paidInvoice.setPaidDate(new Date());
            return new ResponseEntity<>(invoiceRepository.save(paidInvoice), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/transaction/{transactionId}")
    public ResponseEntity<List<Invoice>> getInvoicesByTransaction(@PathVariable String transactionId) {
        List<Invoice> invoices = invoiceRepository.findByTransactionId(transactionId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/template/{templateId}")
    public ResponseEntity<List<Invoice>> getInvoicesByTemplate(@PathVariable String templateId) {
        List<Invoice> invoices = invoiceRepository.findByTemplateId(templateId);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
}