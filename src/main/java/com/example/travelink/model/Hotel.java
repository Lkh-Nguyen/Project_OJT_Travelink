package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Hotel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Hotel_ID")
    private int hotelId;

    @Column(name = "Name")
    private String name;

    @Column(name = "Email", nullable = false, unique = true)
    private String email;

    @Column(name = "Star")
    private Byte star;

    @Column(name = "Rating")
    private Float rating;

    @Column(name = "PhoneNumber")
    private String phoneNumber;

    @Column(name = "Description")
    private String description;

    @Column(name = "CheckInTimeStart")
    private java.sql.Time checkInTimeStart;

    @Column(name = "CheckInTimeEnd")
    private java.sql.Time checkInTimeEnd;

    @Column(name = "CheckOutTimeStart")
    private java.sql.Time checkOutTimeStart;

    @Column(name = "CheckOutTimeEnd")
    private java.sql.Time checkOutTimeEnd;

    @Column(name = "Status")
    private String status;

    @Column(name = "Address")
    private String address;

    @Column(name = "URL_Business_License")
    private String urlBusinessLicense;

    @Column(name = "URL_Security_License")
    private String urlSecurityLicense;

    @Column(name = "URL_FireSafety_License")
    private String urlFireSafetyLicense;

    @ManyToOne
    @JoinColumn(name = "Account_ID")
    private Account account;
}
