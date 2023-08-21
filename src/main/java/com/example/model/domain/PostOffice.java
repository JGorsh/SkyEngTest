package com.example.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class PostOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer mailIndex;
    @Enumerated(EnumType.STRING)
    private PostOfficeName postOfficeName;
    private String recipientAddress;
}
