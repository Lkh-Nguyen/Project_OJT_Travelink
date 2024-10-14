package Chatbox;

import com.travelink.Database.AccountDB;
import jakarta.servlet.http.HttpSession;
import jakarta.websocket.*;
import jakarta.websocket.server.HandshakeRequest;
import jakarta.websocket.server.ServerEndpoint;
import jakarta.websocket.server.ServerEndpointConfig;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import java.sql.*;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import com.travelink.Database.DatabaseInfo;
import static com.travelink.Database.MessageDB.saveMessageToDatabase;
import static com.travelink.Database.MessageDB.sendExistingMessages;
import com.travelink.Model.Account;
import com.travelink.Model.Message;
import java.util.ArrayList;

@ServerEndpoint(value = "/chat", configurator = Chat.Configurator.class)
public class Chat {

    private static final Set<Session> clients = Collections.synchronizedSet(new HashSet<>());

    @OnOpen
    public void onOpen(Session session, EndpointConfig config) throws IOException {
        HttpSession httpSession = (HttpSession) config.getUserProperties().get(HttpSession.class.getName());
        if (httpSession == null || httpSession.getAttribute("account") == null) {
            System.out.println("Account not found in session. Closing connection.");
            session.close(new CloseReason(CloseReason.CloseCodes.UNEXPECTED_CONDITION, "Account not authenticated."));
            return;
        }

        clients.add(session);
        System.out.println("New connection with client: " + session.getId());

        // Load accounts list when a new session opens
        handleLoadFriends(session);
    }

    @OnClose
    public void onClose(Session session) {
        clients.remove(session);
        System.out.println("Client disconnected: " + session.getId());
    }

    @OnError
    public void onError(Session session, Throwable throwable) {
        clients.remove(session);
        throwable.printStackTrace();
    }

    @OnMessage
    public void onMessage(String message, Session session) {
        System.out.println("Received message: " + message);

        try {
            JSONObject jsonMessage = new JSONObject(message);
            String type = jsonMessage.getString("type");

            if ("chat".equals(type)) {
                handleChatMessage(jsonMessage, session);
            } else if ("loadMessages".equals(type)) {
                handleLoadMessages(jsonMessage, session);
            } else if ("loadAccounts".equals(type)) {
                handleLoadFriends(session);
            } else {
                System.out.println("Invalid message type received: " + type);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void handleChatMessage(JSONObject jsonMessage, Session session) throws Exception {
        NotificationWebSocket nw = new NotificationWebSocket();
        int toId = jsonMessage.getInt("toId");
        int fromId = jsonMessage.getInt("fromId");
        String messageText = jsonMessage.getString("messageText");

        // Save message to database
        saveMessageToDatabase(fromId, toId, messageText);

        // Get fromUsername from database
        String fromUsername = getUsername(fromId);

        // Broadcast message to relevant clients
        broadcastMessage(fromId, fromUsername, toId, messageText);
        String notificationMessage = fromUsername + " sent you a message.";
        saveNotificationToDatabase(toId, notificationMessage, "/messenger");
        sendNotificationToClient(toId, notificationMessage, "/messenger");

        // Reload accounts list for both sender and receiver
        handleLoadFriends(session); // Load accounts list for the sender (fromId)
        Session receiverSession = getSessionById(toId);
        if (receiverSession != null) {
            handleLoadFriends(receiverSession); // Load accounts list for the receiver (toId)
        }
    }

    private void handleLoadMessages(JSONObject jsonMessage, Session session) throws Exception {
        int toId = jsonMessage.getInt("toId");
        int fromId = getUserId(session);

        // Send existed message to client
        List<Message> messages = sendExistingMessages(fromId, toId);

        JSONArray messageArray = new JSONArray();
        for (Message message : messages) {
            JSONObject messageObj = new JSONObject();
            messageObj.put("text", message.getMessageText());
            messageObj.put("fromId", message.getFromId());
            messageObj.put("sentTime", message.getSentTime().getTime()); // Bao gồm thời gian gửi
            messageArray.put(messageObj);
        }

        // Tạo JSON phản hồi
        JSONObject response = new JSONObject();
        response.put("type", "loadMessages");
        response.put("messages", messageArray);

        // Gửi danh sách tin nhắn đến client
        session.getAsyncRemote().sendText(response.toString());
    }

    private void handleLoadFriends(Session session) {
        int userId = getUserId(session);
        List<Account> accounts = new ArrayList<>();
        // Get accepted accounts
        if (getUserRole(session) == 3){
            accounts = AccountDB.getAcceptedFriendsOrderByLatestMessage(userId);
        }
        else {
            accounts = AccountDB.getAllAdminAccounts();
        }
        // Create JSON array from the accounts list
        JSONArray accountsArray = new JSONArray();
        for (Account account : accounts) {
            JSONObject accountObj = new JSONObject();
            accountObj.put("id", account.getAccount_ID());
            accountObj.put("username", account.getName());
            accountObj.put("avatar", account.getAvatarURL());
            accountsArray.put(accountObj);
        }

        // Create response JSON
        JSONObject response = new JSONObject();
        response.put("type", "loadAccounts");
        response.put("accounts", accountsArray);

        // Send the accounts list to the client
        session.getAsyncRemote().sendText(response.toString());
    }

    private void broadcastMessage(int fromId, String fromUsername, int toId, String messageText) {
        JSONObject messageObj = new JSONObject();
        messageObj.put("type", "chat");
        messageObj.put("fromId", fromId);
        messageObj.put("fromUsername", fromUsername);
        messageObj.put("toId", toId);
        messageObj.put("messageText", messageText);
        messageObj.put("sentTime", System.currentTimeMillis()); // Thêm thời gian gửi

        String message = messageObj.toString();

        synchronized (clients) {
            for (Session client : clients) {
                HttpSession httpSession = (HttpSession) client.getUserProperties().get(HttpSession.class.getName());
                if (httpSession != null) {
                    Account user = (Account) httpSession.getAttribute("account");
                    if (user != null) {
                        int userId = user.getAccount_ID();

                        // Chỉ gửi tin nhắn đến các session liên quan (fromId và toId trùng với user hiện tại)
                        if (userId == fromId || userId == toId) {
                            try {
                                client.getBasicRemote().sendText(message);
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }
    }

    public String getUsername(int userId) {
        String username = null;
        String getUserQuery = "SELECT Name FROM Account WHERE Account_ID = ?";
        try (Connection conn = DriverManager.getConnection(DatabaseInfo.DBURL, DatabaseInfo.USERDB, DatabaseInfo.PASSDB); PreparedStatement getUserStmt = conn.prepareStatement(getUserQuery)) {

            getUserStmt.setInt(1, userId);
            try (ResultSet rs = getUserStmt.executeQuery()) {
                if (rs.next()) {
                    username = rs.getString("Name");
                } else {
                    System.out.println("Account not found for Account_ID: " + userId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return username;
    }

    private int getUserId(Session session) {
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        if (httpSession != null) {
            Account user = (Account) httpSession.getAttribute("account");
            if (user != null) {
                return user.getAccount_ID();
            }
        }
        throw new IllegalStateException("User not authenticated");
    }
    
    private int getUserRole(Session session){
        HttpSession httpSession = (HttpSession) session.getUserProperties().get(HttpSession.class.getName());
        if (httpSession != null) {
            Account user = (Account) httpSession.getAttribute("account");
            if (user != null) {
                return user.getRole();
            }
        }
        throw new IllegalStateException("User not authenticated");
    }

    private Session getSessionById(int userId) {
        synchronized (clients) {
            for (Session client : clients) {
                HttpSession httpSession = (HttpSession) client.getUserProperties().get(HttpSession.class.getName());
                if (httpSession != null) {
                    Account user = (Account) httpSession.getAttribute("account");
                    if (user != null && user.getAccount_ID() == userId) {
                        return client;
                    }
                }
            }
        }
        return null;
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

    public static class Configurator extends ServerEndpointConfig.Configurator {

        @Override
        public void modifyHandshake(ServerEndpointConfig config, HandshakeRequest request, HandshakeResponse response) {
            HttpSession httpSession = (HttpSession) request.getHttpSession();
            if (httpSession != null) {
                config.getUserProperties().put(HttpSession.class.getName(), httpSession);
            }
        }
    }

}
