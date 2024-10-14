/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class ReservedRoom {
    private int reserved_Room_ID;
    private int amount;
    private int reservation_ID;
    private int room_ID;

    // Constructor
    public ReservedRoom() {}

    // Getters and Setters
    public int getReserved_Room_ID() {
        return reserved_Room_ID;
    }

    public void setReserved_Room_ID(int reserved_Room_ID) {
        this.reserved_Room_ID = reserved_Room_ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public int getRoom_ID() {
        return room_ID;
    }

    public void setRoom_ID(int room_ID) {
        this.room_ID = room_ID;
    }
    
    // toString method
    @Override
    public String toString() {
        return "ReservedRoom{" +
                "reserved_Room_ID=" + reserved_Room_ID +
                ", amount=" + amount +
                ", reservation_ID=" + reservation_ID +
                ", room_ID=" + room_ID +
                '}';
    }
}

