package com.sandu.cashcompass.service;

import com.sandu.cashcompass.model.MonthReport;
import com.sandu.cashcompass.model.Transaction;
import com.sandu.cashcompass.repository.MonthReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

@Service
public class MonthReportService {
    @Autowired
    private MonthReportRepository repository;


    public ResponseEntity<MonthReport> initializer(int month , String userId) {
        if(month > 12 || month < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MonthReport report = repository.findByMonthAndUserId(month , userId);
        if(report != null) {
            return new ResponseEntity<>(report , HttpStatus.NOT_MODIFIED);
        }
        report = new MonthReport();
        report.setUserId(userId);
        report.initializer(month);

        //TODO : Add this to Reports

        repository.save(report);
        return new ResponseEntity<>(report , HttpStatus.CREATED);
    }

    //Method : Add Transaction to month Report
    public boolean addTransaction(Transaction transaction , String userId) {
        try {
            int month = transaction.getDate().getMonthValue();
            if(month > 12 || month < 1) {
                return false;
            }
            MonthReport report = repository.findByMonthAndUserId(month , transaction.getUserId());
            if(report == null) {
                initializer(month , userId);
                report = repository.findByMonthAndUserId(month , userId);
            }
            report.addTransaction(transaction);
            report.updateTotal(transaction.getAmount() , transaction.isExpense());
            repository.save(report);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }
}
