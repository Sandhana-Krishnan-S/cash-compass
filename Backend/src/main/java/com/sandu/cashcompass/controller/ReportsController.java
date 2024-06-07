package com.sandu.cashcompass.controller;

import com.sandu.cashcompass.model.Reports;
import com.sandu.cashcompass.service.ReportsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/reports")
public class ReportsController {
    @Autowired
    private ReportsService service;

    @GetMapping("/initialize")
    public ResponseEntity<Reports> initializer(@RequestParam int year , @RequestParam String userId) {
        return service.initializer(year , userId);
    }


}
