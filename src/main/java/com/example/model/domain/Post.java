package com.example.model.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String postIndex;
    private String name;
    private String recipientAddress;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinColumn(name="postOffice_id")
    private List<PostOffice> postOfficeList;

}
