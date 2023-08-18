package com.example.service;

import com.example.mapping.MailingMapper;
import com.example.model.dto.CreateMailingRequestDto;
import com.example.model.dto.MailingDto;
import com.example.model.dto.UpdateMailingRequestDto;
import com.example.repository.MailingRepository;
import com.example.repository.PostOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;

@AllArgsConstructor
@Service
public class MailingServiceRdb implements MailingService{

    private final MailingRepository mailingRepository;
    private final PostOfficeRepository postOfficeRepository;
    private final MailingMapper mapper;
    private static final String POST_OFFICE_NOT_FOUND =  "Post office with id %s not found!";
    private static final String MAILING_NOT_FOUND = "Mailing with id %s not found!";

    @Override
    public MailingDto createMailing(CreateMailingRequestDto mailingDto) {
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        var mailing = mapper.toEntity(mailingDto);
        mailing.setPostOffice(postOffice);
        mailingRepository.save(mailing);
        return mapper.fromEntity(mailing);
    }

    @Override
    public Page<MailingDto> getAllIssues(Integer page, Integer size) {
        return mailingRepository.findAll(PageRequest.of(page, size)).map(mapper::fromEntity);
    }

    @Override
    public MailingDto getOne(Long id) {
        return mailingRepository.findById(id)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, id)));
    }

    @Override
    public MailingDto updateMailing(UpdateMailingRequestDto mailingDto) {
        var mailing = mailingRepository.findById(mailingDto.getUuid())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        mailing = mapper.updateEntityFromDto(mailingDto, mailing);
        mailing.setPostOffice(postOffice);
        mailingRepository.save(mailing);
        return mapper.fromEntity(mailing);
    }
}
