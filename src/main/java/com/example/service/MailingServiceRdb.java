package com.example.service;

import com.example.mapping.MailingMapper;
import com.example.model.domain.Mailing;
import com.example.model.domain.MailingStatus;
import com.example.model.dto.*;
import com.example.repository.MailingRepository;
import com.example.repository.PostOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MailingServiceRdb implements MailingService{

    private final PostOfficeRepository postOfficeRepository;
    private final MailingRepository mailingRepository;
    private final MailingMapper mapper;
    private static final String POST_OFFICE_NOT_FOUND =  "Post office with id %s not found!";
    private static final String MAILING_NOT_FOUND = "Mailing with uuid %s not found!";
    private static final String INCORRECT_POST_OFFICE = "Invalid post office address entered";

    @Override
    public MailingDto createMailing(CreateMailingRequestDto mailingDto) {
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        var mailing = mapper.toEntity(mailingDto);
        mailing.setMailingStatus(MailingStatus.REGISTRATION);
        mailing.setPostOffice(postOffice);
        mailingRepository.save(mailing);
        return mapper.fromEntity(mailing);
    }

    @Override
    public MailingDto createDepartureMailing(CreateDepartureMailRequestDto mailingDto) {
        List<Mailing> mailingList = mailingRepository.findAllByUuid(mailingDto.getUuid());
        Long mailingId = 0L;
        for(Mailing m : mailingList){
            if(m.getId()>mailingId){
                mailingId=m.getId();
            }
        }
        var mailing = mailingRepository.findById(mailingId)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        var mail = mapper.dtoFromEntity(mailing);
        mail.setPostOffice(postOffice);
        mail.setMailingStatus(MailingStatus.SENT_BY_POST_OFFICE);
        mail.setSentedTime(Instant.now());
        mailing = mapper.toEntity(mail);
        mailingRepository.save(mailing);
        return mapper.fromEntity(mailing);
    }

    @Override
    public Page<MailingDto> getAllMailing(UUID uuid, Pageable pageable) {
        return mailingRepository.findAllByUuid(uuid, pageable).map(mapper::fromEntity);
    }

    @Override
    public MailingDto getOne(UUID uuid) {
        List<Mailing> mailingList = mailingRepository.findAllByUuid(uuid);
        Long mailingId = 0L;
        for(Mailing m : mailingList){
            if(m.getId()>mailingId){
                mailingId=m.getId();
            }
        }
        return mailingRepository.findById(mailingId)
                .map(mapper::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, uuid.toString())));
    }

    @Override
    public MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailingDto) {
        List<Mailing> mailingList = mailingRepository.findAllByUuid(mailingDto.getUuid());
        Long mailingId = 0L;
        for(Mailing m : mailingList){
            if(m.getId()>mailingId){
                mailingId=m.getId();
            }
        }
        var mailing = mailingRepository.findById(mailingId)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        if(mailingDto.getPostOffice().getId().equals(mailing.getPostOffice().getId())){
            var mail = mapper.dtoFromEntity(mailing);
            mail.setPostOffice(postOffice);
            if(mail.getMailIndexRecipient().equals(mail.getPostOffice().getPostOfficeIndex())){
                mail.setMailingStatus(MailingStatus.RECEIVED_BY_POST_OFFICE_TO_THE_ADDRESSEE);
            } else {
                mail.setMailingStatus(MailingStatus.RECEIVED_BY_POST_OFFICE);
            }
            mail.setReceivedTime(Instant.now());
            mailing = mapper.toEntity(mail);
            mailingRepository.save(mailing);
            return mapper.fromEntity(mailing);
        } else {
            return null;
        }
    }

    @Override
    public MailingDto createReceiptAddressee(CreateReceiptAddresseeRequestDto mailingDto) {
        List<Mailing> mailingList = mailingRepository.findAllByUuid(mailingDto.getUuid());
        Long mailingId = 0L;
        for(Mailing m : mailingList){
            if(m.getId()>mailingId){
                mailingId=m.getId();
            }
        }
        var mailing = mailingRepository.findById(mailingId)
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        var mail = mapper.dtoFromEntity(mailing);

        if(mail.getMailingStatus().equals(MailingStatus.RECEIVED_BY_POST_OFFICE_TO_THE_ADDRESSEE)){
            mail.setPostOffice(postOffice);
            mail.setMailingStatus(MailingStatus.DELIVERED_TO_THE_ADDRESSEE);
            mail.setReceivedTime(Instant.now());
            mailing = mapper.toEntity(mail);
            mailingRepository.save(mailing);
            return mapper.fromEntity(mailing);
        } else {
            return null;
        }

    }
}
