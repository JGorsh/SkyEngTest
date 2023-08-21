package com.example.model.dto;

import com.example.model.domain.MailType;
import com.example.model.domain.PostOffice;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateMailingRequestDto {

    @NotNull
    private MailType mailType;
    @NotNull
    private Integer mailIndex;
    @NotNull
    private String recipientName;
    @NotNull
    private String recipientAddress;
//    private boolean isReceived;
    @NotNull
    private PostOffice postOffice;
}
