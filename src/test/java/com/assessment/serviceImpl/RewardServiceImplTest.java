package com.assessment.serviceImpl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class RewardServiceImplTest {

    @Autowired
    private RewardServiceImpl rewardService;

    @Test
    void testAddTransaction1() {
        var result = rewardService.addTransaction("0",300);
        assertThat(result,instanceOf(String.class));
    }

    @Test
    void testQueryRewards0() {
        var result = rewardService.queryRewards("20",1686408017);
        assertThat(result.getTotal(),is(704));
        var subtotals=result.getSubtotals();
        assertThat(subtotals[0].getTotal(),is(50));
        assertThat(subtotals[1].getTotal(),is(554));
        assertThat(subtotals[2].getTotal(),is(100));
    }

    @Test
    void testQueryRewards1() {
        rewardService.addTransaction("0",300);
        //start from 29 days ago so the transaction just created falls into first month in the query result.
        long time= ZonedDateTime.now().minusMonths(1).plusDays(1).toInstant().getEpochSecond();
        var result = rewardService.queryRewards("0",	time);
        assertThat(result.getTotal(),is(450));
        assertThat(result.getSubtotals()[0].getTotal(),is(450));
    }
}