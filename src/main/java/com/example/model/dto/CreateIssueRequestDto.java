package com.example.model.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
public class CreateIssueRequestDto {

    @NotNull
    private String subject;
    private String description;
    @NotNull
    private PersonDto person;
    @NotNull
    private ProjectDto project;

}
