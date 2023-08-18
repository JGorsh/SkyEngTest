package com.example.model.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.time.Instant;

@Entity
@Table
@Getter
@Setter
public class Mailing {

    @Id
    @GeneratedValue(generator = "uuid")
    @GenericGenerator(name = "uuid", strategy = "uuid")
    @Column(name = "uuid", unique = true)
    private Long uuid;
    private MailType mailType;
    private String mailIndex;
    private String recipientName;
    private String recipientAddress;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant sentedTime;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss", timezone = "UTC")
    private Instant receivedTime;



    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="postOffice_id")
    private PostOffice postOfficeList;
}
