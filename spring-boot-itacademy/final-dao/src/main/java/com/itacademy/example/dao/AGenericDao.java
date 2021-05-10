package com.itacademy.example.dao;

import com.itacademy.example.entity.AEntity;
import org.hibernate.Session;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

public class AGenericDao<T extends AEntity<Integer>> implements IAGenericDao<T> {

    private final Class<T> clazz;

    public AGenericDao(Class<T> clazz) {
        this.clazz = clazz;
    }

    @PersistenceContext
    protected EntityManager entityManager;

    public void create(T entity) {
        entityManager.merge(entity);
    }

    public T get(int id) {
        return entityManager.find(getGenericClass(), id);
    }

    public List<T> getAll() {
        CriteriaBuilder builder = entityManager.getCriteriaBuilder();
        CriteriaQuery<T> query = builder.createQuery(getGenericClass());
        Root<T> root = query.from(getGenericClass());
        query.select(root);
        TypedQuery<T> result = entityManager.createQuery(query);
        return result.getResultList();
    }

    public void update(T entity) {
        entityManager.merge(entity);
        entityManager.flush();
    }

    public void delete(T entity) {
        entityManager.remove(entity);
    }

    public Class<T> getGenericClass() {
        return this.clazz;
    }
}
