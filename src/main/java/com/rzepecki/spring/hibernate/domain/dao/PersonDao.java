package com.rzepecki.spring.hibernate.domain.dao;

import com.rzepecki.spring.hibernate.domain.model.Person;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class PersonDao {
    @PersistenceContext
    private EntityManager entityManager;
    public void save(Person person) {
        entityManager.persist(person);
    }
    public Person getById(Long id) {
        return entityManager.find(Person.class, id);
    }
    public void update(Person person) {
        entityManager.merge(person);
    }
    public void delete(Person person) {
        entityManager.remove(entityManager.contains(person) ?
                person : entityManager.merge(person));
    }
}
