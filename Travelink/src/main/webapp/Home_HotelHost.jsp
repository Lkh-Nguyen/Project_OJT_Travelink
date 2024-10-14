<%-- 
    Document   : Home_HotelHost
    Created on : Jun 13, 2024, 2:23:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>HotelHost Page</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Home_HotelHost.css">
        <style>
            @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
            * {
                font-family: Montserrat, sans-serif;
            }
        </style>
    </head>
    <body>
        <div class="container-fluid">
            <%@include file="Header_HotelHost.jsp" %>
            <!-- Card chứa thanh search -->
            <div class="card" id="cardFirst">
                <div class="row justify-content-center align-items-center p-lg-5" id="rowFirst">
                    <h1 class="p-3 text-center text-light fw-bold">What are you looking for?</h1>
                    <div class="col-md-2">
                        <!-- Placeholder for future content if needed -->
                    </div>
                    <div class="col-md-8">
                        <div class="container-fluid">
                            <form class="d-flex" role="search" onsubmit="event.preventDefault(); searchCards();">
                                <input class="form-control me-2" type="search" placeholder="Search" id="searchInput" aria-label="Search">
                                <button class="btn btn-success" type="submit">Search</button>
                            </form>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <!-- Placeholder for future content if needed -->
                    </div>
                </div>
            </div>

            <div class="row mt-2 p-lg-5">
                <!-- Card thứ nhất -->
                <div class="col-md-4 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/dashboard.svg" alt="Dash Board Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">Dash Board</h5>
                            <p class="card-text text-muted">View Hotel Host Dash Board</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="HotelHost_Login.jsp" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="DashboardServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Card thứ hai -->
                <div class="col-md-4 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/calendar.svg" alt="Booking Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">Booking Management</h5>
                            <p class="card-text text-muted">Manage Booking Reservation</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="HotelHost_Login.jsp" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="HotelHost_BookingScheduleServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Card thứ ba -->
                <div class="col-md-4 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/hotel.svg" alt="Hotel Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">Hotel Management</h5>
                            <p class="card-text text-muted">Manage Hotel Information</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="UpdateHotelInformationServlet" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="UpdateHotelInformationServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <!-- Các thẻ khác -->
                <div class="col-md-3 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/information.svg" alt="Hotel Info Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">Service Management</h5>
                            <p class="card-text text-muted">Manage Hotel's Service</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="HotelHost_Login.jsp" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="HotelHostHotelServiceServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/bake.svg" alt="Customer Info Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">Room Management</h5>
                            <p class="card-text text-muted">Manage Hotel's Room</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="HotelHost_Login.jsp" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="UpdateHotelRoomServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/feedback.svg" alt="History Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">View My Hotel's Feedback</h5>
                            <p class="card-text text-muted">View All Feedback about Hotel</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="HotelHost_Login.jsp" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="HotelHost_FeedbackServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3 mb-4 card-container">
                    <div class="card shadow-lg p-3 mb-5 bg-body-tertiary rounded" id="cardBelow">
                        <div class="card-body text-center">
                            <img src="img_Hotel/bill.webp" alt="Customer Info Icon" style="width: 3rem;">
                            <h5 class="card-title mt-3">Bill Management</h5>
                            <p class="card-text text-muted">Manage Hotel's Bill</p>
                            <hr>
                            <div class="row d-flex">
                                <div class="col-md-2">
                                </div>
                                <div class="col-md-8 justify-content-center text-center align-items-center">
                                    <c:choose>
                                        <c:when test="${account.name == null}">
                                            <a href="HotelHost_Login.jsp" class="btn btn-primary w-100 justify-content-center" disabled>
                                                <img src="img_Hotel/lock.svg" alt="Lock Icon" style="width: 1rem;"> Please Login
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="HotelHost_BookingHistoryBillServlet" class="btn btn-primary w-100 justify-content-center">
                                                <img src="img_Hotel/unlock.svg" alt="Check Icon" style="width: 1rem;"> Go to page
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                                <div class="col-md-2">
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="Footer.jsp" %>
        </div>

        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            function searchCards() {
                var input, filter, cards, cardContainer, title, i;
                input = document.getElementById('searchInput');
                filter = input.value.toLowerCase();
                cards = document.getElementsByClassName('card-container');

                for (i = 0; i < cards.length; i++) {
                    cardContainer = cards[i];
                    title = cardContainer.getElementsByClassName('card-title')[0];
                    if (title.innerHTML.toLowerCase().indexOf(filter) > -1) {
                        cardContainer.style.display = "";
                    } else {
                        cardContainer.style.display = "none";
                    }
                }
            }
        </script>
    </body>
</html>
