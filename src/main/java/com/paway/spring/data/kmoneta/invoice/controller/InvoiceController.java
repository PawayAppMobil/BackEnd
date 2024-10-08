package com.paway.spring.data.kmoneta.invoice.controller;

import com.paway.spring.data.kmoneta.invoice.model.Invoice;
import com.paway.spring.data.kmoneta.invoice.service.InvoiceDTO;
import com.paway.spring.data.kmoneta.invoice.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Date;
@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping(consumes = "multipart/form-data")
    public ResponseEntity<Invoice> createInvoice(
            @ModelAttribute InvoiceDTO invoiceDTO, // Usa el DTO
            @RequestPart(required = false) MultipartFile document) { // Archivo como RequestPart

        try {
            Invoice createdInvoice = new Invoice();
            createdInvoice.setDate(invoiceDTO.getDate());
            createdInvoice.setAmount(invoiceDTO.getAmount());
            createdInvoice.setStatus(invoiceDTO.getStatus());
            createdInvoice.setItems(invoiceDTO.getItems());
            createdInvoice.setTransactionId(invoiceDTO.getTransactionId());
            createdInvoice.setUserId(invoiceDTO.getUserId());
            createdInvoice.setDueDate(invoiceDTO.getDueDate());

            // Manejo del documento (archivo)
            if (document != null && !document.isEmpty()) {
                createdInvoice.setDocument(document.getBytes());
            }

            Invoice savedInvoice = invoiceService.createInvoice(createdInvoice);
            return new ResponseEntity<>(savedInvoice, HttpStatus.CREATED);
        } catch (IOException e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Invoice> getInvoice(@PathVariable String id) {
        return invoiceService.getInvoice(id)
                .map(invoice -> new ResponseEntity<>(invoice, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping
    public ResponseEntity<List<Invoice>> getAllInvoices() {
        List<Invoice> invoices = invoiceService.getAllInvoices();
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Invoice> updateInvoice(@PathVariable String id, @RequestBody InvoiceDTO invoiceDTO) {
        Invoice updatedInvoice = invoiceService.updateInvoice(id, invoiceDTO);
        return updatedInvoice != null ? new ResponseEntity<>(updatedInvoice, HttpStatus.OK) : new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }
    @GetMapping("/due-date-range")
    public ResponseEntity<List<Invoice>> getInvoicesByDueDateRange(
            @RequestParam Date startDate,
            @RequestParam Date endDate) {
        List<Invoice> invoices = invoiceService.getInvoicesByDueDateRange(startDate, endDate);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }

    @GetMapping("/status/{status}")
    public ResponseEntity<List<Invoice>> getInvoicesByStatus(@PathVariable String status) {
        List<Invoice> invoices = invoiceService.getInvoicesByStatus(status);
        return new ResponseEntity<>(invoices, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInvoice(@PathVariable String id) {
        invoiceService.deleteInvoice(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}