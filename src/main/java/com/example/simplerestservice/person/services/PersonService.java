package com.example.simplerestservice.person.services;

import com.example.simplerestservice.person.dto.PersonData;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public interface PersonService {
    PersonData getPersonByPersonalIdAndBirthdate(String personalId, LocalDate birthdate);
}
