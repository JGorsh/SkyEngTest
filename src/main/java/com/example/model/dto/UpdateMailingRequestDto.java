package com.example.model.dto;

import com.example.model.domain.MailType;
import com.example.model.domain.PostOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;

@Data
public class UpdateMailingRequestDto {

    @NotNull
    private Long uuid;
    @NotNull
    private MailType mailType;
    @NotNull
    private String mailIndex;
    @NotNull
    private String recipientName;
    @NotNull
    private String recipientAddress;
    @NotNull
    private boolean isReceived;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant sentedTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant receivedTime;

    private PostOffice postOffice;
}
