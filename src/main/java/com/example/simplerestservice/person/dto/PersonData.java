package com.example.simplerestservice.person.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonData {
    private String personalId;
    private String firstName;
    private String lastName;
    private String gender;
    private Date birthdate;
}
