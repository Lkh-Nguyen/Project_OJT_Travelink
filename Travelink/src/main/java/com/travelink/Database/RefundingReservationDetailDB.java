/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.RefundingReservation;
import com.travelink.Model.RefundingReservationViewDetail;
import com.travelink.Model.Reservation;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author admin
 */
public class RefundingReservationDetailDB {

    // Method to get all refunding reservation details
    public static List<RefundingReservationViewDetail> getAllRefundingReservationDetails() {
        List<RefundingReservationViewDetail> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();
            if (connection != null) {
                String query = "SELECT rr.*, r.* FROM Refunding_Reservation rr "
                        + "JOIN Reservation r ON rr.Reservation_ID = r.Reservation_ID";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    // Extract RefundingReservation fields
                    int refundingReservationId = resultSet.getInt("Refunding_Reservation_ID");
                    LocalDateTime cancelDate = resultSet.getObject("Cancel_Date", LocalDateTime.class);
                    int amount = resultSet.getInt("Amount");
                    LocalDateTime refundTime = resultSet.getObject("RefundTime", LocalDateTime.class);
                    String status = resultSet.getString("Status");
                    int reservationId = resultSet.getInt("Reservation_ID");

                    // Extract Reservation fields
                    LocalDateTime reservationDate = resultSet.getObject("Reservation_Date", LocalDateTime.class);
                    int numberOfGuests = resultSet.getInt("Number_of_guests");
                    Date checkInDate = resultSet.getDate("CheckInDate");
                    Date checkOutDate = resultSet.getDate("CheckOutDate");
                    int totalPrice = resultSet.getInt("Total_Price");
                    String paymentMethod = resultSet.getString("Payment_Method");
                    String reservationStatus = resultSet.getString("Status");
                    int accountId = resultSet.getInt("Account_ID");

                    // Create RefundingReservationViewDetail object
                    RefundingReservationViewDetail details = new RefundingReservationViewDetail(
                            refundingReservationId, cancelDate, amount, refundTime, status, reservationId,
                            reservationDate, numberOfGuests, (java.sql.Date) checkInDate, (java.sql.Date) checkOutDate,
                            totalPrice, paymentMethod, reservationStatus, accountId
                    );
                    resultList.add(details);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all refunding reservation details: " + e);
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

        return resultList;
    }
    
    
    //Update status for Refunding
     public static boolean setStatusToPaid(int reservationId) {
        Connection connection = null;
        PreparedStatement statement1 = null;
        PreparedStatement statement2 = null;
        
        boolean isSuccess = false;

        try {
            connection = DatabaseInfo.getConnect();
            if (connection != null) {
                // Bắt đầu giao dịch
                connection.setAutoCommit(false);

                // Cập nhật trạng thái của Refunding_Reservation
                String query1 = "UPDATE Refunding_Reservation SET Status = 'PAID' WHERE Reservation_ID = ?";
                statement1 = connection.prepareStatement(query1);
                statement1.setInt(1, reservationId);
                int rowsUpdated1 = statement1.executeUpdate();

                // Cập nhật trạng thái của Reservation
                String query2 = "UPDATE Reservation SET Status = 'CANCEL' WHERE Reservation_ID = ?";
                statement2 = connection.prepareStatement(query2);
                statement2.setInt(1, reservationId);
                int rowsUpdated2 = statement2.executeUpdate();

                // Kiểm tra nếu cả hai cập nhật thành công
                if (rowsUpdated1 > 0 && rowsUpdated2 > 0) {
                    // Cam kết giao dịch
                    connection.commit();
                    isSuccess = true;
                } else {
                    // Rollback nếu có lỗi
                    connection.rollback();
                }
            }
        } catch (SQLException e) {
            System.out.println("Error updating reservation status to PAID: " + e);
            if (connection != null) {
                try {
                    connection.rollback();
                } catch (SQLException rollbackEx) {
                    System.out.println("Error rolling back transaction: " + rollbackEx);
                }
            }
        } finally {
            try {
                if (statement1 != null) {
                    statement1.close();
                }
                if (statement2 != null) {
                    statement2.close();
                }
                if (connection != null) {
                    connection.setAutoCommit(true);
                    connection.close();
                }
            } catch (SQLException e) {
                System.out.println("Error closing resources: " + e);
            }
        }
        return isSuccess;
    }
   
    //Get by range date
    public static List<RefundingReservationViewDetail> getRefundingDetail(LocalDateTime startDate, LocalDateTime endDate) {
        List<RefundingReservationViewDetail> resultList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT rr.*, r.* FROM Refunding_Reservation rr "
                        + "JOIN Reservation r ON rr.Reservation_ID = r.Reservation_ID "
                        + "WHERE rr.Cancel_Date BETWEEN ? AND ?";
                statement = connection.prepareStatement(query);
                statement.setTimestamp(1, Timestamp.valueOf(startDate));
                statement.setTimestamp(2, Timestamp.valueOf(endDate));
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    // Extract RefundingReservation fields
                    int refundingReservationId = resultSet.getInt("Refunding_Reservation_ID");
                    LocalDateTime cancelDate = resultSet.getObject("Cancel_Date", LocalDateTime.class);
                    int amount = resultSet.getInt("Amount");
                    LocalDateTime refundTime = resultSet.getObject("RefundTime", LocalDateTime.class);
                    String status = resultSet.getString("Status");
                    int reservationId = resultSet.getInt("Reservation_ID");

                    // Extract Reservation fields
                    LocalDateTime reservationDate = resultSet.getObject("Reservation_Date", LocalDateTime.class);
                    int numberOfGuests = resultSet.getInt("Number_of_guests");
                    Date checkInDate = resultSet.getDate("CheckInDate");
                    Date checkOutDate = resultSet.getDate("CheckOutDate");
                    int totalPrice = resultSet.getInt("Total_Price");
                    String paymentMethod = resultSet.getString("Payment_Method");
                    String reservationStatus = resultSet.getString("Status");
                    int accountId = resultSet.getInt("Account_ID");

                    // Create RefundingReservationViewDetail object
                    RefundingReservationViewDetail details = new RefundingReservationViewDetail(
                            refundingReservationId, cancelDate, amount, refundTime, status, reservationId,
                            reservationDate, numberOfGuests, (java.sql.Date) checkInDate, (java.sql.Date) checkOutDate,
                            totalPrice, paymentMethod, reservationStatus, accountId
                    );
                    resultList.add(details);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting refunding reservation details: " + e);
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

        return resultList;
    }

    //Update refund time
    public static boolean updateRefundTime(int reservationId, LocalDateTime refundTime) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();
            if (connection != null) {
                String query = "UPDATE Refunding_Reservation SET RefundTime = ? WHERE Reservation_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setTimestamp(1, Timestamp.valueOf(refundTime));
                statement.setInt(2, reservationId);

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error updating refund time: " + e);
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
        return false;
    }

    public static void main(String[] args) {
//        // Define a date range for testing
//        LocalDateTime startDate = LocalDateTime.of(2024, 5, 22, 0, 0);
//        LocalDateTime endDate = LocalDateTime.of(2024, 6, 6, 23, 59);
//
//        // Call the method to get refunding reservation details
//        List<RefundingReservationViewDetail> details = RefundingReservationDetailDB.getRefundingDetail(startDate, endDate);
//
//        // Print the results
//        if (details != null && !details.isEmpty()) {
//            for (RefundingReservationViewDetail detail : details) {
//                System.out.println(detail);
//            }
//        } else {
//            System.out.println("No refunding reservations found for the given date range.");
//        }

        int reservationId = 44; // Provide the actual reservation ID
        LocalDateTime refundTime = LocalDateTime.now(); // Set the refund time to the current time

        // Call the update method
        boolean success = RefundingReservationDetailDB.setStatusToPaid(44);

        // Print the result
        if (success) {
            System.out.println("Refund time updated successfully.");
        } else {
            System.out.println("Failed to update refund time.");
        }
    }
}
