package com.crypto.coinmixer.service;

import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.domain.Transaction;
import com.crypto.coinmixer.domain.TransactionDestination;
import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private Transaction transaction;
    @Autowired
    private User user;
    @Autowired
    private TransactionDAO transactionDAO;


    public void storeInitialTransaction(String userId,String depositAddress, String srcAddress, List<String> dstAddressList, BigDecimal amount, Status initiated) {
        user.setUserId(userId);
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setStatus(initiated.name());
        transaction.setDepositAddress(depositAddress);
        transaction.setSrcAddress(srcAddress);
        // the rest properies of transaction are null in this stage
        for(String dstAddress: dstAddressList){
            TransactionDestination transactionDestination = new TransactionDestination();
            transactionDestination.setDestinationAddress(dstAddress);
            transactionDestination.setAmount(amount);
            transactionDestination.setStatus(initiated.name());
            transaction.getTransactionDestinationSet().add(transactionDestination);
        }
        transactionDAO.save(transaction);
    }
}


