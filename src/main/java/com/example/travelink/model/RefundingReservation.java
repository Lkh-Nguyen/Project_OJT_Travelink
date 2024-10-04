package com.example.travelink.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Refunding_Reservation")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RefundingReservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Refunding_Reservation_ID")
    private int refundingReservationId;

    @Column(name = "Cancel_Date", nullable = false)
    private Timestamp cancelDate;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Percentage")
    private int percentage;

    @Column(name = "RefundTime")
    private Timestamp refundTime;

    @Column(name = "Status")
    private String status;

    @OneToOne
    @JoinColumn(name = "Reservation_ID", unique = true)
    private Reservation reservation;
}
