package com.example.simplerestservice.person.services.impl;

import com.example.simplerestservice.person.exceptions.PersonNotFoundException;
import com.example.simplerestservice.person.converters.PersonConverter;
import com.example.simplerestservice.person.dto.PersonData;
import com.example.simplerestservice.person.models.Person;
import com.example.simplerestservice.person.repositories.PersonRepository;
import com.example.simplerestservice.person.services.PersonService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Objects;

@Service
@AllArgsConstructor
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;
    private final PersonConverter personConverter;

    @Override
    public PersonData getPersonByPersonalIdAndBirthdate(String personalId, LocalDate birthdate) throws PersonNotFoundException {
        Person person = personRepository.findPersonByPersonalIdAndBirthdate(personalId, birthdate);
        if(Objects.isNull(person)){
            throw new PersonNotFoundException(personalId, birthdate);
        }
        return personConverter.convert(personRepository.findPersonByPersonalIdAndBirthdate(personalId, birthdate));
    }
}
