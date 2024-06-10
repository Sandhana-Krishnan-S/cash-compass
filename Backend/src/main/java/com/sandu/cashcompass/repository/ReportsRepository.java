package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.Reports;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ReportsRepository extends MongoRepository<Reports, String> {

    Reports findByUserIdAndYear(String userId, int year);

    void deleteAllByUserId(String userId);

    List<Reports> findAllByUserId(String userId);
}
