package com.example.model.dto;

import com.example.model.domain.PostOffice;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.util.UUID;

@Data
public class CreateReceiptAddresseeRequestDto {

    @NotNull
    private UUID uuid;
    @NotNull
    private Integer mailIndexRecipient;
    @NotNull
    private String recipientName;
    @NotNull
    private String recipientAddress;
    @NotNull
    private PostOffice postOffice;
}
