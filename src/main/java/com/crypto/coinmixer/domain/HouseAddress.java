package com.crypto.coinmixer.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HouseAddress extends Address {

    private  String name="House Account";
    private final String ownerId="Crypto.com";

    @Value("${crypto.coinmixer.house.address.id}")
    private String addressId="House";


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) { }

    @Override
    public String getOwnerId() {
        return ownerId;
    }

    @Override
    public void setOwnerId(String ID) {}

       @Override
    public String getAddressId() {
        return addressId;
    }

    @Override
    public void setAddressId(String addressId) { }
}
