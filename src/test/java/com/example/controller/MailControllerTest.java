package com.example.controller;

import com.example.model.domain.MailType;
import com.example.model.domain.PostOffice;
import com.example.model.dto.CreateMailingRequestDto;
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
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
//@Sql(value = {"classpath:db/test_script.sql"}, executionPhase = Sql.ExecutionPhase.BEFORE_TEST_METHOD)
class MailControllerTest {

    PostOffice postOffice;
    public static final String UUID_TEST = "cebd667e-41a3-11ee-be56-0242ac120002";
    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        postOffice = new PostOffice();
        postOffice.setId(2L);
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
                .andExpect(jsonPath("$.mailingStatus", equalTo("SENT_BY_POST_OFFICE")));
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
                .andExpect(jsonPath("$.mailingStatus", equalTo("REGISTRATION")));;
    }
    @Test
    void createArrivalMail() {
    }

    @Test
    void createDepartureMail() {
    }

    @Test
    void createReceivingMail() {
    }
}