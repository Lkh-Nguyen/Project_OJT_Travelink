/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class MonthlyPayment {

    private int monthlyPaymentId;
    private int month;
    private int year;
    private int amount;
    private String status;
    private LocalDateTime paymentTime;
    private int hotel_ID;
    
    public int getMonthlyPaymentId() {
        return monthlyPaymentId;
    }

    public void setMonthlyPaymentId(int monthlyPaymentId) {
        this.monthlyPaymentId = monthlyPaymentId;
    }

    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getPaymentTime() {
        return paymentTime;
    }

    public void setPaymentTime(LocalDateTime paymentTime) {
        this.paymentTime = paymentTime;
    }



    public MonthlyPayment() {
    }

    public int getHotel_ID() {
        return hotel_ID;
    }

    public void setHotel_ID(int hotel_ID) {
        this.hotel_ID = hotel_ID;
    }

    @Override
    public String toString() {
        return "MonthlyPayment{" + "monthlyPaymentId=" + monthlyPaymentId + ", month=" + month + ", year=" + year + ", amount=" + amount + ", status=" + status + ", paymentTime=" + paymentTime + ", hotel_ID=" + hotel_ID + '}';
    }

    

}
