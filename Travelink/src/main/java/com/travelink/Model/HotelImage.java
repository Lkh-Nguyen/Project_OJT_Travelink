/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class HotelImage {

    private int hotelImageID;
    private String name; // Optional: Name of the image
    private String url; // Not null, image URL
    private int hotelID;

    // Getters and Setters
    public int getHotelImageID() {
        return hotelImageID;
    }

    public void setHotelImageID(int hotelImageID) {
        this.hotelImageID = hotelImageID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public HotelImage() {
    }

    // Optional: Constructor
    public HotelImage(int hotelImageID, String name, String url, int hotelID) {
        this.hotelImageID = hotelImageID;
        this.name = name;
        this.url = url;
        this.hotelID = hotelID;
    }
    
    // Optional: Override toString() for better printing
    @Override
    public String toString() {
        return "HotelImage{"
                + "hotelImageID=" + hotelImageID
                + ", name='" + name + '\''
                + ", url='" + url + '\''
                + ", hotelID=" + hotelID
                + '}';
    }
}
