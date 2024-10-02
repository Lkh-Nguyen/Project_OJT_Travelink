package com.example.travelink.repository;

import com.example.travelink.model.FavouriteHotel;
import com.example.travelink.model.FavouriteHotel.FavouriteHotelKey;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface FavouriteHotelRepository extends JpaRepository<FavouriteHotel, FavouriteHotelKey> {

    List<FavouriteHotel> findByAccount_AccountId(int accountId);

    boolean existsByHotel_HotelIdAndAccount_AccountId(int hotelId, int accountId);

    Optional<FavouriteHotel> findByHotel_HotelIdAndAccount_AccountId(int hotelId, int accountId);
}
