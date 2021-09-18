package com.crypto.coinmixer.dao;

import com.crypto.coinmixer.domain.SourceAddress;
import com.crypto.coinmixer.domain.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;



@Component
public class UserDAO implements BaseDAO<User> {
    private List<User> users = new ArrayList<>();
    // assumed we have data in memory, instead of configuring and getting data from database

    public UserDAO() {
        User user1= new User();
        user1.isActive();
        user1.setFirstName("Emad");
        user1.setLastName("Aghayi");
        user1.setID("1234");
       SourceAddress sourceAddress= new SourceAddress();
       sourceAddress.setAddressId("eaghayi");
       sourceAddress.setUserId(1234L);
        user1.setSrcAddress(sourceAddress);
        users.add(user1);
    }

    @Override
    public Optional<User> get(long id) {
        return Optional.ofNullable(users.get((int) id));
    }

    @Override
    public List<User> getAll() {
        return users;
    }

    @Override
    public void save(User user) {
        users.add(user);
    }

    @Override
    public void update(User user, String[] params) {
        user.setFirstName(Objects.requireNonNull(
                params[0], "Name cannot be null"));
        user.setLastName(Objects.requireNonNull(
                params[1], "LastName cannot be null"));
        user.setID(Objects.requireNonNull(
                params[1], "ID can not be null"));

        users.add(user);
    }

    @Override
    public void delete(User user) {
        users.remove(user);
    }


}
