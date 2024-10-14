/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class PendingHost {

    private int pending_Host_ID;
    private String email;
    private String password;
    private String cmnd;
    private String name;
    private char gender;
    private Date dateOfBirth;
    private String phoneNumber;
    private String avatarURL;
    private String address;

    public PendingHost() {
    }

    public PendingHost(String email, String password, String cmnd, String name, char gender, Date dateOfBirth, String phoneNumber, String avatarURL, String address) {
        this.email = email;
        this.password = password;
        this.cmnd = cmnd;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.avatarURL = avatarURL;
        this.address = address;
    }
    // Getters and setters for all fields (omitted for brevity)

    public int getPending_Host_ID() {
        return pending_Host_ID;
    }

    public void setPending_Host_ID(int pending_Host_ID) {
        this.pending_Host_ID = pending_Host_ID;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCmnd() {
        return cmnd;
    }

    public void setCmnd(String cmnd) {
        this.cmnd = cmnd;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getAvatarURL() {
        return avatarURL;
    }

    public void setAvatarURL(String avatarURL) {
        this.avatarURL = avatarURL;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "PendingHost{" + "pending_Host_ID=" + pending_Host_ID + ", email=" + email + ", password=" + password + ", cmnd=" + cmnd + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + ", avatarURL=" + avatarURL + ", address=" + address + '}';
    }

}

