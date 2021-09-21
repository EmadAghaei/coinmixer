package com.crypto.coinmixer.service;

import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.dao.TransactionToDestinationDetailDAO;
import com.crypto.coinmixer.domain.HouseAddress;
import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionDestinationEntity;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.service.api.CoinAPI;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TaskScheduledService {
    Logger LOG = LoggerFactory.getLogger(TaskScheduledService.class);

    @Autowired
    private HouseAddress houseAddress;
    @Autowired
    private TransactionDAO transactionDAO;
    @Autowired
    private CoinAPI coinAPI;
    @Autowired
    private TransactionToDestinationDetailDAO transactionToDestinationDetailDAO;

    /**
     * Transferring from deposit to house address
     */
    @Scheduled(fixedDelay = 1000)
    @Transactional
    public void transferFromDepositToHouseAddress() {
        LOG.info("Task of transferring from deposit to house starts");
        List<TransactionEntity> transactionList = transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_DEPOSIT.name());
        for (TransactionEntity transaction : transactionList) {
            String transferResult = transferAmount(transaction.getDepositAddress(), houseAddress.getAddressId(), transaction.getAmount());
            if (transferResult.equals("{\"status\":\"OK\"}")) {
                transaction.setStatus(Status.TRANSFERRED_TO_HOUSE.name());
                transactionDAO.updateStatusOfTransactionToHouse(transaction);
                LOG.info("transaction: "+transaction.getId()+"deposit: "+ transaction.getDepositAddress()+" transferred to house");
            }else{
                LOG.error("transaction: "+transaction.getId()+" deposit: "+ transaction.getDepositAddress()+" Failed in transferring to house");
            }
        }
        LOG.info("Task of transferring from deposit to house completed");
    }

    private String transferAmount(String from, String to, BigDecimal amount) {
        Transfer transfer = new Transfer();
        transfer.setSrcAddress(from);
        transfer.setDstAddress(to);
        transfer.setAmount(amount);
        String transferResult = coinAPI.singleTransfer(transfer);
        return transferResult;
    }

    /**
     * transferring from house to destinations
     */
    @Scheduled(fixedDelay = 1000)
    @Transactional
    protected void transferFromHouseToDestinations() {
        LOG.info("Task of transferring from deposit to house starts");
        List<TransactionEntity> transactionEntityList = transactionDAO.findAllTransactionByStatus(Status.TRANSFERRED_TO_HOUSE.name());
        for (TransactionEntity transaction : transactionEntityList) {
            for (TransactionDestinationEntity destinationObj : transaction.getTransactionDestinationEntitySet()) {
                BigDecimal amountAfterFee = destinationObj.getAmount().subtract(destinationObj.getAmount().multiply(destinationObj.getFee().divide(new BigDecimal(100))));
                BigDecimal amountToPay = amountAfterFee;// amount -(amount * (fee/100))
                BigDecimal increments = new BigDecimal(0.01);
                // todo : I assumed it transfers in 0.01 units increments. It could be improved
                while (amountToPay.compareTo(increments) > 0) {
                    String transferResult = transferAmount(houseAddress.getAddressId(), destinationObj.getDestinationAddress(), increments); // pay 0.01
                    amountToPay = amountToPay.subtract(increments);
                    if (transferResult.equals("{\"status\":\"OK\"}")) {
                        destinationObj.setStatus(Status.TRANSFERRED_TO_DESTINATIONS_SUCCESSFULLY.name());
                        transactionToDestinationDetailDAO.updateDestinationsDetails(destinationObj, increments);
                    }
                }
                // last transaction. It is possible the left amount be less than 'increaments'
                String transferResult = transferAmount(houseAddress.getAddressId(), destinationObj.getDestinationAddress(), amountToPay); // pay rest
                if (transferResult.equals("{\"status\":\"OK\"}")) {
                    destinationObj.setStatus(Status.TRANSFERRED_TO_DESTINATIONS_SUCCESSFULLY.name());
                    transactionToDestinationDetailDAO.updateDestinationsDetails(destinationObj, increments);
                }

            }
        }
    }

    @Scheduled(fixedDelay = 1000000)
    protected void settlments(){
 // TODO: 9/20/21 Steps: 1) fetch data from Table TransactionToDestinationDetail with status of COMPLETE  2) Calcualate Summation fo amounts. Consider Fee Amount.
//TODO:  3) access to Transaction by calling FK . 4) check the Amount value in Transaction Table if it is equal to sum 5) Update Statuses in 2 tables. 6) create timestamps.


    }




}
