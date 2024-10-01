package com.example.travelink.model;

import java.sql.Timestamp;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Reported_Feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ReportedFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reported_Feedback_ID")
    private int reportedFeedbackId;

    @Column(name = "ReportTime")
    private Timestamp reportTime;

    @Column(name = "Reason")
    private String reason;

    @ManyToOne
    @JoinColumn(name = "Feedback_ID")
    private Feedback feedback;
}
