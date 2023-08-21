package com.example.controller;

import com.example.model.domain.Mailing;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.model.dto.UpdateMailingRequestDto;
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
    public MailingDto updateArrivalMail(UpdateMailingRequestDto mailing) {
        return null;
    }

    @Override
    public MailingDto updateDepartureMail(UpdateMailingRequestDto  mailing) {
        return null;
    }

    @Override
    public MailingDto updateReceivingMail(UpdateMailingRequestDto  mailing) {
        return null;
    }

    @Override
    public MailingDto getStatus(UUID uuid) {
        return null;
    }

    @Override
    public Page<MailingDto> getAllMailingByUUID(UUID uuid, Integer page, Integer size) {
        return null;
    }
}
