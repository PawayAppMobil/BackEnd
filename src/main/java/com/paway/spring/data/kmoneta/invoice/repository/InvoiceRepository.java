package com.paway.spring.data.kmoneta.invoice.repository;

import com.paway.spring.data.kmoneta.invoice.model.Invoice;
import org.springframework.data.mongodb.repository.MongoRepository;
import java.util.List;
import java.util.Date;

public interface InvoiceRepository extends MongoRepository<Invoice, String> {
    List<Invoice> findByUserId(String userId);
    List<Invoice> findByStatus(String status);
    List<Invoice> findByDateBetween(Date startDate, Date endDate);
}