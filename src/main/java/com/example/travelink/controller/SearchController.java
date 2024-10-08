package com.example.travelink.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;

import com.example.travelink.model.Reservation;
import com.example.travelink.model.ReservedRoom;
import com.example.travelink.model.Room;
import com.example.travelink.repository.ReservedRoomRepository;
import com.example.travelink.repository.RoomRepository;

@Controller
public class SearchController {
    private static RoomRepository roomRepository;
    private static ReservedRoomRepository reservedRoomRepository;

    public static int numberOfRoomAvailableByDate(int RoomID, Date date,
            List<Reservation> reservationList) {
        // duyet reservationList lan 2
        List<ReservedRoom> reservedRooms = reservedRoomRepository.findAll();

        boolean check = true;
        for (ReservedRoom reservedRoom : reservedRooms) {
            if (reservedRoom.getReservedRoomId() == RoomID) {
                check = false;
                break;
            }
        }
        if (check == true) {
            return roomRepository.findById(RoomID).orElse(null).getTotalRooms();
        } else {
            int roomAvalable = roomRepository.findById(RoomID).orElse(null).getTotalRooms();
            for (Reservation reservation : reservationList) {
                if (checkOverlap(date, reservation.getCheckInDate(),
                        reservation.getCheckOutDate())) {
                    int reservationID = reservation.getReservationId();
                    List<ReservedRoom> reservedRoomList = reservedRoomRepository.find;

                    for (ReservedRoom reservedRoom : reservedRoomList) {
                        if (reservedRoom.getRoom().getRoomId() == RoomID) {
                            roomAvalable = roomAvalable - reservedRoom.getAmount();
                            if (roomAvalable <= 0) {
                                roomAvalable = 0;
                                break;
                            }
                        }
                    }
                }
            }
            return roomAvalable;
        }
    }

    public static boolean checkOverlap(Date reservationDate, Date userCheckIn,
            Date userCheckOut) {
        return !userCheckIn.after(reservationDate) &&
                !userCheckOut.before(reservationDate);
    }

    public static int numberOfRoomAvailableByTime(int RoomID, Date beginDate,
            Date endDate,
            List<Reservation> reservationList) {
        List<Date> dateList = getDateRange(beginDate, endDate);
        List<Integer> numberRoomList = new ArrayList<>();
        for (Date date : dateList) {
            int numberOfRoom = numberOfRoomAvailableByDate(RoomID, date,
                    reservationList);
            numberRoomList.add(numberOfRoom);
        }
        Collections.sort(numberRoomList);
        return numberRoomList.get(0);
    }

    public static List<Date> getDateRange(Date beginDate, Date endDate) {
        List<Date> dateList = new ArrayList<>();
        if (beginDate != null && endDate != null && !beginDate.after(endDate)) {
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(beginDate);
            while (!calendar.getTime().after(endDate)) {
                dateList.add(calendar.getTime());
                calendar.add(Calendar.DATE, 1);
            }
        }
        return dateList;
    }

    public static Date getDateBefore(Date date, int days) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_MONTH, -days);
        return cal.getTime();
    }

}
