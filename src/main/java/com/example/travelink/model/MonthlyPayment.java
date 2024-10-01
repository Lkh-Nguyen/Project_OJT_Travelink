package com.example.travelink.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "MonthlyPayment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class MonthlyPayment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Monthly_Payment_ID")
    private int monthlyPaymentId;

    @Column(name = "Month", nullable = false)
    private int month;

    @Column(name = "Year", nullable = false)
    private int year;

    @Column(name = "Amount")
    private int amount;

    @Column(name = "Status")
    private String status;

    @Column(name = "PaymentTime")
    private LocalDateTime paymentTime;

    @ManyToOne
    @JoinColumn(name = "Hotel_ID", nullable = false)
    private Hotel hotel; // Foreign key reference to the Hotel table
}
