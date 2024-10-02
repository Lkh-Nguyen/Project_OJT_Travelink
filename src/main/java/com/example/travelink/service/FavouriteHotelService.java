package com.example.travelink.service;

import org.hibernate.boot.model.naming.IllegalIdentifierException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.Account;
import com.example.travelink.model.FavouriteHotel;
import com.example.travelink.model.Hotel;
import com.example.travelink.model.FavouriteHotel.FavouriteHotelKey;
import com.example.travelink.repository.CustomerRepository;
import com.example.travelink.repository.FavouriteHotelRepository;
import com.example.travelink.repository.HotelRepository;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;

import java.util.*;

@Service
@AllArgsConstructor
public class FavouriteHotelService {

    @Autowired
    private final FavouriteHotelRepository favouriteHotelRepository;

    @Autowired
    private final HotelRepository hotelRepository;

    @Autowired
    private final CustomerRepository customerRepository;

    // ADD FAVOURITE
    @Transactional
    public void addFavouriteHotel(int hotelId, int accountId) {

        Optional<Hotel> hotel = hotelRepository.findById(hotelId);
        Optional<Account> customer = customerRepository.findById(accountId);

        if (hotel.isPresent() && customer.isPresent()) {
            if (!favouriteHotelRepository.existsByHotel_HotelIdAndAccount_AccountId(hotelId, accountId)) {
                
                //Favourite Hotel Key 
                FavouriteHotelKey favouriteHotelKey = new FavouriteHotelKey();
                favouriteHotelKey.setAccountId(accountId);
                favouriteHotelKey.setHotelId(hotelId);

                //Favourite Hotel 
                FavouriteHotel favouriteHotel = new FavouriteHotel();
                favouriteHotel.setHotel(hotel.get());
                favouriteHotel.setAccount(customer.get());
                favouriteHotel.setId(favouriteHotelKey);

                favouriteHotelRepository.save(favouriteHotel);
            }
        } else {
            throw new IllegalIdentifierException("Hotel or Account is not found");
        }
    }

    // GET FAVOURITE
    @Transactional
    public List<FavouriteHotel> getFavouriteHotelsByAccount(int accountId) {
        return favouriteHotelRepository.findByAccount_AccountId(accountId); 
    }

    // DELETE FAVOURITE
    @Transactional
    public void deleteFavouriteHotel(int hotelId, int accountId) {
        FavouriteHotel favouriteHotel = favouriteHotelRepository.findByHotel_HotelIdAndAccount_AccountId(hotelId, accountId)
                .orElseThrow(() -> new IllegalIdentifierException("Favourite hotel are not found"));
        
        favouriteHotelRepository.delete(favouriteHotel);
    }

}
