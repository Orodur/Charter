package com.assessment.controller;

import com.assessment.DTO.QueryResult;
import com.assessment.service.RewardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reward")
public class RewardController {
    @Autowired
    private RewardService rewardService;

    /**
     * Create a new transaction
     * @param userId
     * @param amount
     * @return transaction id
     */
    @PostMapping
    public ResponseEntity<String> addTransaction( @RequestParam String userId, @RequestParam double amount){
        String result= rewardService.addTransaction(userId,amount);
        if (result==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }

    /**
     * Query total reward points during a 3-month period starts from given start time, as well as subtotal for each month
     * @param userId
     * @param start start timestamp
     * @return Query result as requested
     */
    @GetMapping
    public ResponseEntity<QueryResult> queryRewards(@RequestParam String userId, @RequestParam long start){
        var result=rewardService.queryRewards(userId, start);
        if (result==null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(result,HttpStatus.OK);
    }
}
