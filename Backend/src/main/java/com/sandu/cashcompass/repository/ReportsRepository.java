package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.Reports;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReportsRepository extends MongoRepository<Reports , String> {
    //
    Reports findByUserIdAndYear(String userId, int year);
}
