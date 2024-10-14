/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Account;
import com.travelink.Model.FavouriteHotel;
import com.travelink.Model.HotelInfor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class FavouriteHotelDB implements DatabaseInfo {
    
    public static boolean deleteFavouriteHotel(int hotelID, int accountID) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "DELETE FROM Favourite_Hotel WHERE Hotel_ID = ? AND Account_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setInt(2, accountID);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0; // Return true if a row was deleted
            }
        } catch (SQLException e) {
            System.out.println("Error deleting favourite hotel: " + e);
            return false;
        } finally {
            // Close resources to prevent memory leaks
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e);
                }
            }
        }
        return false;
    }
    
    public static void main(String[] args) {
        createHotelInforView();
    }
    
    public static void createHotelInforView() {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DatabaseInfo.getConnect();

            if (conn != null) {
                // Check if the view already exists
                String checkViewQuery = "SELECT COUNT(*) FROM information_schema.views WHERE table_name = 'HotelInfor'";
                try (Statement checkStatement = conn.createStatement(); ResultSet resultSet = checkStatement.executeQuery(checkViewQuery)) {
                    resultSet.next();
                    int viewCount = resultSet.getInt(1);

                    // If the view doesn't exist, create it
                    if (viewCount == 0) {
                        String createViewQuery
                                = "CREATE VIEW HotelInfor AS "
                                + "WITH RankedHotels AS ( "
                                + "SELECT h.Hotel_ID, h.Name, h.Star, h.Address, hi.URL, fh.Account_ID, "
                                + "ROW_NUMBER() OVER (PARTITION BY h.Hotel_ID ORDER BY (SELECT NULL)) AS rn "
                                + "FROM Favourite_Hotel fh "
                                + "INNER JOIN Hotel h ON fh.Hotel_ID = h.Hotel_ID "
                                + "INNER JOIN Hotel_Image hi ON h.Hotel_ID = hi.Hotel_ID "
                                + "), "
                                + "HotelRatings AS ( "
                                +"SELECT " +
                                   "   ro.Hotel_ID, " +
                                   "   COUNT(f.Rating) AS RatingCount, " +
                                   "   ROUND(AVG(CAST(f.Rating AS FLOAT)),1) AS Average " +
                                   "FROM Feedback f " +
                                   "INNER JOIN Reservation r ON f.Reservation_ID = r.Reservation_ID " +
                                   "INNER JOIN Reserved_Room rr ON r.Reservation_ID = rr.Reservation_ID " +
                                   "INNER JOIN Room ro ON rr.Room_ID = ro.Room_ID " +
                                   "GROUP BY ro.Hotel_ID)"
                                + "SELECT rh.Hotel_ID, rh.Name, rh.Star, rh.Address, rh.URL, rh.Account_ID, "
                                + "COALESCE(hr.RatingCount, 0) AS RatingCount, COALESCE(hr.Average, 0) AS Average "
                                + "FROM RankedHotels rh "
                                + "LEFT JOIN HotelRatings hr ON rh.Hotel_ID = hr.Hotel_ID "
                                + "WHERE rh.rn = 1 ";
                        ps = conn.prepareStatement(createViewQuery);
                        ps.execute();
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println("Error creating view HotelInfor: " + e);
        } finally {
            // Close resources
            try {
                if (ps != null) {
                    ps.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
    }
    public static boolean addFavouriteHotel(int hotelID, int accountID) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "insert into Favourite_Hotel values (?,?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setInt(2, accountID);

                int rowsAffected = statement.executeUpdate();
                return rowsAffected > 0; // Return true if a row was deleted
            }
        } catch (SQLException e) {
            System.out.println("Error add favourite hotel: " + e);
            return false;
        } finally {
            // Close resources to prevent memory leaks
            if (statement != null) {
                try {
                    statement.close();
                } catch (SQLException e) {
                    System.out.println("Error closing statement: " + e);
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    System.out.println("Error closing connection: " + e);
                }
            }
        }
        return false;
    }

    public static List<HotelInfor> getHotelFavourite(int accountID) {
        createHotelInforView();
        List<HotelInfor> cardFavouriteHotelS = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query
                        = "select * from HotelInfor where account_ID = ?";

                statement = connection.prepareStatement(query);
                statement.setInt(1, accountID); // Set the account ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    HotelInfor cardFavouriteHotel = new HotelInfor(); // Assuming you have a Hotel model class
                    cardFavouriteHotel.setHotel_ID(resultSet.getInt("Hotel_ID"));
                    cardFavouriteHotel.setName(resultSet.getString("Name"));
                    cardFavouriteHotel.setStar(resultSet.getInt("Star"));
                    cardFavouriteHotel.setAddress(resultSet.getString("Address"));
                    cardFavouriteHotel.setUrl(resultSet.getString("URL"));
                    cardFavouriteHotel.setRatingCount(resultSet.getInt("RatingCount"));
                    cardFavouriteHotel.setAverage(resultSet.getFloat("Average"));
                    
                    cardFavouriteHotelS.add(cardFavouriteHotel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hotels by account ID: " + e);
        }
        return cardFavouriteHotelS;
    }
    

    public static List<Account> getAccountsByHotelID(int hotelID) {
        List<Account> Accounts = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT c.* "
                        + "FROM Favourite_Hotel fh "
                        + "INNER JOIN Account c ON fh.Account_ID = c.Account_ID "
                        + "WHERE fh.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Account account = new Account();
                    account.setAccount_ID(resultSet.getInt("Account_ID"));
                    account.setEmail(resultSet.getString("Email"));
                    account.setPassword(resultSet.getString("Password"));
                    account.setName(resultSet.getString("Name"));
                    account.setRole(resultSet.getInt("Role"));

                    // Handle potential null phone number
                    account.setPhoneNumber(resultSet.getString("PhoneNumber"));

                    // Handle optional attributes with null checks
                    String cmnd = resultSet.getString("CMND");
                    if (cmnd != null) {
                        account.setCmnd(cmnd);
                    }

                    Character gender = resultSet.getString("Gender") != null ? resultSet.getString("Gender").charAt(0) : ' ';
                    account.setGender(gender);

                    java.sql.Date dateOfBirth = resultSet.getDate("DateOfBirth");
                    if (dateOfBirth != null) {
                        account.setDateOfBirth(dateOfBirth);
                    } else {
                        account.setDateOfBirth(null);
                    }

                    String avatarURL = resultSet.getString("AvatarURL");
                    if (avatarURL != null) {
                        account.setAvatarURL(avatarURL);
                    }

                    String address = resultSet.getString("Address");
                    if (address != null) {
                        account.setAddress(address);
                    }

                    int status = resultSet.getInt("Status");
                    account.setStatus(status);
                    Accounts.add(account);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting Accounts by hotel ID: " + e);
        }
        return Accounts;
    }
    public static boolean getFavoriteHotel(int Hotel_ID,int Account_ID) {
        List<FavouriteHotel> list = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "select *"
                        + "from Favourite_Hotel "
                        + "where Hotel_ID = ? and Account_ID = ? ";
                statement = connection.prepareStatement(query);
                statement.setInt(1, Hotel_ID); // Set the hotel ID parameter
                statement.setInt(2, Account_ID); // Set the hotel ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    FavouriteHotel favouriteHotel = new FavouriteHotel(Hotel_ID, Account_ID);
                    // Set other account attributes from the result set (refer to your Account model)
                    list.add(favouriteHotel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting accounts by hotel ID: " + e);
        }
        if(list.isEmpty()){
            return false;
        }
        return true;
    }
}
