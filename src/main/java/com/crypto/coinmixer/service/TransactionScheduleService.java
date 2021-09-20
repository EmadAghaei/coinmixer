package com.crypto.coinmixer.service;

import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.domain.*;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.service.api.CoinAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class TransactionScheduleService {
    Logger LOG = LoggerFactory.getLogger(TransactionScheduleService.class);

    @Autowired
    private HouseAddress houseAddress;
    @Autowired
    private TransactionDAO transactionDAO;
    @Autowired
    private CoinAPI coinAPI;

    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void transferFromDepositToHouseAddress() {
        LOG.info("Task of transferring from deposit to house starts");
        List<Transaction> transactionList = transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_DEPOSIT.name());
        for (Transaction transaction : transactionList) {
            Transfer transfer = new Transfer();
            transfer.setSrcAddress(transaction.getDepositAddress());
            transfer.setDstAddress(houseAddress.getAddressId());
            transfer.setAmount(transaction.getAmount());

            String transferResult = coinAPI.singleTransfer(transfer);
            if (transferResult.equals("{\"status\":\"OK\"}")) {
                transaction.setStatus(Status.TRANSFERRED_TO_HOUSE.name());
                transactionDAO.updateStatusOfTransactionToHouse(transaction);
            }

        }
    }

//    @Scheduled(fixedDelay = 1000)
//    @Transactional
//    public void transferFromHouseToDestination() {
//        LOG.info("Task of transferring from deposit to house starts");
//        List<Transaction> transactionEntityList = transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_HOUSE.name());
//        for (Transaction transaction : transactionEntityList) {
//            Transfer transfer = new Transfer();
//            transfer.setSrcAddress(houseAddress.getAddressId());
//            for(TransactionDestination transactionDestination)
//            transfer.setDstAddress(transaction);
//            transfer.setAmount(transaction.getAmount());
//
//            String transferResult = coinAPI.singleTransfer(transfer);
//            if (transferResult.equals("{\"status\":\"OK\"}")) {
//                transaction.setStatus(Status.TRANSFERRED_TO_HOUSE.name());
//                transactionDAO.updateStatusOfTransactionToHouse(transaction);
//            }
//
//        }
//    }


}
