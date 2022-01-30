package com.example.simplerestservice.person.controllers;

import com.example.simplerestservice.person.dto.PersonData;
import com.example.simplerestservice.person.exceptions.PersonFormNotValidException;
import com.example.simplerestservice.person.forms.PersonForm;
import com.example.simplerestservice.person.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = "http://localhost:4200")
public class PersonController {
    private final PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<PersonData> getPersonByPersonalIdAndBirthdate(@Valid PersonForm person,
                                                                        BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            throw new PersonFormNotValidException();
        }
        log.info("Received new request for '/person' endpoint - Personal ID: " + person.getPersonalId() +
                ", birthdate: " + person.getBirthdate());
        return ResponseEntity.ok()
                .body(personService.getPersonByPersonalIdAndBirthdate(person.getPersonalId(), person.getBirthdate()));
    }

}
