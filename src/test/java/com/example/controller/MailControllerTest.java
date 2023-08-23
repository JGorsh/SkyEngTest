package com.example.controller;

import com.example.model.domain.MailType;
import com.example.model.domain.PostOffice;
import com.example.model.dto.CreateArrivalMailingRequestDto;
import com.example.model.dto.CreateDepartureMailRequestDto;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.CreateReceiptAddresseeRequestDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.web.servlet.MockMvc;

import java.util.UUID;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Sql(value = {"classpath:db/test_script.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MailControllerTest {

    PostOffice postOffice;

    PostOffice postOffice1;
    public static final String UUID_TEST = "cebd667e-41a3-11ee-be56-0242ac120002";
    public static final String UUID_TEST_1 = "617133d9-4b50-4a5a-bdd6-9c9a3f885517";

    public static final String UUID_TEST_2 = "cfee7e36-41ab-11ee-be56-0242ac120002";

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        postOffice = new PostOffice();
        postOffice.setId(2L);

        postOffice1 = new PostOffice();
        postOffice1.setId(1L);
    }

    @Test
    void shouldReturnAllMailingByUuid() throws Exception {
        mockMvc.perform(get("/mailing/full").queryParam("uuid", UUID_TEST))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.totalElements", greaterThan(0)));
    }

    @Test
    void shouldReturnOneLastMailingByUuid() throws Exception {
        mockMvc.perform(get("/mailing/status").queryParam("uuid", UUID_TEST))
                .andExpect(status().isOk())
                .andExpect(header().string(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_VALUE))
                .andExpect(jsonPath("$.mailingStatus", equalTo("REGISTRATION")));
    }

    @Test
    void shouldCreateMailingAndCheckStatus() throws Exception {
        var mailing = new CreateMailingRequestDto();
        mailing.setMailType(MailType.LETTER);
        mailing.setMailIndexRecipient(460001);
        mailing.setRecipientName("Mike");
        mailing.setRecipientAddress("Paris");
        mailing.setPostOffice(postOffice);
        mockMvc.perform(post("/mailing/registration")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(mailing)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.mailingStatus", equalTo("REGISTRATION")));
    }
    @Test
    void shouldCreateDepartureMailing() throws Exception {
        var mailing = new CreateDepartureMailRequestDto();
        mailing.setUuid(UUID.fromString(UUID_TEST));
        mailing.setPostOffice(postOffice);
        mockMvc.perform(put("/mailing/departure")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(mailing)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.mailingStatus", equalTo("SENT_BY_POST_OFFICE")));
    }

    @Test
    void shouldCreateArrivalMailing() throws Exception{
        var mailing = new CreateArrivalMailingRequestDto();
        mailing.setUuid(UUID.fromString(UUID_TEST_1));
        mailing.setPostOffice(postOffice);
        mockMvc.perform(put("/mailing/arrival")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(mailing)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.mailingStatus", equalTo("RECEIVED_BY_POST_OFFICE")));
    }

    @Test
    void shouldCreateArrivalMailingCheckStatusAddressee() throws Exception{
        var mailing = new CreateArrivalMailingRequestDto();
        mailing.setUuid(UUID.fromString(UUID_TEST_2));
        mailing.setPostOffice(postOffice1);
        mockMvc.perform(put("/mailing/arrival")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(mailing)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.mailingStatus", equalTo("RECEIVED_BY_POST_OFFICE_TO_THE_ADDRESSEE")));
    }

    @Test
    void shouldCreateReceivingMailing() throws Exception{
        var mailing = new CreateReceiptAddresseeRequestDto();
        mailing.setUuid(UUID.fromString(UUID_TEST_2));
        mailing.setRecipientName("Alex");
        mailing.setRecipientAddress("Moscow");
        mailing.setPostOffice(postOffice1);
        mockMvc.perform(put("/mailing/receiving")
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .content(objectMapper.writeValueAsString(mailing)))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.mailingStatus", equalTo("DELIVERED_TO_THE_ADDRESSEE")));
    }
}