package com.rzepecki.spring.hibernate.domain.dao;

import com.rzepecki.spring.hibernate.domain.model.Author;
import com.rzepecki.spring.hibernate.domain.model.Book;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
@Transactional
public class AuthorDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Author getById(Long id) {
        return entityManager.find(Author.class, id);
    }

    public void save(Author entity) {
        entityManager.persist(entity);
    }

    public void update(Author entity) {
        entityManager.merge(entity);
    }

    public Author updateAndReturn(Author entity) {
        return entityManager.merge(entity);
    }

    public void remove(Author entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public List<Author> findAll() {
        return entityManager.createQuery("SELECT a FROM Author a", Author.class).getResultList();
    }
}
