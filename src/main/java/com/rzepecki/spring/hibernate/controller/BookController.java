/**
 *
 1. Utwórz kontroler o nazwie `BookController`.
 2. Utwórz akcje kontrolera które będą realizować następujące operacje:
 - zapis encji
 - edycja encji
 - pobieranie
 - usuwanie

 3. Wszystkie dane wymagane do wykonania operacji mogą być zaszyte w kodzie akcji.
 */
package com.rzepecki.spring.hibernate.controller;


import com.rzepecki.spring.hibernate.domain.dao.AuthorDao;
import com.rzepecki.spring.hibernate.domain.dao.BookDao;
import com.rzepecki.spring.hibernate.domain.dao.PublisherDao;
import com.rzepecki.spring.hibernate.domain.model.Book;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Controller
@RequestMapping("/books")
public class BookController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @GetMapping
    @ResponseBody
    public String get(@RequestParam Long id) {
        return bookDao.getById(id).toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam String title,
                       @RequestParam(required = false) String description,
                       @RequestParam(required = false) Long publisherId,
                       @RequestParam(required = false) List<Long> authorIds) {
        Book book = new Book();
        book.setTitle(title);
        book.setDescription(description);
        if (publisherId != null) {
            Publisher publisher = publisherDao.getById(publisherId);
            if (publisher != null) {
                book.setPublisher(publisher);
            }
            else {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }
        }
        if (authorIds != null) {
            authorIds.stream()
                    .map(authorDao::getById)
                    .filter(Objects::nonNull)
                    .forEach(book.getAuthors()::add);
        }
        bookDao.save(book);
        return book.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam Long id,
                         @RequestParam(required = false) String title,
                         @RequestParam(required = false) String description,
                         @RequestParam(required = false) Long publisherId,
                         @RequestParam(required = false) List<Long> authorIds) {
        Book book = bookDao.getById(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        book.setTitle(title);
        book.setDescription(description);
        Optional.ofNullable(publisherId)
                .map(publisherDao::getById)
                .ifPresentOrElse(
                        book::setPublisher,
                        () -> { throw new ResponseStatusException(HttpStatus.BAD_REQUEST);}
                );
        if (authorIds != null) {
            authorIds.stream()
                    .map(authorDao::getById)
                    .filter(Objects::nonNull)
                    .forEach(book.getAuthors()::add);
        }
        return bookDao.updateAndReturn(book).toString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        Book book = bookDao.getById(id);
        if (book == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        bookDao.remove(book);
    }
}
