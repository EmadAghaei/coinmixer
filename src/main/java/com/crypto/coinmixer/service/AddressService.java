package com.crypto.coinmixer.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressService {
    @Autowired
    private UserService userService;
    @Autowired
    private TransactionService transactionService;
    @Autowired


    public String getDepositAddress(Long userId, String srcAddress, String dstAddress) {


    }
}
