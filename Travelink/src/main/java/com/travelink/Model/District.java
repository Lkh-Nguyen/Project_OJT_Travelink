/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class District {

    private int district_ID;
    private String name;
    private int province_ID;

    // Getters and Setters
    public int getDistrict_ID() {
        return district_ID;
    }

    public void setDistrict_ID(int district_ID) {
        this.district_ID = district_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getProvince_ID() {
        return province_ID;
    }

    public void setProvince_ID(int province_ID) {
        this.province_ID = province_ID;
    }

    // Optional: Constructor
    public District(int district_ID, String name, int province_ID) {
        this.district_ID = district_ID;
        this.name = name;
        this.province_ID = province_ID;
    }

    public District() {
    }

    // Optional: Override toString() for better printing
    @Override
    public String toString() {
        return "District{"
                + "district_ID=" + district_ID
                + ", name='" + name + '\''
                + ", province_ID=" + province_ID
                + '}';
    }
}
