package com.paway.spring.data.kmoneta.transaction.repository;



import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {

    // Buscar transacciones por userId y rango de fechas
    List<Transaction> findByUserIdAndDateBetween(String userId, LocalDate startDate, LocalDate endDate);

    List<Transaction> findByUserId(String userId);
}
