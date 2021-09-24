package com.crypto.coinmixer.service;

import com.crypto.coinmixer.domain.Status;
import com.crypto.coinmixer.util.RandomUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AddressService {
    @Autowired
    private TransactionService transactionService;

    /**
     * it create a unique deposit address for the user.
     * @param userId
     * @param srcAddress
     * @param dstAddressList
     * @param amount
     * @return
     * @throws InterruptedException
     */
    public String getDepositAddress(String userId, String srcAddress, List<String> dstAddressList, BigDecimal amount) throws InterruptedException {
        String depositAddress = RandomUtil.createRandomAlphaNumeric();// TODO: 9/18/21  I assumed it for simplicity. I have to change it later. I  do know how you are building addresses. Is that kind of private key?!
        return depositAddress;

    }
}
