package com.travelink.Database;

import com.travelink.Model.ReportedFeedback;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class ReportedFeedbackDB {
    
    
    public static List<ReportedFeedback> getReportedFeedbackByFeedbackId(int feedbackId) {
        List<ReportedFeedback> reportedFeedbackList = new ArrayList<>();
        Connection con = DatabaseInfo.getConnect();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT Reported_Feedback_ID, ReportTime, Reason, Feedback_ID, Account_ID FROM Reported_Feedback WHERE Feedback_ID = ?");
            // Set parameter
            pstmt.setInt(1, feedbackId);

            // Execute the SQL statement
            ResultSet rs = pstmt.executeQuery();
            
            // Process the ResultSet
            while (rs.next()) {
                ReportedFeedback reportedFeedback = new ReportedFeedback();
                reportedFeedback.setReportedFeedbackId(rs.getInt("Reported_Feedback_ID"));
                reportedFeedback.setReportTime(rs.getTimestamp("ReportTime"));
                reportedFeedback.setReason(rs.getString("Reason"));
                reportedFeedback.setFeedbackId(rs.getInt("Feedback_ID"));
                reportedFeedback.setAccountId(rs.getInt("Account_ID"));
                
                reportedFeedbackList.add(reportedFeedback);
            }

            // Close resources
            rs.close();
            pstmt.close();
            con.close();
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database error appropriately
        } finally {
            // Close resources in a finally block
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle database error appropriately
            }
        }
        return reportedFeedbackList;
    }
    
    public static boolean deleteReportedFeedbackByFeedbackId(int feedbackId) {
        Connection con = DatabaseInfo.getConnect();
        try {
            PreparedStatement pstmt = con.prepareStatement("DELETE FROM Reported_Feedback WHERE Feedback_ID = ?");
            // Set parameter
            pstmt.setInt(1, feedbackId);

            // Execute the SQL statement
            int rowsDeleted = pstmt.executeUpdate();
            // Close resources
            pstmt.close();
            con.close();

            // Return true if at least one row was deleted
            return rowsDeleted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database error appropriately
        } finally {
            // Close resources in a finally block
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle database error appropriately
            }
        }
        return false;
    }

    public static boolean insertReportFeedback(ReportedFeedback reportFeedback) {
        Connection con = DatabaseInfo.getConnect();
        try {
            PreparedStatement pstmt = con.prepareStatement("INSERT INTO Reported_Feedback (ReportTime, Reason, Feedback_ID, Account_ID) VALUES (?, ?, ?, ?)");
            // Set parameters
            pstmt.setTimestamp(1, reportFeedback.getReportTime());
            pstmt.setString(2, reportFeedback.getReason());
            pstmt.setInt(3, reportFeedback.getFeedbackId());
            pstmt.setInt(4, reportFeedback.getAccountId());

            // Execute the SQL statement
            int rowsInserted = pstmt.executeUpdate();
            // Close resources
            pstmt.close();
            con.close();

            // Return true if at least one row was inserted
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database error appropriately
        } finally {
            // Close resources in a finally block
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle database error appropriately
            }
        }
        return false;
    }

    public static boolean checkReportFeedback(int feedbackId, int accountId) {
        Connection con = DatabaseInfo.getConnect();
        try {
            PreparedStatement pstmt = con.prepareStatement("SELECT COUNT(*) FROM Reported_Feedback WHERE Feedback_ID = ? AND Account_ID = ?");
            pstmt.setInt(1, feedbackId);
            pstmt.setInt(2, accountId);

            ResultSet rs = pstmt.executeQuery();
            rs.next();
            int count = rs.getInt(1);
            rs.close();
            pstmt.close();
            con.close();

            return count > 0;
        } catch (SQLException e) {
            e.printStackTrace(); // Handle database error appropriately
        } finally {
            // Close resources in a finally block
            try {
                if (con != null) {
                    con.close();
                }
            } catch (SQLException ex) {
                ex.printStackTrace(); // Handle database error appropriately
            }
        }
        return false;
    }

    public static List<Integer> getAllReportedFeedback_ID() {
        List<Integer> feedbacks = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "select distinct Feedback_ID from Reported_Feedback";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    feedbacks.add(resultSet.getInt("Feedback_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedbacks by account ID: " + e);
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
        return feedbacks;
    }

    public static List<Integer> getCountReport() {
        List<Integer> countReport = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "select count(rf.Feedback_ID) as count from Reported_Feedback rf group by rf.Feedback_ID";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    countReport.add(resultSet.getInt("count"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedbacks by account ID: " + e);
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
        return countReport;
    }

    public static void main(String[] args) {
        // Create a ReportFeedback object
        List<Integer> integers = getAllReportedFeedback_ID();
        System.out.println(integers.size());
    }
}
