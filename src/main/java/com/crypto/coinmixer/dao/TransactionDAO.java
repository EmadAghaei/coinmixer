package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.entity.TransactionEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;


// I used @Component to bypass DB & Hibernate configuration. I can add them later.
@Component
public class TransactionDAO implements BaseDAO{


    @Override
    public Optional get(Long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return null;
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o, String[] params) {

    }

    @Override
    public void delete(Object o) {

    }

    public TransactionEntity getTansactionByDepositAndUserId(String userId, String depositAddress, BigDecimal amount) {
        // TODO: 9/18/21 Assume it query from database and return the value. we want to make sure the user completed the prev step. The step of getting a desposit address and storing of transactions.
        return null;
    }
}
