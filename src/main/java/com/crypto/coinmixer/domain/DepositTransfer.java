package com.crypto.coinmixer.domain;

public class DepositTransfer {
    public String fromAddress;
    public String toAddress;
    public String amount;

    public DepositTransfer (String fromAddress, String toAddress, String amount){
        this.fromAddress=fromAddress;
        this.toAddress=toAddress;
        this.amount=amount;
    }


}
