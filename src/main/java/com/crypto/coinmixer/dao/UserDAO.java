package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.entity.UserEntity;
import org.springframework.stereotype.Component;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;



@Component
public class UserDAO implements BaseDAO<UserEntity> {
    private List<UserEntity> users = new ArrayList<>();
    // todo: assumed we have data in memory, instead of configuring and getting data from database

    public UserDAO() {
        // TODO: 9/18/21 filled for demo. They should be removed in the produciton
        UserEntity user1= new UserEntity();
        user1.setFirstName("Emad");
        user1.setLastName("Aghayi");
        user1.setUserId("eaghayi");
        user1.setAddressId("EmadAddress");
        users.add(user1);
    }

// todo: for demo it is hardcoded. I didnot want to spend time on DB part and data entry

    public  UserEntity getByUserId(String userId){
        return users.get(0).getUserId().equals(userId) ? users.get(0):null;
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

    }

    @Override
    public void update(UserEntity userEntity, String[] params) {

    }

    @Override
    public void delete(UserEntity userEntity) {

    }
}
