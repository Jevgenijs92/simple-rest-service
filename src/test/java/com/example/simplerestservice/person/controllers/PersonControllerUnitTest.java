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
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
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
    private static final String VALIDATION_EXCEPTION = "Validation exception";
    private static final String MESSAGE_NOT_READABLE_EXCEPTION = "Cannot deserialize request data. " +
            "Please make sure that personal ID (personalId) contains 14 symbols " +
            "and birthdate (birthdate) has pattern of yyyy-MM-dd";

    private static final String correctPersonalId = "25031982-35985";
    private static final String correctBirthdateStr = "1982-03-25";
    private static final Date correctBirthdate = new Date(385862400000L);

    private static final JSONObject correctJsonRequest = new JSONObject();
    private static final JSONObject correctJsonResponse = new JSONObject();

    private static final PersonData correctPersonData = new PersonData(correctPersonalId,
            "Eleni", "Lawson", "female", correctBirthdate);

    private static final List<String> personErrors = new ArrayList<>();
    private static final Map<String, List<String>> personResponse = new HashMap<>();

    private static final List<String> validationErrors = new ArrayList<>();
    private static final Map<String, List<String>> validationResponse = new HashMap<>();

    private static final List<String> notReadableErrors = new ArrayList<>();
    private static final Map<String, List<String>> notReadableResponse = new HashMap<>();

    @BeforeAll
    public static void setup() throws Exception {
        correctJsonRequest.put("personalId", correctPersonalId);
        correctJsonRequest.put("birthdate", correctBirthdateStr);

        correctJsonResponse.put("personalId", correctPersonalId);
        correctJsonResponse.put("firstName", "Eleni");
        correctJsonResponse.put("lastName", "Lawson");
        correctJsonResponse.put("gender", "female");
        correctJsonResponse.put("birthdate", correctBirthdateStr);

        //Exceptions
        personErrors.add(PERSON_NOT_FOUND_EXCEPTION);
        personResponse.put("errors", personErrors);

        validationErrors.add(VALIDATION_EXCEPTION);
        validationResponse.put("errors", validationErrors);

        notReadableErrors.add(MESSAGE_NOT_READABLE_EXCEPTION);
        notReadableResponse.put("errors", notReadableErrors);
    }

    @BeforeEach
    public void init() {
        when(personControllerAdvice.handlePersonNotFoundException(any()))
                .thenReturn(new ResponseEntity<>(personResponse,HttpStatus.NOT_FOUND));

        when(personControllerAdvice.handleValidationExceptions(any()))
                .thenReturn(new ResponseEntity<>(validationResponse,HttpStatus.BAD_REQUEST));

        when(personControllerAdvice.handleHttpMessageNotReadableException(any()))
                .thenReturn(new ResponseEntity<>(notReadableResponse,HttpStatus.BAD_REQUEST));
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_then200IsReceived() throws Exception {
        when(personService.getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdate)).thenReturn(correctPersonData);
        mockMvc.perform(get("/person")
                        .content(String.valueOf(correctJsonRequest)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectContentType() throws Exception {
        when(personService.getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdate)).thenReturn(correctPersonData);
        mockMvc.perform(get("/person")
                        .content(String.valueOf(correctJsonRequest)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectResponse() throws Exception {
        when(personService.getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdate)).thenReturn(correctPersonData);
        mockMvc.perform(get("/person")
                        .content(String.valueOf(correctJsonRequest)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(String.valueOf(correctJsonResponse)));
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_then404IsReceived() throws Exception {
        final String personalId = "12345678901234";
        JSONObject requestJson = new JSONObject();
        requestJson.put("personalId", personalId);
        requestJson.put("birthdate", correctBirthdateStr);
        when(personService.getPersonByPersonalIdAndBirthdate(personalId, correctBirthdate))
                .thenThrow(new PersonNotFoundException(personalId, correctBirthdate));

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_thenCorrectContentType() throws Exception {
        final String personalId = "12345678901234";
        JSONObject requestJson = new JSONObject();
        requestJson.put("personalId", personalId);
        requestJson.put("birthdate", correctBirthdateStr);
        when(personService.getPersonByPersonalIdAndBirthdate(personalId, correctBirthdate))
                .thenThrow(new PersonNotFoundException(personalId, correctBirthdate));

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_thenPersonNotFoundResponse() throws Exception {
        final String personalId = "12345678901234";
        JSONObject requestJson = new JSONObject();
        requestJson.put("personalId", personalId);
        requestJson.put("birthdate", correctBirthdateStr);
        when(personService.getPersonByPersonalIdAndBirthdate(personalId, correctBirthdate))
                .thenThrow(new PersonNotFoundException(personalId, correctBirthdate));

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.errors[0]", is(PERSON_NOT_FOUND_EXCEPTION)));
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_then400IsReceived() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("birthdate", correctBirthdateStr);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_thenCorrectContentType() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("birthdate", correctBirthdateStr);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_thenBadRequestResponse() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("birthdate", correctBirthdateStr);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.errors[0]",is(VALIDATION_EXCEPTION)));
    }

    @Test
    public void whenGetRequestToPersonAndNoBirthdate_then400IsReceived() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("personalId", correctPersonalId);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonAndNoBirthdate_thenCorrectContentType() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("personalId", correctPersonalId);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndNoBirthdate_thenBadRequestResponse() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("personalId", correctPersonalId);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.errors[0]", is(VALIDATION_EXCEPTION)));
    }

    @Test
    public void whenGetRequestToPersonWithoutData_then400IsReceived() throws Exception {
        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonWithoutData_thenCorrectContentType() throws Exception {
        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonWithoutData_thenBadRequestResponse() throws Exception {
        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0]", is(MESSAGE_NOT_READABLE_EXCEPTION)));
    }
}
