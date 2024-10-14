<%@page import="java.util.List" %>
<%@page import="com.travelink.View.*" %>
<%@page import="java.util.*" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Right_My_Account.css">
        <link rel="stylesheet" href="css/HotelHost_Feedback.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <%
            SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
            SimpleDateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
            String formattedStartDate = "";
            String formattedEndDate = "";

            if (request.getAttribute("startDate") != null && request.getAttribute("endDate") != null) {
            Date startDate = inputFormat.parse((String) request.getAttribute("startDate"));
            Date endDate = inputFormat.parse((String) request.getAttribute("endDate"));
            formattedStartDate = outputFormat.format(startDate);
            formattedEndDate = outputFormat.format(endDate);
            }
        %>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="col-md-1" style="margin-left: 50px">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2">
                <img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
            </a>
        </div>
        <div class="container-fluid" id="right" >
            <!-- Card History -->
            <!-- Change -->
            <div class="row">

                <div class="col-md-2 mx-auto" style="background: white;margin-top: 40px">
                    <form action="HotelHost_BookingHistoryBillServlet" method="post">
                        <div class="card">
                            <div class="card-header text-center">Choose Hotel</div>
                            <div class="card-body" style=" padding-left: 0px">
                                <div class="form-check d-flex justify-content-between">
                                    <select class="form-select" id="statusSelect" name="hotel_ID">
                                        <c:forEach items="${requestScope.hotels}" var="c">
                                            <option value="${c.hotel_ID}" ${c.hotel_ID == requestScope.hotel_ID ? 'selected="selected"' : ''}>${c.name}</option>
                                        </c:forEach>
                                    </select>
                                    </select>
                                </div>
                            </div>
                        </div>
                        <div class="card mt-3">
                            <div class="card-header text-center">STATUS</div>
                            <div class="card-body">
                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <label class="form-check-label" for="single">All</label>
                                    </div>
                                    <label> ${fn:length(requestScope.listBillByHotel_ID)}</label>
                                </div>
                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <input class="form-check-input" type="checkbox" id="double" name="checked_Feedback" <c:if test="${requestScope.checked_Feedback ==  true}">checked</c:if>>
                                            <label class="form-check-label" for="double">FEEDBACKED</label>
                                        </div>
                                    <%
                                        int countFeedbackBills = 0;
                                        for (Bill bill : (List<Bill>) request.getAttribute("listBillByHotel_ID")) {
                                            if ("FEEDBACKED".equals(bill.getStatus())) {
                                                countFeedbackBills++;
                                            }
                                        }
                                    %>
                                    <label><%= countFeedbackBills %></label>
                                </div>
                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <input class="form-check-input" type="checkbox" id="double" name="checked_Finish" <c:if test="${requestScope.checked_Finish ==  true}">checked</c:if>>
                                            <label class="form-check-label" for="double">FINISHED</label>
                                        </div>
                                    <%
                                        int countFinishBills = 0;
                                        for (Bill bill : (List<Bill>) request.getAttribute("listBillByHotel_ID")) {
                                            if ("FINISHED".equals(bill.getStatus())) {
                                                countFinishBills++;
                                            }
                                        }
                                    %>
                                    <label><%= countFinishBills %></label>
                                </div>

                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <input class="form-check-input" type="checkbox" id="double" name="checked_Processing" <c:if test="${requestScope.checked_Processing ==  true}">checked</c:if>>
                                            <label class="form-check-label" for="triple">PROCESSING</label>
                                        </div>
                                    <%
                                        int countProcessingBills = 0;
                                        for (Bill bill : (List<Bill>) request.getAttribute("listBillByHotel_ID")) {
                                            if ("PROCESSING".equals(bill.getStatus())) {
                                                countProcessingBills++;
                                            }
                                        }
                                    %>
                                    <label><%= countProcessingBills %></label>
                                </div>
                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <input class="form-check-input" type="checkbox" id="double" name="checked_Paid" <c:if test="${requestScope.checked_Paid ==  true}">checked</c:if>>
                                            <label class="form-check-label" for="triple">PAID</label>
                                        </div>
                                    <%
                                        int countPaidBills = 0;
                                        for (Bill bill : (List<Bill>) request.getAttribute("listBillByHotel_ID")) {
                                            if ("PAID".equals(bill.getStatus())) {
                                                countPaidBills++;
                                            }
                                        }
                                    %>
                                    <label><%= countPaidBills %></label>
                                </div>
                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <input class="form-check-input" type="checkbox" id="double" name="checked_Refunding" <c:if test="${requestScope.checked_Refunding ==  true}">checked</c:if>>
                                            <label class="form-check-label" for="triple">REFUNDING</label>
                                        </div>
                                    <%
                                        int countRefundingBills = 0;
                                        for (Bill bill : (List<Bill>) request.getAttribute("listBillByHotel_ID")) {
                                            if ("REFUNDING".equals(bill.getStatus())) {
                                                countRefundingBills++;
                                            }
                                        }
                                    %>
                                    <label><%= countRefundingBills %></label>
                                </div>
                                <div class="form-check d-flex justify-content-between">
                                    <div>
                                        <input class="form-check-input" type="checkbox" id="double" name="checked_Cancel" <c:if test="${requestScope.checked_Cancel ==  true}">checked</c:if>>
                                            <label class="form-check-label" for="triple">CANCEL</label>
                                        </div>
                                    <%
                                        int countCancelBills = 0;
                                        for (Bill bill : (List<Bill>) request.getAttribute("listBillByHotel_ID")) {
                                            if ("CANCEL".equals(bill.getStatus())) {
                                                countCancelBills++;
                                            }
                                        }
                                    %>
                                    <label><%= countCancelBills %></label>
                                </div>
                            </div>
                        </div>
                        <input type="hidden" name="startDate" value="${requestScope.startDate}" > 
                        <input type="hidden" name="endDate" value="${requestScope.endDate}">
                        <input type="hidden" name="hotel_ID" value="${requestScope.hotel_ID}"/>
                        <input type="hidden" name="type" value="2"/>
                        <input type="submit" class="btn btn-outline-secondary mt-3" value="Search"/>
                    </form>
                </div>

                <div class="col-md-10">
                    <div class="container-fluid">
                        <h1 class="fw-bold fs-1">History Bill</h1>
                        <div class="row mb-4">
                            <div class="col-md-3"></div>
                            <div class="col-md-6">
                                <form action="HotelHost_BookingHistoryBillServlet" method="post" onsubmit="return validateDates()">
                                    <div class="input-group">
                                        <input type="date" name="startDate" value="${requestScope.startDate}" style="margin-right: 10px" class="form-control" placeholder="Start Date" required> 
                                        <input type="date" name="endDate" value="${requestScope.endDate}" style="margin-left: 10px" class="form-control" placeholder="End Date" required>
                                        <input type="hidden" name="hotel_ID" value="${requestScope.hotel_ID}"/>
                                        <input type="hidden" name="type" value="1"/>
                                        <button type="submit" class="btn btn-primary" style="margin-left: 10px">Search</button>
                                    </div>
                                </form>
                            </div>
                            <div class="col-md-3"></div>
                        </div>


                        <c:if test="${requestScope.startDate != null and requestScope.endDate != null}">
                            <div><h4>From <%= formattedStartDate %> to <%= formattedEndDate %></h4></div>
                        </c:if>
                    </div>
                    <div id="hotelList">
                        <div class ="row p-4">
                            <c:if test="${!requestScope.checked_Finish && !requestScope.checked_Paid && !requestScope.checked_Cancel}">
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any bills.
                                </div>
                            </c:if>

                            <c:if test="${requestScope.checked_Feedback ==  true}">
                                <h3>Status Bill: Feedback (<%= countFeedbackBills %> bills)</h3>
                                <% if (countFeedbackBills == 0) { %>
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any feedbacked bills.
                                </div>
                                <% } %>
                                <c:forEach items="${requestScope.listBillByHotel_ID}" var="c">
                                    <c:if test="${c.status == 'FEEDBACKED'}">
                                        <div class="col-md-3 mb-4 hotel-card">
                                            <div class="card h-100 border rounded shadow">
                                                <div class="card-body d-flex flex-column custom-bg">
                                                    <div class="row mb-2">
                                                        <div class="col">
                                                            <p class="card-text">
                                                                Reservation: ${c.reservationID}
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-check'></i> ${c.checkInDate}
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-x'></i> ${c.checkOutDate}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-dollar-circle'></i> <span class="formatted-price">${c.total_price}</span> VND
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text">
                                                                <i class='bx bx-check-square'></i>
                                                                <span class="badge text-bg-info">FEEDBACKED</span>
                                                            </p>

                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <a href="HotelHost_BookingHistoryBillDetailServlet?reservationID=${c.reservationID}" class="btn btn-outline-primary mt-auto">
                                                                <i class='bx bx-detail'></i> View Details
                                                            </a>
                                                        </div>
                                                        <div class="col">
                                                            <form action="ViewFeedbackReservationIDServlet" method="post">
                                                                <input type="hidden" name="reservationID" value="${c.reservationID}"/>
                                                                <input type="hidden" name="role" value="2"/>
                                                                <button class="btn btn-outline-primary mt-auto cancel-button update-button">
                                                                    View Feedback
                                                                </button> 
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <c:if test="${requestScope.checked_Finish ==  true}">
                                <h3>Status Bill: Finish (<%= countFinishBills %> bills)</h3>
                                <% if (countFinishBills == 0) { %>
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any finish bills.
                                </div>
                                <% } %>
                                <c:forEach items="${requestScope.listBillByHotel_ID}" var="c">
                                    <c:if test="${c.status == 'FINISHED'}">
                                        <div class="col-md-3 mb-4 hotel-card">
                                            <div class="card h-100 border rounded shadow">
                                                <div class="card-body d-flex flex-column custom-bg">
                                                    <div class="row mb-2">
                                                        <div class="col">
                                                            <p class="card-text">
                                                                Reservation: ${c.reservationID}
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-check'></i> ${c.checkInDate}
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-x'></i> ${c.checkOutDate}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-dollar-circle'></i> <span class="formatted-price">${c.total_price}</span> VND
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text">
                                                                <i class='bx bx-check-square'></i>
                                                                <span class="badge text-bg-primary">${c.status}</span>
                                                            </p>

                                                        </div>
                                                    </div>
                                                    <a href="HotelHost_BookingHistoryBillDetailServlet?reservationID=${c.reservationID}" class="btn btn-outline-primary mt-auto">
                                                        <i class='bx bx-detail'></i> View Details
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>


                            <c:if test="${requestScope.checked_Processing ==  true}">
                                <h3>Status Bill: Processing (<%= countProcessingBills %> bills)</h3>
                                <% if (countProcessingBills == 0) { %>
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any processing bills.
                                </div>
                                <% } %>
                                <c:forEach items="${requestScope.listBillByHotel_ID}" var="c">
                                    <c:if test="${c.status == 'PROCESSING'}">
                                        <div class="col-md-3 mb-4 hotel-card">
                                            <div class="card h-100 border rounded shadow">
                                                <div class="card-body d-flex flex-column custom-bg">
                                                    <div class="row mb-2">
                                                        <div class="col">
                                                            <p class="card-text">
                                                                Reservation: ${c.reservationID}
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-check'></i> ${c.checkInDate}
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-x'></i> ${c.checkOutDate}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-dollar-circle'></i> <span class="formatted-price">${c.total_price}</span> VND
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text">
                                                                <i class='bx bx-check-square'></i>
                                                                <span class="badge text-bg-warning">PROCESSING</span>
                                                            </p>

                                                        </div>
                                                    </div>
                                                    <a href="HotelHost_BookingHistoryBillDetailServlet?reservationID=${c.reservationID}" class="btn btn-outline-primary mt-auto">
                                                        <i class='bx bx-detail'></i> View Details
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <c:if test="${requestScope.checked_Paid ==  true}">
                                <h3>Status Bill: Paid (<%= countPaidBills %> bills)</h3>
                                <% if (countPaidBills == 0) { %>
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any paid bills.
                                </div>
                                <% } %>
                                <c:forEach items="${requestScope.listBillByHotel_ID}" var="c">
                                    <c:if test="${c.status == 'PAID'}">
                                        <div class="col-md-3 mb-4 hotel-card">
                                            <div class="card h-100 border rounded shadow">
                                                <div class="card-body d-flex flex-column custom-bg">
                                                    <div class="row mb-2">
                                                        <div class="col">
                                                            <p class="card-text">
                                                                Reservation: ${c.reservationID}
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-check'></i> ${c.checkInDate}
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-x'></i> ${c.checkOutDate}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-dollar-circle'></i> <span class="formatted-price">${c.total_price}</span> VND
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text">
                                                                <i class='bx bx-check-square'></i>
                                                                <span class="badge text-bg-success">${c.status}</span>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <a href="HotelHost_BookingHistoryBillDetailServlet?reservationID=${c.reservationID}" class="btn btn-outline-primary mt-auto">
                                                        <i class='bx bx-detail'></i> View Details
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <c:if test="${requestScope.checked_Refunding ==  true}">
                                <h3>Status Bill: Refunding (<%= countRefundingBills %> bills)</h3>
                                <% if (countRefundingBills == 0) { %>
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any refunding bills.
                                </div>
                                <% } %>
                                <c:forEach items="${requestScope.listBillByHotel_ID}" var="c">
                                    <c:if test="${c.status == 'REFUNDING'}">
                                        <div class="col-md-3 mb-4 hotel-card">
                                            <div class="card h-100 border rounded shadow">
                                                <div class="card-body d-flex flex-column custom-bg">
                                                    <div class="row mb-2">
                                                        <div class="col">
                                                            <p class="card-text">
                                                                Reservation: ${c.reservationID}
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-check'></i> ${c.checkInDate}
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-x'></i> ${c.checkOutDate}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-dollar-circle'></i> <span class="formatted-price">${c.total_price}</span> VND
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text">
                                                                <i class='bx bx-check-square'></i>
                                                                <span class="badge text-bg-warning">${c.status}</span>
                                                            </p>

                                                        </div>
                                                    </div>
                                                    <a href="HotelHost_BookingHistoryBillDetailServlet?reservationID=${c.reservationID}" class="btn btn-outline-primary mt-auto">
                                                        <i class='bx bx-detail'></i> View Details
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                            <c:if test="${requestScope.checked_Cancel ==  true}">
                                <h3>Status Bill: Cancel (<%= countCancelBills %> bills)</h3>
                                <% if (countCancelBills == 0) { %>
                                <div id="noHistory" class="alert alert-danger text-center">
                                    You don't have any cancel bills.
                                </div>
                                <% } %>
                                <c:forEach items="${requestScope.listBillByHotel_ID}" var="c">
                                    <c:if test="${c.status == 'CANCEL'}">
                                        <div class="col-md-3 mb-4 hotel-card">
                                            <div class="card h-100 border rounded shadow">
                                                <div class="card-body d-flex flex-column custom-bg">
                                                    <div class="row mb-2">
                                                        <div class="col">
                                                            <p class="card-text">
                                                                Reservation: ${c.reservationID}
                                                            </p>
                                                        </div>
                                                    </div>

                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-check'></i> ${c.checkInDate}
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-calendar-x'></i> ${c.checkOutDate}
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <div class="row">
                                                        <div class="col">
                                                            <p class="card-text mb-2">
                                                                <i class='bx bx-dollar-circle'></i> <span class="formatted-price">${c.total_price}</span> VND
                                                            </p>
                                                        </div>
                                                        <div class="col">
                                                            <p class="card-text">
                                                                <i class='bx bx-check-square'></i>
                                                                <span class="badge text-bg-danger">${c.status}</span>
                                                            </p>
                                                        </div>
                                                    </div>
                                                    <a href="HotelHost_BookingHistoryBillDetailServlet?reservationID=${c.reservationID}" class="btn btn-outline-primary mt-auto">
                                                        <i class='bx bx-detail'></i> View Details
                                                    </a>
                                                </div>
                                            </div>
                                        </div>
                                    </c:if>
                                </c:forEach>
                            </c:if>

                        </div>
                    </div>
                </div>
            </div>

        </div>
        <%@include file="Footer.jsp" %>
        <script>
            // Lấy tất cả các phần tử có class là "formatted-price"
            var priceElements = document.getElementsByClassName('formatted-price');

            // Hàm định dạng tiền tệ
            function formatCurrency(number) {
                return number.toLocaleString('vi-VN');
            }

            // Duyệt qua tất cả các phần tử và định dạng lại giá trị
            for (var i = 0; i < priceElements.length; i++) {
                var rawPrice = priceElements[i].textContent;
                var totalPrice = parseInt(rawPrice, 10);
                priceElements[i].textContent = formatCurrency(totalPrice);
            }



            //startDate > endDate
            function validateDates() {
                const startDate = new Date(document.getElementsByName('startDate')[1].value);
                const endDate = new Date(document.getElementsByName('endDate')[1].value);

                if (startDate > endDate) {
                    Swal.fire({
                        icon: 'error',
                        title: 'Invalid Date Range',
                        text: 'Start Date cannot be greater than End Date.',
                    });
                    return false; // Prevent form submission
                }
                return true; // Allow form submission
            }


            //ModalUpdate
            document.addEventListener('DOMContentLoaded', () => {
                const cancelButtons = document.querySelectorAll('.cancel-button');
                const confirmCancelButton = document.getElementById('confirmCancelButton');
                let formToSubmit;

                cancelButtons.forEach(button => {
                    button.addEventListener('click', () => {
                        formToSubmit = button.closest('form');
                        $('#confirmCancelModal').modal('show');
                    });
                });

                confirmCancelButton.addEventListener('click', () => {
                    formToSubmit.submit();
                });
            });
        </script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.all.min.js"></script>

        <!-- Bootstrap JS and dependencies -->
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.5.2/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </body>
</html>
