package com.rzepecki.spring.hibernate.controller;


import com.rzepecki.spring.hibernate.domain.model.Author;
import com.rzepecki.spring.hibernate.domain.model.Book;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;


@Controller
@RequestMapping("/validation")
public class ValidationController {

    private static final Logger logger = LoggerFactory.getLogger(ValidationController.class);

    private final Validator validator;

    public ValidationController(Validator validator) {
        this.validator = validator;
    }

    @GetMapping
    public String test(Model model){
        Book book = new Book();
        book.setTitle("a");
        book.setPages(-1);


        Set<ConstraintViolation<Book>> violations = validator.validate(book);
        if (!violations.isEmpty()) {
            for (ConstraintViolation<Book> violation : violations) {
                logger.warn("Błędna wartość {} dla pola {}, błąd: {}", violation.getInvalidValue(), violation.getPropertyPath(), violation.getMessage());
            }
        }
        model.addAttribute("validations",violations);
        return "validations/validation";
    }

    @GetMapping("/author")
    @ResponseBody
    public String validateAuthor() {
        Author author = new Author();
        author.setYearOfBirth(2005);

        Set<ConstraintViolation<Author>> violations = validator.validate(author);
        for (ConstraintViolation<Author> violation : violations) {
            logger.warn("Błędna wartość {} dla pola {}, błąd: {}", violation.getInvalidValue(), violation.getPropertyPath(), violation.getMessage());
        }

        return "poszło dla autora";
    }

    @GetMapping("/view")
    public String processValidation(Model model) {
        Book book = new Book();
        book.setTitle("a");
        book.setRating(20.0);
        book.setAuthors(new HashSet<>());
        book.setPages(-2);

        Set<ConstraintViolation<Book>> violations = validator.validate(book);

        Map<String, Set<String>> errors = new HashMap<>();
        for (ConstraintViolation<Book> violation : violations) {
            String field = violation.getPropertyPath().toString();
            errors.merge(field, Set.of(violation.getMessage()), (set1, set2) -> {
                HashSet<String> resultSet = new HashSet<>();
                resultSet.addAll(set1);
                resultSet.addAll(set2);
                return resultSet;
            });
        }

        model.addAttribute("errors", violations);
        model.addAttribute("errorsMap", errors);
        return "validations/test";
    }
}
