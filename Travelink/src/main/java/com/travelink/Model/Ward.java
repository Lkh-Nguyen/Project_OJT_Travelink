/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class Ward {

    private int ward_ID;
    private String name;
    private int district_ID;

    // Getters and Setters
    public int getWard_ID() {
        return ward_ID;
    }

    public void setWard_ID(int ward_ID) {
        this.ward_ID = ward_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getDistrict_ID() {
        return district_ID;
    }

    public void setDistrict_ID(int district_ID) {
        this.district_ID = district_ID;
    }

    public Ward() {
    }

    // Optional: Constructor
    public Ward(int ward_ID, String name, int district_ID) {
        this.ward_ID = ward_ID;
        this.name = name;
        this.district_ID = district_ID;
    }

    // Optional: Override toString() for better printing
    @Override
    public String toString() {
        return "Ward{"
                + "ward_ID=" + ward_ID
                + ", name='" + name + '\''
                + ", district_ID=" + district_ID
                + '}';
    }
}
