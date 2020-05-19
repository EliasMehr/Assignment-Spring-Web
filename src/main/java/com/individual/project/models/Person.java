package com.individual.project.models;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.EntityModel;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person extends EntityModel<Person> {

    @Id
    @GeneratedValue
    private UUID id;

    @NotBlank(message = "First name is mandatory!")
    @Size(min = 2, max = 20, message = "Must be min 2 characters  & maximum 20 char long")
    private String firstName;

    @NotBlank(message = "Last name is mandatory!")
    @Size(min = 2, max = 20, message = "Must be min 2 characters  & maximum 20 char long")
    private String lastName;

    @Digits(integer = 2, fraction = 0)
    @Positive(message = "Must be positive and cannot be 0 or negative")
    private int age;

    @NotBlank(message = "social status is mandatory!")
    @Size(min = 2, max = 20, message = "Must be min 2 characters  & maximum 20 char long")
    private String socialStatus;

    @Enumerated(STRING)
    private Gender gender;

    public enum Gender {
        MALE, FEMALE
    }
}
