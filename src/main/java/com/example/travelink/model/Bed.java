package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Bed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Bed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Bed_ID")
    private int bedId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Description")
    private String description;

    @Column(name = "URL")
    private String url;
}
