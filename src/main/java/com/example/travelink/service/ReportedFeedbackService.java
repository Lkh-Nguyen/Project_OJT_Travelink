package com.example.travelink.service;

import org.springframework.stereotype.Service;

import com.example.travelink.model.ReportedFeedback;
import com.example.travelink.repository.ReportedFeedbackRepository;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class ReportedFeedbackService {

    private final ReportedFeedbackRepository reportedFeedbackRepository;

    public void saveReportedFeedback(ReportedFeedback reportedFeedback) {
        reportedFeedbackRepository.save(reportedFeedback);
    }
    
    public boolean isFeedbackAlreadyReported(int feedbackId) {
        return reportedFeedbackRepository.existsByFeedbackId(feedbackId);
    }
    
}
