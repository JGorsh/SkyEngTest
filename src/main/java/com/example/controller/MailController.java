package com.example.controller;

import com.example.model.dto.*;
import com.example.service.MailingService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
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
    public MailingDto createArrivalMail(@RequestBody @Valid CreateArrivalMailingRequestDto mailing) {
        return mailingService.createArrivalMail(mailing);
    }

    @Override
    public MailingDto createDepartureMail(@RequestBody @Valid CreateDepartureMailRequestDto mailing) {
        return mailingService.createDepartureMailing(mailing);
    }

    @Override
    public MailingDto createReceivingMail(@RequestBody @Valid CreateReceiptAddresseeRequestDto mailing) {
        return mailingService.createReceiptAddressee(mailing);
    }

    @Override
    public MailingDto getStatus(UUID uuid) {
        return mailingService.getOne(uuid);
    }

    @Override
    public Page<MailingDto> getAllMailingByUUID(UUID uuid, Pageable pageable) {
        return mailingService.getAllMailing(uuid, pageable);
    }
}
