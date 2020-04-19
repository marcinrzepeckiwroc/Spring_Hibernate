package com.rzepecki.spring.hibernate.controller.forms;


import com.rzepecki.spring.hibernate.domain.dao.AuthorDao;
import com.rzepecki.spring.hibernate.domain.model.Author;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forms/authors")
public class AuthorFormController {

    private final AuthorDao authorDao;

    public AuthorFormController(AuthorDao authorDao) {
        this.authorDao = authorDao;
    }

    @GetMapping
    public String authorsList(Model model){
        model.addAttribute("authors", authorDao.findAll());
        return "authors/list";
    }

    @GetMapping("/add")
    public String prepareAddAuthor(Model model){
        model.addAttribute("authors", new Author());
        return "authors/add";
    }

    @PostMapping("/add")
    public String processAddAuthor(Author author){
        authorDao.save(author);
        return "redirect:/forms/authors";
    }

    @GetMapping("/edit")
    public String prepareEditAuthor(Model model, @RequestParam Long id){
        Author author = authorDao.getById(id);
        model.addAttribute("authors", author);
        return "authors/edit";
    }

    @PostMapping("/edit")
    public String processEditAuthor(Author author){
        Author authorDaoById = authorDao.getById(author.getId());
        authorDaoById.setFirstName(author.getFirstName());
        authorDaoById.setLastName(author.getLastName());

        authorDao.update(authorDaoById);
        return "redirect:/forms/authors";
    }

    @GetMapping("/delete")
    public String prepareDeleteAuthor(Model model, @RequestParam Long id){
        Author author = authorDao.getById(id);
        model.addAttribute("author" , author);
        return "authors/delete";
    }

    @PostMapping("/delete")
    public String processDeleteAuthor(Author author){
        Author authorDaoById = authorDao.getById(author.getId());
        authorDao.remove(authorDaoById);
        return "redirect:/forms/authors";
    }
}
