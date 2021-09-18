package com.crypto.coinmixer.exceptoins;

public class InternalException extends Exception{

    public InternalException() {
        super("Internal Error. System couldnot complete the transaction.");
    }
}
