package com.travelink.Database;

import com.travelink.Model.Notification;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class NotificationDB {

    public static void addNotification(Notification notification) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String sql = "INSERT INTO Notification (To_Account_ID, Message, SentTime, ReadStatus, NotificationLink) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notification.getToAccountId());
            stmt.setString(2, notification.getMessage());
            stmt.setTimestamp(3, Timestamp.valueOf(notification.getSentTime()));
            stmt.setBoolean(4, notification.isReadStatus());
            stmt.setString(5, notification.getNotificationLink());
            stmt.executeUpdate();
        }
    }

    public static Notification getNotification(int notificationId) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String sql = "SELECT * FROM Notification WHERE Notification_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notificationId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new Notification(
                            rs.getInt("Notification_ID"),
                            rs.getInt("To_Account_ID"),
                            rs.getString("Message"),
                            rs.getTimestamp("SentTime").toLocalDateTime(),
                            rs.getBoolean("ReadStatus"),
                            rs.getString("NotificationLink")
                    );
                }
            }
        }
        return null;
    }

    public static List<Notification> getNotificationsForAccount(int accountId) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String sql = "SELECT * FROM Notification WHERE To_Account_ID = ? ORDER BY SentTime DESC";
        List<Notification> notifications = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notifications.add(new Notification(
                            rs.getInt("Notification_ID"),
                            rs.getInt("To_Account_ID"),
                            rs.getString("Message"),
                            rs.getTimestamp("SentTime").toLocalDateTime(),
                            rs.getBoolean("ReadStatus"),
                            rs.getString("NotificationLink")
                    ));
                }
            }
        }
        return notifications;
    }
    
    public static List<Notification> getNotificationsForAccountNotRead(int accountId) throws SQLException{
        Connection connection = DatabaseInfo.getConnect();
        String sql = "SELECT * FROM Notification WHERE To_Account_ID = ? AND ReadStatus = 0 ORDER BY SentTime DESC";
        List<Notification> notifications = new ArrayList<>();
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, accountId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    notifications.add(new Notification(
                            rs.getInt("Notification_ID"),
                            rs.getInt("To_Account_ID"),
                            rs.getString("Message"),
                            rs.getTimestamp("SentTime").toLocalDateTime(),
                            rs.getBoolean("ReadStatus"),
                            rs.getString("NotificationLink")
                    ));
                }
            }
        }
        return notifications;
    }

    public static void markAsRead(int notificationId) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String sql = "UPDATE Notification SET ReadStatus = 1 WHERE Notification_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notificationId);
            stmt.executeUpdate();
        }
    }
    
    public static void markAsReadAll() throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String sql = "UPDATE Notification SET ReadStatus = 1";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.executeUpdate();
        }
    }
    public static void deleteNotification(int notificationId) throws SQLException {
        Connection connection = DatabaseInfo.getConnect();
        String sql = "DELETE FROM Notification WHERE Notification_ID = ?";
        try (PreparedStatement stmt = connection.prepareStatement(sql)) {
            stmt.setInt(1, notificationId);
            stmt.executeUpdate();
        }
    }
    
    public static void main(String[] args) {
        
    }
}
