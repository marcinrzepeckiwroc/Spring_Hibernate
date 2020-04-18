package com.rzepecki.spring.hibernate.controller;

import com.rzepecki.spring.hibernate.domain.dao.PersonDao;
import com.rzepecki.spring.hibernate.domain.model.Person;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/persons")
public class PersonController {

    private final PersonDao personDao;

    public PersonController(PersonDao personDao) {
        this.personDao = personDao;
    }


    @GetMapping("/add")
    public String addPerson(Model model){
        model.addAttribute("person", new Person());
        return "persons/add";
    }

    @PostMapping("/add")
    public String processAddPerson(String login, String email, String password, Model model){
        Person person = new Person();
        person.setLogin(login);
        person.setEmail(email);
        person.setPassword(password);
        personDao.save(person);

        model.addAttribute("modelPerson", person);
        return "persons/add-success";
    }

    @PostMapping("/add-bind")
    public String processAddPersonBind(@ModelAttribute ("modelPerson") Person person){
        personDao.save(person);
        return "persons/add-success";
    }
}
