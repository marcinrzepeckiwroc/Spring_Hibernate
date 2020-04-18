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
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Repository
@Transactional
public class BookDao {

    @PersistenceContext
    private EntityManager entityManager;

    public Book getById(Long id) {
        return entityManager.find(Book.class, id);
    }

    public Book getByIdWithAttributes(Long id) {
        Book book = entityManager.find(Book.class, id);
        Hibernate.initialize(book.getAuthors());
        return book;
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

    public List<Book> findAll() {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b", Book.class);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllWithAuthors() {
        return entityManager
                .createQuery("SELECT DISTINCT b FROM Book b LEFT JOIN FETCH b.authors", Book.class)
                .getResultList();
    }

    public List<Book> getRatingList(Double rating) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.rating = :rating", Book.class);
        query.setParameter("rating", rating);
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllWithAuthor(Author author) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b WHERE b.authors IN :authors", Book.class);
        query.setParameter("authors", Set.of(author));
        List<Book> books = query.getResultList();
        return books;
    }

    public List<Book> findAllWithAuthorWithJoins(Author author) {
        TypedQuery<Book> query = entityManager.createQuery("SELECT b FROM Book b JOIN b.authors a WHERE a = :author", Book.class);
        query.setParameter("author", author);
        return query.getResultList();
    }
}
