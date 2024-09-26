package com.paway.spring.data.kmoneta.transaction.repository;

import com.paway.spring.data.kmoneta.transaction.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction, String> {
}
