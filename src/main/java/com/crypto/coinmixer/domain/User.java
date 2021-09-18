package com.crypto.coinmixer.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User extends Person {
    private String userId;
    private String addressId;
    private String lastDepositId;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getLastDepositId() {
        return lastDepositId;
    }

    public void setLastDepositId(String lastDepositId) {
        this.lastDepositId = lastDepositId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (userId != null ? !userId.equals(user.userId) : user.userId != null) return false;
        if (addressId != null ? !addressId.equals(user.addressId) : user.addressId != null) return false;
        return lastDepositId != null ? lastDepositId.equals(user.lastDepositId) : user.lastDepositId == null;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (addressId != null ? addressId.hashCode() : 0);
        result = 31 * result + (lastDepositId != null ? lastDepositId.hashCode() : 0);
        return result;
    }
}
