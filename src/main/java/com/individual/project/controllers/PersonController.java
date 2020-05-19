package com.individual.project.controllers;

import com.individual.project.models.Person;
import com.individual.project.services.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/")
public class PersonController {

    PersonService personService;

    public PersonController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> findAllPersons() {
        try {
            List<Person> allPersons = personService.getAllPersons();
            return ResponseEntity.ok(allPersons);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
    }

    @Transactional
    @PostMapping("persons")
    public ResponseEntity<String> create(@RequestBody @Valid Person person) {
        try {
            personService.create(person);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok("Created person successfully");
    }


    @Transactional
    @PutMapping("persons/{personId}")
    public ResponseEntity<String> update(@PathVariable UUID personId, @RequestBody Person person) {
        try {
            personService.update(personId, person);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok("Successfully updated person with id: " + personId);
    }

    @Transactional
    @DeleteMapping("persons/{personId}")
    public ResponseEntity<String> delete(@PathVariable UUID personId) {
        try {
            personService.delete(personId);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, e.getMessage());
        }
        return ResponseEntity.ok("Deleted person with id: " + personId + " successfully");
    }

}
