package com.crypto.coinmixer.service;

import com.crypto.coinmixer.domain.GroupTransactions;
import com.crypto.coinmixer.domain.SourceAddress;
import com.crypto.coinmixer.domain.Status;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {

    @Autowired
    private GroupTransactions groupTransactions;

    public boolean store(Long userId, String srcAddress, List<String> dstAddressList, BigDecimal amount, Status initiated) {
        groupTransactions.setDestinationAddressList(dstAddressList);
        SourceAddress src = new SourceAddress();
        src.setUserId(userId);
        groupTransactions.setSourceAddress(src);
//        groupTransactions.

        return true;
    }
}
