package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reserved_Room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReservedRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reserved_Room_ID")
    private int reservedRoomId;

    @Column(name = "Amount")
    private Byte amount;

    @ManyToOne
    @JoinColumn(name = "Reservation_ID")
    private Reservation reservation;

    @ManyToOne
    @JoinColumn(name = "Room_ID")
    private Room room;
}
