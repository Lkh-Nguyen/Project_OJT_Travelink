/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author ASUS
 */
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
import java.sql.Date;
import java.time.LocalDateTime;

/**
 *
 * @author ASUS
 */
public class Reservation {

    private int reservationID;
    private LocalDateTime reservationDate;
    private int number_of_guests;
    private Date checkInDate;
    private Date checkOutDate;
    private int totalPrice;
    private String paymentMethod;
    private String status;
    private int account_ID;

    // Default constructor
    public Reservation() {
    }

    public Reservation(LocalDateTime reservationDate, int number_of_guests, Date checkInDate, Date checkOutDate,
            int totalPrice, String paymentMethod, String status, int account_ID) {
        this.reservationDate = reservationDate;
        this.number_of_guests = number_of_guests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.status = status;
        this.account_ID = account_ID;
    }

    public int getReservationID() {
        return reservationID;
    }

    public void setReservationID(int reservationID) {
        this.reservationID = reservationID;
    }

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumber_of_guests() {
        return number_of_guests;
    }

    public void setNumber_of_guests(int number_of_guests) {
        this.number_of_guests = number_of_guests;
    }

    public Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(Date checkOutDate) {
        this.checkOutDate = checkOutDate;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(int totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getAccount_ID() {
        return account_ID;
    }

    public void setAccount_ID(int account_ID) {
        this.account_ID = account_ID;
    }

    @Override
    public String toString() {
        return "Reservation{" + "reservationID=" + reservationID + ", reservationDate=" + reservationDate
                + ", number_of_guests=" + number_of_guests + ", checkInDate=" + checkInDate + ", checkOutDate="
                + checkOutDate + ", totalPrice=" + totalPrice + ", paymentMethod=" + paymentMethod + ", status="
                + status + ", account_ID=" + account_ID + '}';
    }

}
