package com.sandu.cashcompass.service;

import com.sandu.cashcompass.model.MonthReport;
import com.sandu.cashcompass.model.Reports;
import com.sandu.cashcompass.repository.ReportsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReportsService {
    @Autowired
    private ReportsRepository repository;

    public ResponseEntity<Reports> initializer(int year, String userId) {
        try {
            Reports reports = repository.findByUserIdAndYear(userId , year);
            if (reports != null) {
                return new ResponseEntity<>(reports , HttpStatus.METHOD_NOT_ALLOWED);
            }
            reports = new Reports();
            reports.initializer(userId, year);
            repository.save(reports);
            return new ResponseEntity<>(reports , HttpStatus.CREATED);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    public Boolean addNewMonth(int month, MonthReport report , String userId , int year) {
        try {
            Reports reports = repository.findByUserIdAndYear(userId , year);
            if(reports == null) {
                reports = new Reports();
                reports.initializer(userId, year);
            }
            reports.addReports(report);
            repository.save(reports);
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean UpdateMonth(String userId, MonthReport report , int year) {
        try {
            Reports reports = repository.findByUserIdAndYear(userId , year);
            if(reports == null) {
                reports = new Reports();
                reports.initializer(userId, year);
            }
            reports.addReports(report);
            repository.save(reports);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return false;
        }
    }

    public boolean deleteAll(String userId) {
        try {
            repository.deleteAllByUserId(userId);
            List<Reports> reports = repository.findAllByUserId(userId);
            return reports.isEmpty();
        } catch (Exception e) {
            return false;
        }
    }
}
