package com.assessment.serviceImpl;

import com.assessment.DTO.MonthTotal;
import com.assessment.DTO.QueryResult;
import com.assessment.Entity.Transaction;
import com.assessment.mockRepository.RewardRepository;
import com.assessment.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class RewardServiceImpl implements RewardService {
    @Autowired
    private RewardRepository rewardRepository;

    private static final int monthPeriod = 3;

    /**
     * Create a new transaction
     * @param userId
     * @param amount
     * @return
     */
    @Override
    public String addTransaction(String userId, double amount) {
        var transaction=new Transaction(userId,amount);
        transaction.setRewardPoints(calculateRewardPoints(amount));
        transaction.setTime(Instant.now().getEpochSecond());
        return rewardRepository.save(transaction).getTransactionId();
    }

    /**
     * Query total reward points during a 3-month period starts from given start time, as well as subtotal for each month
     * @param userId
     * @param start
     * @return
     */
    @Override
    public QueryResult queryRewards(String userId, long start) {
        var startDateTime = ZonedDateTime.ofInstant(Instant.ofEpochSecond(start), ZoneId.systemDefault());
        var end = startDateTime.plusMonths(monthPeriod).toInstant().getEpochSecond();

        var transactions= rewardRepository.getTransactionsByUserIdAndTimeBetween(userId,start,end);

        var months=new long[monthPeriod+1];
        var subTotals=new MonthTotal[monthPeriod];
        months[0]=start;
        for (int i=0;i<monthPeriod;i++){
            var nextMonth=startDateTime.plusMonths(1);
            months[i+1]=nextMonth.toInstant().getEpochSecond();
            subTotals[i]=new MonthTotal(months[i],months[i+1]);
            startDateTime=nextMonth;
        }

        var result=new QueryResult(userId,subTotals);
        int total=0;
        for (Transaction t:transactions){
            for (int i = 0; i < monthPeriod ; i++) {
                if(t.getTime()>=months[i]&&t.getTime()<months[i+1]){
                    subTotals[i].increaseTotal(t.getRewardPoints());
                    total+=t.getRewardPoints();
                    break;
                }
            }
        }
        result.setTotal(total);

        return result;
    }

    private int calculateRewardPoints(double amount){
        return (int)Math.max(amount-50,0)+(int) Math.max(amount-100,0);
    }
}
