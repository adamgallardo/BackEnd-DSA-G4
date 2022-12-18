package edu.upc.dsa.dao;

import java.util.List;

public interface Session<E> {
    void save(Object entity);
    void deleteUser(Object entity);
    Object getByUsername(Class theClass, String username);
    Object getById(Class theClass, String id);
    List<Object> findAll(Class theClass);
    void close();
}
