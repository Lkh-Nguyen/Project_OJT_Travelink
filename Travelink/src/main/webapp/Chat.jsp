<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Chat Application</title>
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <style>
            body {
                font-family: 'Roboto', sans-serif;
                background-color: #f1f2f6;
                color: #343a40;
            }
            #chatContainer {
                width: 100%;
                margin: 1rem auto;
                padding: 40px;
                background-color: #fff;
                border-radius: 10px;
                box-shadow: 0 0 20px rgba(0,0,0,0.15);
            }
            #accountsList {
                border-bottom: 2px solid #e9ecef;
                padding-bottom: 10px;
                max-height: 450px;
                overflow-y: auto;
            }
            #mes {
                border: 1px solid #ddd;
                padding: 15px;
                height: 400px;
                overflow-y: auto;
                margin-bottom: 20px;
                border-radius: 8px;
                background-color: #f8f9fa;
            }
            .message {
                margin-bottom: 15px;
                padding: 10px 15px;
                border-radius: 20px;
                max-width: 70%;
                font-size: 1rem;
                line-height: 1.5;
            }
            .user-message {
                background-color: #d1e7dd;
                margin-left: auto;
                color: #155724;
            }
            .other-message {
                background-color: #f8d7da;
                margin-right: auto;
                color: #721c24;
            }
            .account-item {
                cursor: pointer;
                padding: 10px;
                transition: background-color 0.3s;
            }
            .account-item:hover {
                background-color: #e2e6ea;
            }
            .form-control {
                border-radius: 20px;
            }
            .btn {
                border-radius: 20px;
            }
            .message span.text-muted {
                display: block;
                font-size: 0.8rem;
                color: #6c757d;
            }
            .notification-item {
                padding: 10px;
                border: 1px solid #ddd;
                border-radius: 5px;
                background-color: #e9ecef;
            }
        </style>
    </head>
    <body>
        <c:choose>
            <c:when test="${account.role ==1}"><%@include file="Header.jsp" %></c:when>
            <c:when test="${account.role ==2}"><%@include file="Header_HotelHost.jsp" %></c:when>
            <c:when test="${account.role ==3}"><%@include file="Admin_Header.jsp" %></c:when>
        </c:choose>
        <div><button id="backButton" class="btn btn-secondary" onclick="window.history.back()">Back</button></div>
        <div class="container-fluid">
            <div id="chatContainer" class="container">
                <h3 class="text-center mb-4 fw-bold">Chat Application</h3>
                <div id="notificationDiv" class="mb-4"></div> <!-- Notification Container -->
                <div class="row">
                    <div id="accountsList" class="col-md-3 list-group">
                        <!-- Friends List will be loaded here -->
                    </div>
                    <div class="col-md-9">
                        <div id="mes"></div>
                        <div class="input-group">
                            <input id="messageInput" type="text" placeholder="Enter your message" class="form-control">
                            <button id="sendButton" class="btn btn-primary">Send</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <c:if test="${account.role ==1}">
            <%@include file="Footer.jsp" %>
        </c:if>
        <script>
            let wsUri =  "ws://35.197.147.187.nip.io/Travelink/chat";
            let websocket = new WebSocket(wsUri);
            let currentAccountId = null;

            websocket.onopen = function (evt) {
                console.log("Connected to WebSocket server.");
                loadFriends();
            };

            websocket.onmessage = function (evt) {
                let messageObj = JSON.parse(evt.data);
                if (messageObj.type === 'chat') {
                    displayMessage(messageObj);
                } else if (messageObj.type === 'loadAccounts') {
                    handleLoadAccounts(messageObj.accounts);
                } else if (messageObj.type === 'loadMessages') {
                    handleLoadMessages(messageObj.messages);
                } else if (messageObj.type === 'notification') {
                    displayNotification(messageObj);
                }
            };

            websocket.onclose = function (evt) {
                console.log("Disconnected from WebSocket server.");
            };

            websocket.onerror = function (evt) {
                console.log("WebSocket error:", evt);
            };

            document.getElementById("sendButton").onclick = function () {
                sendMessage();
            };

            document.getElementById("messageInput").addEventListener("keypress", function (event) {
                if (event.key === "Enter") {
                    sendMessage();
                }
            });

            function sendMessage() {
                let messageInput = document.getElementById("messageInput");
                let messageText = messageInput.value;

                if (messageText.trim() !== "" && currentAccountId !== null) {
                    let messageObj = {
                        type: "chat",
                        fromId: ${sender},
                        toId: currentAccountId,
                        messageText: messageText
                    };
                    websocket.send(JSON.stringify(messageObj));
                    messageInput.value = "";
                }
            }

            function loadFriends() {
                let messageObj = {
                    type: "loadAccounts",
                    userId: ${sender}
                };
                websocket.send(JSON.stringify(messageObj));
            }

            function handleLoadAccounts(accounts) {
                let accountsList = document.getElementById("accountsList");
                accountsList.innerHTML = "";
                accounts.forEach(function (account) {
                    let accountItem = document.createElement("div");
                    accountItem.className = "list-group-item account-item d-flex align-items-center";

                    let avatar = document.createElement("img");
                    avatar.src = account.avatar;
                    avatar.alt = account.username;
                    avatar.className = "rounded-circle me-3";
                    avatar.style.width = "40px";
                    avatar.style.height = "40px";

                    let accountName = document.createElement("span");
                    accountName.textContent = account.username;

                    accountItem.appendChild(avatar);
                    accountItem.appendChild(accountName);

                    if (account.id === currentAccountId) {
                        accountItem.classList.add("bg-info");
                    }

                    accountItem.onclick = function () {
                        currentAccountId = account.id;
                        // Remove bg-info
                        let allAccountItems = document.querySelectorAll(".account-item");
                        allAccountItems.forEach(function (item) {
                            item.classList.remove("bg-info");
                        });
                        accountItem.classList.add("bg-info");
                        loadMessages(currentAccountId);
                    };

                    accountsList.appendChild(accountItem);
                });
            }

            function handleLoadMessages(messages) {
                let messagesDiv = document.getElementById("mes");
                messagesDiv.innerHTML = "";
                messages.forEach(function (message) {
                    let messageDiv = document.createElement("div");
                    messageDiv.className = "message";
                    if (message.fromId === ${sender}) {
                        messageDiv.classList.add("user-message");
                    } else {
                        messageDiv.classList.add("other-message");
                    }
                    let messageText = document.createElement("span");
                    messageText.textContent = message.text;

                    let messageTime = document.createElement("span");
                    messageTime.className = "text-muted small ms-2";
                    messageTime.textContent = formatTime(message.sentTime);

                    messageDiv.appendChild(messageText);
                    messageDiv.appendChild(messageTime);
                    messagesDiv.appendChild(messageDiv);
                });
                messagesDiv.scrollTop = messagesDiv.scrollHeight;
            }

            function loadMessages(accountId) {
                let messageObj = {
                    type: "loadMessages",
                    toId: accountId,
                };
                if (websocket.readyState === WebSocket.OPEN) {
                    websocket.send(JSON.stringify(messageObj));
                }
            }

            function formatTime(timestamp) {
                let date = new Date(timestamp);
                let hours = date.getHours().toString().padStart(2, '0');
                let minutes = date.getMinutes().toString().padStart(2, '0');
                let day = date.getDate().toString().padStart(2, '0');
                let month = date.getMonth().toString().padStart(2, '0');
                let year = date.getFullYear().toString();
                return hours + ':' + minutes + ' ' + day + '/' + month + '/' + year;
            }

            function displayMessage(messageObj) {
                let messagesDiv = document.getElementById("mes");
                let messageDiv = document.createElement("div");
                messageDiv.className = "message";
                if (messageObj.fromId === ${sender}) {
                    messageDiv.classList.add("user-message");
                } else {
                    messageDiv.classList.add("other-message");
                }
                let messageText = document.createElement("span");
                messageText.textContent = messageObj.messageText;

                let messageTime = document.createElement("span");
                messageTime.className = "text-muted small ms-2";
                messageTime.textContent = formatTime(messageObj.sentTime);

                messageDiv.appendChild(messageText);
                messageDiv.appendChild(messageTime);
                messagesDiv.appendChild(messageDiv);
                messagesDiv.scrollTop = messagesDiv.scrollHeight;
            }
            function displayNotification(notificationObj) {
                let notificationElement = document.getElementById('notificationDiv');
                if (!notificationElement) {
                    console.log('Notification element not found.');
                    return;
                }

                let accountId = ${sessionScope.account.account_ID};
                let msg = notificationObj.message;
                let toId = notificationObj.userId;

                if (accountId === toId) {
                    let newNotification = document.createElement('div');
                    newNotification.className = 'notification-item';
                    newNotification.innerHTML = msg;
                    
                    notificationElement.appendChild(newNotification);
                    
                }
            }
        </script>  
    </body>
</html>
