package com.crypto.coinmixer.dao;

import java.util.List;
import java.util.Optional;

public interface BaseDAO<T> {
    Optional<T> get(Long id);

    List<T> getAll();

    void save(T t);

    void update(T t, String[] params);

    void delete(T t);
}
