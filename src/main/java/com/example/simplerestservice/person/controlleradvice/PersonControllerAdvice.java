package com.example.simplerestservice.person.controlleradvice;

import com.example.simplerestservice.person.exceptions.PersonFormNotValidException;
import com.example.simplerestservice.person.exceptions.PersonNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
@Slf4j
public class PersonControllerAdvice {

    @ResponseBody
    @ExceptionHandler(PersonNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String handlePersonNotFoundException(PersonNotFoundException ex) {
        log.error("Person not found exception occured. Message: " + ex.getMessage());
        return "Person not found";
    }

    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(PersonFormNotValidException.class)
    public String handlePersonFormNotValidException(
            PersonFormNotValidException ex) {
        log.error("Person Form Not Valid exception occurred. Message: " + ex.getMessage());
        return ex.getMessage();
    }
}
