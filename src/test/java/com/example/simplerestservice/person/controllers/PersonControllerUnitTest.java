package com.example.simplerestservice.person.controllers;

import com.example.simplerestservice.person.controlleradvice.PersonControllerAdvice;
import com.example.simplerestservice.person.dto.PersonData;
import com.example.simplerestservice.person.exceptions.PersonNotFoundException;
import com.example.simplerestservice.person.services.PersonService;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(PersonController.class)
public class PersonControllerUnitTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService personService;

    @MockBean
    private PersonControllerAdvice personControllerAdvice;

    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person not found";
    private static final String PERSONAL_ID_NOT_PRESENT = "Required request parameter 'personalId' for method parameter type String is not present";
    private static final String BIRTHDATE_NOT_PRESENT = "Required request parameter 'birthdate' for method parameter type LocalDate is not present";

    private static final String correctPersonalId = "25031982-35985";
    private static final String correctBirthdateStr = "1982-03-25";


    private static final LocalDate correctBirthdate = LocalDate.of(1982, 3, 25);
    private static final JSONObject correctJsonResponse = new JSONObject();
    private static final PersonData correctPersonData = new PersonData(correctPersonalId,
            "Eleni", "Lawson", "female", correctBirthdate);


    @BeforeAll
    public static void setup() throws Exception {
        correctJsonResponse.put("personalId", correctPersonalId);
        correctJsonResponse.put("firstName", "Eleni");
        correctJsonResponse.put("lastName", "Lawson");
        correctJsonResponse.put("gender", "female");
        correctJsonResponse.put("birthdate", correctBirthdateStr);
    }

    @BeforeEach
    public void init() {
        when(personControllerAdvice.handlePersonNotFoundException(any()))
                .thenReturn(PERSON_NOT_FOUND_EXCEPTION);
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_then200IsReceived() throws Exception {
        when(personService.getPersonByPersonalIdAndBirthdate(any(), any())).thenReturn(correctPersonData);
        getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdateStr)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectContentType() throws Exception {
        when(personService.getPersonByPersonalIdAndBirthdate(any(), any())).thenReturn(correctPersonData);
        getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdateStr)
                .andDo(print())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectResponse() throws Exception {
        when(personService.getPersonByPersonalIdAndBirthdate(any(), any())).thenReturn(correctPersonData);
        getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdateStr)
                .andDo(print())
                .andExpect(content().json(String.valueOf(correctJsonResponse)));
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_then404IsReceived() throws Exception {
        final String personalId = "12345678901234";
        when(personService.getPersonByPersonalIdAndBirthdate(any(), any()))
                .thenThrow(new PersonNotFoundException(personalId, correctBirthdate));

        getPersonByPersonalIdAndBirthdate(personalId, correctBirthdateStr)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_thenPersonNotFoundResponse() throws Exception {
        final String personalId = "12345678901234";
        when(personService.getPersonByPersonalIdAndBirthdate(any(), any()))
                .thenThrow(new PersonNotFoundException(personalId, correctBirthdate));

        getPersonByPersonalIdAndBirthdate(personalId, correctBirthdateStr)
                .andDo(print())
                .andExpect(content().string(PERSON_NOT_FOUND_EXCEPTION));
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_then400IsReceived() throws Exception {
        getPersonByPersonalIdAndBirthdate(null, correctBirthdateStr)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_thenBadRequestResponse() throws Exception {
        getPersonByPersonalIdAndBirthdate(null, correctBirthdateStr)
                .andDo(print())
                .andExpect(content().string(PERSONAL_ID_NOT_PRESENT));
    }

    @Test
    public void whenGetRequestToPersonAndNoBirthdate_then400IsReceived() throws Exception {
        getPersonByPersonalIdAndBirthdate(correctPersonalId, null)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonAndNoBirthdate_thenBadRequestResponse() throws Exception {
        getPersonByPersonalIdAndBirthdate(correctPersonalId, null)
                .andDo(print())
                .andExpect(content().string(BIRTHDATE_NOT_PRESENT));
    }

    @Test
    public void whenGetRequestToPersonWithoutData_then400IsReceived() throws Exception {
        getPersonByPersonalIdAndBirthdate(null, null)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonWithoutData_thenBadRequestResponse() throws Exception {
        getPersonByPersonalIdAndBirthdate(null, null)
                .andDo(print())
                .andExpect(content().string(PERSONAL_ID_NOT_PRESENT));
    }

    public ResultActions getPersonByPersonalIdAndBirthdate(String personalId, String birthdate) throws Exception {
        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
        if(Objects.nonNull(personalId)){
            params.put("personalId", List.of(personalId));
        }
        if(Objects.nonNull(birthdate)){
            params.put("birthdate", List.of(birthdate));
        }
        return mockMvc.perform(get("/person")
                .params(params));
    }
}
