package com.example.model.dto;

import com.example.model.domain.PostOffice;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.validation.constraints.NotNull;
import java.time.Instant;
import java.util.UUID;

@Data
public class CreateArrivalMailingRequestDto {

    @NotNull
    private UUID uuid;

    @NotNull
    private PostOffice postOffice;
}
