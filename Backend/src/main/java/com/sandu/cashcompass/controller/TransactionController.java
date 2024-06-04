package com.sandu.cashcompass.controller;

import com.sandu.cashcompass.model.Transaction;
import com.sandu.cashcompass.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/transaction")
public class TransactionController {
    @Autowired
    private TransactionService service;

    @GetMapping("/getAll")
    public ResponseEntity getAll(@RequestParam String userId) {
        return service.getAll(userId);
    }

    @PostMapping("/addTransaction")
    public ResponseEntity<Transaction> addTransaction(@RequestBody Transaction transaction , @RequestParam String userId) {
        return service.addTransaction(transaction , userId);
    }
}
