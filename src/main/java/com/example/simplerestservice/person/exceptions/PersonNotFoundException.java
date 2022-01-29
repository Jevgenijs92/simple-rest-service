package com.example.simplerestservice.person.exceptions;

import java.sql.Date;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(String personalId, Date birthdate) {
        super("Person with personal ID: " + personalId + ", birthdate: " + birthdate.toString() + " not found");
    }
}
