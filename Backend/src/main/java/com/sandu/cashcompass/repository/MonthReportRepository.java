package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.MonthReport;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@EnableMongoRepositories
public interface MonthReportRepository extends MongoRepository<MonthReport , String > {
    public MonthReport findByMonthAndUserId(int month , String userId);
}
