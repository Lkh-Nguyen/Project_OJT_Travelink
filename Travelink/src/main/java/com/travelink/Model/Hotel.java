/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import com.travelink.Database.HotelImageDB;
import com.travelink.Database.HotelServiceDB;
import com.travelink.Database.ProvinceDB;
import java.time.LocalTime;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class Hotel {
    private int hotel_ID;
    private String name;
    private String email;
    private int star;
    private float rating;
    private String phoneNumber;
    private String description;
    private LocalTime checkInTimeStart;
    private LocalTime checkInTimeEnd;
    private LocalTime checkOutTimeStart;
    private LocalTime checkOutTimeEnd;
    private String address;
    private String status;
    private int ward_ID;

    // Constructor
    public Hotel() {}

    public Hotel(String name, String email, String phoneNumber, String address, String status) {
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.status = status;
    }
    

    // Getters and Setters
    public int getHotel_ID() {
        return hotel_ID;
    }

    public void setHotel_ID(int hotel_ID) {
        this.hotel_ID = hotel_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getCheckInTimeStart() {
        return checkInTimeStart;
    }

    public void setCheckInTimeStart(LocalTime checkInTimeStart) {
        this.checkInTimeStart = checkInTimeStart;
    }

    public LocalTime getCheckInTimeEnd() {
        return checkInTimeEnd;
    }

    public void setCheckInTimeEnd(LocalTime checkInTimeEnd) {
        this.checkInTimeEnd = checkInTimeEnd;
    }

    public LocalTime getCheckOutTimeStart() {
        return checkOutTimeStart;
    }

    public void setCheckOutTimeStart(LocalTime checkOutTimeStart) {
        this.checkOutTimeStart = checkOutTimeStart;
    }

    public LocalTime getCheckOutTimeEnd() {
        return checkOutTimeEnd;
    }

    public void setCheckOutTimeEnd(LocalTime checkOutTimeEnd) {
        this.checkOutTimeEnd = checkOutTimeEnd;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getWard_ID() {
        return ward_ID;
    }

    public void setWard_ID(int ward_ID) {
        this.ward_ID = ward_ID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    
    

    public Hotel(String name, String email, int star, String phoneNumber, String description, LocalTime checkInTimeStart, LocalTime checkInTimeEnd, LocalTime checkOutTimeStart, LocalTime checkOutTimeEnd, String address, int ward_ID) {
        this.name = name;
        this.email = email;
        this.star = star;
        this.phoneNumber = phoneNumber;
        this.description = description;
        this.checkInTimeStart = checkInTimeStart;
        this.checkInTimeEnd = checkInTimeEnd;
        this.checkOutTimeStart = checkOutTimeStart;
        this.checkOutTimeEnd = checkOutTimeEnd;
        this.address = address;
        this.ward_ID = ward_ID;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }
    
    public String getAnURL(){
        return HotelImageDB.getHotelImagesByHotelID(this.hotel_ID).get(0).getUrl();
    }
    
    public String getLocation(){
        return ProvinceDB.getLocationByHotelID(hotel_ID);
    }

    public List<Service> getService(){
        return HotelServiceDB.getServicesByHotelID(hotel_ID);
    }
    @Override
    public String toString() {
        return "Hotel{" + "hotel_ID=" + hotel_ID + ", name=" + name + ", email=" + email + ", star=" + star + ", rating=" + rating + ", phoneNumber=" + phoneNumber + ", description=" + description + ", checkInTimeStart=" + checkInTimeStart + ", checkInTimeEnd=" + checkInTimeEnd + ", checkOutTimeStart=" + checkOutTimeStart + ", checkOutTimeEnd=" + checkOutTimeEnd + ", address=" + address + ", status=" + status + ", ward_ID=" + ward_ID + '}';
    }    
}

