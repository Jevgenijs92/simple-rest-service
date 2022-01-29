package com.example.simplerestservice.person;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
public class PersonIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    private static final String correctPersonalId = "25031982-35985";
    private static final String correctBirthdate = "1982-03-25";
    private static final JSONObject correctJsonRequest = new JSONObject();
    private static final JSONObject correctJsonResponse = new JSONObject();

    @BeforeAll
    private static void setup() throws Exception {
        correctJsonRequest.put("personalId", correctPersonalId);
        correctJsonRequest.put("birthdate", correctBirthdate);

        correctJsonResponse.put("personalId", correctPersonalId);
        correctJsonResponse.put("firstName", "Eleni");
        correctJsonResponse.put("lastName", "Lawson");
        correctJsonResponse.put("gender", "female");
        correctJsonResponse.put("birthdate", correctBirthdate);
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_then200IsReceived() throws Exception {
        mockMvc.perform(get("/person")
                        .content(String.valueOf(correctJsonRequest)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectContentType() throws Exception {
        mockMvc.perform(get("/person")
                        .content(String.valueOf(correctJsonRequest)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectResponse() throws Exception {
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
        requestJson.put("birthdate", correctBirthdate);

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
        requestJson.put("birthdate", correctBirthdate);

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
        requestJson.put("birthdate", correctBirthdate);

        String response = "Person with personal ID: " + personalId + ", birthdate: " + correctBirthdate + " not found";
        JSONObject responseJson = new JSONObject();
        responseJson.put("errors", new JSONArray(List.of(response)));
        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(String.valueOf(responseJson)));
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_then400IsReceived() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("birthdate", correctBirthdate);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_thenCorrectContentType() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("birthdate", correctBirthdate);

        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_thenBadRequestResponse() throws Exception {
        JSONObject requestJson = new JSONObject();
        requestJson.put("birthdate", correctBirthdate);

        String response = "Personal ID cannot be blank";
        JSONObject responseJson = new JSONObject();
        responseJson.put("errors", new JSONArray(List.of(response)));
        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(String.valueOf(responseJson)));
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

        String response = "Birthdate cannot be null";
        JSONObject responseJson = new JSONObject();
        responseJson.put("errors", new JSONArray(List.of(response)));
        mockMvc.perform(get("/person")
                        .content(String.valueOf(requestJson)).characterEncoding("UTF-8")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(content().json(String.valueOf(responseJson)));
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
        final String errorMsg = "Cannot deserialize request data. " +
                "Please make sure that personal ID (personalId) contains 14 symbols " +
                "and birthdate (birthdate) has pattern of yyyy-MM-dd";
        mockMvc.perform(get("/person")
                        .contentType(MediaType.APPLICATION_JSON))
                .andDo(print())
                .andExpect(jsonPath("$.errors", hasSize(1)))
                .andExpect(jsonPath("$.errors[0]", is(errorMsg)));
    }
}
