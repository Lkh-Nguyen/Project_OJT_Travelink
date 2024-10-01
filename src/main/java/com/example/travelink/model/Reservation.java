package com.example.travelink.model;

import java.sql.Date;
import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reservation_ID")
    private int reservationId;

    @Column(name = "Reservation_Date", nullable = false)
    private Timestamp reservationDate;

    @Column(name = "Number_of_guests", nullable = false)
    private Byte numberOfGuests;

    @Column(name = "CheckInDate", nullable = false)
    private Date checkInDate;

    @Column(name = "CheckOutDate", nullable = false)
    private Date checkOutDate;

    @Column(name = "Total_Price", nullable = false)
    private int totalPrice;

    @Column(name = "Payment_Method", nullable = false)
    private String paymentMethod;

    @Column(name = "Status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "Account_ID")
    private Account account;
}
