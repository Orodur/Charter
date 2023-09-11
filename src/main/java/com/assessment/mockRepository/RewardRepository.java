package com.assessment.mockRepository;

import com.assessment.Entity.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

/**
 * Here I mock the transaction database and index on userID using a Hashmap. I ignored the index on transaction ID because we don't need to query on it.
 */
@Repository
public interface RewardRepository extends CrudRepository<Transaction,String> {
    List<Transaction> getTransactionsByUserIdAndTimeBetween(String userId, long start, long end);
}
