package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Hotel_Image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hotel_Image_ID")
    private int hotelImageId;

    @Column(name = "Name")
    private String name;

    @Column(name = "URL", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "Hotel_ID")
    private Hotel hotel;
}
