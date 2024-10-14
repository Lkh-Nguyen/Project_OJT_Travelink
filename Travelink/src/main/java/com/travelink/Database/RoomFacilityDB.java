/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.RoomFacility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RoomFacilityDB implements DatabaseInfo {
    // Method to get room facilities by Room ID

    public List<RoomFacility> getRoomFacilitiesByRoomID(int roomID) {
        List<RoomFacility> roomFacilities = new ArrayList<>();

        try (Connection connection = DatabaseInfo.getConnect()) {
            String sql = "SELECT * FROM Room_Facility WHERE Room_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, roomID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        RoomFacility roomFacility = new RoomFacility();
                        roomFacility.setRoom_ID(resultSet.getInt("Room_ID"));
                        roomFacility.setFacility_ID(resultSet.getInt("Facility_ID"));
                        roomFacilities.add(roomFacility);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return roomFacilities;
    }

    public static void main(String[] args) {
        RoomFacilityDB roomFacilityDB = new RoomFacilityDB();
        int roomID = 123; // Replace with the actual room ID you want to query
        List<RoomFacility> roomFacilities = roomFacilityDB.getRoomFacilitiesByRoomID(roomID);

        if (roomFacilities.isEmpty()) {
            System.out.println("No room facilities found for room ID: " + roomID);
        } else {
            System.out.println("Room facilities for room ID " + roomID + ":");
            for (RoomFacility roomFacility : roomFacilities) {
                System.out.println("Room ID: " + roomFacility.getRoom_ID() + ", Facility ID: " + roomFacility.getFacility_ID());
            }
        }
    }
}
