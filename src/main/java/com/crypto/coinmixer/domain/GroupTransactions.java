package com.crypto.coinmixer.domain;

import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class GroupTransactions extends Transaction{
    private List<String> destinationAddressList;
    public List<String> getDestinationAddressList() {
        return destinationAddressList;
    }

    public void setDestinationAddressList(List<String> destinationAddressList) {
        this.destinationAddressList = destinationAddressList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof GroupTransactions)) return false;

        GroupTransactions that = (GroupTransactions) o;

        return destinationAddressList.equals(that.destinationAddressList);
    }

    @Override
    public int hashCode() {
        return destinationAddressList != null ? destinationAddressList.hashCode() : 0;
    }
}
