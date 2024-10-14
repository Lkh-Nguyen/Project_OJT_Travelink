/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class RoomImage {

    private int room_Image_ID;
    private String name; // Optional: Name of the image
    private String url; // Not null, image URL
    private int room_ID;

    // Getters and Setters
    public int getRoom_Image_ID() {
        return room_Image_ID;
    }

    public void setRoom_Image_ID(int room_Image_ID) {
        this.room_Image_ID = room_Image_ID;
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

    public int getRoom_ID() {
        return room_ID;
    }

    public void setRoom_ID(int room_ID) {
        this.room_ID = room_ID;
    }

    public RoomImage() {
    }

    // Optional: Constructor
    public RoomImage(int room_Image_ID, String name, String url, int room_ID) {
        this.room_Image_ID = room_Image_ID;
        this.name = name;
        this.url = url;
        this.room_ID = room_ID;
    }

    // Optional: Override toString() for better printing
    @Override
    public String toString() {
        return "RoomImage{"
                + "room_Image_ID=" + room_Image_ID
                + ", name='" + name + '\''
                + ", url='" + url + '\''
                + ", room_ID=" + room_ID
                + '}';
    }
}
