package com.assessment.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
class RewardControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @Test
    void testAddTransaction() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/reward?userId=20&amount=250")
                )
                .andExpect(status().isOk())
                .andDo(print());
    }

    @Test
    void testQueryRewards() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders
                .post("/reward?userId=20&amount=250")
        ).andExpect(status().isOk());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/reward?userId=20&start=1694341505")
                )
                .andExpect(jsonPath("$.total",is(350)))
                .andExpect(jsonPath("$.subtotals[0].total",is(350)))
                .andDo(print());
    }
}