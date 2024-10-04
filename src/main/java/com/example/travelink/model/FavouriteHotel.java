package com.example.travelink.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Favourite_Hotel")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class FavouriteHotel {

    @EmbeddedId
    private FavouriteHotelKey id;

    @ManyToOne
    @MapsId("hotelId")
    @JoinColumn(name = "Hotel_ID")
    private Hotel hotel;

    @ManyToOne
    @MapsId("accountId")
    @JoinColumn(name = "Account_ID")
    private Account account;
}

@Embeddable
class FavouriteHotelKey implements java.io.Serializable {

    @Column(name = "Hotel_ID")
    private int hotelId;

    @Column(name = "Account_ID")
    private int accountId;
}
