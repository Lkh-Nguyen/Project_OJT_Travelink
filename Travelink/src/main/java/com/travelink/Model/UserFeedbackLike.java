/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Model;

/**
 *
 * @author DUYAN
 */
public class UserFeedbackLike {
    private int likeId;
    private int accountId;
    private int feedbackId;
    private boolean liked;
    private boolean disliked;

    public UserFeedbackLike(int likeId, int accountId, int feedbackId, boolean liked, boolean disliked) {
        this.likeId = likeId;
        this.accountId = accountId;
        this.feedbackId = feedbackId;
        this.liked = liked;
        this.disliked = disliked;
    }

    public UserFeedbackLike() {
    }
    
    

    public int getLikeId() {
        return likeId;
    }

    public void setLikeId(int likeId) {
        this.likeId = likeId;
    }

    public int getAccountId() {
        return accountId;
    }

    public void setAccountId(int accountId) {
        this.accountId = accountId;
    }

    public int getFeedbackId() {
        return feedbackId;
    }

    public void setFeedbackId(int feedbackId) {
        this.feedbackId = feedbackId;
    }

    public boolean isLiked() {
        return liked;
    }

    public void setLiked(boolean liked) {
        this.liked = liked;
    }

    public boolean isDisliked() {
        return disliked;
    }

    public void setDisliked(boolean disliked) {
        this.disliked = disliked;
    }
}

