package com.example.controller;

import com.example.model.domain.Mailing;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;


@RequestMapping("mailing")
public interface MailApi {

    @PostMapping("registration")
    Mailing createMailing(Mailing mailing);


    @PutMapping("arrival")
    Mailing updateArrivalMail(Mailing mailing);

    @PutMapping("departure")
    Mailing updateDepartureMail(Mailing mailing);

    @PutMapping("receiving")
    Mailing updateReceivingMail(Mailing mailing);

    @GetMapping("status")
    Mailing getStatus (UUID uuid);

    @GetMapping("comments")
    Page<Mailing> getAllMailingByUUID(@RequestParam(required = false) UUID uuid,
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size);
}
