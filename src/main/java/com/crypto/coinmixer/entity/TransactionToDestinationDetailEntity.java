package com.crypto.coinmixer.entity;

import org.springframework.jmx.export.annotation.ManagedAttribute;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.math.BigDecimal;
@Entity
@Table(name = "TRANSACTION_TO_DESTINATION_DETAIL")
public class TransactionToDestinationDetailEntity extends BaseEntity{
    private String destinationAddress;
    private BigDecimal amount;
    private String status;
    //FK to transaction Destination Table
    @ManyToOne
    @JoinColumn(name = "transaction_destination_ID")
    private TransactionDestinationEntity transactionDestinationEntity;

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

    public TransactionDestinationEntity getTransactionDestinationEntity() {
        return transactionDestinationEntity;
    }

    public void setTransactionDestinationEntity(TransactionDestinationEntity transactionDestinationEntity) {
        this.transactionDestinationEntity = transactionDestinationEntity;
    }
}
