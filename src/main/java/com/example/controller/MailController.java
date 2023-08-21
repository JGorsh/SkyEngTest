package com.example.controller;

import com.example.model.domain.Mailing;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.service.MailingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.UUID;

@AllArgsConstructor
@RestController
public class MailController implements MailApi {

    private final MailingService mailingService;

    @Override
    public MailingDto createMailing(@RequestBody @Valid CreateMailingRequestDto mailing) {
        return mailingService.createMailing(mailing);
    }

    @Override
    public Mailing updateArrivalMail(Mailing mailing) {
        return null;
    }

    @Override
    public Mailing updateDepartureMail(Mailing mailing) {
        return null;
    }

    @Override
    public Mailing updateReceivingMail(Mailing mailing) {
        return null;
    }

    @Override
    public Mailing getStatus(UUID uuid) {
        return null;
    }

    @Override
    public Page<Mailing> getAllMailingByUUID(UUID uuid, Integer page, Integer size) {
        return null;
    }
}
