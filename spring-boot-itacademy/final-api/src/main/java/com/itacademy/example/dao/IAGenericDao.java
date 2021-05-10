package com.itacademy.example.dao;

import com.itacademy.example.entity.AEntity;

import java.util.List;

public interface IAGenericDao<T extends AEntity<Integer>>{

    Class<T> getGenericClass();

    void create(T entity);

    T get(int id);

    List<T> getAll();

    void update (T entity);

    void delete (T entity);

}
