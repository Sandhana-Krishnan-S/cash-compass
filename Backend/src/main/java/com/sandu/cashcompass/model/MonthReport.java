package com.sandu.cashcompass.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document("MonthReport")
@Getter
@Setter
public class MonthReport {
    @Id
    private String id;

    private String userId;
    private  int month;
    private float total;
    private float totalSpends;
    private float totalIncome;

    //transactions
    private List<Transaction> transactions;

    //methods

    public void initializer(int month) {
        this.transactions = new ArrayList<>();
        this.month = month;
        setAmount();
    }

    public void setAmount() {
        this.total = 0;
    }

    public void updateTotal(float amount , boolean isExpense) {
        if(isExpense) {
            this.total -= amount;
            this.totalSpends += amount;
        }
        else {
            this.total += amount;
            this.totalIncome += amount;
        }
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}
