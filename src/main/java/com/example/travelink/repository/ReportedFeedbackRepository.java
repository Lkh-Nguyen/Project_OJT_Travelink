package com.example.travelink.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.travelink.model.ReportedFeedback;

@Repository
public interface ReportedFeedbackRepository extends JpaRepository<ReportedFeedback, Integer> {
    boolean existsByFeedbackId(int feedbackId);
}
