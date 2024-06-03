package com.sandu.cashcompass.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import java.util.Date;

@Document("Transactions")
@Getter
@Setter
public class Transaction {
    @Id
    private String id;

    private float amount;
    private String transactionType;
    private String reason;
    private Date date;
    private String counterparty;
    private boolean isExpense;
}
