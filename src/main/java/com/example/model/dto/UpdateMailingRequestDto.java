package com.example.model.dto;

import com.example.model.domain.MailType;
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
    private MailType mailType;
    private Integer mailIndex;
    private String recipientName;
    private String recipientAddress;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
//    private Instant sentedTime;
//
//    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
//    private Instant receivedTime;

    private PostOffice postOffice;
}
