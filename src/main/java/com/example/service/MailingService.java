package com.example.service;


import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;

public interface MailingService {

    MailingDto createIssue(CreateMailingRequestDto mailing);
}
