/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Account;
import com.travelink.Model.Hotel;
import com.travelink.Model.OwnedHotel;
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
public class OwnedHotelDB implements DatabaseInfo {

    public static List<Hotel> getHotelsByAccountID(int AccountID) {
        List<Hotel> hotels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT h.* "
                        + "FROM Owned_Hotel oh "
                        + "INNER JOIN Hotel h ON oh.Hotel_ID = h.Hotel_ID "
                        + "WHERE oh.Account_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, AccountID); // Set the Account ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Hotel hotel = new Hotel(); // Assuming you have a Hotel model class
                    hotel.setHotel_ID(resultSet.getInt("Hotel_ID"));
                    hotel.setName(resultSet.getString("Name"));
                    hotel.setEmail(resultSet.getString("Email"));
                    hotel.setStar(resultSet.getInt("Star"));
                    hotel.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    hotel.setDescription(resultSet.getString("Description"));
                    hotel.setCheckInTimeStart(resultSet.getTime("CheckInTimeStart").toLocalTime());
                    hotel.setCheckInTimeEnd(resultSet.getTime("CheckInTimeEnd").toLocalTime());
                    hotel.setCheckOutTimeStart(resultSet.getTime("CheckOutTimeStart").toLocalTime());
                    hotel.setCheckOutTimeEnd(resultSet.getTime("CheckOutTimeEnd").toLocalTime());
                    hotel.setAddress(resultSet.getString("Address"));
                    hotel.setWard_ID(resultSet.getInt("Ward_ID"));
                    hotel.setStatus(resultSet.getString("Status"));
                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hotels by Account ID: " + e);
        }
        return hotels;
    }

    public static Account getAccountByHotelID(int hotelID) {
        Account account = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT c.* "
                        + "FROM Owned_Hotel oh "
                        + "INNER JOIN Account c ON oh.Account_ID = c.Account_ID "
                        + "WHERE oh.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    account = new Account();
                    account.setAccount_ID(resultSet.getInt("Account_ID"));
                    account.setEmail(resultSet.getString("Email"));
                    account.setPassword(resultSet.getString("Password"));
                    account.setCmnd(resultSet.getString("Cmnd"));
                    account.setName(resultSet.getString("Name"));
                    account.setGender(resultSet.getString("Gender").charAt(0)); // Assuming Gender is stored as char in the database
                    account.setDateOfBirth(resultSet.getDate("DateOfBirth"));
                    account.setPhoneNumber(resultSet.getString("PhoneNumber"));
                    account.setAvatarURL(resultSet.getString("AvatarURL"));
                    account.setAddress(resultSet.getString("Address"));
                    account.setRole(resultSet.getInt("Role"));
                    account.setStatus(resultSet.getInt("Status"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting Account by hotel ID: " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex);
            }
        }
        return account;
    }

    public static boolean addNewOwned(int accountID, int hotelID) {
        boolean added = false;
        String query = "INSERT INTO Owned_Hotel (Account_ID, Hotel_ID) VALUES (?, ?)";

        try (Connection connection = DatabaseInfo.getConnect(); PreparedStatement statement = connection.prepareStatement(query)) {

            if (connection != null) {
                statement.setInt(1, accountID);
                statement.setInt(2, hotelID);

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    added = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error adding new owned hotel: " + e);
        }

        return added;
    }

    public static void main(String[] args) {
        int accountID = 6; // Replace with the account ID you want to test

        List<Hotel> hotels = OwnedHotelDB.getHotelsByAccountID(accountID);

        if (hotels.isEmpty()) {
            System.out.println("No hotels found for Account ID: " + accountID);
        } else {
            System.out.println("Hotels owned by Account ID: " + accountID);
            for (Hotel hotel : hotels) {
                System.out.println("Hotel ID: " + hotel.getHotel_ID());
                System.out.println("Name: " + hotel.getName());
                System.out.println("Email: " + hotel.getEmail());
                System.out.println("Star: " + hotel.getStar());
                System.out.println("Phone Number: " + hotel.getPhoneNumber());
                System.out.println("Description: " + hotel.getDescription());
                System.out.println("Check-in Time Start: " + hotel.getCheckInTimeStart());
                System.out.println("Check-in Time End: " + hotel.getCheckInTimeEnd());
                System.out.println("Check-out Time Start: " + hotel.getCheckOutTimeStart());
                System.out.println("Check-out Time End: " + hotel.getCheckOutTimeEnd());
                System.out.println("Address: " + hotel.getAddress());
                System.out.println("Ward ID: " + hotel.getWard_ID());
                System.out.println("Status: " + hotel.getStatus());
                System.out.println("----------------------------------------");
            }
        }
    }

    public static List<OwnedHotel> getAllOwnedHotels() {
        List<OwnedHotel> ownedHotels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Owned_Hotel";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    int hotelID = resultSet.getInt("Hotel_ID");
                    int accountID = resultSet.getInt("Account_ID");
                    OwnedHotel ownedHotel = new OwnedHotel(hotelID, accountID);
                    ownedHotels.add(ownedHotel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all owned hotels: " + e);
        } finally {
            try {
                if (resultSet != null) {
                    resultSet.close();
                }
                if (statement != null) {
                    statement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                System.out.println("Error closing resources: " + ex);
            }
        }
        return ownedHotels;
    }
}
