package com.example.service;


import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.model.dto.UpdateMailingRequestDto;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PathVariable;

public interface MailingService {
    Page<MailingDto> getAllIssues(Integer page, Integer size);

    MailingDto getOne(@PathVariable Long id);

    MailingDto createMailing(CreateMailingRequestDto mailing);

    MailingDto updateMailing(UpdateMailingRequestDto mailing);

}