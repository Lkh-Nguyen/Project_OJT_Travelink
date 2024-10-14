package com.travelink.Database;

import com.travelink.Model.Feedback;
import com.travelink.Model.UserFeedbackLike;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserFeedbackLikeDB implements DatabaseInfo {

    public static UserFeedbackLike getUserFeedbackLikeByID(int likeId) {
        UserFeedbackLike like = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM UserFeedbackLikes WHERE Like_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, likeId); // Set the ID parameter
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    like = new UserFeedbackLike();
                    like.setLikeId(resultSet.getInt("Like_ID"));
                    like.setAccountId(resultSet.getInt("Account_ID"));
                    like.setFeedbackId(resultSet.getInt("Feedback_ID"));
                    like.setLiked(resultSet.getBoolean("Liked"));
                    like.setDisliked(resultSet.getBoolean("Disliked"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting user feedback like by ID: " + e);
        } finally {
            // Close resources
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
        return like;
    }

    public static List<UserFeedbackLike> getAllUserFeedbackLikes() {
        List<UserFeedbackLike> likes = new ArrayList<>();
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM UserFeedbackLikes";
                statement = connection.prepareStatement(query);
                resultSet = statement.executeQuery();

                while (resultSet.next()) {
                    UserFeedbackLike like = new UserFeedbackLike();
                    like.setLikeId(resultSet.getInt("Like_ID"));
                    like.setAccountId(resultSet.getInt("Account_ID"));
                    like.setFeedbackId(resultSet.getInt("Feedback_ID"));
                    like.setLiked(resultSet.getBoolean("Liked"));
                    like.setDisliked(resultSet.getBoolean("Disliked"));
                    likes.add(like);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting all user feedback likes: " + e);
        } finally {
            // Close resources
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
        return likes;
    }

    public static boolean insertUserFeedbackLike(UserFeedbackLike like) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean rowInserted = false;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "INSERT INTO UserFeedbackLikes (Account_ID, Feedback_ID, Liked, Disliked) VALUES (?, ?, ?, ?)";
                statement = connection.prepareStatement(query);
                statement.setInt(1, like.getAccountId());
                statement.setInt(2, like.getFeedbackId());
                statement.setBoolean(3, like.isLiked());
                statement.setBoolean(4, like.isDisliked());

                rowInserted = statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error inserting user feedback like: " + e);
        } finally {
            // Close resources
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
        return rowInserted;
    }

    public static boolean updateUserFeedbackLike(UserFeedbackLike like) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean rowUpdated = false;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "UPDATE UserFeedbackLikes SET Account_ID = ?, Feedback_ID = ?, Liked = ?, Disliked = ? WHERE Like_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, like.getAccountId());
                statement.setInt(2, like.getFeedbackId());
                statement.setBoolean(3, like.isLiked());
                statement.setBoolean(4, like.isDisliked());
                statement.setInt(5, like.getLikeId());

                rowUpdated = statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error updating user feedback like: " + e);
        } finally {
            // Close resources
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
        return rowUpdated;
    }

    public static boolean deleteUserFeedbackLike(int likeId) {
        Connection connection = null;
        PreparedStatement statement = null;
        boolean rowDeleted = false;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "DELETE FROM UserFeedbackLikes WHERE Like_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, likeId);

                rowDeleted = statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            System.out.println("Error deleting user feedback like: " + e);
        } finally {
            // Close resources
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
        return rowDeleted;
    }

    public static void main(String[] args) throws SQLException {

//        // Test getUserFeedbackLikeByID
//        System.out.println("\n** Test getUserFeedbackLikeByID **");
//        int testLikeID = 2; // Replace with an existing like ID
//        UserFeedbackLike like = UserFeedbackLikeDB.getUserFeedbackLikeByID(testLikeID);
//        if (like == null) {
//            System.out.println("UserFeedbackLike with ID " + testLikeID + " not found.");
//        } else {
//            System.out.println("Details of UserFeedbackLike (ID: " + testLikeID + "):");
//            System.out.println(like); // Assuming your UserFeedbackLike model has a toString() method
//        }
//
//        // Test getAllUserFeedbackLikes
//        System.out.println("\n** Test getAllUserFeedbackLikes **");
//        List<UserFeedbackLike> likes = UserFeedbackLikeDB.getAllUserFeedbackLikes();
//        if (likes.isEmpty()) {
//            System.out.println("No user feedback likes found in the database.");
//        } else {
//            System.out.println("List of all UserFeedbackLikes:");
//            for (UserFeedbackLike likeItem : likes) {
//                System.out.println(likeItem);
//            }
//        }
//        Feedback f = new Feedback();
//        System.out.println(f.getStatus(f.getFeedbackID(),f.getAccount(f.getFeedbackID()).getAccount_ID()));
    }
    public static UserFeedbackLike getUserFeedbackLikeByAccountIDAndFeedbackID(int accountId, int feedbackId) {
        UserFeedbackLike like = null;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DatabaseInfo.getConnect();

            if (connection != null) {
                String query = "SELECT * FROM UserFeedbackLikes WHERE Account_ID = ? AND Feedback_ID = ?";
                statement = connection.prepareStatement(query);
                statement.setInt(1, accountId);
                statement.setInt(2, feedbackId);
                resultSet = statement.executeQuery();

                if (resultSet.next()) {
                    like = new UserFeedbackLike();
                    like.setLikeId(resultSet.getInt("Like_ID"));
                    like.setAccountId(resultSet.getInt("Account_ID"));
                    like.setFeedbackId(resultSet.getInt("Feedback_ID"));
                    like.setLiked(resultSet.getBoolean("Liked"));
                    like.setDisliked(resultSet.getBoolean("Disliked"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error getting user feedback like by Account ID and Feedback ID: " + e);
        } finally {
            // Close resources
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
        return like;
    }
}
