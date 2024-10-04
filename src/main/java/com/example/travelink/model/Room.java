package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Room")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_ID")
    private int roomId;

    @Column(name = "Name", nullable = false)
    private String name;

    @Column(name = "Room_Description")
    private String roomDescription;

    @Column(name = "Capacity")
    private Byte capacity;

    @Column(name = "Total_Rooms")
    private Byte totalRooms;

    @Column(name = "Price")
    private int price;

    @Column(name = "Status", nullable = false)
    private String status;

    @ManyToOne
    @JoinColumn(name = "Hotel_ID")
    private Hotel hotel;
}
