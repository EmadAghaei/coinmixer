package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.domain.DepositAddress;
import com.crypto.coinmixer.domain.SourceAddress;
import com.crypto.coinmixer.domain.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Component
public class DepositDAO implements BaseDAO {
    private List<DepositAddress> depositsAddressesList = new ArrayList<>();
    // assumed we have data in memory, instead of configuring and getting data from database

    public DepositDAO() {
        DepositAddress deposit1= new DepositAddress();
        deposit1.setAddressId("deposit1");
        depositsAddressesList.add(deposit1);

    }
    public String getAddressIdStringForTest(){
        return depositsAddressesList.get(new Random().nextInt(depositsAddressesList.size())).getAddressId();
    }
    @Override
    public Optional get(long id) {
       throw new NotImplementedException();
    }

    @Override
    public List getAll() {
        return depositsAddressesList;
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
