package com.example.travelink.model;

import java.sql.Date;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "Feedback")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class Feedback {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "Feedback_ID")
    private int feedbackId;

    @Column(name = "Description")
    private String description;

    @Column(name = "Rating")
    private Byte rating;

    @Column(name = "Date")
    private Date date;

    @Column(name = "LikesCount")
    private int likesCount;

    @Column(name = "DislikesCount")
    private int dislikesCount;

    @ManyToOne
    @JoinColumn(name = "Reservation_ID")
    private Reservation reservation;
}
