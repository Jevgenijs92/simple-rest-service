package com.example.simplerestservice.person.forms;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Date;

public class PersonForm {
    @NotBlank(message = "Personal ID cannot be blank")
    @Size(min = 14, max = 14, message = "Personal ID should contain 14 symbols")
    private String personalId;

    @NotNull(message = "Birthdate cannot be null")
    private Date birthdate;

    public String getPersonalId() {
        return personalId;
    }

    public void setPersonalId(String personalId) {
        this.personalId = personalId;
    }

    public Date getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(Date birthdate) {
        this.birthdate = birthdate;
    }
}
