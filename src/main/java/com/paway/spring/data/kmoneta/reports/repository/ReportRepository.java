package com.paway.spring.data.kmoneta.reports.repository;

import com.paway.spring.data.kmoneta.reports.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
}
