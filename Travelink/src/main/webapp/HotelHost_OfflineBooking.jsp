<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.travelink.Model.*" %>
<%@page import="com.travelink.Database.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>
<%@ page import="java.text.NumberFormat" %>
<%@ page import="java.util.Locale" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <title>Reservation Form</title>
        <style>
            h1 {
                text-align: center;
                color: #333;
            }
            form {
                display: flex;
                flex-direction: column;
            }
            label {
                margin-top: 10px;
                font-weight: bold;
                color: #555;
            }
            input[type="number"],
            input[type="date"],
            input[type="text"],
            select {
                width: 100%;
                padding: 10px;
                margin-top: 5px;
                border: 1px solid #ccc;
                border-radius: 4px;
                box-sizing: border-box;
            }
            input[readonly] {
                background-color: #f9f9f9;
            }
            table {
                width: 100%;
                border-collapse: collapse;
                margin-top: 20px;
            }
            table, th, td {
                border: 1px solid #ddd;
            }
            th, td {
                padding: 10px;
                text-align: left;
            }
            th {
                background-color: #f2f2f2;
            }
            tr:nth-child(even) {
                background-color: #f9f9f9;
            }
            tr:hover {
                background-color: #f1f1f1;
            }
            .submit-btn {
                background-color: #4CAF50;
                color: white;
                padding: 15px;
                margin-top: 20px;
                border: none;
                border-radius: 4px;
                cursor: pointer;
                text-align: center;
            }
            .submit-btn:hover {
                background-color: #45a049;
            }
        </style>
    </head>
    <body>
        <div style="display: block">
            <%@include file="Header_HotelHost.jsp" %>
            <div class="container">
                <h1>Reservation Form</h1>
                <form action="InsertOfflineBookingServlet" method="POST">
                    <label for="number_of_guests">Number of Guests:</label>
                    <input type="number" id="number_of_guests" name="number_of_guests" value="1" min="1" required><br>

                    <label for="checkInDate">Check-In Date:</label>
                    <input type="date" id="checkInDate" name="checkInDate" required value="${requestScope.checkInDate}" readonly><br>

                    <label for="checkOutDate">Check-Out Date:</label>
                    <input type="date" id="checkOutDate" name="checkOutDate" required value="${requestScope.checkOutDate}" readonly><br>

                    <label for="totalPrice">Total Price:</label>
                    <input type="text" id="price" name="price" required readonly value="0 VND"><br>

                    <label for="paymentMethod">Payment Method:</label>
                    <input type="text" id="paymentMethod" name="paymentMethod" required value="OFFLINE" readonly><br>

                    <label for="status">Status:</label>
                    <input type="text" id="status" name="status" required value="PAID" readonly><br>

                    <h2>Available Rooms</h2>
                    <table>
                        <tr>
                            <th>Room Name</th>
                            <th>Room Price</th>
                            <th>Number of Rooms</th>
                        </tr>
                        <%
                            List<Room> roomList = (List<Room>) request.getAttribute("roomList");
                            List<Integer> availableRoomList = (List<Integer>) request.getAttribute("availableRoomList");

                            for (int i = 0; i < roomList.size(); i++) {
                                Room room = roomList.get(i);
                                Integer availableRooms = availableRoomList.get(i);
                        %>
                        <tr data-room-id="<%= room.getRoom_ID() %>" data-room-name="<%= room.getName() %>" data-price="<%= room.getPrice() %>">
                            <td><%= room.getName() %></td>
                            <td>
                                <%
                                    double price = room.getPrice();
                                    NumberFormat formatter = NumberFormat.getInstance(new Locale("vi", "VN"));
                                    String formattedPrice = formatter.format(price) + " VND";
                                %>
                                <%= formattedPrice %>
                            </td>
                            <td>
                                <select name="room_<%= room.getRoom_ID() %>_count">
                                    <% for (int j = 0; j <= availableRooms; j++) { %>
                                    <option value="<%= j %>"><%= j %></option>
                                    <% } %>
                                </select>
                            </td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                    <label for="bookingStr">Booking String:</label>
                    <input type="hidden" name="bookingStr" id="bookingStr" ><br>
                    <label for="bookingStr">Total Price</label>
                    <input type="hidden" name="totalPrice" id="totalPrice" value="0"><br>
                    <button type="submit" class="submit-btn">Submit</button>
                </form>
            </div>
            <%@include file="Footer.jsp" %>
        </div>
        <script>
            document.addEventListener('DOMContentLoaded', (event) => {
                const selects = document.querySelectorAll('select');
                const bookingStr = document.getElementById("bookingStr");
                const priceInput = document.getElementById("price");
                const totalPriceInput = document.getElementById("totalPrice");
                const form = document.querySelector('form');

                selects.forEach(select => {
                    select.addEventListener('change', checkSelectValues);
                });

                form.addEventListener('submit', function (event) {
                    if (parseFloat(totalPriceInput.value) === 0) {
                        event.preventDefault(); // Prevent form submission if no room is selected
                        alert("Please select at least one room.");
                    }
                });

                function checkSelectValues() {
                    let bookingString = "";
                    let totalPrice = 0;

                    selects.forEach(select => {
                        let row = select.closest('tr');
                        let roomID = row.getAttribute('data-room-id');
                        let amount = parseInt(select.value, 10);
                        let price = parseFloat(row.getAttribute('data-price'));

                        if (amount > 0) {
                            let info = `Room_ID=` + roomID + `,Quantity=` + amount;
                            if (bookingString === "") {
                                bookingString = info;
                            } else {
                                bookingString += "/" + info;
                            }
                            totalPrice += amount * price; // Calculate the total price
                        }
                    });

                    bookingStr.value = bookingString;
                    priceInput.value = totalPrice.toLocaleString('vi-VN') + " VND";
                    totalPriceInput.value = totalPrice;
                }
            });
        </script>
    </body>
</html>