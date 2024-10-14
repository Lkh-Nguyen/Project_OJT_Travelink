/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class HotelService {

    private int hotelServiceID;
    private int hotelID;
    private int serviceID;
    private Integer price; // Can be null for services without a price
    private Hotel hotel;

    public HotelService() {
        // Default constructor
    }

    public HotelService(int hotelID, int serviceID, Integer price) {
        this.hotelID = hotelID;
        this.serviceID = serviceID;
        this.price = price;
    }

    // Getters and Setters for member variables
    public int getHotelServiceID() {
        return hotelServiceID;
    }

    public void setHotelServiceID(int hotelServiceID) {
        this.hotelServiceID = hotelServiceID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getServiceID() {
        return serviceID;
    }

    public void setServiceID(int serviceID) {
        this.serviceID = serviceID;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }


    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }


    @Override
    public String toString() {
        return "HotelService{"
                + "hotelServiceID=" + hotelServiceID
                + ", hotelID=" + hotelID
                + ", serviceID=" + serviceID
                + ", price=" + price
                + '}';
    }
}
