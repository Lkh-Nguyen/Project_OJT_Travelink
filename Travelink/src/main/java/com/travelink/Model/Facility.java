/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class Facility {

    private int facilityID;
    private String url; // Can be null for optional image URL
    private String name;

    public Facility() {
        // Default constructor
    }

    public Facility(int facilityID, String url, String name) {
        this.facilityID = facilityID;
        this.url = url;
        this.name = name;
    }

    // Getters and Setters for all member variables
    public int getFacilityID() {
        return facilityID;
    }

    public void setFacilityID(int facilityID) {
        this.facilityID = facilityID;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Facility{"
                + "facilityID=" + facilityID
                + ", url='" + url + '\''
                + ", name='" + name + '\''
                + '}';
    }
}
