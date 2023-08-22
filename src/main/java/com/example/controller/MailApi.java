package com.example.controller;

import com.example.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("mailing")
public interface MailApi {

    @PostMapping("registration")
    MailingDto createMailing(CreateMailingRequestDto mailing);


    @PutMapping("arrival")
    MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailing);

    @PutMapping("departure")
    MailingDto createDepartureMail(CreateDepartureMailRequestDto mailing);

    @PutMapping("receiving")
    MailingDto createReceivingMail(CreateReceiptAddresseeRequestDto mailing);

    @GetMapping("status")
    MailingDto getStatus (UUID uuid);

    @GetMapping("full")
    Page<MailingDto> getAllMailingByUUID(@RequestParam(required = false) UUID uuid,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size);
}
