package com.rzepecki.spring.hibernate.controller;

import com.rzepecki.spring.hibernate.domain.dao.BookDao;
import com.rzepecki.spring.hibernate.domain.dao.PublisherDao;
import com.rzepecki.spring.hibernate.domain.model.Book;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/forms/books")
public class BookFormController {

    private final BookDao bookDao;
    private final PublisherDao publisherDao;
    private final Logger logger = LoggerFactory.getLogger(BookFormController.class);

    public BookFormController(BookDao bookDao, PublisherDao publisherDao) {
        this.bookDao = bookDao;
        this.publisherDao = publisherDao;
    }

    @ModelAttribute("publishers")
    public List<Publisher> publishers(){
        return publisherDao.findAll();
    }

    @GetMapping
    public String prepareAllBooks(Model model){
        model.addAttribute("books", bookDao.findAllWithAuthors());
        return "books/list";
    }

    @GetMapping("/add")
    public String prepareAddBookForm(Model model){
        model.addAttribute("book", new Book());
        return "books/add";
    }

    @PostMapping("/add")
    public String processAddBookForm(Book book){
        logger.info("Book before writing {}", book);
        bookDao.save(book);
        return "redirect:/forms/books";
    }
}
