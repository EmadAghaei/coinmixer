package com.crypto.coinmixer.domain;


import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.springframework.boot.test.context.SpringBootTest;


@SpringBootTest(properties = { "crypto.coinmixer.house.address.id=House" })
public class Housechecking {

    @Test
    public void houseAddressChecking(){
        HouseAddress houseAddress = new HouseAddress() ;
        Assertions.assertEquals(houseAddress.getUserId(),"Crypto.com");
        Assertions.assertEquals("House",houseAddress.getAddressId());
        Assertions.assertEquals(houseAddress.getName(),"House Account");
    }
}
