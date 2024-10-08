package com.example.travelink.service;

import java.util.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.travelink.model.Reservation;
import com.example.travelink.repository.ReservationRepository;

@Service
public class ReservationService {
    @Autowired
    private ReservationRepository reservationRepository;

    // check reservation trong khoang thoi gian
    public List<Reservation> reservationCoincide(Date userCheck_in_date, Date userCheck_out_date) {
        List<Reservation> reservations = reservationRepository.findAll();
        List<Reservation> newReservations = new ArrayList<>();
        for (Reservation reservation : reservations) {
            if (checkOverlap(reservation.getCheckInDate(),
                    getDateBefore(reservation.getCheckOutDate(), 1),
                    userCheck_in_date, userCheck_out_date)) {
                newReservations.add(reservation);
            }
        }
        return newReservations;
    }

    public static boolean checkOverlap(Date reservationDate, Date userCheckIn,
            Date userCheckOut) {
        return !userCheckIn.after(reservationDate) &&
                !userCheckOut.before(reservationDate);
    }

    public static boolean checkOverlap(Date reservationCheckIn, Date reservationCheckOut, Date userCheckIn,
            Date userCheckOut) {
        return (userCheckIn.before(addDays(reservationCheckOut, 1))
                && userCheckIn.after(addDays(reservationCheckIn, -1)))
                || (userCheckOut.before(addDays(reservationCheckOut, 1))
                        && userCheckOut.after(addDays(reservationCheckIn, -1)));
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    public static Date getDateBefore(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -days);
        return cal.getTime();
    }
}
