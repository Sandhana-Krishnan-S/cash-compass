package com.sandu.cashcompass.controller;

import com.sandu.cashcompass.model.MonthReport;
import com.sandu.cashcompass.service.MonthReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;

@RestController
@RequestMapping("api/month/report/")
public class MonthReportController {
    @Autowired
    private MonthReportService service;

    @GetMapping("initialize")
    public ResponseEntity<MonthReport> initializer(@RequestParam LocalDate date, @RequestParam String userId) {
        return service.initializer(date , userId);
    }

}
