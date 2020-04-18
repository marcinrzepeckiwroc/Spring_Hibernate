package com.rzepecki.spring.hibernate.controller;

import com.rzepecki.spring.hibernate.domain.model.Student;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;


@Controller
@RequestMapping("/students")
public class StudentController {

    private final static Logger logger = LoggerFactory.getLogger(StudentController.class);

    @GetMapping("/add")
    public String getForm(Model model){
        model.addAttribute("modelStudent" , new Student());
        return "students/add";
    }

    @PostMapping("/add")
    public String processAddPersonBind(@ModelAttribute("modelStudent") Student student){
        logger.info(student.toString());
        return "students/add-success";
    }

    @ModelAttribute("countryItems")
    public List<String> countries() {
        return Arrays.asList("Poland", "Germany", "France", "Russia", "Denmark");
    }

    @ModelAttribute("programingItems")
    public Collection<String> skill(){
        List<String> skills = new ArrayList<>();
        skills.add("Java");
        skills.add("SQL");
        skills.add("JavaScript");
        return skills;
    }

    @ModelAttribute("hobbiesItems")
    public Collection<String> hobby(){
        List<String> hobby = new ArrayList<>();
        hobby.add("Programowanie");
        hobby.add("Siłownia");
        hobby.add("Ksiązki");
        return hobby;
    }
}
