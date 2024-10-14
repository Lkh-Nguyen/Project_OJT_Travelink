/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class RoomFacility {
    private int room_ID;
    private int facility_ID;

    // Constructors
    public RoomFacility() {}

    public RoomFacility(int room_ID, int facility_ID) {
        this.room_ID = room_ID;
        this.facility_ID = facility_ID;
    }

    // Getters and Setters
    public int getRoom_ID() {
        return room_ID;
    }

    public void setRoom_ID(int room_ID) {
        this.room_ID = room_ID;
    }

    public int getFacility_ID() {
        return facility_ID;
    }

    public void setFacility_ID(int facility_ID) {
        this.facility_ID = facility_ID;
    }

    @Override
    public String toString() {
        return "RoomFacility{" + "room_ID=" + room_ID + ", facility_ID=" + facility_ID + '}';
    }
    
}

