package com.example.travelink.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "Reason_Feedback")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class ReactionFeedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Reaction_ID")
    private int reactionId;

    @Column(name = "Reaction_Type")
    private String reactionType;

    @ManyToOne
    @JoinColumn(name = "Feedback_ID", nullable = false)
    private Feedback feedback; // Foreign key reference to Feedback table

    @ManyToOne
    @JoinColumn(name = "Account_ID", nullable = false)
    private Account account; // Foreign key reference to Account table
}
