package com.crypto.domain;


import com.crypto.coinmixer.domain.HouseAddress;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.PropertySource;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;


@SpringBootTest(properties = { "crypto.coinmixer.house.address.id=House" })
public class Housechecking {

    @Test
    public void houseAddressChecking(){
        HouseAddress houseAddress = new HouseAddress() ;
        Assertions.assertEquals(houseAddress.getOwnerId(),"Crypto.com");
        Assertions.assertEquals("House",houseAddress.getAddressId());
        Assertions.assertEquals(houseAddress.getName(),"House Account");
    }
}
