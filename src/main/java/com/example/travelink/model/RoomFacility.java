package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;
import java.io.Serializable;

@Entity
@Table(name = "Room_Facility")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class RoomFacility {

    @EmbeddedId
    private RoomFacilityKey id;

    @ManyToOne
    @MapsId("roomId")
    @JoinColumn(name = "Room_ID")
    private Room room;

    @ManyToOne
    @MapsId("facilityId")
    @JoinColumn(name = "Facility_ID")
    private Facility facility;

    // Không cần định nghĩa RoomFacilityKey ở đây

    @Embeddable
    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    public class RoomFacilityKey implements Serializable {

        @Column(name = "Room_ID")
        private int roomId;

        @Column(name = "Facility_ID")
        private int facilityId;
    }
}


