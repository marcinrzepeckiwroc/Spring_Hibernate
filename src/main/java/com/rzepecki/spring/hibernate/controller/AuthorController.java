package com.rzepecki.spring.hibernate.controller;

import com.rzepecki.spring.hibernate.domain.dao.AuthorDao;
import com.rzepecki.spring.hibernate.domain.model.Author;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@Controller
@RequestMapping("/authors")
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping
    @ResponseBody
    public String get(@RequestParam Long id) {
        return authorDao.getById(id).toString();
    }

    @RequestMapping("/save")
    @ResponseBody
    public String save(@RequestParam String firstName,
                       @RequestParam String lastName) {
        Author author = new Author();
        author.setFirstName(firstName);
        author.setLastName(lastName);
        authorDao.save(author);
        return author.toString();
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam Long id,
                         @RequestParam String firstName,
                         @RequestParam String lastName) {
        Author author = authorDao.getById(id);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        author.setFirstName(firstName);
        author.setLastName(lastName);
        return authorDao.updateAndReturn(author).toString();
    }

    @RequestMapping("/delete")
    @ResponseBody
    public void delete(@RequestParam Long id) {
        Author author = authorDao.getById(id);
        if (author == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        authorDao.remove(author);
    }
}
