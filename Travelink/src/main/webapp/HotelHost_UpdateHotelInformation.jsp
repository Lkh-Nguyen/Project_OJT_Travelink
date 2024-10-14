<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            #btn-update {
                width: 100px;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .no-resize {
                resize: none !important;
            }
            .input-group-text {
                width: 150px; /* Adjust the width as needed */
            }
            #navbarNav li a:hover {
                color: black;
                transition: 0.3s ease;
            }
            #navbarNav li a:active {
                color: #4646d2;
            }
            .navbar-nav {
                display: flex;
                justify-content: center;
                width: 100%;
            }
            .nav-item {
                margin: 0 10px;
            }
            .nav-link {
                padding: 10px 20px;
                transition: background-color 0.3s, color 0.3s;
                border-radius: 15px;
            }
            .nav-link:hover {
                background-color: #e2e6ea;
                color: #0056b3;
            }
            .nav-link.active {
                background-color: #007bff;
                color: white;
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container-fluid">
            <a href="UpdateHotelInformationServlet" class="btn btn-outline-primary mt-2"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height justify-content-center align-items-center" style="border-radius: 30px;">
                <nav class="navbar w-100 navbar-expand-lg navbar-light bg-light mb-4 nav-underline" style="border-radius: 30px;">
                    <div class="collapse navbar-collapse p-lg-2 p-2" id="navbarNav">
                        <ul class="navbar-nav fw-bold">
                            <li class="nav-item">
                                <a class="nav-link active text-light" aria-current="page" href="#">Update Hotel</a>
                            </li>
                        </ul>
                    </div>
                </nav>
                <div class="card w-100 h-100 shadow p-lg-5 mb-5 bg-body-tertiary border-0 equal-height" style="border-radius: 30px;">
                    <h4 class='mb-3 text-center'>HOTEL INFORMATION</h4>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="hotel-form" action="HotelHost_UpdateHotelServlet" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Hotel Name</span>
                                    <input type="text" class="form-control" aria-label="hotelname" aria-describedby="basic-addon1" value="${hotel.name}" name="name">
                                    <div class="col-md-4 d-flex justify-content-center align-items-center">
                                        <button type="submit" class="btn btn-primary btn-block" id="btn-update" style="margin-left:10px;">Update</button>
                                        <c:if test="${hotel != null}">
                                            <input type="hidden" name="hotelID" value="${hotel.hotel_ID}">
                                            <input type="hidden" name="phone" value="${hotel.phoneNumber}">
                                            <input type="hidden" name="description" value="${hotel.description}">
                                            <input type="hidden" name="address" value="${hotel.address}">
                                            <input type="hidden" name="email" value="${hotel.email}">
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="hotel-form" action="HotelHost_UpdateHotelServlet" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Email</span>
                                    <input type="email" class="form-control" placeholder="Email..." aria-label="email" aria-describedby="basic-addon1" value="${hotel.email}" name="email">
                                    <div class="col-md-4 d-flex justify-content-center align-items-center">
                                        <button type="submit" class="btn btn-primary btn-block" id="btn-update" style="margin-left:10px;">Update</button>
                                        <c:if test="${hotel != null}">
                                            <input type="hidden" name="hotelID" value="${hotel.hotel_ID}">
                                            <input type="hidden" name="phone" value="${hotel.phoneNumber}">
                                            <input type="hidden" name="description" value="${hotel.description}">
                                            <input type="hidden" name="address" value="${hotel.address}">
                                            <input type="hidden" name="name" value="${hotel.name}">
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="hotel-form" action="HotelHost_UpdateHotelServlet" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Phone Number</span>
                                    <input type="text" class="form-control" placeholder="Phone..." aria-label="phone" aria-describedby="basic-addon1"value="${hotel.phoneNumber}" name="phone">
                                    <div class="col-md-4 d-flex justify-content-center align-items-center">
                                        <button type="submit" class="btn btn-primary btn-block" id="btn-update" style="margin-left:10px;">Update</button>
                                        <c:if test="${hotel != null}">
                                            <input type="hidden" name="hotelID" value="${hotel.hotel_ID}">
                                            <input type="hidden" name="name" value="${hotel.name}">
                                            <input type="hidden" name="description" value="${hotel.description}">
                                            <input type="hidden" name="address" value="${hotel.address}">
                                            <input type="hidden" name="email" value="${hotel.email}">
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="hotel-form" action="HotelHost_UpdateHotelServlet" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Description</span>
                                    <textarea name="description"class="form-control no-resize" aria-label="description">${hotel.description}</textarea>
                                    <div class="col-md-4 d-flex justify-content-center align-items-center">
                                        <button type="submit" class="btn btn-primary btn-block" id="btn-update" style="margin-left:10px;">Update</button>
                                        <c:if test="${hotel != null}">
                                            <input type="hidden" name="hotelID" value="${hotel.hotel_ID}">
                                            <input type="hidden" name="phone" value="${hotel.phoneNumber}">
                                            <input type="hidden" name="name" value="${hotel.name}">
                                            <input type="hidden" name="address" value="${hotel.address}">
                                            <input type="hidden" name="email" value="${hotel.email}">
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-md-12">
                            <form id="hotel-form" action="HotelHost_UpdateHotelServlet" method="post">
                                <div class="input-group mb-3">
                                    <span class="input-group-text">Address</span>
                                    <textarea name="address"class="form-control no-resize" aria-label="address">${hotel.address}</textarea>
                                    <div class="col-md-4 d-flex justify-content-center align-items-center">
                                        <button type="submit" class="btn btn-primary btn-block" id="btn-update" style="margin-left:10px;">Update</button>
                                        <c:if test="${hotel != null}">
                                            <input type="hidden" name="hotelID" value="${hotel.hotel_ID}">
                                            <input type="hidden" name="phone" value="${hotel.phoneNumber}">
                                            <input type="hidden" name="description" value="${hotel.description}">
                                            <input type="hidden" name="name" value="${hotel.name}">
                                            <input type="hidden" name="email" value="${hotel.email}">
                                        </c:if>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>

                    <div class="row mt-4 justify-content-center align-items-center">
                        <div class="col-md-4 justify-content-center align-items-center"></div>
                        <div class="col-md-4 d-flex justify-content-center align-items-center">
                           
                        </div>
                        <div class="col-md-4 justify-content-center align-items-center"></div>
                    </div>
                </div>

            </div>
        </div>
    </div>
    <%@include file="Footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="https://maxcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    <script>
        document.getElementById('btn-cancel').addEventListener('click', function () {
            window.history.back(); // Go back to the previous page
        });

        document.getElementById('hotel-form').addEventListener('submit', function (event) {
            const imageUrlsInput = document.getElementById('image-urls');
            const rawUrls = imageUrlsInput.value.trim(); // L?y giá tr? t? textarea và lo?i b? kho?ng tr?ng th?a
            const imageUrls = rawUrls.split(/\s*,\s*/); // Chia các URL b?ng d?u ph?y và lo?i b? kho?ng tr?ng
            if (imageUrls.length < 9) {
                alert('You must add at least 9 image URLs.');
                event.preventDefault(); // Ng?n ch?n g?i bi?u m?u n?u không ?? URL ?nh
            }
        });

        // Fetch image URLs from the server and populate the input field
        document.addEventListener('DOMContentLoaded', function () {
            fetch('/ImageURLServlet')
                    .then(response => response.json())
                    .then(data => {
                        document.getElementById('image-urls').value = data.join(', ');
                    })
                    .catch(error => console.error('Error fetching image URLs:', error));
        });
    </script>
</body>
</html>
