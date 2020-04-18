package com.rzepecki.spring.hibernate.controller;

import com.rzepecki.spring.hibernate.domain.dao.AuthorDao;
import com.rzepecki.spring.hibernate.domain.model.Author;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/author")
public class AuthorController {

    private final AuthorDao authorDao;

    public AuthorController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String save(){
        Author author = new Author();
        author.setFirstName("Janusz");
        author.setLastName("Karolak");
        authorDao.save(author);
        return "Autor dodana "+ author.toString();
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable Long id){
        Author author = authorDao.findById(id);
        author.setLastName("Kisiel");
        authorDao.update(author);
        return author.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String get(@PathVariable Long id){
        return authorDao.findById(id).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id){
        authorDao.delete(authorDao.findById(id));
        return "Autor "+id+" skasowany";
    }
}
