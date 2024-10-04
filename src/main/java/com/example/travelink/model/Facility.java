package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Facility")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Facility {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Facility_ID")
    private int facilityId;

    @Column(name = "URL")
    private String url;

    @Column(name = "Name", nullable = false)
    private String name;
}
