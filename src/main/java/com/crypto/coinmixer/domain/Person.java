package com.crypto.coinmixer.domain;

import org.springframework.stereotype.Component;

@Component
public abstract class Person {

    private String lastName;
    private String firstName;
    private boolean active;


    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getFirstName() {
        return firstName;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getLastName() {
        return lastName;
    }


    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
