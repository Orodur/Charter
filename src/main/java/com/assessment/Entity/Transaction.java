package com.assessment.Entity;

import jakarta.persistence.*;

@Entity
@Table
public class Transaction {

    @Id
    @GeneratedValue(strategy= GenerationType.UUID)
    private String transactionId;
    private long time;
    private String userId;
    private double amount;
    private int rewardPoints;

    public Transaction() {
    }

    public Transaction(String userId, double amount) {
        this.userId = userId;
        this.amount = amount;
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
