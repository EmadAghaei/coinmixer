package com.crypto.coinmixer.dao;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class TransactionDAO implements BaseDAO{
    @Override
    public Optional get(long id) {
        throw new NotImplementedException();
    }

    @Override
    public List getAll() {
        throw new NotImplementedException();
    }

    @Override
    public void save(Object o) {
        throw new NotImplementedException();
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
