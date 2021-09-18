package com.crypto.coinmixer.domain;

import java.time.Instant;

public abstract class Address {
    private String name;
    private String userId;
    private Instant accessDate;
    private String addressId;



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name=name;
    }


    public String getUserId() {
        return userId;
    }


    public void setUserId(String ID) {
        this.userId = userId;
    }


    public Instant getLasAccessedDate() {
        return accessDate;
    }


    public void setLasAccessedDate(Instant instant) {
        this.accessDate=accessDate;
    }


    public String getAddressId() {
        return addressId;
    }


    public void setAddressId(String addressId) {
        this.addressId=addressId;
    }

}
