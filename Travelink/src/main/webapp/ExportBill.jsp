<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html>
    <head>
        <title>Bill Payment</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 0;
                padding: 0;
            }

            .header {
                display: flex;
                justify-content: space-between;
                align-items: center;
                padding: 20px;
                background-color: #f0f0f0; /* Light gray background */
            }

            .logo {
                font-size: 24px;
                font-weight: bold;
            }

            .company-info {
                font-style: italic;
            }

            .bill-info,
            .hotel-info,
            .bill-summary {
                margin: 20px 0;
                padding: 20px;
                border: 1px solid #ddd; /* Light gray border */
                border-radius: 5px; /* Rounded corners */
            }

            .bill-info h2,
            .hotel-info h2,
            .bill-summary h2 {
                text-align: center;
                margin-bottom: 10px;
            }

            .details {
                margin-bottom: 10px;
            }

            .details p {
                margin-bottom: 5px;
            }

            .reservation-date {
                text-align: right;
            }

            .bill-table {
                width: 100%;
                border: 1px solid #ddd; /* Light gray border */
                border-collapse: collapse; /* Ensures table cells don't have double borders */
            }

            .bill-table th,
            .bill-table td {
                padding: 5px 10px;
                border: 1px solid #ddd; /* Light gray border for table cells */
            }

            .totals {
                display: flex;
                justify-content: space-between;
                margin-top: 10px;
            }
        </style>
    </head>
    <body>
        <header class="header">
            <h1 class="logo">TRAVELINK</h1>
            <p class="company-info">RESERVATION ID: ${requestScope.list_bill[0].reservationID}</p>
        </header>

        <section class="bill-info">
            <h2>Booking Details</h2>
            <div class="details">
                <p>Name: Travelink</p>
                <p>Contact: +123456789</p>
                <p>Email: group1swp391@fpt.edu.vn</p>
                <p>Address: FPT Complex, Da Nang City</p>
            </div>
            <p class="reservation-date">Date: ${requestScope.list_bill[0].reservationDate}</p>
        </section>

        <section class="hotel-info">
            <h2>Hotel Information</h2>
            <c:if test="${not empty requestScope.list_bill}"> 
                <div class="details">
                    <p>Hotel Brand: ${requestScope.list_bill[0].hotel_Name}</p>
                    <p>Check In Date: ${requestScope.list_bill[0].checkInDate}</p>
                    <p>Check Out Date: ${requestScope.list_bill[0].checkOutDate}</p>
                    <p>Customer: ${account.name}</p>                  
                </div>
            </c:if>
        </section>

        <section class="bill-summary">
            <h2>Booking Bill</h2>
            <table class="bill-table">
                <thead>
                    <tr>
                        <th>No.</th>
                        <th>Room Name</th>
                        <th>Quantity</th>
                        <th>Price</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="bill" items="${requestScope.list_bill}">
                        <tr>
                            <td>${status.index + 1}</td>
                            <td>${bill.room_Name}</td>
                            <td>${bill.amount}</td>
                            <td>${bill.room_price}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
            <div class="totals">
                <p class="grand-total">Grand Total: ${requestScope.list_bill[0].total_price}</p>
            </div>
        </section>

    </body>
</html>
