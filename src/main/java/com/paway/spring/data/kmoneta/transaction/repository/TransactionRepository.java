package com.paway.spring.data.kmoneta.transaction.repository;



import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TransactionRepository extends MongoRepository<Transaction, String> {
    // Buscar transacciones por userId
    List<Transaction> findByUserId(String userId);

    // Buscar transacciones por userId y rango de fechas
    List<Transaction> findByUserIdAndDateBetween(String userId, Date startDate, Date endDate);

    // Buscar transacciones por userId y tipo (income o expense)
    List<Transaction> findByUserIdAndType(String userId, String type);
}
