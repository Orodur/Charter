package com.assessment.DTO;

public class MonthTotal{
    private long start;
    private long end;
    private int total;

    public MonthTotal(long start, long end) {
        this.start = start;
        this.end = end;
        this.total = 0;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public long getEnd() {
        return end;
    }

    public void setEnd(long end) {
        this.end = end;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public void increaseTotal(int diff){
        this.total+=diff;
    }
}
