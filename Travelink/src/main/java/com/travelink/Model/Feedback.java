/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

import com.travelink.Database.AccountDB;
import com.travelink.Database.HotelDB;
import com.travelink.Database.UserFeedbackLikeDB;
import java.sql.Date;

/**
 *
 * @author ASUS
 */
public class Feedback {

  private int feedbackID;
  private String description;
  private byte rating; // Assuming rating is stored as a byte (0-127)
  private java.sql.Date date;
  private int likesCount;
  private int dislikesCount;
  private int reservation_ID;


    public Feedback() {
    }

    public Feedback(String description, byte rating, Date date, int likesCount, int dislikesCount, int reservation_ID) {
        this.description = description;
        this.rating = rating;
        this.date = date;
        this.likesCount = likesCount;
        this.dislikesCount = dislikesCount;
        this.reservation_ID = reservation_ID;
    }

  
    public int getFeedbackID() {
        return feedbackID;
    }

    public void setFeedbackID(int feedbackID) {
        this.feedbackID = feedbackID;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public byte getRating() {
        return rating;
    }

    public void setRating(byte rating) {
        this.rating = rating;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getLikesCount() {
        return likesCount;
    }

    public void setLikesCount(int likesCount) {
        this.likesCount = likesCount;
    }

    public int getDislikesCount() {
        return dislikesCount;
    }

    public void setDislikesCount(int dislikesCount) {
        this.dislikesCount = dislikesCount;
    }

    public int getReservation_ID() {
        return reservation_ID;
    }

    public void setReservation_ID(int reservation_ID) {
        this.reservation_ID = reservation_ID;
    }

    public Account getAccount(int feedbackID){
        return AccountDB.getAccountByFeedbackID(feedbackID);
    }
    
    public Hotel getHotel(int feedbackID){
        return HotelDB.getHotelByFeedbackID(feedbackID);
    }
    public String getStatusLike(int feedbackID,int accountID){
        UserFeedbackLike ufl = new UserFeedbackLike();
        try {
            ufl = UserFeedbackLikeDB.getUserFeedbackLikeByAccountIDAndFeedbackID(accountID, feedbackID);
        } catch (Exception e) {
        }
        if (ufl == null) return "";
        else if (ufl.isLiked() == true) return "liked";
                else return "";
    }
    public String getStatusDislike(int feedbackID,int accountID){
        UserFeedbackLike ufl = new UserFeedbackLike();
        try {
            ufl = UserFeedbackLikeDB.getUserFeedbackLikeByAccountIDAndFeedbackID(accountID, feedbackID);
        } catch (Exception e) {
        }
        if (ufl == null) return "";
        else if (ufl.isDisliked()== true) return "disliked";
                else return "";
    }
    @Override
    public String toString() {
        return "Feedback{" + "feedbackID=" + feedbackID + ", description=" + description + ", rating=" + rating + ", date=" + date + ", likesCount=" + likesCount + ", dislikesCount=" + dislikesCount + ", reservation_ID=" + reservation_ID + '}';
    } 
}
