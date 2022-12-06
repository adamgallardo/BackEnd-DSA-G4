package edu.upc.dsa.dao;

import java.util.List;

public interface Session<E> {
    void save(Object entity);
    Object getByUsername(Class theClass, String username);
    List<Object> findAll(Class theClass);
    void close();
}
