/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Reservation;
import com.travelink.Model.ReservedRoom;
import com.travelink.Model.Room;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 *
 * @author ASUS
 */
public class RoomDB implements DatabaseInfo {

    public static List<Room> getAllActiveRooms() {
        List<Room> Rooms = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room WHERE Status = 'ACTIVE'";
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Room Room = new Room();
                    Room.setRoom_ID(resultSet.getInt("Room_ID"));
                    Room.setName(resultSet.getString("Name"));
                    Room.setRoomDescription(resultSet.getString("Room_Description"));
                    Room.setCapacity(resultSet.getInt("Capacity"));
                    Room.setTotalRooms(resultSet.getInt("Total_Rooms"));
                    Room.setPrice(resultSet.getInt("Price"));
                    Room.setStatus(resultSet.getString("Status"));
                    Room.setHotel_ID(resultSet.getInt("Hotel_ID"));
                    Rooms.add(Room);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all room types: " + e);
        }
        return Rooms;
    }

    public static Room getRoomByRoomID(int id) {
        Room Room = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room WHERE Room_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, id); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    Room = new Room();
                    Room.setRoom_ID(resultSet.getInt("Room_ID"));
                    Room.setName(resultSet.getString("Name"));
                    Room.setRoomDescription(resultSet.getString("Room_Description"));
                    Room.setCapacity(resultSet.getInt("Capacity"));
                    Room.setTotalRooms(resultSet.getInt("Total_Rooms"));
                    Room.setPrice(resultSet.getInt("Price"));
                    Room.setStatus(resultSet.getString("Status"));
                    Room.setHotel_ID(resultSet.getInt("Hotel_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting room type by ID: " + e);
        }
        return Room;
    }

    public static List<Room> getActiveRoomsByHotel_ID(int hotel_ID) {
        List<Room> Rooms = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room WHERE Status = 'ACTIVE' AND Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotel_ID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Room Room = new Room();
                    Room.setRoom_ID(resultSet.getInt("Room_ID"));
                    Room.setName(resultSet.getString("Name"));
                    Room.setRoomDescription(resultSet.getString("Room_Description"));
                    Room.setCapacity(resultSet.getInt("Capacity"));
                    Room.setTotalRooms(resultSet.getInt("Total_Rooms"));
                    Room.setPrice(resultSet.getInt("Price"));
                    Room.setStatus(resultSet.getString("Status"));
                    Room.setHotel_ID(resultSet.getInt("Hotel_ID"));
                    Rooms.add(Room); // Add the retrieved room type to the list
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting room types by hotel ID: " + e);
        }
        return Rooms;
    }

    //Get Rooms By Hotel_ID
    public static List<Room> getRoomsByHotel_ID(int hotel_ID) {
        List<Room> Rooms = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Room WHERE Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotel_ID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Room Room = new Room();
                    Room.setRoom_ID(resultSet.getInt("Room_ID"));
                    Room.setName(resultSet.getString("Name"));
                    Room.setRoomDescription(resultSet.getString("Room_Description"));
                    Room.setCapacity(resultSet.getInt("Capacity"));
                    Room.setTotalRooms(resultSet.getInt("Total_Rooms"));
                    Room.setPrice(resultSet.getInt("Price"));
                    Room.setStatus(resultSet.getString("Status"));
                    Room.setHotel_ID(resultSet.getInt("Hotel_ID"));
                    Rooms.add(Room); // Add the retrieved room type to the list
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting room types by hotel ID: " + e);
        }
        return Rooms;
    }

    public static boolean checkOverlap(Date reservationDate, Date userCheckIn, Date userCheckOut) {
        return !userCheckIn.after(reservationDate) && !userCheckOut.before(reservationDate);
    }

    public static boolean checkOverlap(Date reservationCheckIn, Date reservationCheckOut, Date userCheckIn, Date userCheckOut) {
        return (userCheckIn.before(addDays(reservationCheckOut, 1)) && userCheckIn.after(addDays(reservationCheckIn, -1)))
                || (userCheckOut.before(addDays(reservationCheckOut, 1)) && userCheckOut.after(addDays(reservationCheckIn, -1)));
    }

    public static Date addDays(Date date, int days) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(date);
        calendar.add(Calendar.DAY_OF_MONTH, days);
        return calendar.getTime();
    }

    

    public static int numberOfRoomAvailableByDate(int RoomID, Date date, List<Reservation> reservationList, Map<Integer, List<ReservedRoom>> reservedRoomsByReservation) {
        List<ReservedRoom> reservedRooms = ReservedRoomDB.getReservedRoomsByRoomID(RoomID);
        boolean check = true;
        for (ReservedRoom reservedRoom : reservedRooms) {
            if (reservedRoom.getRoom_ID() == RoomID) {
                check = false;
                break;
            }
        }
        if (check) {
            return RoomDB.getRoomByRoomID(RoomID).getTotalRooms();
        } else {
            int roomAvalable = RoomDB.getRoomByRoomID(RoomID).getTotalRooms();
            for (Reservation reservation : reservationList) {
                if (checkOverlap(date, reservation.getCheckInDate(), getDateBefore(reservation.getCheckOutDate(), 1))) {
                    List<ReservedRoom> reservedRoomList = reservedRoomsByReservation.get(reservation.getReservationID());
                    for (ReservedRoom reservedRoom : reservedRoomList) {
                        if (reservedRoom.getRoom_ID() == RoomID) {
                            roomAvalable -= reservedRoom.getAmount();
                            if(roomAvalable <= 0){
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

    public static int numberOfRoomAvailableByDate(int RoomID, Date date, List<Reservation> reservationList) {
        // duyet reservationList lan 2 
        List<ReservedRoom> reservedRooms = ReservedRoomDB.getAllReservedRoom();
        boolean check = true;
        for (ReservedRoom reservedRoom : reservedRooms) {
            if (reservedRoom.getRoom_ID() == RoomID) {
                check = false;
                break;
            }
        }
        if (check == true) {
            return RoomDB.getRoomByRoomID(RoomID).getTotalRooms();
        } else {
            int roomAvalable = RoomDB.getRoomByRoomID(RoomID).getTotalRooms();
            for (Reservation reservation : reservationList) {
                if (checkOverlap(date, reservation.getCheckInDate(), reservation.getCheckOutDate())) {
                    int reservationID = reservation.getReservationID();
                    List<ReservedRoom> reservedRoomList = ReservedRoomDB.getReservedRoomsByReservationID(reservationID);
                    for (ReservedRoom reservedRoom : reservedRoomList) {
                        if (reservedRoom.getRoom_ID() == RoomID) {
                            roomAvalable = roomAvalable - reservedRoom.getAmount();
                            if(roomAvalable <= 0){
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

    public static int numberOfRoomAvailableByTime(int RoomID, Date beginDate, Date endDate, List<Reservation> reservationList) {
        Room room = RoomDB.getRoomByRoomID(RoomID);
        List<Date> dateList = getDateRange(beginDate, endDate);
        List<Integer> numberRoomList = new ArrayList<>();
        for (Date date : dateList) {
            int numberOfRoom = numberOfRoomAvailableByDate(RoomID, date, reservationList);
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

    public static boolean updateRoom(int hotelID, int roomID, Room room) {
        boolean updated = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                // Update query
                String query = "UPDATE r "
                        + "SET r.Name = ?, r.Room_Description = ?, r.Capacity = ?, r.Total_Rooms = ?, r.Price = ?, r.Status = ? "
                        + "FROM Room r "
                        + "JOIN Hotel h ON h.Hotel_ID = r.Hotel_ID "
                        + "WHERE h.Hotel_ID = ? AND r.Room_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, room.getName());
                statement.setString(2, room.getRoomDescription());
                statement.setInt(3, room.getCapacity());
                statement.setInt(4, room.getTotalRooms());
                statement.setInt(5, room.getPrice());
                statement.setString(6, room.getStatus());
                statement.setInt(7, hotelID);
                statement.setInt(8, roomID);

                // Execute update
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    updated = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating room: " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return updated;
    }

    public static boolean addRoom(int hotelID, Room room) {
        boolean added = false;
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                // Insert query
                String query = "INSERT INTO Room (Hotel_ID, Name, Room_Description, Capacity, Total_Rooms, Price, Status) "
                        + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setString(2, room.getName());
                statement.setString(3, room.getRoomDescription());
                statement.setInt(4, room.getCapacity());
                statement.setInt(5, room.getTotalRooms());
                statement.setInt(6, room.getPrice());
                statement.setString(7, room.getStatus());

                // Execute insert
                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    added = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding room: " + e);
        } finally {
            try {
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return added;
    }

    public static void main(String[] args) throws SQLException {

//        // Test getAllRooms
//        System.out.println("** Test getAllRooms **");
//        List<Room> allRooms = RoomDB.getAllActiveRooms();
//        if (allRooms.isEmpty()) {
//            System.out.println("No room types found in the database.");
//        } else {
//            System.out.println("List of all room types:");
//            for (Room Room : allRooms) {
//                System.out.println(Room); // Uses toString() for informative output
//            }
//        }
//
//        // Test getRoomByID
//        System.out.println("\n** Test getRoomByID **");
//        int specificID = 3; // Replace with an existing room type ID
//        Room RoomByID = RoomDB.getRoomByRoomID(specificID);
//        if (RoomByID != null) {
//            System.out.println("Room Type Details (ID: " + specificID + "):");
//            System.out.println(RoomByID);
//        } else {
//            System.out.println("Room type with ID " + specificID + " not found.");
//        }
//
//        // Test getRoomsByHotel_ID
//        System.out.println("\n** Test getRoomsByHotel_ID **");
//        int targetHotel_ID = 1; // Replace with an existing hotel ID
//        List<Room> hotelRooms = RoomDB.getActiveRoomsByHotel_ID(targetHotel_ID);
//        if (hotelRooms.isEmpty()) {
//            System.out.println("No room types found for hotel with ID " + targetHotel_ID + ".");
//        } else {
//            System.out.println("Room Types for Hotel (ID: " + targetHotel_ID + "):");
//            for (Room Room : hotelRooms) {
//                System.out.println(Room);
//            }
//        }
        List<Room> hotelRooms = RoomDB.getRoomsByHotel_ID(4);
        for (Room Room : hotelRooms) {
            System.out.println(Room);
        }
//        System.out.println("** Test updateRoom **");
//
//        int hotelID = 1; // Thay thế bằng hotelID thực tế trong cơ sở dữ liệu
//        int roomID = 1; // Thay thế bằng roomID thực tế trong cơ sở dữ liệu
//
//        // Tạo một đối tượng Room mới với thông tin cập nhật
//        Room updatedRoom = new Room();
//        updatedRoom.setName("Updated Room Name");
//        updatedRoom.setRoomDescription("Updated Room Description");
//        updatedRoom.setCapacity(4);
//        updatedRoom.setTotalRooms(10);
//        updatedRoom.setPrice(200);
//        updatedRoom.setStatus("Available");
//
//        boolean updated = updateRoom(hotelID, roomID, updatedRoom);
//        if (updated) {
//            System.out.println("Room updated successfully.");
//        } else {
//            System.out.println("Failed to update room.");
//        }
        
    }

}
