package com.example.model.dto;

import com.example.model.domain.MailType;
import com.example.model.domain.PostOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.time.Instant;

@Data
public class MailingDto {

    private Long uuid;
    private MailType mailType;
    private String mailIndex;
    private String recipientName;
    private String recipientAddress;
    private boolean isReceived;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant sentedTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant receivedTime;

    private PostOffice postOffice;
}
