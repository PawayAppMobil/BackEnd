package com.paway.spring.data.kmoneta.invoice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.paway.spring.data.kmoneta.inventory.model.Product;
import com.paway.spring.data.kmoneta.inventory.model.ProductDTO;
import com.paway.spring.data.kmoneta.inventory.repository.ProductRepository;
import com.paway.spring.data.kmoneta.invoice.model.Invoice;
import com.paway.spring.data.kmoneta.invoice.service.InvoiceDTO;
import com.paway.spring.data.kmoneta.invoice.service.InvoiceService;


import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import com.paway.spring.data.kmoneta.transaction.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.DataInput;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;
    @Autowired
    private ProductRepository productService;
    @Autowired
    private TransactionRepository transactionService;

    @PostMapping
    public ResponseEntity<Map<String, Object>> createInvoice(@RequestBody InvoiceDTO invoiceDTO) {
        Invoice createdInvoice = new Invoice();
        createdInvoice.setDate(invoiceDTO.getDate());
        createdInvoice.setAmount(invoiceDTO.getAmount());
        createdInvoice.setStatus(invoiceDTO.getStatus());
        createdInvoice.setUserId(invoiceDTO.getUserId());
        createdInvoice.setDueDate(invoiceDTO.getDueDate());

        // Obtener los productos por los IDs proporcionados
        List<Product> products = new ArrayList<>();

        for (String productId : invoiceDTO.getProductIds()) {
            Optional<Product> productOptional = productService.findById(productId);
            productOptional.ifPresent(products::add);
        }

        createdInvoice.setItems(products);

        // Guardar la factura creada
        Invoice savedInvoice = invoiceService.createInvoice(createdInvoice);

        // Crear una transacción basada en la factura recién creada
        Transaction newTransaction = new Transaction();
        newTransaction.setAmount(savedInvoice.getAmount().intValue());
        newTransaction.setDate(savedInvoice.getDate());
        newTransaction.setInvoiceId(savedInvoice.getId());
        newTransaction.setUserId(savedInvoice.getUserId());
        newTransaction.setDetails("Transaction for Invoice ID: " + savedInvoice.getId());

        if ("Income".equalsIgnoreCase(savedInvoice.getStatus())) {
            newTransaction.setIncome(true);
        } else if ("Expense".equalsIgnoreCase(savedInvoice.getStatus())) {
            newTransaction.setIncome(false);
        } else {
            newTransaction.setIncome(true);  // Default a Income
        }

        // Guardar la transacción creada
        Transaction savedTransaction = transactionService.save(newTransaction);

        // Crear un mapa para retornar la factura y la transacción en la respuesta
        Map<String, Object> response = new HashMap<>();
        response.put("invoice", savedInvoice);
        response.put("transaction", savedTransaction);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value="/{id}/document", consumes = "multipart/form-data")
    public ResponseEntity<Invoice> addInvoiceDocument(
            @PathVariable String id,
            @RequestPart(required = false) MultipartFile document) {
        try {
            Invoice updatedInvoice = invoiceService.addInvoiceDocument(id, document);
            if (updatedInvoice != null) {
                return new ResponseEntity<>(updatedInvoice, HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }
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
