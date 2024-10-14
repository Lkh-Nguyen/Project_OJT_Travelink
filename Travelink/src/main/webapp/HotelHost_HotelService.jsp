<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Dash Board</title>
    <link rel="icon" href="img_Home/logo.png">
    <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
    <link rel="stylesheet" href="css/HotelHost_HotelService.css">
    <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    <style>
        @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
        * {
            font-family: Montserrat, sans-serif;
        }
        .row-compact {
            margin-bottom: 1rem; /* Adjust as needed */
        }
        #pieChart, #myChart {
            width: 100% !important;
            height: 100% !important;
        }
        .equal-height {
            display: flex;
            flex-direction: column;
            justify-content: space-between;
        }
        .card-flex {
            display: flex;
            flex: 1 1 auto;
            flex-direction: column;
        }
    </style>
</head>
<body>
<%@include file="Header_HotelHost.jsp" %>
<div class="container-fluid">
    <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2">
        <img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
    </a>
    <div class="row p-4 d-flex align-items-center row-compact">
        <div class="col-md-2">
            <div class="card">
                <div class="card-header fw-bolder">
                    Hotel Service
                </div>
                <div class="card-body">
                    <c:forEach items="${sessionScope.allServices}" var="service">
                        <div class="form-check">
                            <input class="form-check-input service-filter" type="checkbox" value="${service.serviceID}" id="service-${service.serviceID}">
                            <label class="form-check-label" for="service-${service.serviceID}">
                                ${service.name}
                            </label>
                        </div>
                    </c:forEach>
                </div>
            </div>
<!--            <div class="card mt-4">
                <button type="button" class="btn btn-outline-primary position-relative" data-mdb-ripple-init>
                    <img src="img_Hotel/search.svg" alt="Search Icon" class="me-2" style="width: 1rem;">
                    Search
                </button>
            </div>-->
        </div>
        <div class="col-md-10">
            <div class="card">
                <h5 class="card-header">
                    <div class="d-flex align-items-center">
                        <img src="img_Hotel/list.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                        <h5 class="card-title mb-0">List Hotel Service</h5>
                    </div>
                </h5>
                <c:if test="${requestScope.hotels == null || requestScope.hotels.isEmpty()}">
                    <div class="alert alert-danger mt-4">You don't own any hotels.</div>
                </c:if>

                <c:if test="${requestScope.hotels != null}">
                    <div class="card-body">
                        <table class="table-hover table align-items-center justify-content-center">
                            <thead>
                                <tr class="text-center">
                                    <th scope="col">No.</th>
                                    <th scope="col">HOTEL NAME</th>
                                    <th scope="col">SERVICE</th>
                                    <th scope="col">PRICE</th>
                                    <th scope="col">LOCATION</th>
                                    <th scope="col">HOTEL INFORMATION</th>
                                    <th scope="col">ACTION</th>
                                </tr>
                            </thead>
                            <tbody class="table-group-divider text-center">
                                <c:forEach items="${requestScope.hotels}" var="hotel" varStatus="status">
                                    <tr id="hotel-${hotel.hotel_ID}" class="hotel-row">
                                        <th scope="row">${status.index +1}</th>
                                        <td>${hotel.name}</td>
                                        <td class="hotel-services">
                                            <div class="align-item-center">
                                                <c:forEach items="${hotel.getService()}" var="service">
                                                    <div class="d-flex justify-content-end align-items-center" data-service-id="${service.serviceID}">
                                                        <img src="img_Hotel/breakfast.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                                        <p class="card-title mb-0">${service.name}</p>
                                                        <button class="btn btn-danger delete-btn d-none p-0 mx-2" data-hotel-id="${hotel.hotel_ID}" data-service-id="${service.serviceID}" style="height:fit-content">
                                                            <img src="img_Hotel/delete.svg" alt="Delete" style="width: 1rem; height: 1rem; margin-right: 5px;">
                                                        </button>
                                                    </div>
                                                </c:forEach>
                                            </div>
                                        </td>
                                        <td class="price">
                                            <c:set var="hotelServices" value="${hotel.getService()}" scope="session" />
                                            <c:forEach items="${hotel.getService()}" var="service">
                                                <div id="service-${service.serviceID}">${service.getPrice(hotel.hotel_ID)}</div>
                                            </c:forEach>
                                        </td>
                                        <td>${hotel.getLocation()} - <a href="#"><img src="img_Hotel/map.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2"></a></td>
                                        <td><a href="HotelHost_ViewHotelInformationServlet?hotelID=${hotel.hotel_ID}" class="btn btn-outline-primary"><img src="img_Hotel/view.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">View</a></td>
                                        <td>
                                            <button class="btn btn-success me-4 update-btn" data-id="${hotel.hotel_ID}">
                                                <img src="img_Hotel/update.svg" alt="Update" style="width: 1rem; height: 1rem; margin-right: 5px;"> Update
                                            </button>
                                            <button class="btn btn-success save-btn d-none" data-id="${hotel.hotel_ID}">
                                                <img src="img_Hotel/create.svg" alt="Save" style="width: 1rem; height: 1rem; margin-right: 5px;"> Save
                                            </button>
                                            <button class="btn btn-secondary me-4 my-3 cancel-btn d-none" data-id="${hotel.hotel_ID}">
                                                <img src="img_Hotel/cancel_invoice.svg" alt="Cancel" style="width: 1rem; height: 1rem; margin-right: 5px;"> Cancel
                                            </button>
                                            <a class="btn btn-danger" href="AddService.jsp?hotelId=${hotel.hotel_ID}">
                                                <img src="img_Hotel/create.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                                Add
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </c:if>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script src="bootstrap_js/js/bootstrap.bundle.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="bootstrap_js/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                //Handle update
                $('.update-btn').click(function () {
                    var hotelID = $(this).data('id');
                    var hotelDiv = $('#hotel-' + hotelID);
                    var prices = hotelDiv.find('.price div').map(function () {
                        return $(this).text().trim();
                    }).get();

                    hotelDiv.find('.price div').each(function (index) {
                        var currentPrice = prices[index];
                        $(this).html('<input type="text" value="' + currentPrice + '">');
                    });

                    $(this).addClass('d-none');
                    hotelDiv.find('.save-btn, .cancel-btn').removeClass('d-none');
                    hotelDiv.find('.delete-btn').removeClass('d-none');
                });
                //Handle Delete
                $('.delete-btn').click(function () {
                    var serviceID = $(this).data('service-id');
                    var hotelID = $(this).data('hotel-id');
                    var confirmation = confirm("Are you sure you want to delete this service?");
                    if (confirmation) {
                        $.post('DeleteServiceServlet', {
                            hotelID: hotelID,
                            serviceID: serviceID
                        }, function (res) {
                            alert("Service deleted successfully.");
                            location.reload();
                        }).fail(function () {
                            alert("Failed to delete the service.");
                        });
                    }
                });
                //Handle Save
                $('.save-btn').click(function () {
                    var hotelID = $(this).data('id');
                    var hotelDiv = $('#hotel-' + hotelID);

                    hotelDiv.find('.price div').each(function () {
                        var serviceID = $(this).attr('id').split('-')[1];
                        var price = $(this).find('input').val().trim();

                        $.post('UpdateServiceServlet', {
                            hotelId: hotelID,
                            price: price,
                            serviceId: serviceID
                        }, function (res) {
                            location.reload();
                        }).fail(function () {
                            alert("Failed to update the service price.");
                        });
                    });
                });
                //Handle Cancel
                $('.cancel-btn').click(function () {
                    location.reload();
                });

                // Filter hotels based on selected services
                $('.service-filter').change(function () {
                    var selectedServices = $('.service-filter:checked').map(function () {
                        return $(this).val();
                    }).get();

                    $('.hotel-row').each(function () {
                        var hotelServices = $(this).find('.hotel-services div').map(function () {
                            return $(this).data('service-id');
                        }).get();

                        var match = selectedServices.every(function (service) {
                            return hotelServices.includes(parseInt(service));
                        });

                        if (match || selectedServices.length === 0) {
                            $(this).show();
                        } else {
                            $(this).hide();
                        }
                    });
                });
            });
        </script>
    </body>
</html>
