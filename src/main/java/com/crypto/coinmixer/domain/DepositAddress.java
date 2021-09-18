package com.crypto.coinmixer.domain;

import com.crypto.coinmixer.dao.DepositDAO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DepositAddress extends Address {
    private  String name="Deposit Account";
    private Long ownerId="Crypto.com";
    @Autowired
    private DepositDAO depositDAO;

    //todo: update this implementation. It is for test. I assumed we only have several deposit. I use one of them randomly.

    public String getAddressId(){
        return depositDAO.getAddressIdStringForTest();
    }
    public Long getUserId() {
        return ownerId;
    }
    public String getName() {
        return name;
    }





}
