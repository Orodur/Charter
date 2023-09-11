package com.assessment.serviceImpl;

import com.assessment.DTO.MonthTotal;
import com.assessment.DTO.QueryResult;
import com.assessment.Entity.Transaction;
import com.assessment.mockRepository.MockRepository;
import com.assessment.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.*;

@Service
public class RewardServiceImpl implements RewardService {
    private MockRepository mockRepository;

    private static final int monthPeriod = 3;

    @Autowired
    public RewardServiceImpl(MockRepository mockRepository) {
        this.mockRepository=mockRepository;
        initDataset();
    }

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
        return mockRepository.addTransaction(transaction);
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

        var transactions=mockRepository.queryTransactions(userId,start,end);

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

    //used for testing
    private void initDataset(){
        addTransaction("20",100,1686408017);
        addTransaction("20",259.78,1690575939);
        addTransaction("20",125.64,1692936846);
        addTransaction("20",168.59,1689346638);
        addTransaction("96",480.35,1686668109);
        addTransaction("18",79.65,1685494724);
        addTransaction("14",59.6,1690850216);
        addTransaction("22",191.6,1686752664);
        addTransaction("27",293.39,1689225963);
        addTransaction("61",375.93,1688373764);
        addTransaction("36",485.63,1689623315);
        addTransaction("24",355.55,1685361021);
        addTransaction("95",437.92,1685066692);
        addTransaction("62",213.9,1689922333);
        addTransaction("67",328.29,1693670304);
    }

    //used for testing
    private void addTransaction(String userId, double amount, long time){
        var transaction=new Transaction(userId,amount);
        transaction.setRewardPoints(calculateRewardPoints(amount));
        transaction.setTime(time);
        mockRepository.addTransaction(transaction);
    }
}
