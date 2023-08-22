package com.example.model.dto;

import com.example.model.domain.PostOfficeName;
import lombok.Data;

@Data
public class PostOfficeDto {
    private Long id;
    private Integer postOfficeIndex;
    private PostOfficeName postOfficeName;
    private String recipientAddress;
}
