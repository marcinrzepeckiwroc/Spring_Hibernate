package com.rzepecki.spring.hibernate.controller.forms;

import com.rzepecki.spring.hibernate.domain.dao.AuthorDao;
import com.rzepecki.spring.hibernate.domain.dao.BookDao;
import com.rzepecki.spring.hibernate.domain.dao.PublisherDao;
import com.rzepecki.spring.hibernate.domain.model.Author;
import com.rzepecki.spring.hibernate.domain.model.Book;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/forms/books")
public class BookFormController {

    private final Logger logger = LoggerFactory.getLogger(BookFormController.class);
    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final AuthorDao authorDao;

    public BookFormController(BookDao bookDao, PublisherDao publisherDao, AuthorDao authorDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
        this.authorDao = authorDao;
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers() {
        return publisherDao.findAll();
    }

    @ModelAttribute("authors")
    public List<Author> authors() {
        return authorDao.findAll();
    }

    @GetMapping
    public String prepareAllBooksPage(Model model) {
        model.addAttribute("books", bookDao.findAllWithAuthors());
        return "books/list";
    }

    @GetMapping("/add")
    public String prepareAddBookForm(Model model) {
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/add")
    public String processAddBookForm(@Valid Book book, BindingResult bindingResult) {
        logger.info("Książka do zapisu: {}", book);
        if (bindingResult.hasErrors()) {
            logger.warn("Błędne dane książki!");
            return "books/add";
        }
        logger.info("Książka przed zapisem: {}", book);
        bookDao.save(book);
        logger.info("Książka po zapisie: {}", book);
        return "redirect:/forms/books";
    }

    @GetMapping("/edit")
    public ModelAndView prepareEditBookForm(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookDao.getByIdWithAttributes(id);
        if (book == null) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            return modelAndView;
        }
        modelAndView.getModelMap().addAttribute("book", book);
        modelAndView.setViewName("books/edit");
        return modelAndView;
    }

    @PostMapping("/edit")
    public ModelAndView processEditBookForm(@Valid Book book, BindingResult bindingResult) {
        ModelAndView modelAndView = new ModelAndView();
        if (bindingResult.hasErrors()) {
            modelAndView.setViewName("books/edit");
            return modelAndView;
        }
        Book bookInDB = bookDao.getById(book.getId());
        if (bookInDB == null) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            return modelAndView;
        }
        bookDao.update(book);
        modelAndView.setViewName("redirect:/forms/books");
        return modelAndView;
    }

    @GetMapping("/delete")
    public ModelAndView prepareDeleteBookForm(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookDao.getById(id);
        if (book == null) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            return modelAndView;
        }
        modelAndView.getModelMap().addAttribute("book", book);
        modelAndView.setViewName("books/delete");
        return modelAndView;
    }

    @PostMapping("/delete")
    public ModelAndView processDeleteBookForm(Long id) {
        ModelAndView modelAndView = new ModelAndView();
        Book book = bookDao.getById(id);
        if (book == null) {
            modelAndView.setStatus(HttpStatus.NOT_FOUND);
            return modelAndView;
        }
        bookDao.remove(book);
        modelAndView.setViewName("redirect:/forms/books");
        return modelAndView;
    }


}
