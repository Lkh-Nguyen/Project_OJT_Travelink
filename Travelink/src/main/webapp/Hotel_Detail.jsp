<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.travelink.Model.*" %>
<%@page import="com.travelink.Database.*" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.sql.Date" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Hotel_Detail.css">
        <link rel="stylesheet" href="css/listPage.css">
        <link rel="stylesheet" href="css/HotelHost_Feedback.css">
        <!-- SweetAlert CSS -->
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <!-- SweetAlert JS -->

        <title>Hotel</title>
        <style>
            table {
                width: 100%;
                border-collapse: collapse;
            }
            th, td {
                border: 1px solid #dddddd;
                text-align: left;
                padding: 8px;
            }
            th {
                background-color: #f2f2f2;
            }
            tfoot th {
                background-color: #4CAF50;
                color: white;
            }
            .liked{
                color: blue !important;
                background-color: white;
            }
            .liked span{
                color: blue !important;
            }
            .disliked{
                color : red !important;
                background-color: white;
            }
            .disliked span{
                color : red !important;
            }
            .form-container {
                background-color: white;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            }
            .form-group {
                margin-right: 20px;
            }
            .form-inline .form-group label {
                margin-right: 10px;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <div class="container" id="detail">
            <div id="detail_header">
                <h2>${requestScope.hotel_view.name}</h2>
                <button disabled>Hotel</button>
                <c:if test="${requestScope.hotel_view.star eq 1}">
                    <i class='bx bxs-star'></i>
                </c:if>
                <c:if test="${requestScope.hotel_view.star eq 2}">
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                </c:if>
                <c:if test="${requestScope.hotel_view.star eq 3}">
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                </c:if>
                <c:if test="${requestScope.hotel_view.star eq 4}">
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                </c:if>
                <c:if test="${requestScope.hotel_view.star eq 5}">
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                    <i class='bx bxs-star'></i>
                </c:if>
            </div>
            <div id="detail_address">
                <i class='bx bxs-location-plus' ></i> ${requestScope.hotel_view.address}
            </div>
            <div class="row" style="margin-top: 2%" id="img_Hotel">
                <div class="col-md-8" id="left_img">
                    <img class="thumbnail1" style="width: 102%" src="${requestScope.hotelImgCenter}" alt="alt"/>
                </div>
                <div class="col-md-4" id="right_img">
                    <div class="row" style="margin-bottom: 10px;">
                        <div class="col-6">
                            <img class="thumbnail1" style="width: 108%; height: 195px; object-fit: cover;" src="${requestScope.hotelImg1}" alt="image1"/>
                        </div>
                        <div class="col-6">
                            <img class="thumbnail1" style="width: 100%; height: 195px; object-fit: cover;" src="${requestScope.hotelImg2}" alt="image2"/>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-6">
                            <img class="thumbnail1" style="width: 108%; height: 195px; object-fit: cover;" src="${requestScope.hotelImg3}" alt="image3"/>
                        </div>
                        <div class="col-6" id="lastImg" style="position: relative;">
                            <img style="width: 100%; height: 195px; object-fit: cover; filter: brightness(50%);" src="${requestScope.hotelImg4}" alt="image4"/>
                            <div style="position: absolute; top: 50%; left: 50%; transform: translate(-50%, -50%); z-index: 1;">
                                <form action="viewHotel" method="get">
                                    <button id="btn_show_more" style="background-color: rgba(255, 255, 255, 0.5); padding: 10px 25px; border: none; border-radius: 5px">More images</button>
                                    <input type="hidden" name="hotelId" value="${requestScope.hotel_view.hotel_ID}">
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>

            <div id="myModal1" class="modal1">
                <span class="close1">&times;</span>
                <img class="modal-content1" id="img01">
            </div>
            <div class="row" style="margin-top:1%;padding-bottom: 3%;border-bottom: 1px solid grey" id="content">
                <div class="col-md-8" id="left_content" >
                    <h5 style="font-weight: 550">Introducing the accommodation</h5>
                    <c:set var="description" value="${requestScope.hotel_view.description}" />
                    <%
                    String description = (String) pageContext.getAttribute("description");
                    if (description != null) {
                        description = description.replace("\n", "<br><br>");
                        // Set the modified description back to the page context
                        pageContext.setAttribute("description", description);
                    }
                    %>
                    <p><%= description %></p>
                    <h5 style="font-weight: 550">Favorite amenities</h5>
                    <div style="width: 100%; display: flex; flex-wrap: wrap;" id="amenities">
                        <c:forEach items="${requestScope.hotelFacilityList}" var="facility">
                            <div style="display: flex; align-items: center; margin-right: 20px;">
                                <i class='${facility.url}' style="color: green;"></i><span style="color: black;">${facility.name}</span>
                            </div>
                        </c:forEach>                       
                    </div>
                </div>

                <div class="col-md-4">
                    <div class="card" style="width: 100%; background-color: rgb(240,246,255)">
                        <div class="card-body">
                            <h5 class="card-title" style="font-weight: bold">Highlights of the property</h5>
                            <h6>Perfect for a 1 night stay!</h6>
                            <p class="card-text">Located in the top-rated area in VietNam. This is the best choice for you in holiday. Try it !!!</p>
                            <a href="#idBooking" class="btn btn-primary" style="background-color: blue;  color:white">Booking Now</a><br>
                            <!-- Favorite Hotel -->
                            <c:if test="${requestScope.checkFavorite eq 'true'}">
                                <form action="viewHotelDetailServlet" method="post">
                                    <h6>Add to favorites hotel</h6>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" name="checkedFavorite" checked>
                                        <label class="form-check-label" for="flexSwitchCheckDefault">Add to favorites hotel</label>
                                        <input type="hidden" name="statusFav" value="yes">
                                    </div>
                                    <button class="btn btn-success w-100 favorButton">Save</button>
                                    <input type="hidden" name="idHotelFavor" value="${requestScope.hotel_view.hotel_ID}"/>
                                </form>
                            </c:if>
                            <c:if test="${requestScope.checkFavorite eq 'false'}">
                                <form action="viewHotelDetailServlet" method="post">
                                    <h6>Add to favorites hotel</h6>
                                    <div class="form-check form-switch">
                                        <input class="form-check-input" type="checkbox" role="switch" id="flexSwitchCheckDefault" name="checkedFavorite">
                                        <label class="form-check-label" for="flexSwitchCheckDefault">Add to favorites hotel</label>
                                        <input type="hidden" name="statusFav" value="no">
                                    </div>
                                    <button class="btn btn-success w-100 favorButton">Save</button>
                                    <input type="hidden" name="idHotelFavor" value="${requestScope.hotel_view.hotel_ID}"/>
                                </form>
                            </c:if>
                            <c:if test="${requestScope.alterDeleteSuccess != null}">
                                <div id="status-message" style="background-color: rgb(233,251,233);height: 70px;margin-top: 10px" class="hidden">
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
                                <div id="status-message" style="background-color: rgb(253,233,231);height: 70px ;margin-top: 10px" class="hidden">
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
                            <!-- Favorite Hotel -->
                        </div>
                    </div>
                </div>
            </div>
            <div class="container">
                <div class="row justify-content-center">
                    <div class="col-md-12">
                        <div class="form-container mt-5">
                            <form id="bookingForm" action="viewHotelDetailServlet" method="get" class="form-inline">
                                <input type="hidden" value="${param.hotel_ID}" name="hotel_ID">
                                <div class="form-group">
                                    <label for="check_in_date">Check-in Date:</label>
                                    <input type="date" id="check_in_date" name="check_in_date" class="form-control" required value="${requestScope.check_in_date}">
                                </div>
                                <div class="form-group mt-3">
                                    <label for="check_out_date">Check-out Date:</label>
                                    <input type="date" id="check_out_date" name="check_out_date" class="form-control" required value="${requestScope.check_out_date}">
                                </div>
                                <div class="form-group mt-3">
                                    <label for="check_out_date">Number of guests:</label>
                                    <input type="number" id="number_of_guests" name="number_of_guests" class="form-control" required value="${requestScope.number_of_guests}" min="1">
                                </div>
                                <div class="text-center">
                                    <button type="submit" class="btn btn-primary mt-3">Search</button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="margin-top:2%;padding-bottom: 2%;border-bottom: 1px solid grey">
                <h5 style="font-weight: 550" id="idBooking">Introducing the accommodation</h5>              
                <div class="row">
                    <div class="col-md-12">
                        <c:forEach var="roomHotel" items="${requestScope.roomList}" varStatus="status">
                            <div class="card mb-3" >
                                <div class="row g-0" id="images_rom">
                                    <div class="col-md-3" style="padding-top:10px">
                                        <img src="${requestScope.roomImgList[status.index].url}" class="img-fluid rounded-start img-custom" alt="...">
                                        <div id="show_detail">
                                            <form action="viewRoom" method="get">
                                                <button><i class='bx bx-folder-plus' style="margin-right:5px"></i>View Detail Room</button>
                                                <input type="hidden" name="roomId" value="${roomHotel.room_ID}">
                                                <input type="hidden" name="hotelId" value="${requestScope.hotel_view.hotel_ID}">
                                            </form>
                                        </div>
                                    </div>
                                    <div class="col-md-9">
                                        <div class="card-body1">
                                            <h4 class="card-title" style="margin-top:10px">${roomHotel.name}</h4>
                                            <table id="roomTable">
                                                <thead>
                                                    <tr>
                                                        <th style="width: 250px;">Type Bed Room</th>
                                                        <th>Number people</th>
                                                        <th>Price/Room/Night</th>
                                                        <th>Amount</th>
                                                        <th style="width: 250px;">Price</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <tr data-price="${roomHotel.price}" data-room-name="${roomHotel.name}" data-id-hotel="${roomHotel.room_ID}">
                                                        <td style="width: 250px;">
                                                            <%
                                                                Room roomHotel = (Room)pageContext.getAttribute("roomHotel");
                                                                for(RoomBed roomBed : RoomBedDB.getRoomBedsByRoomID(roomHotel.getRoom_ID())){
                                                                   Bed bed = BedDB.getBedByRoomBedID(roomBed.getRoom_Bed_ID()); 
                                                            %>
                                                            <p style=" margin-bottom:0px"><i class='bx bxs-bed' style="font-size: 12px;"></i> <%= roomBed.getAmount()%> <%=bed.getName() %> </p>
                                                            <%
                                                                }
                                                            %>

                                                            <% 
                                                                
                                                            %> 
                                                            <h4 id="service" style="margin-top: 5px">Breakfast Included</h4>
                                                            <a id="numberRoom">Only ${requestScope.numberOfRoomList[status.index]} rooms left on our site</a>
                                                        </td>
                                                        <td>
                                                            <div id="amount_People">
                                                                <%
                                                                     int n = roomHotel.getCapacity();
                                                                     for (int i = 0; i < n; i++) {
                                                                %>
                                                                <i class='bx bxs-user'></i>
                                                                <%
                                                                    }
                                                                %>


                                                            </div>
                                                        </td>
                                                        <td>
                                                            <p id="save">Tiết kiệm 0%</p><br>
                                                            <h4 id="price_save">${roomHotel.price}</h4>
                                                        </td>
                                                        <td>
                                                            <select style="width:50px">
                                                                <c:forEach var="i" begin="0" end="${requestScope.numberOfRoomList[status.index]}">
                                                                    <option value="${i}">${i}</option>
                                                                </c:forEach>
                                                            </select>
                                                            <input type="hidden" id="infor" value=""/>
                                                        </td>
                                                        <td rowspan="3" style="width: 300px;" id="message">No room selected</td>
                                                    </tr>
                                                </tbody>
                                            </table>
                                            <input type="hidden" id="infor" value=""/>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <form action="CheckoutServlet" method="post" onsubmit="return validateForm()">
                        <input type="hidden" value="" name="bookingStr" id="bookingStr">
                        <input type="hidden" value="${param.hotel_ID}" name="hotel_ID">  
                        <input type="hidden" name="check_in_date" value="${requestScope.check_in_date}">
                        <input type="hidden" name="check_out_date" value="${requestScope.check_out_date}">
                        <input type="hidden" name="number_of_guests" value="${requestScope.number_of_guests}">
                        <input type="submit" value="Continue" id="continue">
                    </form>
                </div>
            </div>

            <div class="row" style="margin-top:2%">
                <h5 style="font-weight: bold">Other reviews from travelers about My Villa Airport Hotel</h5>
                <!--            Overall rating-->
                <%
                    double totalRating = 0;
                    int feedbackCount = 0;


                    for (Feedback f : (List<Feedback>) request.getAttribute("feedbacks")) {
                    totalRating += f.getRating();
                    feedbackCount++;
                }
                double overallRating = (feedbackCount > 0) ? (totalRating / feedbackCount) : 0;
                %>
                <h6>Rating & Overall Review Score</h6>
                <div class="row">
                    <div class="col-md-3 mt-5" id="left_rate">
                        <p id="rating"><%= String.format("%.1f", overallRating) %></p>
                        <div style="display: flex" id="rate">

                            <% if(overallRating == 1){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <%  }else if(overallRating > 1  && overallRating < 2){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star-half'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <%}else if(overallRating == 2){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <%}else if(overallRating > 2 && overallRating < 3){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star-half'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <%}else if(overallRating == 3){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bx-star'></i>
                            <i class='bx bx-star'></i>
                            <%}else if(overallRating > 3 &&  overallRating < 4){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star-half'></i>
                            <i class='bx bx-star'></i>
                            <%}else if(overallRating == 4){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bx-star'></i>
                            <%}else if(overallRating >  4 && overallRating < 5){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star-half'></i>
                            <%}else if(overallRating ==5){%>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <i class='bx bxs-star'></i>
                            <%}%>
                        </div>
                    </div>
                    <!--                    Feedback-->
                    <div class="col-md-9">
                        <div class="my-feedback-button d-flex justify-content-end">
                            <a href="MyFeedbackServlet" class="btn btn-dark mb-2">Show all my feedback</a>
                        </div>
                        <c:forEach var="f" items="${requestScope.feedbacks}">
                            <div class="w-100 mb-3">

                                <div class="card card-body mb-3" data-customer-id="${f.getAccount(f.feedbackID).account_ID}" style="border: 1px solid rgb(227,227,227);border-radius: 5px;padding: 1rem">
                                    <div class="d-flex">
                                        <div class="avatar"><img src="${f.getAccount(f.feedbackID).avatarURL}" width="50"></div>
                                        <div class="ms-3">
                                            <div class="row">
                                                <div class="col-md-11">
                                                    <h6 class="mb-0" style="font-size: 20px;font-weight: bold">${f.getAccount(f.feedbackID).name}</h6>
                                                    <%
                                                    // Example of how you might retrieve the date
                                                    // This should be replaced with your actual logic
                                                    // For example: Date date = f.getAccount(f.feedbackID).getDate();
                                                    Date date = new Date(System.currentTimeMillis()); // Replace with your actual date

                                                    // Format the date
                                                    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
                                                    String formattedDate = "";
                                                    if (date != null) {
                                                        formattedDate = formatter.format(date);
                                                    }
                                                    %>
                                                    <span class="text-muted" style="float: left;font-size: 18px;font-weight: bold"><%= formattedDate %></span>
                                                </div>
                                                <c:if test="${sessionScope.account.account_ID != f.getAccount(f.feedbackID).account_ID}">
                                                    <div class="col-md-1" >
                                                        <form action="ReportFeedbackServlet" method="get">
                                                            <button style="background-color: white;border: none"><i class='bx bxs-error' id="reportFeedback"></i></button>
                                                            <input type="hidden" name="feedbackID" value="${f.feedbackID}"/>
                                                            <input type="hidden" name="hotel_ID" value="${requestScope.hotel_view.hotel_ID}"/>
                                                        </form>
                                                    </div>
                                                </c:if>
                                            </div>
                                            <div style="color: orange;font-weight: bold">
                                                <c:if test="${f.rating eq 1}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </c:if>
                                                <c:if test="${f.rating eq 2}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </c:if>
                                                <c:if test="${f.rating eq 3}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </c:if>
                                                <c:if test="${f.rating eq 4}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </c:if>
                                                <c:if test="${f.rating eq 5}">
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                </c:if>
                                            </div>
                                            <p style="font-size: 18px">${f.description}</p>
                                        </div>
                                    </div>



                                    <div class="d-flex justify-content-end m-2">
                                        <c:set var="feedbackAccountID" value="${f.getAccount(f.feedbackID).account_ID}" />
                                        <c:set var="sessionAccountID" value="${sessionScope.account.account_ID}" />
                                        <c:choose>
                                            <c:when test="${sessionScope.account != null}">
                                                <c:choose>
                                                    <c:when test="${sessionAccountID != feedbackAccountID}">

                                                        <button id="likeButton-${f.feedbackID}" class="btn btn-primary mx-2 ${f.getStatusLike(f.feedbackID,sessionAccountID)}" onclick="likeFeedback(${f.feedbackID})">
                                                            <i class="bx bx-like mx-2"></i>
                                                            <span id="likesCount-${f.feedbackID}" class="mx-2" style="color:#fff">${f.likesCount}</span>
                                                        </button>
                                                        <button id="dislikeButton-${f.feedbackID}" class="btn btn-danger ${f.getStatusDislike(f.feedbackID,sessionAccountID)}" onclick="dislikeFeedback(${f.feedbackID})">
                                                            <i class="bx bx-dislike mx-2"></i>
                                                            <span id="dislikesCount-${f.feedbackID}" class="mx-2" style="color:#fff">${f.dislikesCount}</span>
                                                        </button>
                                                    </c:when>  
                                                    <c:otherwise>
                                                        <button id="likeButton-${f.feedbackID}" class="btn btn-primary mx-2" onclick="error()">
                                                            <i class="bx bx-like mx-2"></i>
                                                            <span id="likesCount-${f.feedbackID}" class="mx-2" style="color:#fff">${f.likesCount}</span>
                                                        </button>
                                                        <button id="dislikeButton-${f.feedbackID}" class="btn btn-danger" onclick="error()">
                                                            <i class="bx bx-dislike mx-2"></i>
                                                            <span id="dislikesCount-${f.feedbackID}" class="mx-2" style="color:#fff">${f.dislikesCount}</span>
                                                        </button>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <button id="likeButton-${f.feedbackID}" class="btn btn-primary mx-2" onclick="errorLogin()">
                                                    <i class="bx bx-like mx-2"></i>
                                                    <span id="likesCount-${f.feedbackID}" class="mx-2" style="color:#fff">${f.likesCount}</span>
                                                </button>
                                                <button id="dislikeButton-${f.feedbackID}" class="btn btn-danger" onclick="errorLogin()">
                                                    <i class="bx bx-dislike mx-2"></i>
                                                    <span id="dislikesCount-${f.feedbackID}" class="mx-2" style="color:#fff">${f.dislikesCount}</span>
                                                </button>
                                            </c:otherwise>
                                        </c:choose> 
                                    </div>     
                                </div>
                            </div>
                        </c:forEach>


                    </div>
                </div>
            </div>
        </div>
        <ul class="listPage">
        </ul>
        <%@include file="Footer.jsp" %>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script>
                                                    function error() {
                                                        Swal.fire({
                                                            title: "Travelink",
                                                            text: "You can't react your feedback",
                                                            icon: "error"
                                                        });
                                                    }
                                                    function errorLogin() {
                                                        Swal.fire({
                                                            title: "Travelink",
                                                            text: "You must login first",
                                                            icon: "error"
                                                        });
                                                    }
                                                    //Handle Like,Dislike
                                                    // Add a debounce function to prevent multiple rapid clicks
                                                    const likeFeedback = (feedbackID) => {
                                                        var likesCountElement = document.getElementById('likesCount-' + feedbackID);
                                                        var likeButton = document.getElementById('likeButton-' + feedbackID);
                                                        var dislikeButton = document.getElementById('dislikeButton-' + feedbackID);
                                                        var dislikesCountElement = document.getElementById('dislikesCount-' + feedbackID);

                                                        var likesCount = parseInt(likesCountElement.textContent);
                                                        var dislikesCount = parseInt(dislikesCountElement.textContent);

                                                        var isLiking = !likeButton.classList.contains('liked');
                                                        var isDisliking = dislikeButton.classList.contains('disliked');

                                                        // Optimistically update the UI
                                                        likesCountElement.textContent = likesCount + (isLiking ? 1 : -1);
                                                        likeButton.classList.toggle('liked');

                                                        if (isDisliking) {
                                                            dislikesCountElement.textContent = dislikesCount - 1;
                                                            dislikeButton.classList.remove('disliked');
                                                        }

                                                        // Send AJAX request
                                                        var xhr = new XMLHttpRequest();
                                                        xhr.open("POST", "ReactFeedbackServlet", true);
                                                        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                        xhr.onreadystatechange = function () {
                                                            if (xhr.readyState === 4) {
                                                                if (xhr.status === 200) {
                                                                    var response = JSON.parse(xhr.responseText);
                                                                    likesCountElement.textContent = response.likesCount;
                                                                    dislikesCountElement.textContent = response.dislikesCount;
                                                                } else {
                                                                    // Revert UI changes if request fails
                                                                    likesCountElement.textContent = likesCount;
                                                                    likeButton.classList.toggle('liked');
                                                                    if (isDisliking) {
                                                                        dislikesCountElement.textContent = dislikesCount;
                                                                        dislikeButton.classList.add('disliked');
                                                                    }
                                                                    alert("Something went wrong!");
                                                                }
                                                            }
                                                        };
                                                        xhr.send("action=" + (isLiking ? "like" : "unlike") + "&feedbackID=" + feedbackID);
                                                    };

                                                    const dislikeFeedback = (feedbackID) => {
                                                        var dislikesCountElement = document.getElementById('dislikesCount-' + feedbackID);
                                                        var dislikeButton = document.getElementById('dislikeButton-' + feedbackID);
                                                        var likeButton = document.getElementById('likeButton-' + feedbackID);
                                                        var likesCountElement = document.getElementById('likesCount-' + feedbackID);

                                                        var dislikesCount = parseInt(dislikesCountElement.textContent);
                                                        var likesCount = parseInt(likesCountElement.textContent);

                                                        var isDisliking = !dislikeButton.classList.contains('disliked');
                                                        var isLiking = likeButton.classList.contains('liked');

                                                        // Optimistically update the UI
                                                        dislikesCountElement.textContent = dislikesCount + (isDisliking ? 1 : -1);
                                                        dislikeButton.classList.toggle('disliked');

                                                        if (isLiking) {
                                                            likesCountElement.textContent = likesCount - 1;
                                                            likeButton.classList.remove('liked');
                                                        }

                                                        // Send AJAX request
                                                        var xhr = new XMLHttpRequest();
                                                        xhr.open("POST", "ReactFeedbackServlet", true);
                                                        xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
                                                        xhr.onreadystatechange = function () {
                                                            if (xhr.readyState === 4) {
                                                                if (xhr.status === 200) {
                                                                    var response = JSON.parse(xhr.responseText);
                                                                    likesCountElement.textContent = response.likesCount;
                                                                    dislikesCountElement.textContent = response.dislikesCount;
                                                                } else {
                                                                    // Revert UI changes if request fails
                                                                    dislikesCountElement.textContent = dislikesCount;
                                                                    dislikeButton.classList.toggle('disliked');
                                                                    if (isLiking) {
                                                                        likesCountElement.textContent = likesCount;
                                                                        likeButton.classList.add('liked');
                                                                    }
                                                                    alert("Something went wrong!");
                                                                }
                                                            }
                                                        };
                                                        xhr.send("action=" + (isDisliking ? "dislike" : "undislike") + "&feedbackID=" + feedbackID);
                                                    };

                                                    document.getElementById('bookingForm').addEventListener('submit', function (event) {
                                                        var checkInDate = new Date(document.getElementById('check_in_date').value);
                                                        var checkOutDate = new Date(document.getElementById('check_out_date').value);
                                                        var today = new Date();
                                                        today.setHours(0, 0, 0, 0); // Đặt giờ phút giây của ngày hiện tại về 0 để so sánh chỉ ngày

                                                        if (checkInDate < today) {
                                                            event.preventDefault();
                                                            Swal.fire({
                                                                icon: 'error',
                                                                title: 'Invalid Date',
                                                                text: 'Check-in date must be after today.'
                                                            });
                                                        } else if (checkInDate >= checkOutDate) {
                                                            event.preventDefault();
                                                            Swal.fire({
                                                                icon: 'error',
                                                                title: 'Invalid Date',
                                                                text: 'Check-out date must be after check-in date.'
                                                            });
                                                        }
                                                    });
        </script>
        <script src="js/Hotel_Detail.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

    </body>
</html>
