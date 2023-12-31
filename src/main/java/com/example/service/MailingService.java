package com.example.service;


import com.example.errors.IncorrectDataException;
import com.example.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

public interface MailingService {
    Page<MailingDto> getAllMailing(UUID uuid, Pageable page);

//    MailingDto getOne(@PathVariable Long id);

    MailingDto getOne(UUID uuid);
    MailingDto createMailing(CreateMailingRequestDto mailing);

    MailingDto createDepartureMailing(CreateDepartureMailRequestDto mailing) throws IncorrectDataException;

    MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailing) throws IncorrectDataException;

    MailingDto createReceiptAddressee(CreateReceiptAddresseeRequestDto mailing) throws IncorrectDataException;

}
