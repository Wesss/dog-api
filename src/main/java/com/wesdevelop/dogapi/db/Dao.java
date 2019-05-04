package com.wesdevelop.dogapi.db;

import java.util.List;
import java.util.Optional;

public interface Dao<T> {
     
    Optional<T> get(long id) throws Exception;
     
    List<T> getAll() throws Exception;
     
    void create(T t) throws Exception;
     
    void update(long id, T t) throws Exception;
     
    void delete(long id) throws Exception;
}