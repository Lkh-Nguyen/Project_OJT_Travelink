/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

/**
 *
 * @author ASUS
 */
import com.travelink.Model.Bed;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class BedDB implements DatabaseInfo {

    // Method to retrieve all beds
    public static List<Bed> getAllBeds() {
        List<Bed> beds = new ArrayList<>();

        try (Connection connection = DatabaseInfo.getConnect()) {
            String sql = "SELECT * FROM Bed";
            try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(sql)) {
                while (resultSet.next()) {
                    Bed bed = new Bed();
                    bed.setBed_ID(resultSet.getInt("Bed_ID"));
                    bed.setName(resultSet.getString("Name"));
                    bed.setDescription(resultSet.getString("Description"));
                    bed.setUrl(resultSet.getString("URL"));
                    beds.add(bed);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beds;
    }

    // Method to retrieve a bed by ID
    public static Bed getBedByID(int bedID) {
        Bed bed = null;

        try (Connection connection = DatabaseInfo.getConnect()) {
            String sql = "SELECT * FROM Bed WHERE Bed_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, bedID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        bed = new Bed();
                        bed.setBed_ID(resultSet.getInt("Bed_ID"));
                        bed.setName(resultSet.getString("Name"));
                        bed.setDescription(resultSet.getString("Description"));
                        bed.setUrl(resultSet.getString("URL"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bed;
    }

    public static Bed getBedByRoomBedID(int roomBedID) {
        Bed bed = null;

        try (Connection connection = DatabaseInfo.getConnect()) {
            String sql = "SELECT b.* "
                    + "FROM Bed b "
                    + "JOIN Room_Bed rb ON b.Bed_ID = rb.Bed_ID "
                    + "WHERE rb.Room_Bed_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, roomBedID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        bed = new Bed();
                        bed.setBed_ID(resultSet.getInt("Bed_ID"));
                        bed.setName(resultSet.getString("Name"));
                        bed.setDescription(resultSet.getString("Description"));
                        bed.setUrl(resultSet.getString("URL"));
                        // You can add more attributes as needed
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return bed;
    }

    //Get Bed By Room ID
    public static List<Bed> getBedsByRoomID(int roomID) {
        List<Bed> beds = new ArrayList<>();

        try (Connection connection = DatabaseInfo.getConnect()) {
            String sql = "SELECT b.Bed_ID, b.name, b.Description, b.URL "
                    + "FROM Room r "
                    + "JOIN Room_Bed rb ON r.Room_ID = rb.Room_ID "
                    + "JOIN Bed b ON rb.Bed_ID = b.Bed_ID "
                    + "WHERE r.Room_ID = ?";
            try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                preparedStatement.setInt(1, roomID);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        Bed bed = new Bed();
                        bed.setBed_ID(resultSet.getInt("Bed_ID"));
                        bed.setName(resultSet.getString("Name"));
                        bed.setDescription(resultSet.getString("Description"));
                        bed.setUrl(resultSet.getString("URL"));
                        beds.add(bed);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return beds;
    }

        //Add Bed By Room ID
    public static boolean insertBedByRoomID(Bed bed, int roomID, int amount) {
        boolean success = false;
        Connection connection = null;
        PreparedStatement insertBedStatement = null;
        PreparedStatement insertRoomBedStatement = null;
        ResultSet generatedKeys = null;

        try {
            connection = DatabaseInfo.getConnect();
            connection.setAutoCommit(false); // Start transaction

            // Insert bed into Bed table
            String insertBedSQL = "INSERT INTO Bed (Name, Description, URL) VALUES (?, ?, ?)";
            insertBedStatement = connection.prepareStatement(insertBedSQL, Statement.RETURN_GENERATED_KEYS);
            insertBedStatement.setString(1, bed.getName());
            insertBedStatement.setString(2, bed.getDescription());
            insertBedStatement.setString(3, bed.getUrl());
            int affectedRows = insertBedStatement.executeUpdate();

            if (affectedRows > 0) {
                generatedKeys = insertBedStatement.getGeneratedKeys();
                if (generatedKeys.next()) {
                    int bedID = generatedKeys.getInt(1);

                    // Insert association into Room_Bed table with amount
                    String insertRoomBedSQL = "INSERT INTO Room_Bed (Room_ID, Bed_ID, Amount) VALUES (?, ?, ?)";
                    insertRoomBedStatement = connection.prepareStatement(insertRoomBedSQL);
                    insertRoomBedStatement.setInt(1, roomID);
                    insertRoomBedStatement.setInt(2, bedID);
                    insertRoomBedStatement.setInt(3, amount); // Set the amount
                    insertRoomBedStatement.executeUpdate();

                    success = true;
                    connection.commit(); // Commit transaction
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
            if (connection != null) {
                try {
                    connection.rollback(); // Rollback transaction on error
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        } finally {
            // Close resources
            try {
                if (generatedKeys != null) {
                    generatedKeys.close();
                }
                if (insertBedStatement != null) {
                    insertBedStatement.close();
                }
                if (insertRoomBedStatement != null) {
                    insertRoomBedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return success;
    }


    public static void main(String[] args) {
        BedDB bedDB = new BedDB();

//        // Test getAllBed method
//        System.out.println("Testing getAllBeds method:");
//        if (allBeds.isEmpty()) {
//            System.out.println("No beds found in the database.");
//        } else {
//            for (Bed bed : allBeds) {
//                System.out.println(bed);
//            }
//        }
//        System.out.println();
        // Test getBedByID method
//        Bed newBed = new Bed();
//        newBed.setName("King Size Bed");
//        newBed.setDescription("A luxurious king-size bed.");
//        newBed.setUrl("http://example.com/king-size-bed.jpg");
//
//        // Room ID to associate the bed with
//        int roomID = 91;
//
//        // Insert the new bed and associate it with the room
//        boolean result = bedDB.insertBedByRoomID(newBed, roomID);
//        if (result) {
//            System.out.println("Bed inserted successfully and associated with Room ID " + roomID);
//        } else {
//            System.out.println("Failed to insert the bed.");
//        }
        int roomIDToSearch = 92; // Replace with the actual room ID you want to search
        System.out.println("Testing getBedsByRoomID method for Room ID: " + roomIDToSearch);
        List<Bed> bedsByRoomID = bedDB.getBedsByRoomID(roomIDToSearch);
        if (bedsByRoomID.isEmpty()) {
            System.out.println("No beds found for Room ID " + roomIDToSearch + " in the database.");
        } else {
            System.out.println("Found beds:");
            for (Bed bed : bedsByRoomID) {
                System.out.println(bed);
            }
        }
    }
}
