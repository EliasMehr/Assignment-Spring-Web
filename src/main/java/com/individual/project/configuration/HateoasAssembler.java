package com.individual.project.configuration;

import com.individual.project.controllers.PersonController;
import com.individual.project.models.Person;
import org.springframework.hateoas.Link;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@Component
public class HateoasAssembler  {


    public void addHATEOASLink(Person person) {

        Link findAllLink = linkTo(
                methodOn(PersonController.class).findAllPersons()).withRel("findAll");
        person.add(findAllLink);

        Link selfLink = linkTo(
                methodOn(PersonController.class).findPersonById(person.getId())
        ).withSelfRel();
        person.add(selfLink);

        Link deleteLink = linkTo(methodOn(PersonController.class).delete(person.getId()))
                .withRel("delete");
        person.add(deleteLink);

        Link createLink = linkTo(methodOn(PersonController.class).create(person)).withRel("create");
        person.add(createLink);
    }
}
