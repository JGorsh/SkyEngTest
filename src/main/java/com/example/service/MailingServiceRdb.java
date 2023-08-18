package com.example.service;

import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.repository.MailingRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class MailingServiceRdb implements MailingService{

    private final MailingRepository mailingRepository;

    @Override
    public MailingDto createIssue(CreateMailingRequestDto mailing) {
        return null;
    }
}
