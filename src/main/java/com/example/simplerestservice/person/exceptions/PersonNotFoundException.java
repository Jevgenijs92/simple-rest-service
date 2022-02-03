package com.example.simplerestservice.person.exceptions;

import java.time.LocalDate;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String personalId, LocalDate birthdate) {
        super("Person with personal ID: " + personalId + ", birthdate: " + birthdate.toString() + " not found");
    }
}
