package com.rzepecki.spring.hibernate.controller;

import com.rzepecki.spring.hibernate.domain.dao.PublisherDao;
import com.rzepecki.spring.hibernate.domain.model.Publisher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/publisher")
public class PublisherController {

    private final PublisherDao publisherDao;

    public PublisherController(PublisherDao publisherDao) {
        this.publisherDao = publisherDao;
    }

    @GetMapping("/save")
    @ResponseBody
    public String save(){
        Publisher publisher = new Publisher();
        publisher.setName("publisher1");
        publisherDao.save(publisher);
        return publisher.toString();
    }

    @GetMapping("/edit/{id}")
    @ResponseBody
    public String edit(@PathVariable Long id){
        Publisher publisher = publisherDao.getById(id);
        publisher.setName("publisher2");
        publisherDao.update(publisher);
        return publisher.toString();
    }

    @GetMapping("/{id}")
    @ResponseBody
    public String get(@PathVariable Long id){
        return publisherDao.getById(id).toString();
    }

    @GetMapping("/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Long id){
        publisherDao.delete(publisherDao.getById(id));
        return id+" deleted";
    }

}
