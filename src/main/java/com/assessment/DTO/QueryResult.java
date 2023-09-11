package com.assessment.DTO;

import java.time.Month;
import java.util.List;

public class QueryResult {
    private String userId;
    private int total;
    private MonthTotal[] subtotals;

    public QueryResult(String userId, MonthTotal[] subtotals) {
        this.userId = userId;
        this.subtotals = subtotals;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public MonthTotal[] getSubtotals() {
        return subtotals;
    }

    public void setSubtotals(MonthTotal[] subtotals) {
        this.subtotals = subtotals;
    }
}


