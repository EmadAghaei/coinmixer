package com.crypto.coinmixer.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class DepositAddress extends Address {
    private  String name="Deposit Account";
    private final String ownerId="Crypto.com";

    @Value("#{${listOfStrings}}")
    private List<String> addressIdsList;


    public String getOwnerId() {
        return ownerId;
    }
    public String getName() {
        return name;
    }

    /**
     * Assumed mixer has limited number of addresses. It can read them from a config file. If they are not
     * limited number of file we need to read them from database.
     * @return
     */
    public String getAddressId() {
         return  addressIdsList.get(new Random().nextInt(addressIdsList.size()));
    }



}
