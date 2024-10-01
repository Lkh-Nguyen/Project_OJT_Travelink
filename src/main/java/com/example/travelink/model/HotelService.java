package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Hotel_Service")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hotel_Service_ID")
    private int hotelServiceId;

    @ManyToOne
    @JoinColumn(name = "Hotel_ID")
    private Hotel hotel;

    @ManyToOne
    @JoinColumn(name = "Service_ID")
    private Service service;

    @Column(name = "Price")
    private int price;
}
