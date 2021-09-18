package com.crypto.coinmixer.domain;

import java.time.Instant;

public abstract class Address {
    private String name;
    private String ownerId;
    private Instant accessDate;
    private String addressId;



    public String getName() {
        return name;
    }


    public void setName(String name) {
        this.name=name;
    }


    public String getOwnerId() {
        return ownerId;
    }


    public void setOwnerId(String ID) {
        this.ownerId=ownerId;
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
