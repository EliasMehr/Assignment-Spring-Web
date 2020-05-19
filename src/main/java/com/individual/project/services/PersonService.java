package com.individual.project.services;

import com.individual.project.models.Person;
import com.individual.project.repositories.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

@Service
public class PersonService {

    PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void create(Person person) throws Exception {

        if (person != null) {
            personRepository.save(person);
        } else {
            throw new Exception("Could not create a new person");
        }
    }

    public void update(UUID personId, Person person) throws Exception {
        Optional<Person> existingPerson = personRepository.findById(personId);

        if (existingPerson.isPresent()) {

            existingPerson.get().setFirstName(person.getFirstName());
            existingPerson.get().setLastName(person.getLastName());
            existingPerson.get().setAge(person.getAge());
            existingPerson.get().setSocialStatus(person.getSocialStatus());
            existingPerson.get().setGender(person.getGender());

            try {
                Person updatedPerson = existingPerson.get();
                personRepository.save(updatedPerson);
            } catch (Exception e) {
                throw new Exception("Could not save current person " + e.getMessage());
            }
        } else {
            throw new Exception("Could not find any person by that id");
        }
    }

    public void delete(UUID personId) throws Exception {
        try {
            personRepository.deleteById(personId);
        } catch (Exception e) {
            throw new Exception("Could not delete person with this id: " + personId);
        }
    }

    public Person findPersonById(UUID personId) {
        Optional<Person> person = personRepository.findById(personId);

        if (person.isPresent()) {
            return person.get();
        }
        throw new NoSuchElementException();
    }
}
