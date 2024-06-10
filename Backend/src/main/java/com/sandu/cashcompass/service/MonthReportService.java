package com.sandu.cashcompass.service;

import com.sandu.cashcompass.model.MonthReport;
import com.sandu.cashcompass.model.Transaction;
import com.sandu.cashcompass.repository.MonthReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthReportService {
    @Autowired
    private MonthReportRepository repository;

    @Autowired
    private ReportsService reportsService;


    public ResponseEntity<MonthReport> initializer(LocalDate date , String userId) {
        int month = date.getMonthValue();
        int year = date.getYear();
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

        //Add Month to Reports
        if(!reportsService.addNewMonth(month , report , userId , year)) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        } else {
            repository.save(report);
            return new ResponseEntity<>(report , HttpStatus.CREATED);
        }

    }

    //Method : Add Transaction to month Report
    public boolean addTransaction(Transaction transaction , String userId) {
        try {
            int month = transaction.getDate().getMonthValue();
            int year = transaction.getDate().getYear();
            if(month > 12 || month < 1) {
                return false;
            }
            MonthReport report = repository.findByMonthAndUserId(month , transaction.getUserId());
            if(report == null) {
                initializer(transaction.getDate() , userId);
            }
            report = repository.findByMonthAndUserId(month , userId);

            assert report != null;
            report.addTransaction(transaction);
            report.updateTotal(transaction.getAmount() , transaction.isExpense());
            //Update Month to report
            if(reportsService.UpdateMonth(userId , report , year)) {
                repository.save(report);
                return true;
            }
            System.out.println("I sent");
            return false;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteAll(String userId) {
       try {
           repository.deleteAllByUserId(userId);
           return reportsService.deleteAll(userId);
       } catch (Exception e) {
           return false;
       }
    }
}
