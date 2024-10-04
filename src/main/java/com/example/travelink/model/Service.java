package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Service {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Service_ID")
    private int serviceId;

    @Column(name = "Name", nullable = false)
    private String name;
}
