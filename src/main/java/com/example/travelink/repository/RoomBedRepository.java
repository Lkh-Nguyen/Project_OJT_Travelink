package com.example.travelink.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.RoomBed;

@Repository
public interface RoomBedRepository extends JpaRepository<RoomBed, Integer> {

    // @Query("SELECT rb FROM RoomBed rb JOIN rb.room r JOIN r.hotel h WHERE
    // h.hotelId = :hotelId")
    // List<RoomBed> findRoomBedsByHotelId(int hotelId);

    List<RoomBed> findByRoom_RoomId(int roomId);
}
