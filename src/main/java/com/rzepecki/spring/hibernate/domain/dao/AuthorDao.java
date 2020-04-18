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
public class AuthorDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Author author){
        entityManager.persist(author);
    }

    @Transactional
    public void update(Author author){
        entityManager.merge(author);
    }

    public Author findById(Long id){
        return entityManager.find(Author.class, id);
    }

    @Transactional
    public void delete(Author author){
        entityManager.remove(entityManager.contains(author) ?
                author : entityManager.merge(author));
    }

    public List<Author> findAll(){
        TypedQuery<Author> query = entityManager.createQuery("SELECT a FROM Author a", Author.class);
        List<Author> resultList = query.getResultList();
        return resultList;
    }
}
