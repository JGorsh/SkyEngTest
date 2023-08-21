package com.example.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table
@Getter
@Setter
public class PostOffice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Integer mailIndex;
    private String namePostOffice;
    private String recipientAddress;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="mailing_id")
    private Mailing mailing;


}
