package com.crypto.coinmixer.domain;

import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.entity.TransactionToDestinationDetailEntity;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Set;

@Component
public class TransactionDestination {
    private TransactionEntity transactionEntity;
    private String destinationAddress;
    private BigDecimal amount;
    private String status;
    private Set<TransactionToDestinationDetailEntity> transactionToDestinationDetailEntitySet;

    public TransactionEntity getTransactionEntity() {
        return transactionEntity;
    }

    public void setTransactionEntity(TransactionEntity transactionEntity) {
        this.transactionEntity = transactionEntity;
    }

    public String getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddress(String destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Set<TransactionToDestinationDetailEntity> getTransactionToDestinationDetailEntitySet() {
        return transactionToDestinationDetailEntitySet;
    }

    public void setTransactionToDestinationDetailEntitySet(Set<TransactionToDestinationDetailEntity> transactionToDestinationDetailEntitySet) {
        this.transactionToDestinationDetailEntitySet = transactionToDestinationDetailEntitySet;
    }
}
