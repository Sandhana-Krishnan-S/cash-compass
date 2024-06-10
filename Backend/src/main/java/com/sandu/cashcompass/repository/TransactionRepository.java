package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface TransactionRepository extends MongoRepository<Transaction , String > {
    List<Transaction> findAllByUserId(String userId);

    void deleteAllByUserId(String userId);
}
