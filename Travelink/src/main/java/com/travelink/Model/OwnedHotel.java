/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class OwnedHotel {
    private int hotel_ID;
    private int account_ID;

    // Constructor
    public OwnedHotel(int hotel_ID, int account_ID) {
        this.hotel_ID = hotel_ID;
        this.account_ID = account_ID;
    }

    // Getters and Setters
    public int getHotel_ID() {
        return hotel_ID;
    }

    public void setHotel_ID(int hotel_ID) {
        this.hotel_ID = hotel_ID;
    }

    public int getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(int account_ID) {
        this.account_ID = account_ID;
    }

    // toString method for easy printing
    @Override
    public String toString() {
        return "OwnedHotel{" +
                "hotel_ID=" + hotel_ID +
                ", account_ID=" + account_ID +
                '}';
    }
}

