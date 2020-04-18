package com.rzepecki.spring.hibernate.domain.dao;

import com.rzepecki.spring.hibernate.domain.model.Book;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class PublisherDao {

    @PersistenceContext
    EntityManager entityManager;

    @Transactional
    public void save(Publisher publisher){
        entityManager.persist(publisher);
    }

    @Transactional
    public void update(Publisher publisher){
        entityManager.merge(publisher);
    }

    public Publisher getById(Long id){
        return entityManager.find(Publisher.class, id);
    }

    @Transactional
    public void delete(Publisher publisher){
        entityManager.remove(entityManager.contains(publisher) ?
                publisher : entityManager.merge(publisher));
    }

    public List<Publisher> findAll(){
        TypedQuery<Publisher> query = entityManager.createQuery("SELECT p FROM Publisher p", Publisher.class);
        List<Publisher> resultList = query.getResultList();
        return resultList;
    }


}
