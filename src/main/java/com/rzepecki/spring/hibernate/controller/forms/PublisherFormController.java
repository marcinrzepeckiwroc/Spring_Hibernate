package com.rzepecki.spring.hibernate.controller.forms;


import com.rzepecki.spring.hibernate.domain.dao.PublisherDao;
import com.rzepecki.spring.hibernate.domain.model.Author;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/forms/publishers")
public class PublisherFormController {

    private final PublisherDao publisherDao;

    public PublisherFormController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping
    public String authorsList(Model model){
        model.addAttribute("publishers", publisherDao.findAll());
        return "publishers/list";
    }

    @GetMapping("/add")
    public String prepareAddAuthor(Model model){
        model.addAttribute("publishers", new Publisher());
        return "publishers/add";
    }

    @PostMapping("/add")
    public String processAddAuthor(Publisher publisher){
        publisherDao.save(publisher);
        return "redirect:/forms/publishers";
    }

    @GetMapping("/edit")
    public String prepareEditAuthor(Model model, @RequestParam Long id){
        Publisher publisher = publisherDao.getById(id);
        model.addAttribute("publishers", publisher);
        return "publishers/edit";
    }

    @PostMapping("/edit")
    public String processEditAuthor(Publisher publisher){
        Publisher publisherDaoById = publisherDao.getById(publisher.getId());
        publisherDaoById.setName(publisher.getName());

        publisherDao.update(publisherDaoById);
        return "redirect:/forms/publishers";
    }

    @GetMapping("/delete")
    public String prepareDeleteAuthor(Model model, @RequestParam Long id){
        Publisher publisher = publisherDao.getById(id);
        model.addAttribute("publisher" , publisher);
        return "publishers/delete";
    }

    @PostMapping("/delete")
    public String processDeleteAuthor(Publisher publisher){
        Publisher publisherDaoById = publisherDao.getById(publisher.getId());
        publisherDao.delete(publisherDaoById);
        return "redirect:/forms/publishers";
    }
}
