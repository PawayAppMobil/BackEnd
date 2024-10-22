package com.paway.spring.data.kmoneta.reports.repository;

import com.paway.spring.data.kmoneta.reports.model.Report;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface ReportRepository extends MongoRepository<Report, String> {
    List<Report> findByUserId(String userId);
    List<Report> findByUserIdAndDateRangeStartDateGreaterThanEqualAndDateRangeEndDateLessThanEqual(String userId, LocalDate startDate, LocalDate endDate);
}
