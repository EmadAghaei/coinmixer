package com.crypto.coinmixer.domain;

import java.math.BigDecimal;
import java.util.List;

public class batchTransactions extends Transaction{
    private List<DestinationAddress> destinationAddressList;
    public List<DestinationAddress> getDestinationAddressList() {
        return destinationAddressList;
    }

    public void setDestinationAddressList(List<DestinationAddress> destinationAddressList) {
        this.destinationAddressList = destinationAddressList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof batchTransactions)) return false;

        batchTransactions that = (batchTransactions) o;

        return destinationAddressList.equals(that.destinationAddressList);
    }

    @Override
    public int hashCode() {
        return destinationAddressList != null ? destinationAddressList.hashCode() : 0;
    }
}
