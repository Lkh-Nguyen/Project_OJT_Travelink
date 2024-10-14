/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author DUYAN
 */
import com.google.gson.Gson;
import java.sql.Timestamp;

public class Message {

    private int toId;
    private int fromId;
    private String messageText;
    private Timestamp sentTime;

    public Timestamp getSentTime() {
        return sentTime;
    }

    public void setSentTime(Timestamp sentTime) {
        this.sentTime = sentTime;
    }

    public Message(int toId, int fromId, String messageText) {
        this.toId = toId;
        this.fromId = fromId;
        this.messageText = messageText;
    }

    public Message(String toId) {
        this.toId = Integer.parseInt(toId);
    }
    
    public Message(){
        
    }

    public int getToId() {
        return toId;
    }

    public void setToId(int toId) {
        this.toId = toId;
    }

    public int getFromId() {
        return fromId;
    }

    public void setFromId(int fromId) {
        this.fromId = fromId;
    }

    public String getMessageText() {
        return messageText;
    }

    public void setMessageText(String messageText) {
        this.messageText = messageText;
    }
    
    public String toJson() {
        Gson gson = new Gson();
        return gson.toJson(this);
    }

    public static Message fromJson(String json) {
        Gson gson = new Gson();
        return gson.fromJson(json, Message.class);
    }
}
