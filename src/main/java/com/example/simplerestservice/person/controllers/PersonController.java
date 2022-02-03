package com.example.simplerestservice.person.controllers;

import com.example.simplerestservice.person.dto.PersonData;
import com.example.simplerestservice.person.services.PersonService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@RestController
@AllArgsConstructor
@CrossOrigin(origins = {"${cors.allowed.origin}"})
@Validated
public class PersonController {
    private final PersonService personService;

    @GetMapping("/person")
    public ResponseEntity<PersonData> getPersonByPersonalIdAndBirthdate(
            @RequestParam(name = "personalId")
            @Length(min = 14, max = 14, message = "Personal ID must be exactly 14 symbols") String personalId,

            @RequestParam(name = "birthdate") @NotNull(message = "Birthdate must be not null")
            @DateTimeFormat(pattern = "yyyy-MM-dd") LocalDate birthdate) {

        log.info("Received new request for '/person' endpoint - Personal ID: " + personalId +
                ", birthdate: " + birthdate);
        return ResponseEntity.ok()
                .body(personService.getPersonByPersonalIdAndBirthdate(personalId, birthdate));
    }

    @ResponseBody
    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleConstraintViolationException(ConstraintViolationException ex) {
        final List<String> violations = ex.getConstraintViolations().
                stream().map(ConstraintViolation::getMessage).collect(Collectors.toList());

        return violations.toString();
    }

    @ResponseBody
    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleConstraintViolationException(MethodArgumentTypeMismatchException ex) {
        return ex.getMessage();
    }

    @ResponseBody
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    private String handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return ex.getMessage();
    }

}
