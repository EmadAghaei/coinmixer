package com.crypto.coinmixer.service;

import com.crypto.coinmixer.dao.TransactionDAO;
import com.crypto.coinmixer.dao.UserDAO;
import com.crypto.coinmixer.domain.Transaction;
import com.crypto.coinmixer.domain.Transfer;
import com.crypto.coinmixer.entity.TransactionEntity;
import com.crypto.coinmixer.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private TransactionDAO transactionDAO;

    /**
     * check validity of user before processing any anything
     *
     * @param userId
     * @param srcAddress
     * @return boolean
     */
    public boolean isValid(String userId, String srcAddress) {
        UserEntity user = userDAO.getByUserId(userId);
        return user !=null && user.isActive() && user.getAddressId().equals(srcAddress);
    }

}
