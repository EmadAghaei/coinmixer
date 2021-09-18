package com.crypto.coinmixer.exceptoins;

public class UserException extends Exception {
    public UserException(){
        super("User is not valid");
    }
}
