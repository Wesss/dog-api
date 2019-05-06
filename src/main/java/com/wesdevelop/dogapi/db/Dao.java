package com.wesdevelop.dogapi.db;

import java.util.Collection;
import java.util.Optional;

public interface Dao<T> {
     
    Optional<T> get(long id) throws DaoException;
     
    Collection<T> getAll() throws DaoException;
     
    void upsert(T t) throws DaoException;
     
    void delete(long id) throws DaoException;
}