package com.assessment.Entity;

public class Transaction {
    private String transactionId;
    private long time;
    private String userId;
    private double amount;
    private int rewardPoints;

    public Transaction(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
    }

    public Transaction(long time, String userId, double amount, int rewardPoints) {
        this.time = time;
        this.userId = userId;
        this.amount = amount;
        this.rewardPoints = rewardPoints;
    }

    public Transaction(String transactionId, long time, String userId, double amount, int rewardPoints) {
        this.transactionId = transactionId;
        this.time = time;
        this.userId = userId;
        this.amount = amount;
        this.rewardPoints = rewardPoints;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getRewardPoints() {
        return rewardPoints;
    }

    public void setRewardPoints(int rewardPoints) {
        this.rewardPoints = rewardPoints;
    }
}
