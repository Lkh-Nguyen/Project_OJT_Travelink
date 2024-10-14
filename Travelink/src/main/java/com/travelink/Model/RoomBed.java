/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class RoomBed {
    private int room_Bed_ID;
    private int amount;
    private int bed_ID;
    private int room_ID;

    // Constructors
    public RoomBed() {}

    public RoomBed(int room_Bed_ID, int amount, int bed_ID, int room_ID) {
        this.room_Bed_ID = room_Bed_ID;
        this.amount = amount;
        this.bed_ID = bed_ID;
        this.room_ID = room_ID;
    }

    // Getters and Setters
    public int getRoom_Bed_ID() {
        return room_Bed_ID;
    }

    public void setRoom_Bed_ID(int room_Bed_ID) {
        this.room_Bed_ID = room_Bed_ID;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public int getBed_ID() {
        return bed_ID;
    }

    public void setBed_ID(int bed_ID) {
        this.bed_ID = bed_ID;
    }

    public int getRoom_ID() {
        return room_ID;
    }

    public void setRoom_ID(int room_ID) {
        this.room_ID = room_ID;
    }

    @Override
    public String toString() {
        return "RoomBed{" + "room_Bed_ID=" + room_Bed_ID + ", amount=" + amount + ", bed_ID=" + bed_ID + ", room_ID=" + room_ID + '}';
    }


}
