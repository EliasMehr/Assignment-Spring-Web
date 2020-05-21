package com.individual.project.controllers;

import com.individual.project.Exception.APIRequestException;
import com.individual.project.configuration.HateoasAssembler;
import com.individual.project.models.Person;
import com.individual.project.services.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import javax.validation.ValidationException;
import java.util.*;

@RestController
@RequestMapping("/")
public class PersonController {

    private PersonService personService;
    private HateoasAssembler hateoasAssembler;

    @Autowired
    public PersonController(PersonService personService, HateoasAssembler hateoasAssembler) {
        this.personService = personService;
        this.hateoasAssembler = hateoasAssembler;
    }

    @GetMapping("persons")
    public ResponseEntity<List<Person>> findAllPersons() {

        List<Person> allPersons = personService.getAllPersons();
        allPersons.forEach(hateoasAssembler::addHATEOASLinks);
        return ResponseEntity.ok(allPersons);
    }

    @GetMapping("persons/{id}")
    public ResponseEntity<Person> findPersonById(@PathVariable UUID id) {
        try {
            Person personById = personService.findPersonById(id);
            hateoasAssembler.addHATEOASLinks(personById);
            return ResponseEntity.ok(personById);
        } catch (NoSuchElementException e) {
            throw new APIRequestException("No such person found");
        }
    }

    @Transactional
    @PostMapping("persons")
    public ResponseEntity<Person> create(@Valid @RequestBody Person person) {
        try {
            personService.create(person);
            hateoasAssembler.addHATEOASLinks(person);
            return ResponseEntity.ok(person);
        } catch (Exception e) {
            throw new ValidationException(e.getMessage());
        }
    }

    @Transactional
    @PutMapping("persons/{personId}")
    public ResponseEntity<String> update(@PathVariable UUID personId, @RequestBody Person person) {
        try {
            personService.update(personId, person);
        } catch (Exception e) {
            throw new APIRequestException("Could not update current person");
        }
        return ResponseEntity.ok("Successfully updated person with id: " + personId);
    }

    @Transactional
    @DeleteMapping("persons/{personId}")
    public ResponseEntity<String> delete(@PathVariable UUID personId) {
        try {
            personService.delete(personId);
        } catch (Exception e) {
            throw new APIRequestException("Could not delete current person");
        }
        return ResponseEntity.ok("Deleted person with id: " + personId + " successfully");
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler({MethodArgumentNotValidException.class})
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }


}
