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

    private PersonRepository personRepository;

    @Autowired
    public PersonService(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }

    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    public void create(Person person) {
        try {
            personRepository.save(person);
        } catch (Exception e) {
            e.getMessage();
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
                throw new Exception();
            }
        } else {
            throw new Exception();
        }
    }

    public void delete(UUID personId) throws Exception {
        try {
            personRepository.deleteById(personId);
        } catch (Exception e) {
            throw new Exception();
        }
    }

    public Person findPersonById(UUID personId) {
        return personRepository.findById(personId)
                .orElseThrow(NoSuchElementException::new);
    }
}
