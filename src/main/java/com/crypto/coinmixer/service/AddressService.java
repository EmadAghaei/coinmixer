package com.crypto.coinmixer.service;

import com.crypto.coinmixer.domain.DepositAddress;
import com.crypto.coinmixer.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class AddressService {

    @Autowired
    private TransactionService transactionService;
    @Autowired
    DepositAddress depositAddress;

    public String getDepositAddress(Long userId, String srcAddress, List<String> dstAddress, BigDecimal amount) {

         return transactionService.store(userId,srcAddress,dstAddress,amount,Status.INITIATED) == true ?
                         depositAddress.getAddressId() : null;



    }
}
