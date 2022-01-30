package com.example.simplerestservice.person.exceptions;

public class PersonFormNotValidException extends RuntimeException{
    public PersonFormNotValidException() {
        super("Person form data is not valid. " +
                "Please make sure that personal ID (personalId) contains 14 symbols " +
                "and birthdate (birthdate) has pattern of yyyy-MM-dd");
    }
}
