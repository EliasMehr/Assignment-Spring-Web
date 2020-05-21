package com.individual.project.models;

import com.fasterxml.jackson.annotation.JsonEnumDefaultValue;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.*;
import java.util.UUID;

import static javax.persistence.EnumType.STRING;

@Getter
@Setter
@NoArgsConstructor
@Entity
public class Person extends RepresentationModel<Person> {

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
    @NotNull(message = "Gender must be FEMALE or MALE")
    private Gender gender;

    public enum Gender {
        MALE,
        FEMALE
    }
}
