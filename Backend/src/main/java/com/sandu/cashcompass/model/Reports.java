package com.sandu.cashcompass.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Document("Reports")
@Getter
@Setter
public class Reports {
    @Id
    private String id;

    private int year;

    private List<MonthReport> reports;

    public void initializer() {
        reports = new ArrayList<>();
        this.year = LocalDate.now().getYear();
    }
}
