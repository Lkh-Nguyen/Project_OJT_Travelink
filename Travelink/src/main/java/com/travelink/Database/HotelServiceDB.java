/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Hotel;
import com.travelink.Model.HotelService;
import com.travelink.Model.Reservation;
import com.travelink.Model.Service;
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
public class HotelServiceDB implements DatabaseInfo {
    public static HotelService getHotelServiceByID(int hotelServiceID) {
        HotelService service = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Hotel_Service WHERE Hotel_Service_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelServiceID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    service = new HotelService();
                    service.setHotelServiceID(resultSet.getInt("Hotel_Service_ID"));
                    service.setHotelID(resultSet.getInt("Hotel_ID"));
                    service.setServiceID(resultSet.getInt("Service_ID"));
                    service.setPrice(resultSet.getInt("Price")); // Assuming price is always stored as an integer
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting service by HotelServiceID: " + e);
        }
        return service;
    }

    public static List<Service> getServicesByHotelID(int hotelID) {
        List<Service> services = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT s.* FROM Service s INNER JOIN Hotel_Service hs ON s.Service_ID = hs.Service_ID WHERE hs.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Service service = new Service();
                    service.setServiceID(resultSet.getInt("Service_ID"));
                    service.setName(resultSet.getString("Name"));
                    services.add(service);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting services by hotel ID: " + e);
        }
        return services;
    }

    public static List<Hotel> getHotelsByServiceID(int serviceID) {
        List<Hotel> hotels = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT h.* FROM Hotel h INNER JOIN Hotel_Service hs ON h.Hotel_ID = hs.Hotel_ID WHERE hs.Service_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, serviceID);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Hotel hotel = new Hotel(); // Assuming you have a Hotel model class
                    hotel.setHotel_ID(resultSet.getInt("Hotel_ID"));
                    // Add getters and setters for other Hotel model properties
                    hotels.add(hotel);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting hotels by service ID: " + e);
        }
        return hotels;
    }
    public static int getPriceByHotelIDByServiceID(int hotelID, int serviceID) {
        Integer price = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT Price FROM Hotel_Service WHERE Hotel_ID = ? AND Service_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setInt(2, serviceID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    price = resultSet.getInt("Price");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting price by hotel ID and service ID: " + e);
        } finally {
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return price;
    }
    
    public static boolean insertHotelService(int hotelID, int serviceID, int price) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isInserted = false;

        try {   
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "INSERT INTO Hotel_Service (Hotel_ID, Service_ID, Price) VALUES (?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setInt(2, serviceID);
                statement.setInt(3, price);

                int rowsInserted = statement.executeUpdate();
                if (rowsInserted > 0) {
                    isInserted = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting hotel service: " + e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return isInserted;
    }
    
    public static boolean updateService(int hotelID, int serviceID, int newPrice) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isUpdated = false;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "UPDATE Hotel_Service SET Price = ? WHERE Hotel_ID = ? AND Service_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, newPrice);
                statement.setInt(2, hotelID);
                statement.setInt(3, serviceID);

                int rowsUpdated = statement.executeUpdate();
                if (rowsUpdated > 0) {
                    isUpdated = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating service price: " + e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return isUpdated;
    }
    
    public static boolean deleteHotelService(int hotelID,int serviceID) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean isDeleted = false;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "DELETE FROM Hotel_Service WHERE Hotel_ID = ? AND Service_ID =?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                statement.setInt(2,serviceID);

                int rowsDeleted = statement.executeUpdate();
                if (rowsDeleted > 0) {
                    isDeleted = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error deleting hotel service: " + e);
        } finally {
            try {
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return isDeleted;
    }

    public static void main(String[] args) throws SQLException {

//        // Test getServiceByHotelServiceID
//        System.out.println("\n** Test getServiceByHotelServiceID **");
//        int testHotelServiceID = 3; // Replace with an existing HotelService ID
//        HotelService service = HotelServiceDB.getHotelServiceByID(testHotelServiceID);
//        if (service == null) {
//            System.out.println("Hotel service with ID " + testHotelServiceID + " not found.");
//        } else {
//            System.out.println("Details of Hotel Service (ID: " + testHotelServiceID + "):");
//            System.out.println(service); // Assuming your HotelService model has a toString() method
//        }
//
//        // Test getServicesByHotelID
//        System.out.println("\n** Test getServicesByHotelID **");
//        int testHotelID = 1; // Replace with an existing hotel ID
//        List<Service> services = HotelServiceDB.getServicesByHotelID(testHotelID);
//        if (services.isEmpty()) {
//            System.out.println("No services found for hotel (ID: " + testHotelID + ").");
//        } else {
//            System.out.println("Services offered by Hotel (ID: " + testHotelID + "):");
//            for (Service service1 : services) {
//                System.out.println(service1);
//            }
//        }
//
//        // Test getHotelsByServiceID
//        System.out.println("\n** Test getHotelsByServiceID **");
//        int testServiceID = 2; // Replace with an existing service ID
//        List<Hotel> hotels = HotelServiceDB.getHotelsByServiceID(testServiceID);
//        if (hotels.isEmpty()) {
//            System.out.println("No hotels found offering service (ID: " + testServiceID + ").");
//        } else {
//            System.out.println("Hotels offering Service (ID: " + testServiceID + "):");
//            for (Hotel hotel : hotels) {
//                System.out.println("Hotel ID: " + hotel.getHotel_ID()); // Assuming your Hotel model has a getHotelID() method
//            }
//        }
//        


    }
}
