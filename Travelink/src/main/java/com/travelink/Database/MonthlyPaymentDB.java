/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Hotel;
import com.travelink.Model.MonthlyPayment;
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
public class MonthlyPaymentDB {

    public static List<MonthlyPayment> getAllMonthlyPaymentsByHotelID(int hotelID) {
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();
        Connection connection = DatabaseInfo.getConnect(); // Assuming DatabaseInfo provides a connection

        try {
            String sql = "SELECT * FROM MonthlyPayment WHERE Hotel_ID = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hotelID);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MonthlyPayment monthlyPayment = new MonthlyPayment();
                monthlyPayment.setMonthlyPaymentId(resultSet.getInt("Monthly_Payment_ID"));
                monthlyPayment.setMonth(resultSet.getInt("Month"));
                monthlyPayment.setYear(resultSet.getInt("Year"));
                monthlyPayment.setAmount(resultSet.getInt("Amount"));
                monthlyPayment.setStatus(resultSet.getString("Status"));
                // Check if PaymentTime is null before conversion
                if (resultSet.getTimestamp("PaymentTime") != null) {
                    monthlyPayment.setPaymentTime(resultSet.getTimestamp("PaymentTime").toLocalDateTime());
                } else {
                    // Set PaymentTime to null or a default value (e.g., LocalDateTime.MIN)
                    monthlyPayment.setPaymentTime(null);
                }
                monthlyPayment.setHotel_ID(resultSet.getInt("Hotel_ID"));
                //Add
                monthlyPayments.add(monthlyPayment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log or handle connection closing exception
            }
        }

        return monthlyPayments;
    }

    public static List<MonthlyPayment> getAllMonthlyPaymentsByHotelID(int hotelID, int year) {
        List<MonthlyPayment> monthlyPayments = new ArrayList<>();
        Connection connection = DatabaseInfo.getConnect(); // Assuming DatabaseInfo provides a connection

        try {
            String sql = "SELECT * FROM MonthlyPayment WHERE Hotel_ID = ? AND Year = ? ORDER BY Month";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hotelID);
            preparedStatement.setInt(2, year);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                MonthlyPayment monthlyPayment = new MonthlyPayment();
                monthlyPayment.setMonthlyPaymentId(resultSet.getInt("Monthly_Payment_ID"));
                monthlyPayment.setMonth(resultSet.getInt("Month"));
                monthlyPayment.setYear(resultSet.getInt("Year"));
                monthlyPayment.setAmount(resultSet.getInt("Amount"));
                monthlyPayment.setStatus(resultSet.getString("Status"));
                // Check if PaymentTime is null before conversion
                if (resultSet.getTimestamp("PaymentTime") != null) {
                    monthlyPayment.setPaymentTime(resultSet.getTimestamp("PaymentTime").toLocalDateTime());
                } else {
                    // Set PaymentTime to null or a default value (e.g., LocalDateTime.MIN)
                    monthlyPayment.setPaymentTime(null);
                }
                monthlyPayment.setHotel_ID(resultSet.getInt("Hotel_ID"));
                //Add
                monthlyPayments.add(monthlyPayment);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block (unchanged)
        }

        return monthlyPayments;
    }

    public static MonthlyPayment getMonthlyPaymentByHotelIDYearMonth(int hotelID, int year, int month) {
        MonthlyPayment monthlyPayment = null;
        Connection connection = DatabaseInfo.getConnect(); // Assuming DatabaseInfo provides a connection

        try {
            String sql = "SELECT * FROM MonthlyPayment WHERE Hotel_ID = ? AND Year = ? AND Month = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, hotelID);
            preparedStatement.setInt(2, year);
            preparedStatement.setInt(3, month);

            ResultSet resultSet = preparedStatement.executeQuery();

            while (resultSet.next()) {
                monthlyPayment = new MonthlyPayment();
                monthlyPayment.setMonthlyPaymentId(resultSet.getInt("Monthly_Payment_ID"));
                monthlyPayment.setMonth(resultSet.getInt("Month"));
                monthlyPayment.setYear(resultSet.getInt("Year"));
                monthlyPayment.setAmount(resultSet.getInt("Amount"));
                monthlyPayment.setStatus(resultSet.getString("Status"));

                // Check if PaymentTime is null before conversion
                if (resultSet.getTimestamp("PaymentTime") != null) {
                    monthlyPayment.setPaymentTime(resultSet.getTimestamp("PaymentTime").toLocalDateTime());
                } else {
                    // Set PaymentTime to null or a default value (e.g., LocalDateTime.MIN)
                    monthlyPayment.setPaymentTime(null);
                }

                monthlyPayment.setHotel_ID(resultSet.getInt("Hotel_ID"));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block (unchanged)
        }

        return monthlyPayment;
    }

    public static int getTotalMonthlyPaymentsByMonthAndYear(int month, int year) {
        int totalAmount = 0;
        Connection connection = DatabaseInfo.getConnect(); // Assuming DatabaseInfo provides a connection

        try {
            String sql = "SELECT SUM(Amount) AS TotalAmount FROM MonthlyPayment WHERE Month = ? AND Year = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, month);
            preparedStatement.setInt(2, year);

            ResultSet resultSet = preparedStatement.executeQuery();

            if (resultSet.next()) {
                totalAmount = resultSet.getInt("TotalAmount");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources in a finally block
            try {
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log or handle connection closing exception
            }
        }

        return totalAmount;
    }

    public static boolean updatePaymentStatus(int monthlyPaymentId) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        boolean isUpdated = false;

        try {
            // Establish database connection
            connection = DatabaseInfo.getConnect();

            // SQL update statement
            String sql = "UPDATE MonthlyPayment SET Status = 'PAID' WHERE Monthly_Payment_ID = ?";
            preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, monthlyPaymentId);

            // Execute update
            int rowsAffected = preparedStatement.executeUpdate();
            isUpdated = rowsAffected > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            // Handle SQL exception
        } finally {
            // Close resources
            try {
                if (preparedStatement != null) {
                    preparedStatement.close();
                }
                if (connection != null) {
                    connection.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Log or handle connection closing exception
            }
        }

        return isUpdated;
    }

    public static void main(String[] args) throws SQLException {

        // Test Update
//        boolean result = MonthlyPaymentDB.updatePaymentStatus(2);
//        if (result) {
//            System.out.println("Payment status updated successfully.");
//        } else {
//            System.out.println("Failed to update payment status.");
//        }

        // Test case 1: Get all payments for hotel ID 1 (no year or month specified)
        System.out.println("** Test 1: Get all payments for Hotel ID 1 **");
        List<MonthlyPayment> payments1 = MonthlyPaymentDB.getAllMonthlyPaymentsByHotelID(1);
        if (!payments1.isEmpty()) {
            for (MonthlyPayment payment : payments1) {
                System.out.println(payment); // Assuming MonthlyPayment has a meaningful toString() method
            }
        } else {
            System.out.println("No payments found for Hotel ID 1.");
        }

        // Test case 2: Get all payments for hotel ID 2 in year 2023 (no month specified)
        System.out.println("\n** Test 2: Get all payments for Hotel ID 2 in year 2023 **");
        List<MonthlyPayment> payments2 = MonthlyPaymentDB.getAllMonthlyPaymentsByHotelID(2, 2023);
        if (!payments2.isEmpty()) {
            for (MonthlyPayment payment : payments2) {
                System.out.println(payment);
            }
        } else {
            System.out.println("No payments found for Hotel ID 2 in year 2023.");
        }

        // Test case 3: Get all payments for hotel ID 3 in year 2024, month 6 (current month)
        System.out.println("\n** Test 3: Get all payments for Hotel ID 3 in year 2024, month 6 (current month) **");
        MonthlyPayment payment3 = MonthlyPaymentDB.getMonthlyPaymentByHotelIDYearMonth(1, 2024, 9); // Assuming June is the current month
        if (payment3 != null) {
            System.out.println(payment3);
        } else {
            System.out.println("No payments found for Hotel ID 3 in June 2024.");
        }

        // Test case 4: Get total payments for all hotels in year 2024, month 6 (current month)
        System.out.println("\n** Test 4: Get total payments for all hotels in year 2024, month 6 (current month) **");
        int totalPayments = MonthlyPaymentDB.getTotalMonthlyPaymentsByMonthAndYear(6, 2024); // Assuming June is the current month
        System.out.println("Total payments for June 2024: " + totalPayments);
        
    }
}
