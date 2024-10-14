package com.travelink.Model;

import java.time.LocalDateTime;

public class RefundingReservationViewDetail {

    // Fields for RefundingReservation
    private int refundingReservationId;
    private LocalDateTime cancelDate;
    private int amount;
    private LocalDateTime refundTime;
    private String status;
    private int reservationId;

    // Fields for Reservation
    private LocalDateTime reservationDate;
    private int numberOfGuests;
    private java.sql.Date checkInDate;
    private java.sql.Date checkOutDate;
    private int totalPrice;
    private String paymentMethod;
    private String reservationStatus;
    private int accountId;

    // Constructor
    public RefundingReservationViewDetail(
            int refundingReservationId, LocalDateTime cancelDate, int amount, LocalDateTime refundTime, String status, int reservationId, LocalDateTime reservationDate, int numberOfGuests, java.sql.Date checkInDate, java.sql.Date checkOutDate,
            int totalPrice, String paymentMethod, String reservationStatus, int accountId) {
        this.refundingReservationId = refundingReservationId;
        this.cancelDate = cancelDate;
        this.amount = amount;
        this.refundTime = refundTime;
        this.status = status;
        this.reservationId = reservationId;
        this.reservationDate = reservationDate;
        this.numberOfGuests = numberOfGuests;
        this.checkInDate = checkInDate;
        this.checkOutDate = checkOutDate;
        this.totalPrice = totalPrice;
        this.paymentMethod = paymentMethod;
        this.reservationStatus = reservationStatus;
        this.accountId = accountId;
    }

    // Default constructor
    public RefundingReservationViewDetail() {
    }

    // Getters and Setters
    public int getRefundingReservationId() {
        return refundingReservationId;
    }

    public void setRefundingReservationId(int refundingReservationId) {
        this.refundingReservationId = refundingReservationId;
    }

    public LocalDateTime getCancelDate() {
        return cancelDate;
    }

    public void setCancelDate(LocalDateTime cancelDate) {
        this.cancelDate = cancelDate;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public LocalDateTime getRefundTime() {
        return refundTime;
    }

    public void setRefundTime(LocalDateTime refundTime) {
        this.refundTime = refundTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getReservationId() {
        return reservationId;
    }

    public void setReservationId(int reservationId) {
        this.reservationId = reservationId;
    }

   

    public LocalDateTime getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDateTime reservationDate) {
        this.reservationDate = reservationDate;
    }

    public int getNumberOfGuests() {
        return numberOfGuests;
    }

    public void setNumberOfGuests(int numberOfGuests) {
        this.numberOfGuests = numberOfGuests;
    }

    public java.sql.Date getCheckInDate() {
        return checkInDate;
    }

    public void setCheckInDate(java.sql.Date checkInDate) {
        this.checkInDate = checkInDate;
    }

    public java.sql.Date getCheckOutDate() {
        return checkOutDate;
    }

    public void setCheckOutDate(java.sql.Date checkOutDate) {
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

    public String getReservationStatus() {
        return reservationStatus;
    }

    public void setReservationStatus(String reservationStatus) {
        this.reservationStatus = reservationStatus;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    @Override
    public String toString() {
        return "RefundingReservationViewDetail{" +
                "refundingReservationId=" + refundingReservationId +
                ", cancelDate=" + cancelDate +
                ", amount=" + amount +
                ", refundTime=" + refundTime +
                ", status='" + status + '\'' +
                ", reservationId=" + reservationId +
                ", reservationDate=" + reservationDate +
                ", numberOfGuests=" + numberOfGuests +
                ", checkInDate=" + checkInDate +
                ", checkOutDate=" + checkOutDate +
                ", totalPrice=" + totalPrice +
                ", paymentMethod='" + paymentMethod + '\'' +
                ", reservationStatus='" + reservationStatus + '\'' +
                ", accountId=" + accountId +
                '}';
    }
}
