package com.crypto.coinmixer.domain;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class User extends Person {
    @Autowired
    private SourceAddress sourceAddress;

       public void setSrcAddress(SourceAddress sourceAddress) {
        this.sourceAddress = sourceAddress;
    }

    public SourceAddress getSourceAddress() {
        return sourceAddress;
    }


}
