package com.example.model.dto;

import com.example.model.domain.MailType;
import com.example.model.domain.MailingStatus;
import com.example.model.domain.PostOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Data
public class UpdateMailingRequestDto {

    @NotNull
    private UUID uuid;
    @NotNull
    private MailType mailType;
    @NotNull
    private Integer mailIndexRecipient;
    @NotNull
    private String recipientName;
    @NotNull
    private String recipientAddress;
    private MailingStatus mailingStatus;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant registrationTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant sentedTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant receivedTime;

    @NotNull
    private PostOffice postOffice;
}
