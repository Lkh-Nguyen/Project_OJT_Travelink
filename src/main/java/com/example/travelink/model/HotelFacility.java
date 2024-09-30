package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Hotel_Facility")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class HotelFacility {

    @EmbeddedId
    private HotelFacilityKey id;

    @ManyToOne
    @MapsId("hotelId")
    @JoinColumn(name = "Hotel_ID")
    private Hotel hotel;

    @ManyToOne
    @MapsId("facilityId")
    @JoinColumn(name = "Facility_ID")
    private Facility facility;
}

@Embeddable
class HotelFacilityKey implements java.io.Serializable {

    @Column(name = "Hotel_ID")
    private int hotelId;

    @Column(name = "Facility_ID")
    private int facilityId;
}
