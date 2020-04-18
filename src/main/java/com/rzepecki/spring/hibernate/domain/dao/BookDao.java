/**
 * 1. Na podstawie przykładu z prezentacji utwórz klasę `BookDao`.
 * 2. Klasa ma realizować podstawowe operacje na encji:
 * - zapis encji
 * - edycja encji
 * - pobieranie po id
 * - usuwanie po id
 */
package com.rzepecki.spring.hibernate.domain.dao;

import com.rzepecki.spring.hibernate.domain.model.Author;
import com.rzepecki.spring.hibernate.domain.model.Book;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.stream.Collectors;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Book getById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public void save(Book entity) {
        entityManager.persist(entity);
    }

    public void update(Book entity) {
        entityManager.merge(entity);
    }

    public Book updateAndReturn(Book entity) {
        return entityManager.merge(entity);
    }

    public void remove(Book entity) {
        entityManager.remove(entityManager.contains(entity) ? entity : entityManager.merge(entity));
    }

    public List<Book> findAll(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        return query.getResultList();
    }

    public List<Book> getRatingList(Double rating){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :rating", Book.class);
        query.setParameter("rating", rating);
        List<Book> resultList = query.getResultList();
        return resultList;
    }

    public List<Book> findAllWherePublisherIsNotNull(){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisherId is not null", Book.class);
        return query.getResultList();
    }

    public List<Book> findAllWherePublisherNameIsLike(String name){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher.name = :name", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
    }

    public List<Book> findAllWhereAuthorNameIsLike(String name){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a.firstName=:name ", Book.class);
        query.setParameter("name", name);
        return query.getResultList();
        //return resultList.stream().filter(book -> book.getAuthors().stream().anyMatch(author -> author.getFirstName().equals(name))).collect(Collectors.toList());
    }

    public List<Book> findAllWherePublisherIsLike(Publisher publisher){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.publisher = :publisher", Book.class);
        query.setParameter("publisher", publisher);
        return query.getResultList();
    }

    public List<Book> findAllWhereAuthorIsLike(Author author){
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a =:author ", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
        //return resultList.stream().filter(book -> book.getAuthors().stream().anyMatch(author -> author.getFirstName().equals(name))).collect(Collectors.toList());
    }


}
