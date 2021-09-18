package com.crypto.coinmixer.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

@Component
public class TransactionDAO implements BaseDAO{
    @Override
    public Optional get(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public List getAll() {
        throw new NotImplementedException();
    }

    @Override
    public void save(Object o) {
        // TODO: 9/18/21 I assume it read th 'o' object and then create TransactionDAO and store it.  
    }

    @Override
    public void update(Object o, String[] params) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Object o) {
        throw new NotImplementedException();
    }
}
