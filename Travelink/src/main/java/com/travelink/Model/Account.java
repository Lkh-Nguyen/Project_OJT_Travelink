/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import java.sql.Date;

public class Account {

    private int account_ID;
    private String email;
    private String password;
    private String cmnd;
    private String name;
    private char gender;
    private java.sql.Date dateOfBirth;
    private String phoneNumber;
    private String avatarURL;
    private String address;
    private int role;
    private int status;

    public int getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(int account_ID) {
        this.account_ID = account_ID;
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

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Account(String email, String password, String cmnd, String name, char gender, Date dateOfBirth, String phoneNumber, String avatarURL, String address, int role, int status) {
        this.email = email;
        this.password = password;
        this.cmnd = cmnd;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.avatarURL = avatarURL;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public Account(String email, String name, int role, int status) {
        this.email = email;
        this.name = name;
        this.role = role;
        this.status = status;
    }

    public Account(String email, String password, String name, String phoneNumber, int role, int status) {
        this.email = email;
        this.password = password;
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.role = role;
        this.status = status;
    }

    public Account(String email, String cmnd, String name, char gender, Date dateOfBirth, String phoneNumber, String address, int role, int status) {
        this.email = email;
        this.cmnd = cmnd;
        this.name = name;
        this.gender = gender;
        this.dateOfBirth = dateOfBirth;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.role = role;
        this.status = status;
    }

    public Account() {
    }
    
    

    @Override
    public String toString() {
        return "Account{" + "account_ID=" + account_ID + ", email=" + email + ", password=" + password + ", cmnd=" + cmnd + ", name=" + name + ", gender=" + gender + ", dateOfBirth=" + dateOfBirth + ", phoneNumber=" + phoneNumber + ", avatarURL=" + avatarURL + ", address=" + address + ", role=" + role + ", status=" + status + '}';
    }
}