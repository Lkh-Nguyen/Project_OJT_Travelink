/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
public class Bed {

    private int bed_ID;
    private String name;
    private String description;
    private String url;

    // Constructors
    public Bed() {
    }

    public Bed(int bed_ID, String name, String description, String url) {
        this.bed_ID = bed_ID;
        this.name = name;
        this.description = description;
        this.url = url;
    }

    // Getters and Setters
    public int getBed_ID() {
        return bed_ID;
    }

    public void setBed_ID(int bed_ID) {
        this.bed_ID = bed_ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Bed{" + "name=" + name + ", description=" + description + ", url=" + url + '}';
    }


}
