package com.example.controller;

import com.example.model.domain.Mailing;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.model.dto.UpdateMailingRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("mailing")
public interface MailApi {

    @PostMapping("registration")
    MailingDto createMailing(CreateMailingRequestDto mailing);


    @PutMapping("arrival")
    MailingDto updateArrivalMail(UpdateMailingRequestDto mailing);

    @PutMapping("departure")
    MailingDto updateDepartureMail(UpdateMailingRequestDto  mailing);

    @PutMapping("receiving")
    MailingDto updateReceivingMail(UpdateMailingRequestDto mailing);

    @GetMapping("status")
    MailingDto getStatus (UUID uuid);

    @GetMapping("comments")
    Page<MailingDto> getAllMailingByUUID(@RequestParam(required = false) UUID uuid,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size);
}
