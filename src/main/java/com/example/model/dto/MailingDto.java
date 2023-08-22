package com.example.model.dto;

import com.example.model.domain.MailType;
import com.example.model.domain.MailingStatus;
import com.example.model.domain.PostOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Data
public class MailingDto {

    private Long id;
    private UUID uuid;
    private MailType mailType;
    private Integer mailIndexRecipient;
    private String recipientName;
    private String recipientAddress;
    private MailingStatus mailingStatus;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant registrationTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant sentedTime;
    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant receivedTime;

    private PostOffice postOffice;
}
