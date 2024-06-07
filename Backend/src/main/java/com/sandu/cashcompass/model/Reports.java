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

    private String userId;
    private int year;

    private List<MonthReport> reports;

    public void initializer(String userId , int year) {
        reports = new ArrayList<>();
        this.year = year;
        this.userId = userId;
    }

    public void addReports(MonthReport report) {
        for (int i = 0; i < this.reports.size(); i++) {
            if (this.reports.get(i).getMonth() == report.getMonth()) {
                this.reports.set(i, report);
                return;
            }
        }
        this.reports.add(report);
    }
}
