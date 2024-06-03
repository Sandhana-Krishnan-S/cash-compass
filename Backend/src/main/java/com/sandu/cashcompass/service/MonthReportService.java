package com.sandu.cashcompass.service;

import com.sandu.cashcompass.model.MonthReport;
import com.sandu.cashcompass.repository.MonthReportRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class MonthReportService {
    @Autowired
    private MonthReportRepository repository;


    public ResponseEntity<MonthReport> initializer(int month) {
        if(month > 12 || month < 1) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        MonthReport report = repository.findByMonth(month);
        if(report != null) {
            return new ResponseEntity<>(report , HttpStatus.NOT_MODIFIED);
        }
        report = new MonthReport();
        report.initializer(month);

        //TODO : Add this to Reports

        repository.save(report);
        return new ResponseEntity<>(report , HttpStatus.CREATED);
    }
}
