package com.example.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.time.Instant;
import java.util.UUID;

@Entity
@Table
@Getter
@Setter
public class Mailing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private UUID uuid;

    @Enumerated(EnumType.STRING)
    private MailType mailType;
    private Integer mailIndex;
    private String recipientName;
    private String recipientAddress;

    @Enumerated(EnumType.STRING)
    private MailingStatus mailingStatus;

    @CreationTimestamp
    private Instant registrationTime;
    private Instant sentedTime;
    private Instant receivedTime;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="post_office_id")
    private PostOffice postOffice;



}
