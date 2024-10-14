/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.travelink.Database;

import Chatbox.AESUtil;
import Chatbox.Chat;
import com.travelink.Model.Message;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author DUYAN
 */
public class MessageDB {

    // Phương thức lưu tin nhắn vào cơ sở dữ liệu
    public static void saveMessageToDatabase(int fromId, int toId, String messageText) throws Exception {
        Chat chat = new Chat();
        String fromUsername = chat.getUsername(fromId);

        try (Connection conn = DriverManager.getConnection(DatabaseInfo.DBURL, DatabaseInfo.USERDB, DatabaseInfo.PASSDB)) {
            String insertMessageQuery = "INSERT INTO Message (From_Account_ID, To_Account_ID, MessageText, SentTime) VALUES (?, ?, ?, CURRENT_TIMESTAMP)";
            try (PreparedStatement insertMessageStmt = conn.prepareStatement(insertMessageQuery)) {
                insertMessageStmt.setInt(1, fromId);
                insertMessageStmt.setInt(2, toId);
                String encryptedMessage = AESUtil.encrypt(messageText);
                insertMessageStmt.setString(3, encryptedMessage);
                insertMessageStmt.executeUpdate();
            }
            System.out.println("Message saved to database successfully.");
        }
    }

    // Phương thức lấy các tin nhắn giữa hai tài khoản
    public static List<Message> sendExistingMessages(int fromId, int toId) throws Exception {
        List<Message> messages = new ArrayList<>();

        try {
            Connection con = DatabaseInfo.getConnect();
            if (con != null) {
                String query = "SELECT From_Account_ID, To_Account_ID, MessageText, SentTime FROM Message "
                        + "WHERE (From_Account_ID = ? AND To_Account_ID = ?) OR (From_Account_ID = ? AND To_Account_ID = ?)";
                PreparedStatement pstmt = con.prepareStatement(query);
                pstmt.setInt(1, fromId);
                pstmt.setInt(2, toId);
                pstmt.setInt(3, toId);
                pstmt.setInt(4, fromId);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Message message = new Message();
                    int rsFromId = rs.getInt("From_Account_ID");
                    int rsToId = rs.getInt("To_Account_ID");
                    String encryptedMessage = rs.getString("MessageText");
                    String decryptedMessage = AESUtil.decrypt(encryptedMessage);
                    Timestamp sentTime = rs.getTimestamp("SentTime");

                    message.setFromId(rsFromId);
                    message.setToId(rsToId);
                    message.setMessageText(decryptedMessage);
                    message.setSentTime(sentTime);

                    messages.add(message);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error: " + ex.getMessage());
        }

        return messages;
    }

    // Phương thức lấy tất cả các tin nhắn từ cơ sở dữ liệu
    public static List<Message> getAllMessages() throws Exception {
        List<Message> messages = new ArrayList<>();

        try {
            Connection con = DatabaseInfo.getConnect();
            if (con != null) {
                String query = "SELECT From_Account_ID, To_Account_ID, MessageText, SentTime FROM Message";
                PreparedStatement pstmt = con.prepareStatement(query);
                ResultSet rs = pstmt.executeQuery();
                while (rs.next()) {
                    Message message = new Message();
                    int rsFromId = rs.getInt("From_Account_ID");
                    int rsToId = rs.getInt("To_Account_ID");
                    String encryptedMessage = rs.getString("MessageText");
                    String decryptedMessage = AESUtil.decrypt(encryptedMessage);
                    Timestamp sentTime = rs.getTimestamp("SentTime");

                    message.setFromId(rsFromId);
                    message.setToId(rsToId);
                    message.setMessageText(decryptedMessage);
                    message.setSentTime(sentTime);

                    messages.add(message);
                }
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new Exception("Database error: " + ex.getMessage());
        } catch (Exception ex) {
            ex.printStackTrace();
            throw new Exception("Error: " + ex.getMessage());
        }

        return messages;
    }

    public static void main(String[] args) {
        try {
            // Test phương thức sendExistingMessages
            List<Message> existingMessages = sendExistingMessages(1, 2);
            System.out.println("Existing Messages: " + existingMessages);

            // Test phương thức getAllMessages
            List<Message> allMessages = getAllMessages();
            System.out.println("All Messages: " + allMessages);
        } catch (Exception ex) {
            Logger.getLogger(MessageDB.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
