
package com.travelink.Model;

public class HotelInfor {
    private int hotel_ID;
    private String name;
    private int star;
    private String address;
    private String url;
    private int ratingCount;
    private float average;

    public HotelInfor(int hotel_ID, String name, int star, String address, String url, int ratingCount, float average) {
        this.hotel_ID = hotel_ID;
        this.name = name;
        this.star = star;
        this.address = address;
        this.url = url;
        this.ratingCount = ratingCount;
        this.average = average;
    }

    public HotelInfor() {
    }

    public int getHotel_ID() {
        return hotel_ID;
    }

    public void setHotel_ID(int hotel_ID) {
        this.hotel_ID = hotel_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStar() {
        return star;
    }

    public void setStar(int star) {
        this.star = star;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public void setRatingCount(int ratingCount) {
        this.ratingCount = ratingCount;
    }

    public float getAverage() {
        return average;
    }

    public void setAverage(float average) {
        this.average = average;
    }
    
    @Override
    public String toString() {
        return "HotelInfor{" + "hotel_ID=" + hotel_ID + ", name=" + name + ", star=" + star + ", address=" + address + ", url=" + url + ", ratingCount=" + ratingCount + ", average=" + average + '}';
    }
    
    
}
