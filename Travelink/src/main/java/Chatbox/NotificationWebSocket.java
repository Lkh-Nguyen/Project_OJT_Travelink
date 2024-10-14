package Chatbox;

import com.travelink.Database.DatabaseInfo;
import jakarta.websocket.*;
import jakarta.websocket.server.ServerEndpoint;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

@ServerEndpoint("/notifications")
public class NotificationWebSocket {

    private static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session) {
        clients.add(session);
        System.out.println("New notification connection with client: " + session.getId());
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Notification client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        clients.remove(session);
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received notification message: " + message);
    }

    public void saveNotificationToDatabase(int userId, String message, String link) {
        String insertNotificationQuery = "INSERT INTO Notification (To_Account_ID, Message, SentTime, ReadStatus, NotificationLink) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DatabaseInfo.DBURL, DatabaseInfo.USERDB, DatabaseInfo.PASSDB); PreparedStatement stmt = conn.prepareStatement(insertNotificationQuery)) {

            stmt.setInt(1, userId);
            stmt.setString(2, message);
            stmt.setTimestamp(3, new java.sql.Timestamp(System.currentTimeMillis())); // Set SentTime to current time
            stmt.setBoolean(4, false); // Set ReadStatus to false (unread)
            stmt.setString(5, link);

            stmt.executeUpdate();

            System.out.println("Notification saved to database successfully.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void sendNotificationToClient(int userId, String message, String link) {
        JSONObject notification = new JSONObject();
        notification.put("type", "notification");
        notification.put("userId", userId);
        notification.put("message", message);
        notification.put("link", link);

        String notificationMessage = notification.toString();

        synchronized (clients) {
            for (Session client : clients) {
                try {
                    client.getBasicRemote().sendText(notificationMessage);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
