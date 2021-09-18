package com.crypto.coinmixer.domain;

import java.math.BigDecimal;
import java.util.List;

public abstract class Transaction {
    private SourceAddress sourceAddress;
    private DestinationAddress destinationAddress;
    private BigDecimal amount = new BigDecimal(0);
    private BigDecimal fee = new BigDecimal(0);

    public Status getTransactionStatus() {
        return transactionStatus;
    }

    public void setTransactionStatus(Status transactionStatus) {
        this.transactionStatus = transactionStatus;
    }

    private Status transactionStatus;


    public SourceAddress getSourceAddress() {
        return sourceAddress;
    }

    public void setSourceAddress(SourceAddress sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public DestinationAddress getDestinationAddress() {
        return destinationAddress;
    }

    public void setDestinationAddressList(DestinationAddress destinationAddress) {
        this.destinationAddress = destinationAddress;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getFee() {
        return fee;
    }

    public void setFee(BigDecimal fee) {
        this.fee = fee;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;

        Transaction that = (Transaction) o;

        if (sourceAddress != null ? !sourceAddress.equals(that.sourceAddress) : that.sourceAddress != null)
            return false;
        if (destinationAddress != null ? !destinationAddress.equals(that.destinationAddress) : that.destinationAddress != null)
            return false;
        if (amount != null ? !amount.equals(that.amount) : that.amount != null) return false;
        if (fee != null ? !fee.equals(that.fee) : that.fee != null) return false;
        return transactionStatus == that.transactionStatus;
    }

    @Override
    public int hashCode() {
        int result = sourceAddress != null ? sourceAddress.hashCode() : 0;
        result = 31 * result + (destinationAddress != null ? destinationAddress.hashCode() : 0);
        result = 31 * result + (amount != null ? amount.hashCode() : 0);
        result = 31 * result + (fee != null ? fee.hashCode() : 0);
        result = 31 * result + (transactionStatus != null ? transactionStatus.hashCode() : 0);
        return result;
    }
}
