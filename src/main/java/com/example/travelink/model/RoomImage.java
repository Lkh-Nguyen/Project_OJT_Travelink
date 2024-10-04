package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Room_Image")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Room_Image_ID")
    private int roomImageId;

    @Column(name = "Name")
    private String name;

    @Column(name = "URL", nullable = false)
    private String url;

    @ManyToOne
    @JoinColumn(name = "Room_ID")
    private Room room;
}
