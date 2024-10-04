package com.example.travelink.controller;

import java.sql.Timestamp;
import java.time.Instant;
// import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.travelink.model.Feedback;
import com.example.travelink.model.ReportedFeedback;
import com.example.travelink.service.FeedbackService;
import com.example.travelink.service.ReportedFeedbackService;

@Controller
public class CustomerFeedbackController {

    @Autowired
    private FeedbackService feedbackService;
    @Autowired
    private ReportedFeedbackService reportedFeedbackService;

    @GetMapping("/HotelFeedback")
    public String getFeedbackByHotelId(@RequestParam(name = "Hotel_ID") int hotelId, Model model) {
        List<Feedback> feedbacks = feedbackService.getFeedbackByHotelId(hotelId);
        double avg = feedbackService.findAverageRatingByHotelId(hotelId);
        model.addAttribute("feedbacks", feedbacks);
        model.addAttribute("avg", avg);
        List<String> dates = new ArrayList<>();
        int countOne = 0;
        int countTwo = 0;
        int countThree = 0;
        int countFour = 0;
        int countFive = 0;
        for (Feedback feedback : feedbacks) {
            // Định dạng ngày
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yyyy");
            String formattedDate = feedback.getDate().format(formatter);
            dates.add(formattedDate); // In ra kết quả đã định dạng
            if (feedback.getRating() == 1) {
                countOne++;
            } else if (feedback.getRating() == 2) {
                countTwo++;
            } else if (feedback.getRating() == 3) {
                countThree++;
            } else if (feedback.getRating() == 4) {
                countFour++;
            } else if (feedback.getRating() == 5) {
                countFive++;
            }
        }
        model.addAttribute("countOne", countOne);
        model.addAttribute("countTwo", countTwo);
        model.addAttribute("countThree", countThree);
        model.addAttribute("countFour", countFour);
        model.addAttribute("countFive", countFive);
        model.addAttribute("dates", dates);
        return "Hotel_Feedback"; // Trả về trang hiển thị feedbacks
    }

    @GetMapping("/reportFeedback")
    public String reportFeedbackForm(@RequestParam("feedbackId") int feedbackId, Model model) {
        Feedback feedback = feedbackService.findById(feedbackId);
        model.addAttribute("feedback", feedback);
        return "Reported_Feedback";
    }

    @PostMapping("/reportFeedback")
    public String submitReportFeedback(@RequestParam("feedbackId") int feedbackId,
            @RequestParam("reason") String reason,
            Model model) {
        try {
            // Check if the feedback has already been reported
            if (reportedFeedbackService.isFeedbackAlreadyReported(feedbackId)) {
                model.addAttribute("message", "This feedback has already been reported.");
                model.addAttribute("messageType", "warning");
                return "redirect:/HotelFeedback";
            }

            // Proceed to report the feedback
            Feedback feedback = feedbackService.findById(feedbackId);
            ReportedFeedback reportedFeedback = new ReportedFeedback();
            reportedFeedback.setFeedback(feedback);
            reportedFeedback.setReason(reason);
            reportedFeedback.setReportTime(Timestamp.from(Instant.now()));

            reportedFeedbackService.saveReportedFeedback(reportedFeedback);

            // Add success message to the model
            model.addAttribute("message", "Feedback reported successfully.");
            model.addAttribute("messageType", "success");
        } catch (Exception e) {
            // Add error message to the model
            model.addAttribute("message", "Failed to report feedback.");
            model.addAttribute("messageType", "danger");
        }

        // Redirect back to HotelFeedback
        return "redirect:/HotelFeedback";
    }

}