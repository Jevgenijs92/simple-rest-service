package com.example.simplerestservice.person.repositories;

import com.example.simplerestservice.person.models.Person;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    Person findPersonByPersonalIdAndBirthdate(String personalId, LocalDate birthdate);
}
