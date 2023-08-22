package com.example.service;


import com.example.model.dto.*;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

public interface MailingService {
    Page<MailingDto> getAllMailing(Integer page, Integer size);

    MailingDto getOne(@PathVariable Long id);

    MailingDto createMailing(CreateMailingRequestDto mailing);

    MailingDto createDepartureMailing(CreateDepartureMailRequestDto mailing);

    MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailing);

    MailingDto createReceiptAddressee(CreateReceiptAddresseeRequestDto mailing);

}
