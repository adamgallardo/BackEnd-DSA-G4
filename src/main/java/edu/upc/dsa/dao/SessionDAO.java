package edu.upc.dsa.dao;

import java.util.List;

public interface SessionDAO<E> {
    void save(Object entity);
    void delete(Object entity);
    Object getByName(Class theClass, String username);
    Object getById(Class theClass, String id);
    List<E> findAll(Class theClass);
    void close();
    void update(Object object);
}
