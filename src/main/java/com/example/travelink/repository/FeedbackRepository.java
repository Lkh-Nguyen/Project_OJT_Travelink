package com.example.travelink.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.Feedback;

@Repository
public interface FeedbackRepository extends JpaRepository<Feedback, Integer> {

        @Query("SELECT f FROM Feedback f " +
                        "JOIN f.reservation r " +
                        "JOIN ReservedRoom rr ON r.reservationId = rr.reservation.reservationId " +
                        "JOIN Room ro ON rr.room.roomId = ro.roomId " +
                        "WHERE ro.hotel.hotelId = :hotelId")
        List<Feedback> findFeedbackByHotelId(@Param("hotelId") int hotelId);

        @Query("SELECT ROUND(AVG(f.rating), 1) FROM Feedback f " +
                        "JOIN f.reservation r " +
                        "JOIN ReservedRoom rr ON r.reservationId = rr.reservation.reservationId " +
                        "JOIN Room ro ON rr.room.roomId = ro.roomId " +
                        "WHERE ro.hotel.hotelId = :hotelId")
        Double findAverageRatingByHotelId(@Param("hotelId") int hotelId);

}