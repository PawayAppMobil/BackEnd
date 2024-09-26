package com.paway.spring.data.kmoneta.invoice.service;

import com.paway.spring.data.kmoneta.invoice.model.Invoice;
import com.paway.spring.data.kmoneta.invoice.model.InvoiceItem;
import com.paway.spring.data.kmoneta.invoice.repository.InvoiceRepository;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.Date;
@Service
public class InvoiceService {

    private final InvoiceRepository invoiceRepository;

    public InvoiceService(InvoiceRepository invoiceRepository) {
        this.invoiceRepository = invoiceRepository;
    }

    public Invoice createInvoice(InvoiceDTO invoiceDTO) {
        Invoice invoice = new Invoice();
        invoice.setDate(invoiceDTO.getDate());
        invoice.setStatus(invoiceDTO.getStatus());
        invoice.setItems(invoiceDTO.getItems());
        invoice.setTransactionId(invoiceDTO.getTransactionId());
        invoice.setUserId(invoiceDTO.getUserId());
        invoice.setDueDate(invoiceDTO.getDueDate());
        invoice.setAmount(invoice.calculateAmount());
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> getInvoice(String id) {
        return invoiceRepository.findById(id);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice updateInvoice(String id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);
        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();
            invoice.setDate(invoiceDTO.getDate());
            invoice.setStatus(invoiceDTO.getStatus());
            invoice.setItems(invoiceDTO.getItems());
            invoice.setTransactionId(invoiceDTO.getTransactionId());
            invoice.setUserId(invoiceDTO.getUserId());
            invoice.setDueDate(invoiceDTO.getDueDate());
            invoice.setAmount(invoice.calculateAmount());
            return invoiceRepository.save(invoice);
        }
        return null;
    }

    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }

    public List<Invoice> getInvoicesByCustomer(String userId) {
        return invoiceRepository.findByUserId(userId);
    }

    public List<Invoice> getInvoicesByStatus(String status) {
        return invoiceRepository.findByStatus(status);
    }

    public List<Invoice> getInvoicesNearDueDate(Date startDate, Date endDate) {
        return invoiceRepository.findByDueDateBetween(startDate, endDate);
    }

    public Invoice markAsPaid(String id) {
        Optional<Invoice> invoiceOpt = invoiceRepository.findById(id);
        if (invoiceOpt.isPresent()) {
            Invoice invoice = invoiceOpt.get();
            invoice.setStatus("paid");
            return invoiceRepository.save(invoice);
        }
        return null;
    }
}



