package com.sandu.cashcompass.service;

import com.sandu.cashcompass.model.Transaction;
import com.sandu.cashcompass.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;



@Service
public class TransactionService {
    @Autowired
    private MonthReportService monthReportService;

    @Autowired
    private TransactionRepository repository;

    public ResponseEntity getAll(String userId) {
        try {

            return new ResponseEntity<>(repository.findAllByUserId(userId) , HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public ResponseEntity<Transaction> addTransaction(Transaction transaction , String userId) {
        if(transaction.getAmount() < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        transaction.setUserId(userId);
        if(monthReportService.addTransaction(transaction , userId)) {
            return new ResponseEntity<>(repository.save(transaction) , HttpStatus.OK);
        }
        System.out.println("On Last Step");
        return new ResponseEntity<>(HttpStatus.REQUEST_TIMEOUT);
    }
}
