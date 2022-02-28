package com.example.simplerestservice.person.services.impl;

import com.example.simplerestservice.person.exceptions.PersonNotFoundException;
import com.example.simplerestservice.person.converters.PersonConverter;
import com.example.simplerestservice.person.dto.PersonData;
import com.example.simplerestservice.person.repositories.PersonRepository;
import com.example.simplerestservice.person.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @Override
    public PersonData getPersonByPersonalIdAndBirthdate(String personalId, LocalDate birthdate) throws PersonNotFoundException {
        return personConverter.convert(personRepository.
                findPersonByPersonalIdAndBirthdate(personalId, birthdate)
                .orElseThrow(() -> new PersonNotFoundException(personalId, birthdate)));
    }
}
