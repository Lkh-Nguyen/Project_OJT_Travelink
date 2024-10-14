
<%-- 
    Document   : Hotel_Detail.jsp
    Created on : May 24, 2024, 3:27:14 PM
    Author     : HELLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Hotel_Detail.css">
        <link rel="stylesheet" href="css/ShowHotel_Images.css">
        <title>Hotel</title>
    </head>
    <body>
        <div class='container-fluid'>
            <%@include file="Header.jsp" %>
        </div>
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
                                <i class='bx bx-child' style="color: green;"></i><span style="color: black;">${facility.name}</span>
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
                            <form action="viewHotelDetailServlet" method="get">
                                <button class="btn btn-primary" style="background-color: blue;  color:white">Booking Now</button>
                                <input type="hidden" name="hotel_ID" value="${requestScope.hotel_view.hotel_ID}">                               
                            </form>
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
                            <!-- Favorite Hotel -->
                        </div>
                    </div>
                </div>
            </div>
        </div>    
        <div class="container" style="margin-top:1%">
            <h3>Images of room</h3>
            <div class="row" >
                <c:forEach var="roomImg" items="${requestScope.roomImgList}">
                    <div class="col-md-2 Img">
                        <img src="${roomImg.url}" alt="Thumbnail 1" class="thumbnail">
                    </div>
                </c:forEach>
            </div>

            <div id="myModal" class="modal">
                <span class="close">&times;</span>
                <span class="prev">&#10094;</span>
                <img class="modal-content" id="img01">
                <span class="next">&#10095;</span>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="js/ShowHotel_Images.js"></script>
    </body>
</html>
