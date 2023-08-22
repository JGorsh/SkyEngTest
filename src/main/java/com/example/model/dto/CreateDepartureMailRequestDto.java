package com.example.model.dto;

import com.example.model.domain.PostOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Data
public class CreateDepartureMailRequestDto {

    @NotNull
    private UUID uuid;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant receivedTime;

    @NotNull
    private PostOffice postOffice;

}
