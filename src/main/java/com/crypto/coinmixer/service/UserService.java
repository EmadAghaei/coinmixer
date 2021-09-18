package com.crypto.coinmixer.service;

import com.crypto.coinmixer.dao.UserDAO;
import com.crypto.coinmixer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserDAO userDAO;

    public boolean isValid(Long userId, String srcAddress) {
        Optional<User> optionalUser = userDAO.get(userId);
        return optionalUser.get().isActive() && optionalUser.get().getSourceAddress().getAddressId() == srcAddress;
    }

}
