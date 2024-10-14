/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class HotelFacility {

    private int hotelID;
    private int facilityID;

    public HotelFacility() {
        // Default constructor
    }

    public HotelFacility(int hotelID, int facilityID) {
        this.hotelID = hotelID;
        this.facilityID = facilityID;
    }

    // Getters and Setters for member variables
    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    @Override
    public String toString() {
        return "HotelFacility{"
                + "hotelID=" + hotelID
                + ", facilityID=" + facilityID
                + '}';
    }
}
