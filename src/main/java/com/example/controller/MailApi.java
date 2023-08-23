package com.example.controller;

import com.example.errors.IncorrectDataException;
import com.example.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("mailing")
public interface MailApi {

    @PostMapping("registration")
    MailingDto createMailing(CreateMailingRequestDto mailing);


    @PutMapping("arrival")
    MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailing) throws IncorrectDataException;

    @PutMapping("departure")
    MailingDto createDepartureMail(CreateDepartureMailRequestDto mailing) throws IncorrectDataException;

    @PutMapping("receiving")
    MailingDto createReceivingMail(CreateReceiptAddresseeRequestDto mailing) throws IncorrectDataException;

    @GetMapping("status")
    MailingDto getStatus (@RequestParam UUID uuid);

    @GetMapping("full")
    Page<MailingDto> getAllMailingByUUID(@RequestParam UUID uuid,
                                         @PageableDefault(value = 100, page = 0) Pageable pageable);
}
