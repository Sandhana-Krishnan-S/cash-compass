package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.MonthReport;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface MonthReportRepository extends MongoRepository<MonthReport , String > {
    //
}
