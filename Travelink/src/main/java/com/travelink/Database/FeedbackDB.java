/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import com.travelink.Model.Feedback;
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
public class FeedbackDB implements DatabaseInfo {

    public static Feedback getFeedbackByFeedbackID(int feedbackID) {
        Feedback feedback = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Feedback WHERE Feedback_ID = ? Order by date desc";
                statement = connection.prepareStatement(query);
                statement.setInt(1, feedbackID); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    feedback = new Feedback();
                    feedback.setFeedbackID(resultSet.getInt("Feedback_ID"));
                    feedback.setDescription(resultSet.getString("Description"));
                    feedback.setRating(resultSet.getByte("Rating"));
                    feedback.setDate(resultSet.getDate("Date"));
                    feedback.setLikesCount(resultSet.getInt("LikesCount"));
                    feedback.setDislikesCount(resultSet.getInt("DislikesCount"));
                    feedback.setReservation_ID(resultSet.getInt("Reservation_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedback by ID: " + e);
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
        return feedback;
    }

    public static Feedback getFeedbackByReservationID(int reservationID) {
        Feedback feedback = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM Feedback WHERE Reservation_ID = ? Order by date desc";
                statement = connection.prepareStatement(query);
                statement.setInt(1, reservationID); // Set the ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    feedback = new Feedback();
                    feedback.setFeedbackID(resultSet.getInt("Feedback_ID"));
                    feedback.setDescription(resultSet.getString("Description"));
                    feedback.setRating(resultSet.getByte("Rating"));
                    feedback.setDate(resultSet.getDate("Date"));
                    feedback.setLikesCount(resultSet.getInt("LikesCount"));
                    feedback.setDislikesCount(resultSet.getInt("DislikesCount"));
                    feedback.setReservation_ID(resultSet.getInt("Reservation_ID"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedback by reservation ID: " + e);
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
        return feedback;
    }

    public static int insertFeedback(Feedback feedback) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "INSERT INTO Feedback (Description, Rating, Date, LikesCount, DislikesCount, Reservation_ID) VALUES (?, ?, ?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setString(1, feedback.getDescription());
                statement.setByte(2, feedback.getRating());
                statement.setDate(3, feedback.getDate());
                statement.setInt(4, feedback.getLikesCount());
                statement.setInt(5, feedback.getDislikesCount());
                statement.setInt(6, feedback.getReservation_ID());

                int rowsInserted = statement.executeUpdate();
                return rowsInserted;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting feedback: " + e);
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
        return 0;
    }

    public static List<Feedback> getFeedbacksByAccountID(int accountID) {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT f.* FROM Feedback f "
                        + "JOIN Reservation r ON f.Reservation_ID = r.Reservation_ID "
                        + "WHERE r.Account_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, accountID); // Set the Account_ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedbackID(resultSet.getInt("Feedback_ID"));
                    feedback.setDescription(resultSet.getString("Description"));
                    feedback.setRating(resultSet.getByte("Rating"));
                    feedback.setDate(resultSet.getDate("Date"));
                    feedback.setLikesCount(resultSet.getInt("LikesCount"));
                    feedback.setDislikesCount(resultSet.getInt("DislikesCount"));
                    feedback.setReservation_ID(resultSet.getInt("Reservation_ID"));
                    feedbackList.add(feedback);
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
        return feedbackList;
    }

    public static List<Feedback> getFeedbacksByHotelID(int hotelID) {
        List<Feedback> feedbackList = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT DISTINCT f.Feedback_ID,f.Description, f.Rating, f.Date, f.LikesCount, f.DislikesCount, f.Reservation_ID "
                        + "FROM Feedback f "
                        + "JOIN Reservation r ON f.Reservation_ID = r.Reservation_ID "
                        + "JOIN Reserved_Room rr ON r.Reservation_ID = rr.Reservation_ID "
                        + "JOIN Room ro ON rr.Room_ID = ro.Room_ID "
                        + "WHERE ro.Hotel_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, hotelID); // Set the Hotel_ID parameter
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    Feedback feedback = new Feedback();
                    feedback.setFeedbackID(resultSet.getInt("Feedback_ID"));
                    feedback.setDescription(resultSet.getString("Description"));
                    feedback.setRating(resultSet.getByte("Rating"));
                    feedback.setDate(resultSet.getDate("Date"));
                    feedback.setLikesCount(resultSet.getInt("LikesCount"));
                    feedback.setDislikesCount(resultSet.getInt("DislikesCount"));
                    feedback.setReservation_ID(resultSet.getInt("Reservation_ID"));
                    feedbackList.add(feedback);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting feedbacks by hotel ID: " + e);
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
        return feedbackList;
    }

    public static boolean updateFeedback(Feedback feedback) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "UPDATE Feedback SET Description = ?, Rating = ?, Date = ?, LikesCount = ?, DislikesCount = ?, Reservation_ID = ? WHERE Feedback_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setString(1, feedback.getDescription());
                statement.setByte(2, feedback.getRating());
                statement.setDate(3, feedback.getDate());
                statement.setInt(4, feedback.getLikesCount());
                statement.setInt(5, feedback.getDislikesCount());
                statement.setInt(6, feedback.getReservation_ID());
                statement.setInt(7, feedback.getFeedbackID());

                int rowsUpdated = statement.executeUpdate();
                return rowsUpdated > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error updating feedback: " + e);
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

    public static boolean deleteFeedback(int feedbackID) {
        Connection connection = null;
        PreparedStatement statement = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "DELETE FROM Feedback WHERE Feedback_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, feedbackID);

                int rowsDeleted = statement.executeUpdate();
                return rowsDeleted > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting feedback: " + e);
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

    //-------------------------------------------------------------------------------------------------------------------------------
    public static boolean hasUserLikedFeedback(int accountID, int feedbackID) throws SQLException {
        Connection conn = DatabaseInfo.getConnect();
        String query = "SELECT Liked FROM UserFeedbackLikes WHERE Account_ID = ? AND Feedback_ID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.setInt(2, feedbackID);
        ResultSet rs = ps.executeQuery();
        boolean liked = false;
        if (rs.next()) {
            liked = rs.getBoolean("Liked");
        }
        rs.close();
        ps.close();
        conn.close();
        return liked;
    }

    public static boolean hasUserDislikedFeedback(int accountID, int feedbackID) throws SQLException {
        Connection conn = DatabaseInfo.getConnect();
        String query = "SELECT Disliked FROM UserFeedbackLikes WHERE Account_ID = ? AND Feedback_ID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.setInt(2, feedbackID);
        ResultSet rs = ps.executeQuery();
        boolean disliked = false;
        if (rs.next()) {
            disliked = rs.getBoolean("Disliked");
        }
        rs.close();
        ps.close();
        conn.close();
        return disliked;
    }

    public static void addUserFeedbackLike(int accountID, int feedbackID) throws SQLException {
        Connection conn = DatabaseInfo.getConnect();
        String query = "MERGE INTO UserFeedbackLikes WITH (HOLDLOCK) AS target "
                + "USING (SELECT ? AS Account_ID, ? AS Feedback_ID) AS source "
                + "ON target.Account_ID = source.Account_ID AND target.Feedback_ID = source.Feedback_ID "
                + "WHEN MATCHED THEN "
                + "    UPDATE SET Liked = 1, Disliked = 0 "
                + "WHEN NOT MATCHED THEN "
                + "    INSERT (Account_ID, Feedback_ID, Liked, Disliked) VALUES (?, ?, 1, 0);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.setInt(2, feedbackID);
        ps.setInt(3, accountID);
        ps.setInt(4, feedbackID);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void addUserFeedbackDislike(int accountID, int feedbackID) throws SQLException {
        Connection conn = DatabaseInfo.getConnect();
        String query = "MERGE INTO UserFeedbackLikes WITH (HOLDLOCK) AS target "
                + "USING (SELECT ? AS Account_ID, ? AS Feedback_ID) AS source "
                + "ON target.Account_ID = source.Account_ID AND target.Feedback_ID = source.Feedback_ID "
                + "WHEN MATCHED THEN "
                + "    UPDATE SET Disliked = 1, Liked = 0 "
                + "WHEN NOT MATCHED THEN "
                + "    INSERT (Account_ID, Feedback_ID, Liked, Disliked) VALUES (?, ?, 0, 1);";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.setInt(2, feedbackID);
        ps.setInt(3, accountID);
        ps.setInt(4, feedbackID);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void removeUserLike(int accountID, int feedbackID) throws SQLException {
        Connection conn = DatabaseInfo.getConnect();
        String query = "UPDATE UserFeedbackLikes SET Liked = 0 WHERE Account_ID = ? AND Feedback_ID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.setInt(2, feedbackID);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void removeUserDislike(int accountID, int feedbackID) throws SQLException {
        Connection conn = DatabaseInfo.getConnect();
        String query = "UPDATE UserFeedbackLikes SET Disliked = 0 WHERE Account_ID = ? AND Feedback_ID = ?";
        PreparedStatement ps = conn.prepareStatement(query);
        ps.setInt(1, accountID);
        ps.setInt(2, feedbackID);
        ps.executeUpdate();
        ps.close();
        conn.close();
    }

    public static void incrementLikesCount(int feedbackID) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String query = "UPDATE Feedback SET LikesCount = LikesCount + 1 WHERE Feedback_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, feedbackID);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void decrementLikesCount(int feedbackID) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String query = "UPDATE Feedback SET LikesCount = LikesCount - 1 WHERE Feedback_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, feedbackID);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void incrementDisLikesCount(int feedbackID) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String query = "UPDATE Feedback SET DislikesCount = DislikesCount + 1 WHERE Feedback_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, feedbackID);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static void decrementDisLikesCount(int feedbackID) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String query = "UPDATE Feedback SET DislikesCount = DislikesCount - 1 WHERE Feedback_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, feedbackID);
        statement.executeUpdate();
        statement.close();
        connection.close();
    }

    public static boolean isFeedbackOwner(int feedbackID, int userID) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String query = "SELECT COUNT(*) FROM Feedback WHERE Feedback_ID = ? AND Account_ID = ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setInt(1, feedbackID);
        statement.setInt(2, userID);
        try (ResultSet rs = statement.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } finally {
            statement.close();
            connection.close();
        }
        return false;
    }
    
    public static int getLikesCount(int feedbackID) throws SQLException {
        Connection con = DatabaseInfo.getConnect();
        String query = "SELECT likes_count FROM Feedback WHERE feedback_id = ?";
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, feedbackID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("likes_count");
                }
            }
        }
        return 0;
    }

    public static int getDislikesCount(int feedbackID) throws SQLException {
        Connection con = DatabaseInfo.getConnect();
        String query = "SELECT dislikes_count FROM Feedback WHERE feedback_id = ?";
        try ( PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, feedbackID);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt("dislikes_count");
                }
            }
        }
        return 0;
    }

    public static void main(String[] args) {
        Feedback feedback = FeedbackDB.getFeedbackByFeedbackID(1);
        System.out.println(feedback);
    }

    // You can add more functions as needed, such as:
    // - Get all feedback for a specific hotel
    // - Create new feedback
    // - Update existing feedback
    // - Delete feedback (consider security measures)
}
