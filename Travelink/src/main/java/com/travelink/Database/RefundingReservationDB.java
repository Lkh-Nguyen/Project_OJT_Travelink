/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.RefundingReservation;
import com.travelink.Model.Reservation;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ASUS
 */
public class RefundingReservationDB implements DatabaseInfo {

    public static RefundingReservation getPendingCancelReservationByReservationID(int reservationID) {
        RefundingReservation pendingCancelReservation = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Refunding_Reservation WHERE Reservation_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, reservationID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    pendingCancelReservation = new RefundingReservation();
                    pendingCancelReservation.setRefundingReservationId(resultSet.getInt("Refunding_Reservation_ID"));

                    java.sql.Timestamp timestamp = resultSet.getTimestamp("Cancel_Date");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    pendingCancelReservation.setCancelDate(localDateTime);

                    pendingCancelReservation.setAmount(resultSet.getInt("Amount"));

                    java.sql.Timestamp refundTimestamp = resultSet.getTimestamp("RefundTime");
                    if (refundTimestamp != null) {
                        pendingCancelReservation.setRefundTime(refundTimestamp.toLocalDateTime());
                    }

                    pendingCancelReservation.setStatus(resultSet.getString("Status"));
                    pendingCancelReservation.setReservationId(resultSet.getInt("Reservation_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting pending cancellation by reservation ID: " + e);
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
        return pendingCancelReservation;
    }
    
    public static RefundingReservation getRefundingReservationByReservationID(int reservationID) {
        RefundingReservation pendingCancelReservation = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Refunding_Reservation WHERE Reservation_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, reservationID);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    pendingCancelReservation = new RefundingReservation();
                    pendingCancelReservation.setRefundingReservationId(resultSet.getInt("Refunding_Reservation_ID"));

                    java.sql.Timestamp timestamp = resultSet.getTimestamp("Cancel_Date");
                    LocalDateTime localDateTime = timestamp.toLocalDateTime();
                    pendingCancelReservation.setCancelDate(localDateTime);

                    pendingCancelReservation.setAmount(resultSet.getInt("Amount"));

                    java.sql.Timestamp refundTimestamp = resultSet.getTimestamp("RefundTime");
                    if (refundTimestamp != null) {
                        pendingCancelReservation.setRefundTime(refundTimestamp.toLocalDateTime());
                    }

                    pendingCancelReservation.setStatus(resultSet.getString("Status"));
                    pendingCancelReservation.setReservationId(resultSet.getInt("Reservation_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting pending cancellation by reservation ID: " + e);
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
        return pendingCancelReservation;
    }


    public static int insertRefundingReservation(RefundingReservation refundingReservation) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "INSERT INTO Refunding_Reservation (Cancel_Date, Amount, RefundTime, Status, Reservation_ID) VALUES (?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setTimestamp(1, Timestamp.valueOf(refundingReservation.getCancelDate()));
                statement.setInt(2, refundingReservation.getAmount());

                // Handle nullable RefundTime
                if (refundingReservation.getRefundTime() != null) {
                    statement.setTimestamp(3, Timestamp.valueOf(refundingReservation.getRefundTime()));
                } else {
                    statement.setNull(3, Types.TIMESTAMP);
                }

                statement.setString(4, refundingReservation.getStatus());
                statement.setInt(5, refundingReservation.getReservationId());

                int rowsAffected = statement.executeUpdate();
                if (rowsAffected > 0) {
                    System.out.println("Refunding reservation record inserted successfully.");
                    return rowsAffected; // Return the number of rows inserted
                } else {
                    System.out.println("Error inserting refunding reservation record.");
                    return 0; // Return 0 if no rows were inserted
                }
            }
        } catch (SQLException e) {
            System.out.println("Error inserting refunding reservation record: " + e);
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

        return 0; // Return 0 if an exception occurred
    }

    public static List<RefundingReservation> getRefundingReservationsByReservations(List<Reservation> reservationList) {
        List<RefundingReservation> refundingReservations = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null && reservationList != null && !reservationList.isEmpty()) {
                // Prepare query with placeholders for Reservation_IDs
                StringBuilder sb = new StringBuilder();
                for (int i = 0; i < reservationList.size(); i++) {
                    sb.append("?");
                    if (i < reservationList.size() - 1) {
                        sb.append(",");
                    }
                }

                String query = "SELECT * FROM Refunding_Reservation WHERE Reservation_ID IN (" + sb.toString() + ")";
                statement = connection.prepareStatement(query);

                // Set Reservation_ID parameters
                for (int i = 0; i < reservationList.size(); i++) {
                    statement.setInt(i + 1, reservationList.get(i).getReservationID());
                }

                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    RefundingReservation refundingReservation = new RefundingReservation();
                    refundingReservation.setRefundingReservationId(resultSet.getInt("Refunding_Reservation_ID"));

                    java.sql.Timestamp cancelTimestamp = resultSet.getTimestamp("Cancel_Date");
                    LocalDateTime cancelLocalDateTime = cancelTimestamp.toLocalDateTime();
                    refundingReservation.setCancelDate(cancelLocalDateTime);

                    refundingReservation.setAmount(resultSet.getInt("Amount"));

                    java.sql.Timestamp refundTimestamp = resultSet.getTimestamp("RefundTime");
                    if (refundTimestamp != null) {
                        refundingReservation.setRefundTime(refundTimestamp.toLocalDateTime());
                    }

                    refundingReservation.setStatus(resultSet.getString("Status"));
                    refundingReservation.setReservationId(resultSet.getInt("Reservation_ID"));

                    refundingReservations.add(refundingReservation);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting refunding reservations by reservation IDs: " + e);
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

        return refundingReservations;
    }


    public static void main(String[] args) {
        
    }
}
