package com.paway.spring.data.kmoneta.reports.repository;

import com.paway.spring.data.kmoneta.reports.model.ExpenseCategory;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ExpenseCategoryRepository extends MongoRepository<ExpenseCategory, String> {
}
