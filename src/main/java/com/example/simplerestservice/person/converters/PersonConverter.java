package com.example.simplerestservice.person.converters;

import com.example.simplerestservice.person.dto.PersonData;
import com.example.simplerestservice.person.models.Person;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class PersonConverter implements Converter<Person, PersonData> {
    @Override
    public PersonData convert(Person source) {
        PersonData target = new PersonData();
        target.setPersonalId(source.getPersonalId());
        target.setFirstName(source.getFirstName());
        target.setLastName(source.getLastName());
        setGender(source, target);
        target.setBirthdate(source.getBirthdate());
        return target;
    }

    public void setGender(Person source, PersonData target) {
        switch(source.getGender()) {
            case 0:
                target.setGender("not known");
                break;
            case 1:
                target.setGender("male");
                break;
            case 2:
                target.setGender("female");
                break;
            case 9:
                target.setGender("not applicable");
                break;
            default:
                target.setGender("");
                break;
        }
    }
}
