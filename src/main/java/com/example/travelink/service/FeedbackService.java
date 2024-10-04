package com.example.travelink.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.travelink.model.Feedback;
import com.example.travelink.repository.FeedbackRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class FeedbackService {

    private final FeedbackRepository feedbackRepository;

    // get List FeedbackByID_Hotel
    public List<Feedback> getFeedbackByHotelId(int hotelId) {
        return feedbackRepository.findFeedbackByHotelId(hotelId);
    }

    // get Doublte FeedbackByID_Hotel
    public double findAverageRatingByHotelId(int hotelId) {
        return feedbackRepository.findAverageRatingByHotelId(hotelId);
    }

    public Feedback findById(int feedbackId) {
        return feedbackRepository.findById(feedbackId)
                .orElseThrow(() -> new IllegalArgumentException("Invalid feedback ID: " + feedbackId));
    }
}