/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Province;
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
public class ProvinceDB implements DatabaseInfo {

    public static List<Province> getAllProvince() {
        List<Province> provinces = new ArrayList<>();
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM province Order by name"; // Replace "province" with your actual table name
                statement = connection.createStatement();
                resultSet = statement.executeQuery(query);

                while (resultSet.next()) {
                    Province province = new Province();
                    province.setProvince_ID(resultSet.getInt("province_ID"));
                    province.setName(resultSet.getString("name"));
                    provinces.add(province);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all provinces: " + e);
        }
        return provinces;
    }

    public static Province getProvinceByProvinceID(int id) {
        Province province = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM province WHERE province_ID = ?"; // Replace "province" with your actual table name
                statement = connection.prepareStatement(query);
                statement.setInt(1, id); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    province = new Province();
                    province.setProvince_ID(resultSet.getInt("province_ID"));
                    province.setName(resultSet.getString("name"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting province by ID: " + e);
        }
        return province;
    }

    //Get URL by Province Name
    public static String getURLByProvinceName(String provinceName) {
        String url = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT URL FROM province WHERE name = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, provinceName); // Set the province name parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    url = resultSet.getString("URL");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting URL by province name: " + e);
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
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return url;
    }

    //Get Province Location by Hotel_ID
    public static String getLocationByHotelID(int hotelID) {
        String provinceName = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT p.name AS provinceName "
                        + "FROM Hotel h "
                        + "JOIN Ward w ON h.Ward_ID = w.Ward_ID "
                        + "JOIN District d ON w.District_ID = d.District_ID "
                        + "JOIN Province p ON d.Province_ID = p.Province_ID "
                        + "WHERE h.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    provinceName = resultSet.getString("provinceName");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting province location by hotel ID: " + e);
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
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return provinceName;
    }

    // Get names of top 3 provinces with most reservations
    public static List<String> getTop3ProvincesWithMostReservations() {
        List<String> topProvinces = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT TOP 3 p.name AS provinceName, COUNT(r.Reservation_ID) AS ReservationCount "
                        + "FROM Province p "
                        + "JOIN District d ON p.Province_ID = d.Province_ID "
                        + "JOIN Ward w ON d.District_ID = w.District_ID "
                        + "JOIN Hotel h ON w.Ward_ID = h.Ward_ID "
                        + "JOIN Room rm ON h.Hotel_ID = rm.Hotel_ID "
                        + "JOIN Reserved_Room rr ON rm.Room_ID = rr.Room_ID "
                        + "JOIN Reservation r ON rr.Reservation_ID = r.Reservation_ID "
                        + "GROUP BY p.name "
                        + "ORDER BY ReservationCount DESC";

                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    topProvinces.add(resultSet.getString("provinceName"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting top provinces with most reservations: " + e);
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
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return topProvinces;
    }

    // Get the reservation counts for top 3 provinces with most reservations
    public static List<Integer> getTop3ProvincesReservations() {
        List<Integer> topProvincesReservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT TOP 3 COUNT(r.Reservation_ID) AS ReservationCount "
                        + "FROM Province p "
                        + "JOIN District d ON p.Province_ID = d.Province_ID "
                        + "JOIN Ward w ON d.District_ID = w.District_ID "
                        + "JOIN Hotel h ON w.Ward_ID = h.Ward_ID "
                        + "JOIN Room rm ON h.Hotel_ID = rm.Hotel_ID "
                        + "JOIN Reserved_Room rr ON rm.Room_ID = rr.Room_ID "
                        + "JOIN Reservation r ON rr.Reservation_ID = r.Reservation_ID "
                        + "GROUP BY p.name "
                        + "ORDER BY ReservationCount DESC";

                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    topProvincesReservations.add(resultSet.getInt("ReservationCount"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting top provinces reservations: " + e);
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
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return topProvincesReservations;
    }

    public static void main(String[] args) {
        List<Province> provinces;
        provinces = getAllProvince();

        System.out.println("List of Provinces:");
        for (Province province : provinces) {
            System.out.println("ID: " + province.getProvince_ID() + ", Name: " + province.getName());
        }

        int ID = 5;
        Province province = getProvinceByProvinceID(ID);
        System.out.println("Provinces with ID : " + ID);
        System.out.println("ID: " + province.getProvince_ID() + ", Name: " + province.getName());
    }
}
