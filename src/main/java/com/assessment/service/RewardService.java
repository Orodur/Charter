package com.assessment.service;

import com.assessment.DTO.QueryResult;
import org.springframework.stereotype.Service;


public interface RewardService {
    public String addTransaction(String userId, double amount);
    public QueryResult queryRewards(String userId, long start);
}
