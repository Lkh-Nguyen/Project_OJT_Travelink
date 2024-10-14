/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class FavouriteHotel {

  private int hotelID;
  private int account_ID;

    public FavouriteHotel() {
    }

    public FavouriteHotel(int hotelID, int account_ID) {
        this.hotelID = hotelID;
        this.account_ID = account_ID;
    }

    public int getHotelID() {
        return hotelID;
    }

    public void setHotelID(int hotelID) {
        this.hotelID = hotelID;
    }

    public int getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(int account_ID) {
        this.account_ID = account_ID;
    }

    @Override
    public String toString() {
        return "FavouriteHotel{" + "hotelID=" + hotelID + ", account_ID=" + account_ID + '}';
    }


}

