package com.crypto.coinmixer.domain;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class HouseAddress extends Address {

    private  String name="House Account";
    private final Long userId =555L;

    @Value("${crypto.coinmixer.house.address.id}")
    private String addressId="House";


    @Override
    public String getName() {
        return name;
    }

    @Override
    public void setName(String name) { }

    @Override
    public Long getUserId() {
        return userId;
    }

    @Override
    public void setUserId(Long ID) {}

       @Override
    public String getAddressId() {
        return addressId;
    }

    @Override
    public void setAddressId(String addressId) { }
}
