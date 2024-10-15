package com.paway.spring.data.kmoneta.invoice.service;

import com.paway.spring.data.kmoneta.invoice.model.Invoice;
import com.paway.spring.data.kmoneta.invoice.model.InvoiceItem;
import com.paway.spring.data.kmoneta.invoice.repository.InvoiceRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.Date;
@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    public Invoice createInvoice(Invoice invoice) {
        return invoiceRepository.save(invoice);
    }

    public Optional<Invoice> getInvoice(String id) {
        return invoiceRepository.findById(id);
    }

    public List<Invoice> getAllInvoices() {
        return invoiceRepository.findAll();
    }

    public Invoice updateInvoice(String id, InvoiceDTO invoiceDTO) {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            invoice.setDate(invoiceDTO.getDate());
            invoice.setAmount(invoiceDTO.getAmount());
            invoice.setStatus(invoiceDTO.getStatus());
            invoice.setUserId(invoiceDTO.getUserId());
            invoice.setDueDate(invoiceDTO.getDueDate());
            return invoiceRepository.save(invoice);
        }
        return null;
    }
    public List<Invoice> getInvoicesByDueDateRange(Date startDate, Date endDate) {
        return invoiceRepository.findByDueDateBetween(startDate, endDate);
    }
    public Invoice addInvoiceDocument(String id, MultipartFile document) throws IOException {
        Optional<Invoice> optionalInvoice = invoiceRepository.findById(id);
        if (optionalInvoice.isPresent()) {
            Invoice invoice = optionalInvoice.get();
            if (document != null && !document.isEmpty()) {
                invoice.setDocument(document.getBytes());
            }
            return invoiceRepository.save(invoice);
        }
        return null;
    }

    public List<Invoice> getInvoicesByStatus(String status) {
        return invoiceRepository.findByStatus(status);
    }
    public void deleteInvoice(String id) {
        invoiceRepository.deleteById(id);
    }
}
