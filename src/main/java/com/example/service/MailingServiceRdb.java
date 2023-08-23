package com.example.service;

import com.example.errors.IncorrectDataException;
import com.example.mapping.MailingMapper;
import com.example.model.domain.Mailing;
import com.example.model.domain.MailingStatus;
import com.example.model.dto.*;
import com.example.repository.MailingRepository;
import com.example.repository.PostOfficeRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class MailingServiceRdb implements MailingService {

    private final PostOfficeRepository postOfficeRepository;
    private final MailingRepository mailingRepository;
    private final MailingMapper mapper;
    private static final String POST_OFFICE_NOT_FOUND = "Post office with id %s not found!";
    private static final String MAILING_NOT_FOUND = "Mailing with uuid %s not found!";
    private static final String INCORRECT_MAILING_STATUS_DEPARTURE = "Mailing status is %s. Must be " + MailingStatus.REGISTRATION
            + " or " + MailingStatus.RECEIVED_BY_POST_OFFICE;
    private static final String INCORRECT_MAILING_STATUS_RECEIPT= "Mailing status is %s. Must be " + MailingStatus.RECEIVED_BY_POST_OFFICE_TO_THE_ADDRESSEE;
    private static final String INCORRECT_MAILING_STATUS_ARRIVAL = "Mailing status is %s. Must be " + MailingStatus.SENT_BY_POST_OFFICE
            + " or incorrect office id = %s";

    @Override
    public MailingDto createMailing(CreateMailingRequestDto mailingDto) {
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        var mailing = mapper.toEntity(mailingDto);
        mailing.setUuid(UUID.randomUUID());
        mailing.setMailingStatus(MailingStatus.REGISTRATION);
        mailing.setPostOffice(postOffice);
        mailingRepository.save(mailing);
        return mapper.fromEntity(mailing);
    }

    @Override
    public MailingDto createDepartureMailing(CreateDepartureMailRequestDto mailingDto) throws IncorrectDataException {
        var mailing = mailingRepository.findById(getLastMailingId(mailingDto.getUuid()))
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        if (mailing.getMailingStatus().equals(MailingStatus.REGISTRATION) ||
                mailing.getMailingStatus().equals(MailingStatus.RECEIVED_BY_POST_OFFICE) &&
        !mailing.getPostOffice().getId().equals(mailingDto.getPostOffice().getId())) {
            var mail = mapper.dtoFromEntity(mailing);
            mail.setPostOffice(postOffice);
            mail.setMailingStatus(MailingStatus.SENT_BY_POST_OFFICE);
            mail.setSentedTime(Instant.now());
            mailing = mapper.toEntity(mail);
            mailingRepository.save(mailing);
            return mapper.fromEntity(mailing);
        } else {
            throw new IncorrectDataException(String.format(INCORRECT_MAILING_STATUS_DEPARTURE, mailing.getMailingStatus()));
        }
    }

    @Override
    public Page<MailingDto> getAllMailing(UUID uuid, Pageable pageable) {
        return mailingRepository.findAllByUuid(uuid, pageable).map(mapper::fromEntity);
    }

    @Override
    public MailingDto getOne(UUID uuid) {
        return mailingRepository.findById(getLastMailingId(uuid))
                .map(mapper::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, uuid.toString())));
    }

    @Override
    public MailingDto createArrivalMail(CreateArrivalMailingRequestDto mailingDto) throws IncorrectDataException {
        var mailing = mailingRepository.findById(getLastMailingId(mailingDto.getUuid()))
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        if (mailingDto.getPostOffice().getId().equals(mailing.getPostOffice().getId()) &&
                mailing.getMailingStatus().equals(MailingStatus.SENT_BY_POST_OFFICE)) {
            var mail = mapper.dtoFromEntity(mailing);
            mail.setPostOffice(postOffice);
            if (mail.getMailIndexRecipient().equals(mail.getPostOffice().getPostOfficeIndex())) {
                mail.setMailingStatus(MailingStatus.RECEIVED_BY_POST_OFFICE_TO_THE_ADDRESSEE);
            } else {
                mail.setMailingStatus(MailingStatus.RECEIVED_BY_POST_OFFICE);
            }
            mail.setReceivedTime(Instant.now());
            mailing = mapper.toEntity(mail);
            mailingRepository.save(mailing);
            return mapper.fromEntity(mailing);
        } else {
            throw new IncorrectDataException(String.format(INCORRECT_MAILING_STATUS_ARRIVAL, mailing.getMailingStatus(),
                    mailingDto.getPostOffice().getId()));
        }
    }

    @Override
    public MailingDto createReceiptAddressee(CreateReceiptAddresseeRequestDto mailingDto) throws IncorrectDataException {
        var mailing = mailingRepository.findById(getLastMailingId(mailingDto.getUuid()))
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(MAILING_NOT_FOUND, mailingDto.getUuid()))
                );
        var postOffice = postOfficeRepository.findById(mailingDto.getPostOffice().getId())
                .orElseThrow(
                        () -> new EntityNotFoundException(String.format(POST_OFFICE_NOT_FOUND,
                                mailingDto.getPostOffice().getId())
                        ));
        var mail = mapper.dtoFromEntity(mailing);

        if (mail.getMailingStatus().equals(MailingStatus.RECEIVED_BY_POST_OFFICE_TO_THE_ADDRESSEE) &&
                mailing.getRecipientName().equals(mailingDto.getRecipientName())&&
                mailing.getRecipientAddress().equals(mailingDto.getRecipientAddress())&&
                mailing.getPostOffice().getId().equals(mailingDto.getPostOffice().getId())) {
            mail.setPostOffice(postOffice);
            mail.setMailingStatus(MailingStatus.DELIVERED_TO_THE_ADDRESSEE);
            mail.setReceivedTime(Instant.now());
            mailing = mapper.toEntity(mail);
            mailingRepository.save(mailing);
            return mapper.fromEntity(mailing);
        } else {
            throw new IncorrectDataException(String.format(INCORRECT_MAILING_STATUS_RECEIPT, mailing.getMailingStatus()));
        }
    }

    public Long getLastMailingId(UUID uuid) {
        List<Mailing> mailingList = mailingRepository.findAllByUuid(uuid);
        Long mailingId = 0L;
        for (Mailing m : mailingList) {
            if (m.getId() > mailingId) {
                mailingId = m.getId();
            }
        }
        return mailingId;
    }
}
