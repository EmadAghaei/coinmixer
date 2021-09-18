package com.crypto.coinmixer.entity;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "USER")
public class UserEntity extends BaseEntity{
    @Column(name = "USER_ID",unique = true)
    private String userId;

    private String lastName;
    private String firstName;
    private String addressId;
    private String lastDepositId;
    private boolean active;
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TransactionEntity> transactionEntitySet;

    public String getLastDepositId() {
        return lastDepositId;
    }

    public void setLastDepositId(String lastDepositId) {
        this.lastDepositId = lastDepositId;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getAddressId() {
        return addressId;
    }

    public void setAddressId(String addressId) {
        this.addressId = addressId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
}
