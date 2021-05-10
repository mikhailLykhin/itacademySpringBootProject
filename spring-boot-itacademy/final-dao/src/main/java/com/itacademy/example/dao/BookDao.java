package com.itacademy.example.dao;

import com.itacademy.example.entity.Book;
import com.itacademy.example.entity.Book_;
import org.springframework.stereotype.Repository;

import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

@Repository
public class BookDao extends AGenericDao<Book> implements IBookDao{
    public BookDao() {
        super(Book.class);
    }

    public Book getByIsbn(String isbn) throws NoResultException {
        try {
            CriteriaBuilder builder = entityManager.getCriteriaBuilder();
            CriteriaQuery<Book> query = builder.createQuery(getGenericClass());
            Root<Book> userRoot = query.from(Book.class);
            query.select(userRoot).where(builder.equal(userRoot.get(Book_.isbn), isbn));
            TypedQuery<Book> result = entityManager.createQuery(query);
            return result.getResultList().stream().findFirst().orElse(null);
        } catch (NoResultException e) {
            throw new NoResultException(e.getMessage());
        }
    }
}
