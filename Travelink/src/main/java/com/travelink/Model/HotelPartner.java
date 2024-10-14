/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import com.travelink.Model.Account;
import com.travelink.Model.Hotel;

/**
 *
 * @author ASUS
 */
public class HotelPartner {
    private Hotel hotel;
    private Account account;
    private String province;

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public HotelPartner(Hotel hotel, Account account, String province) {
        this.hotel = hotel;
        this.account = account;
        this.province = province;
    }

    public HotelPartner() {
    }

    
    @Override
    public String toString() {
        return "HotelPartner{" + "hotel=" + hotel + ", account=" + account + ", province=" + province + '}';
    }    
}
