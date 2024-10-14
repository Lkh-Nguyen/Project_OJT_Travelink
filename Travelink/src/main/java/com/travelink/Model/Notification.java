/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import java.time.LocalDateTime;

/**
 *
 * @author DUYAN
 */
public class Notification {

    private int notificationId;
    private int toAccountId;
    private String message;
    private LocalDateTime sentTime;
    private boolean readStatus;
    private String notificationLink;

    public Notification(int notificationId, int toAccountId, String message, LocalDateTime sentTime, boolean readStatus, String notificationLink) {
        this.notificationId = notificationId;
        this.toAccountId = toAccountId;
        this.message = message;
        this.sentTime = sentTime;
        this.readStatus = readStatus;
        this.notificationLink = notificationLink;
    }

    public int getNotificationId() {
        return notificationId;
    }

    public void setNotificationId(int notificationId) {
        this.notificationId = notificationId;
    }

    public int getToAccountId() {
        return toAccountId;
    }

    public void setToAccountId(int toAccountId) {
        this.toAccountId = toAccountId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getSentTime() {
        return sentTime;
    }

    public void setSentTime(LocalDateTime sentTime) {
        this.sentTime = sentTime;
    }

    public boolean isReadStatus() {
        return readStatus;
    }

    public void setReadStatus(boolean readStatus) {
        this.readStatus = readStatus;
    }

    public String getNotificationLink() {
        return notificationLink;
    }

    public void setNotificationLink(String notificationLink) {
        this.notificationLink = notificationLink;
    }
    
    
}
