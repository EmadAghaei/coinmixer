package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.entity.UserEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Component
public class UserDAO implements BaseDAO<UserEntity> {


    public  UserEntity getByUserId(String userId){
        throw new NotImplementedException();
    }
    @Override
    public Optional<UserEntity> get(Long id) {
        throw new NotImplementedException();
    }

    @Override
    public List<UserEntity> getAll() {
        return null;
    }

    @Override
    public void save(UserEntity userEntity) {
        throw new NotImplementedException();
    }

    @Override
    public void update(UserEntity userEntity, String[] params) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(UserEntity userEntity) {
        throw new NotImplementedException();
    }
}
