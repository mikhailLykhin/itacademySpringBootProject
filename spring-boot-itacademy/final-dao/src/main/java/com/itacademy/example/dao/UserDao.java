package com.itacademy.example.dao;

import com.itacademy.example.entity.User;
import com.itacademy.example.entity.User_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class UserDao extends AGenericDao<User> implements IUserDao {
    public UserDao() {
        super(User.class);
    }

    public User getByName(String username) throws NoResultException {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<User> query = builder.createQuery(getGenericClass());
            Root<User> userRoot = query.from(User.class);
            query.select(userRoot).where(builder.equal(userRoot.get(User_.username), username));
            TypedQuery<User> result = entityManager.createQuery(query);
            return result.getResultList().stream().findFirst().orElse(null);
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage());
        }
    }
}
