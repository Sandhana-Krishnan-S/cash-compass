package com.sandu.cashcompass.repository;

import com.sandu.cashcompass.model.Transaction;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface TransactionRepository extends MongoRepository<Transaction , String > {
    //
}
