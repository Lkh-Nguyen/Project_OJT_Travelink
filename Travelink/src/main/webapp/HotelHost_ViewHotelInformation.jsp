<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Hotel Information</title>
    <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
    <style>
        body {
            padding-top: 20px;
        }
        .card-title {
            font-size: 1.5rem;
            margin-bottom: 20px;
        }
        .form-group label {
            font-weight: bold;
        }
        .form-control[readonly] {
            background-color: #e9ecef;
            cursor: not-allowed;
        }
        .form-control.textarea-non-resizable {
            resize: none;
        }
        .facility-item {
            display: flex;
            align-items: center;
        }
        .facility-item i {
            margin-right: 10px;
        }
    </style>
</head>
<body>
    <%@ include file="Header_HotelHost.jsp" %>
    <div class="container-fluid">
        <a href="UpdateHotelInformationServlet" class="btn btn-outline-primary mt-2"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
        <h1 class="my-4">Hotel Information</h1>
        <div class="card">
            <div class="card-header">
                <h5 class="card-title">Hotel Details</h5>
            </div>
            <div class="card-body">
                <form>
                    <div class="row">
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="hotel_ID">Hotel ID:</label>
                                <input class="form-control" type="text" id="hotel_ID" name="hotel_ID" value="${hotel.hotel_ID}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="name">Name:</label>
                                <input class="form-control" type="text" id="name" name="name" value="${hotel.name}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="email">Email:</label>
                                <input class="form-control" type="text" id="email" name="email" value="${hotel.email}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="star">Star:</label>
                                <input class="form-control" type="text" id="star" name="star" value="${hotel.star}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="rating">Rating:</label>
                                <input class="form-control" type="text" id="rating" name="rating" value="${hotel.rating}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="phoneNumber">Phone Number:</label>
                                <input class="form-control" type="text" id="phoneNumber" name="phoneNumber" value="${hotel.phoneNumber}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="address">Address:</label>
                                <input class="form-control" type="text" id="address" name="address" value="${hotel.address}" readonly>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="form-group">
                                <label for="description">Description:</label>
                                <textarea class="form-control textarea-non-resizable" id="description" name="description" rows="3" readonly>${hotel.description}</textarea>
                            </div>
                            <div class="form-group">
                                <label for="checkInTimeStart">Check-In Time Start:</label>
                                <input class="form-control" type="text" id="checkInTimeStart" name="checkInTimeStart" value="${hotel.checkInTimeStart}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="checkInTimeEnd">Check-In Time End:</label>
                                <input class="form-control" type="text" id="checkInTimeEnd" name="checkInTimeEnd" value="${hotel.checkInTimeEnd}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="checkOutTimeStart">Check-Out Time Start:</label>
                                <input class="form-control" type="text" id="checkOutTimeStart" name="checkOutTimeStart" value="${hotel.checkOutTimeStart}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="checkOutTimeEnd">Check-Out Time End:</label>
                                <input class="form-control" type="text" id="checkOutTimeEnd" name="checkOutTimeEnd" value="${hotel.checkOutTimeEnd}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="status">Status:</label>
                                <input class="form-control" type="text" id="status" name="status" value="${hotel.status}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="ward_ID">Ward:</label>
                                <input class="form-control" type="text" id="ward_ID" name="ward_ID" value="${ward.name}" readonly>
                            </div>
                            <div class="form-group">
                                <label for="facilities">Facilities:</label>
                                <ul class="list-group">
                                    <c:forEach var="facility" items="${facilityList}">
                                        <li class="list-group-item facility-item">
                                            <i class="fas fa-check"></i> ${facility.name}
                                        </li>
                                    </c:forEach>
                                </ul>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
                            <%@ include file="Footer.jsp" %>
</body>
</html>
