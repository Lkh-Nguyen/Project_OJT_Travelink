<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.travelink.Model.*" %>
<%@page import="com.travelink.Database.*" %>
<%@page import="java.time.LocalDate"%>
<%@page import="java.time.temporal.ChronoUnit"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Checkout.css">
        <style>

        </style>
        <title>Don't forget your booking</title>
    </head>
    <body>
        <div class="wrapper">
            <%@include file="Header.jsp" %>
            <c:set var="hotel" value="${requestScope.bookingHotel}" />
            <c:set var="bookingMap" value="${requestScope.bookingMap}" />
            <c:set var="account" value="${sessionScope.account}" />
            <c:set var="totalPrice" value="${requestScope.bookingTotalPrice}" />
            <c:set var="checkInDate" value="${requestScope.checkInDate}" />
            <c:set var="checkOutDate" value="${requestScope.checkOutDate}" />
            <c:set var="number_of_guests" value="${requestScope.number_of_guests}" />
            <div class="container mt-4 content">
                <div class="row">
                    <!-- Hotel Info -->
                    <div class="col-md-4">
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">
                                    Hotel
                                    <span class="stars">
                                        <c:forEach begin="1" end="${hotel.star}">
                                            <i class='bx bxs-star'></i>
                                        </c:forEach>
                                    </span> <br>



                                    <b style="font-weight: 550">${hotel.name}</b>
                                </h5>
                                <p class="card-text" style="margin-bottom: 0px">${hotel.address}</p>

                            </div>
                        </div>

                        <!-- Booking Details -->
                        <div class="card mb-4">
                            <div class="card-body">
                                <h5 class="card-title">Your booking details</h5>
                                <div class="row">
                                    <div class="col">
                                        <strong>Checkin</strong>
                                        <p style="margin-bottom: 0px">${checkInDate}</p>
                                        <p>Từ ${hotel.checkInTimeStart}</p>
                                    </div>
                                    <div class="col">
                                        <strong>Checkout</strong>
                                        <p style="margin-bottom: 0px">${checkOutDate}</p>
                                        <p>Cho đến ${hotel.checkOutTimeEnd}</p>
                                    </div>
                                </div>
                                <strong>Number of guests: </strong>${number_of_guests}

                                <!--<p>Total length of stay: <strong>nights</strong></p>-->
                                <p style="border-top: 1px grey solid;padding-top: 5px;margin-bottom: 0px">You selected</p>
                                <c:forEach items="${bookingMap}" var="entry">
                                    <strong>${entry.value} x ${entry.key.name}</strong>: <span class="price text-danger">${entry.key.price}</span> VND<br>
                                </c:forEach>

                                <a href="viewHotelDetailServlet?hotel_ID=${hotel.hotel_ID}">Change your selection</a>
                                <hr>
                                <h4 class="text-danger">Total: <span class="price text-danger">${totalPrice}</span> VND</h4>
                                <small>Includes taxes and fees</small>

                            </div>
                        </div>
                    </div>

                    <!-- User Info Form -->
                    <div class="col-md-8">
                        <div class="card">
                            <div class="card-body">
                                <h5 class="card-title">Check Your Information</h5>
                                <form action="PaymentServlet" method="post">
                                    <div class="row mb-3">
                                        <div class="col-md-12">
                                            <label for="first-name" class="form-label">Full Name*</label>
                                            <input type="text" class="form-control" id="first-name" readonly value="${account.name}">
                                        </div>
                                    </div>
                                    <div class="mb-3">
                                        <label for="email" class="form-label">Email *</label>
                                        <input type="email" class="form-control" id="email" readonly value="${account.email}">
                                    </div>
                                    <div class="mb-3">
                                        <label for="phone" class="form-label">Telephone*</label>
                                        <div class="input-group">
                                            <span class="input-group-text">+84</span>
                                            <input type="tel" class="form-control" id="phone" readonly value="${account.phoneNumber}">
                                        </div>
                                    </div>

                                    <fieldset class="mb-3">
                                        <legend class="col-form-label pt-0">Who are you booking for?</legend>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="booking-for" id="self" value="self" checked>
                                            <label class="form-check-label" for="self">
                                                I'm the main guest
                                            </label>
                                        </div>
                                        <div class="form-check">
                                            <input class="form-check-input" type="radio" name="booking-for" id="other" value="other">
                                            <label class="form-check-label" for="other">
                                                I'm booking for someone else
                                            </label>
                                        </div>
                                    </fieldset>
                                    <input type="hidden" value="${requestScope.bookingStr}" name="bookingStr" id="bookingStr">
                                    <input type="hidden" name="check_in_date" value="${requestScope.checkInDate}">
                                    <input type="hidden" name="check_out_date" value="${requestScope.checkOutDate}">
                                    <input type="hidden" name="number_of_guests" value="${requestScope.number_of_guests}">
                                    <button type="submit" class="btn btn-primary">Booking</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="Footer.jsp" %>
        </div>
        <script src="bootstrap_js/bootstrap.bundle.min.js"></script>
        <script type="text/javascript">
            // Function to format numbers with thousand separators
            function formatCurrency(amount) {
                return amount.toString().replace(/\B(?=(\d{3})+(?!\d))/g, ".");
            }

            // Function to format all prices in the document
            function formatPrices() {
                var prices = document.getElementsByClassName("price");
                for (var i = 0; i < prices.length; i++) {
                    var price = prices[i].innerHTML;
                    prices[i].innerHTML = formatCurrency(price);
                }
            }

            window.onload = formatPrices;
        </script>
    </body>
</html>