package com.example.simplerestservice.person;

import org.json.JSONObject;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.util.List;
import java.util.Objects;

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
    private static final JSONObject correctJsonResponse = new JSONObject();

    private static final String PERSON_NOT_FOUND_EXCEPTION = "Person not found";
    private static final String PERSON_FORM_NOT_VALID_EXCEPTION = "Person form data is not valid. " +
            "Please make sure that personal ID (personalId) contains 14 symbols and " +
            "birthdate (birthdate) has pattern of yyyy-MM-dd";

    @BeforeAll
    private static void setup() throws Exception {
        correctJsonResponse.put("personalId", correctPersonalId);
        correctJsonResponse.put("firstName", "Eleni");
        correctJsonResponse.put("lastName", "Lawson");
        correctJsonResponse.put("gender", "female");
        correctJsonResponse.put("birthdate", correctBirthdate);
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_then200IsReceived() throws Exception {
        getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdate)
                .andDo(print())
                .andExpect(status().isOk());
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectContentType() throws Exception {
        getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdate)
                .andDo(print())
                .andExpect(content()
                        .contentType(MediaType.APPLICATION_JSON));
    }

    @Test
    public void whenGetRequestToPersonAndValidPerson_thenCorrectResponse() throws Exception {
        getPersonByPersonalIdAndBirthdate(correctPersonalId, correctBirthdate)
                .andDo(print())
                .andExpect(content().json(String.valueOf(correctJsonResponse)));
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_then404IsReceived() throws Exception {
        final String personalId = "12345678901234";

        getPersonByPersonalIdAndBirthdate(personalId, correctBirthdate)
                .andDo(print())
                .andExpect(status().isNotFound());
    }

    @Test
    public void whenGetRequestToPersonAndInvalidPerson_thenPersonNotFoundResponse() throws Exception {
        final String personalId = "12345678901234";
        getPersonByPersonalIdAndBirthdate(personalId, correctBirthdate)
                .andDo(print())
                .andExpect(content().string(PERSON_NOT_FOUND_EXCEPTION));
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_then400IsReceived() throws Exception {
        getPersonByPersonalIdAndBirthdate(null, correctBirthdate)
                .andDo(print())
                .andExpect(status().isBadRequest());
    }

    @Test
    public void whenGetRequestToPersonAndNoPersonalId_thenBadRequestResponse() throws Exception {
        getPersonByPersonalIdAndBirthdate(null, correctBirthdate)
                .andDo(print())
                .andExpect(content().string(PERSON_FORM_NOT_VALID_EXCEPTION));
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
                .andExpect(content().string(PERSON_FORM_NOT_VALID_EXCEPTION));
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
                .andExpect(content().string(PERSON_FORM_NOT_VALID_EXCEPTION));
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
