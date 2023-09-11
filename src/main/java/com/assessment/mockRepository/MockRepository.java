package com.assessment.mockRepository;

import com.assessment.Entity.Transaction;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Here I mock the transaction database and index on userID using a Hashmap. I ignored the index on transaction ID because we don't need to query on it.
 */
@Repository
public class MockRepository {

    private final HashMap<String, List<Transaction>> transactions;


    public MockRepository() {
        this.transactions = new HashMap<>();
    }

    /**
     * This method take a transaction object without transactionId as parameter, give the object an ID, and store it in the database.
     * @param transaction Transaction object without transactionId
     * @return TransactionId
     */
    public String addTransaction(Transaction transaction){
        String id=UUID.randomUUID().toString().replaceAll("-", "");
        transaction.setTransactionId(id);
        transactions.computeIfAbsent(transaction.getUserId(),k->new ArrayList<>());
        transactions.get(transaction.getUserId()).add(transaction);
        return id;
    }

    /**
     * This method query transactions between given time period, in accordance to the userId.
     * @param userId
     * @param start start timestamp
     * @param end end timestamp
     * @return The list of eligible transactions
     */
    public List<Transaction>  queryTransactions(String userId, long start, long end){
        var userTransactions= Optional.ofNullable(transactions.get(userId));
        return userTransactions.orElseGet(ArrayList::new)
                .stream()
                .filter(t -> t.getTime()>=start&&t.getTime()<=end)
                .collect(Collectors.toList());
    }
}
