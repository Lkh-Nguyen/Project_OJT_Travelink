
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <title>Favorite Hotel</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="css/Left_My_Account.css">
        <link rel="stylesheet" href="css/Favorite_Hotel.css">
        <link rel="stylesheet" href="css/Alter.css">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/listPage.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <!-- Dùng để đăng xuất-->
        <style>
            body {
                display: flex;
                flex-direction: column;
                min-height: 100vh;
                margin: 0;
            }

            .content {
                flex: 1;
                display: flex;
                flex-direction: column;
            }
        </style>
        <!-- Dùng để đăng xuất-->
    </head>
    <body>
        <%@include file="Header.jsp" %>

        <!-- Dùng để đăng xuất-->
        <div id="overlay"></div>
        <div id="logoutConfirm">
            <h2>Logging Out</h2>
            <p>Oh, no! You’ll miss a lot of things by logging out: Traveloka Points, 
                Passenger Quick Pick, Price Alerts, and other member-only benefits.
                Are you sure want to log out?</p>
            <button id="confirmYes"><a href="logout">Yes</a></button>
            <button id="confirmNo"><a href="#">No</a></button>
        </div>
        <!-- Dùng để đăng xuất-->
        <!-- Dùng để in a   lter-->
        <c:if test="${requestScope.alterDeleteSuccess != null}">
            <div id="status-message" style="background-color: rgb(233,251,233);height: 70px;" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%; text-align: start">
                        <h3 style="color:green;margin-top: 5px; margin-bottom: -2px;font-weight:550">Success</h3>
                        <p style="color: black;font-size: 14px">${alterDeleteSuccess}</p>
                    </div>
                </div>           
            </div>
        </c:if>
        <c:if test="${requestScope.alterDeleteUnSuccess != null}">
            <div id="status-message" style="background-color: rgb(253,233,231);height: 70px;" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-error-circle' style="font-size: 50px;color: red;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%;text-align: start">
                        <h3 style="color: red;margin-top: 5px;font-weight: 550 ">Error</h3>
                        <p style="color: black;margin-top: -10px;font-size: 14px">${alterDeleteUnSuccess}</p>
                    </div>
                </div>           
            </div>
        </c:if>

        <!-- Dùng để in alter-->
        <div class="content">
            <div id="mid_container">
                <div id="left">
                    <div id="header_left">
                        <div>
                            <img src="${account.avatarURL}" alt="alt"/>
                        </div>
                        <div style="margin-left: 10px">
                            <h1>${account.name}</h1>
                            <p>Google</p>
                        </div>
                    </div>
                    <div id="header_list">
                        <div class="list0">
                            <a href="My_Account_Update.jsp"><i class='bx bx-cog'></i> <b>My Account</b></a>
                        </div>
                        <div class="list0">
                            <a href="View_Avatar.jsp"><i class='bx bx-image-add' ></i> <b>View Avatar</b></a>
                        </div>
                        <div class="list0">
                            <a href="MyFeedbackServlet"><i class='bx bx-calendar' ></i> <b>My Feedback</b></a>
                        </div>
                        <div class="list0">
                            <a href="All_Hotel_Service"><i class='bx bx-money-withdraw'></i> <b>Booking History</b></a>
                        </div>
                        <div class="list1">
                            <a href="#"><i class='bx bx-heart-circle' ></i> <b>Favorite Hotel</b></a>
                        </div>
                        <div class="list0">
                            <a href="#"><i class='bx bx-log-out' ></i><b id="logoutButton">Logging Out</b></a>
                        </div>
                    </div>
                </div>
                <div id="right">
                    <h1 style="font-weight: bold">Settings</h1>
                    <div id="data_img">
                        <div id="pd_h3">
                            <h2>Favorite Hotels</h2>
                        </div>
                        <c:if test="${empty requestScope.list_Hotel}">
                            <div id="noHistory" class="alert alert-danger text-center" style="margin-top: 3%">
                                You don't have any favorite hotels yet.
                            </div> 
                        </c:if>
                        <c:set var="i" value="0"/>
                        <c:forEach items="${requestScope.list_Hotel}" var="c">

                            <div class="card mb-3 list_Card"  data-hotel-id="${c.hotel_ID}" style="max-width: 100%;height: 210px; margin-top: 2%;margin-left: 2%;margin-right: 2%">
                                <div class="row g-0" >   
                                    <div class="col-md-4">
                                        <div class="img-container">
                                            <img src="${c.url}" class="img-fluid rounded-start" alt="...">
                                        </div>
                                    </div>
                                    <div class="col-md-8">
                                        <div class="card-body" >
                                            <div id="detail_header">
                                                <h5>${c.name}</h5>
                                                <button disabled>Hotel</button>
                                                <c:if test="${c.star eq 1}">
                                                    <i class='bx bxs-star'></i>
                                                </c:if>
                                                <c:if test="${c.star eq 2}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                </c:if>
                                                <c:if test="${c.star eq 3}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>

                                                </c:if>
                                                <c:if test="${c.star eq 4}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                </c:if>
                                                <c:if test="${c.star eq 5}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                </c:if>
                                            </div>
                                            <div id="detail_address">
                                                <i class='bx bxs-location-plus'></i>${c.address}
                                            </div>
                                            <c:set var="j" value="0"/>
                                            <div id="left_rate">
                                                <p id="rating">
                                                    <fmt:formatNumber value="${c.average}" type="number" maxFractionDigits="1" minFractionDigits="1"/>
                                                </p>
                                                <p id="reports" style="font-size: 15px;">${c.ratingCount} feedbacks</p>                                             
                                            </div>
                                            <div id="show_detail" style="margin-left:0px">
                                                <form action="viewHotelDetailServlet" method="get">
                                                    <i class='bx bx-folder-plus'></i>
                                                    <button>View Details Hotel</button>
                                                    <input type="hidden" name="hotel_ID" value="${c.hotel_ID}"/>
                                                </form>
                                            </div>
                                            <form action="ListFavoriteHotel" method="post">
                                                <button class="close-button deleteButton">&times;</button>
                                                <input type="hidden" name="idHotelDelete" value="${c.hotel_ID}"/>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <c:set var="i" value="${i + 1}" />
                        </c:forEach>

                    </div>
                </div>
            </div>
        </div>
        <c:if test="${not empty requestScope.list_Hotel}">
            <c:if test="${fn:length(requestScope.list_Hotel) > 2}">
                <ul class="listPage"></ul>
            </c:if>
        </c:if>

        <%@include file="Footer.jsp"%>
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                document.querySelectorAll(".list_Card").forEach(card => {
                    card.addEventListener("click", function (event) {
                        // Prevent click events on child elements (like buttons) from triggering the card click event
                        if (event.target.tagName !== 'BUTTON' && !event.target.classList.contains('deleteButton')) {
                            const hotelID = this.getAttribute("data-hotel-id");
                            window.location.href = 'viewHotelDetailServlet?hotel_ID=' + hotelID;
                        }
                    });
                });
            });



            function cancel() {
                location.reload();
                event.preventDefault();
            }
            document.getElementById("logoutButton").addEventListener("click", function () {
                document.getElementById("overlay").style.display = "block";
                var logoutConfirm = document.getElementById("logoutConfirm");
                logoutConfirm.style.display = "block"; // Hiển thị khung xác nhận
                setTimeout(function () {
                    logoutConfirm.classList.add("active");
                }, 50);
            });

            document.getElementById("confirmNo").addEventListener("click", function () {
                var logoutConfirm = document.getElementById("logoutConfirm");
                logoutConfirm.classList.remove("active");
                setTimeout(function () {
                    logoutConfirm.style.display = "none"; // Ẩn khung xác nhận
                    document.getElementById("overlay").style.display = "none";
                }, 500);
            });

            document.getElementById("overlay").addEventListener("click", function () {
                var logoutConfirm = document.getElementById("logoutConfirm");
                logoutConfirm.classList.remove("active");
                setTimeout(function () {
                    logoutConfirm.style.display = "none"; // Ẩn khung xác nhận
                    document.getElementById("overlay").style.display = "none";
                }, 500);
            });
        </script>
        <script src="js/Favorite_Hotel.js"></script>
        <script src="js/Alter.js"></script>
    </body>
</html>
