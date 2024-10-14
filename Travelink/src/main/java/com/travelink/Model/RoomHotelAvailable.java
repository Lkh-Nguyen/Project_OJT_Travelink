/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import java.util.Date;

/**
 *
 * @author MSI
 */
public class RoomHotelAvailable {
    private int hotelId;
    private int roomId;
    private int roomAvailable;
    private Date date;

    public RoomHotelAvailable() {
    }

    public RoomHotelAvailable(int hotelId, int roomId, int roomAvailable, Date date) {
        this.hotelId = hotelId;
        this.roomId = roomId;
        this.roomAvailable = roomAvailable;
        this.date = date;
    }

    public int getHotelId() {
        return hotelId;
    }

    public void setHotelId(int hotelId) {
        this.hotelId = hotelId;
    }

    public int getRoomId() {
        return roomId;
    }

    public void setRoomId(int roomId) {
        this.roomId = roomId;
    }

    public int getRoomAvailable() {
        return roomAvailable;
    }

    public void setRoomAvailable(int roomAvailable) {
        this.roomAvailable = roomAvailable;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "RoomHotelAvailable{" + "hotelId=" + hotelId + ", roomId=" + roomId + ", roomAvailable=" + roomAvailable + ", date=" + date + '}';
    }
    
    
}
