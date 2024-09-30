package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Room_Bed")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomBed {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_Bed_ID")
    private int roomBedId;

    @Column(name = "Amount")
    private Byte amount;

    @ManyToOne
    @JoinColumn(name = "Bed_ID")
    private Bed bed;

    @ManyToOne
    @JoinColumn(name = "Room_ID")
    private Room room;
}
