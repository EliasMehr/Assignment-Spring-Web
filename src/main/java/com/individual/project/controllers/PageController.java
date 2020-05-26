package com.individual.project.controllers;

import com.individual.project.domain.Person;
import com.individual.project.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/")
public class PageController {

    PersonService personService;

    @Autowired
    public PageController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping
    public String ViewPage(Model model) {
        try {
            List<Person> persons = personService.getAllPersons();
            model.addAttribute("persons", persons);
            return "index";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/add")
    public String addPerson(Model model) {
        Person person = new Person();
        model.addAttribute("person", person);
        return "new";
    }


    @PostMapping("/submit")
    public String submit(Person person, BindingResult result) {

        if (!result.hasErrors()) {
            try {
                personService.create(person);
                return "redirect:/";
            } catch (Exception e) {
                return e.getMessage();
            }
        }
        return "error";
    }

    @GetMapping("/remove/{personId}")
    public String delete(@PathVariable UUID personId) {
        try {
            personService.delete(personId);
            return "redirect:/";
        } catch (Exception e) {
            return "error";
        }
    }

    @GetMapping("/update/{personId}")
    public String update(@PathVariable UUID personId, Model model) {
        try {
            Person person = personService.findPersonById(personId);
            model.addAttribute("existingPerson", person);
            return "edit";
        } catch (Exception e) {
            return "error";
        }
    }

}
