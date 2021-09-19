package com.crypto.coinmixer.service;

import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.domain.*;
import com.crypto.coinmixer.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private Transaction transaction;
    @Autowired
    private User user;
    @Autowired
    private TransactionDAO transactionDAO;


    public synchronized void storeInitialTransaction(String userId, String depositAddress, String srcAddress, List<String> dstAddressList, BigDecimal amount, Status initiated) {
        user.setUserId(userId);
        user.setLastDepositId(depositAddress);
        transaction.setUser(user);
        transaction.setAmount(amount);
        transaction.setStatus(initiated.name());
        transaction.setDepositAddress(depositAddress);
        transaction.setSrcAddress(srcAddress);
        transaction.setTransactionDestinationSet(new HashSet<>());
        // the rest properies of transaction are null in this stage
        for (String dstAddress : dstAddressList) {
            TransactionDestination transactionDestination = new TransactionDestination();
            transactionDestination.setDestinationAddress(dstAddress);
            transactionDestination.setAmount(amount);
            transactionDestination.setStatus(initiated.name());
            transaction.getTransactionDestinationSet().add(transactionDestination);
        }
        transactionDAO.save(transaction);
    }

    public boolean checkDepositAddressConsistency(Transfer transfer) {
        TransactionEntity transactionEntity = transactionDAO.getTansactionByDepositAndUserId(transfer.getUserId(), transfer.getDstAddress(), transfer.getAmount());
        return transactionEntity != null ? true : false;
    }

    public void updateUserTransferredToDeposit(Transfer transfer) {
        transactionDAO.updateStatus(Status.TRANSFERRED_TO_DEPOSIT.name(),transfer);
    }


}


