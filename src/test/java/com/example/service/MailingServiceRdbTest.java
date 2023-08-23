package com.example.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@AutoConfigureMockMvc
@SpringBootTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MailingServiceRdbTest {

    @Test
    void createMailing() {
    }

    @Test
    void createDepartureMailing() {
    }

    @Test
    void getAllMailing() {
    }

    @Test
    void getOne() {
    }

    @Test
    void createArrivalMail() {
    }

    @Test
    void createReceiptAddressee() {
    }

    @Test
    void getLastMailingId() {
    }
}