package com.travelink.Model;

import java.time.LocalDateTime;

public class RefundingReservation {

    private int refundingReservationId;
    private LocalDateTime cancelDate;
    private int amount;
    private LocalDateTime refundTime;
    private String status;
    private int reservationId;

    // Constructor
    public RefundingReservation(int refundingReservationId, LocalDateTime cancelDate, int amount, LocalDateTime refundTime, String status, int reservationId) {
        this.refundingReservationId = refundingReservationId;
        this.cancelDate = cancelDate;
        this.amount = amount;
        this.refundTime = refundTime;
        this.status = status;
        this.reservationId = reservationId;
    }

    // Default constructor
    public RefundingReservation() {
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

    @Override
    public String toString() {
        return "RefundingReservation{"
                + "refundingReservation_ID=" + refundingReservationId
                + ", cancelDate=" + cancelDate
                + ", amount=" + amount
                + ", refundTime=" + refundTime
                + ", status='" + status + '\''
                + ", reservation_ID=" + reservationId
                + '}';
    }
}
