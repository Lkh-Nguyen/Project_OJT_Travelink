/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

public class Province {

    private int province_ID;
    private String name;

    public Province() {
        // Default constructor
    }

    public Province(int province_ID, String name) {
        this.province_ID = province_ID;
        this.name = name;
    }

    // Getters and Setters (using same naming convention as SQL)
    public int getProvince_ID() {
        return province_ID;
    }

    public void setProvince_ID(int province_ID) {
        this.province_ID = province_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    // Override toString() for better printing
    @Override
    public String toString() {
        return "Province [province_ID=" + province_ID + ", name=" + name + "]";
    }
}


