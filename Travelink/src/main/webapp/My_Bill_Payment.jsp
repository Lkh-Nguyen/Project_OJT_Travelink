<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="com.travelink.Model.Account" %>
<%@page import="java.util.List" %>
<%@page import="com.travelink.Model.HotelService" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking Account</title>
        <link rel="stylesheet" href="css/Right_My_Account.css">
        <link rel="stylesheet" href="css/Left_My_Account.css">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="css/Alter.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href='bootstrap_css/bootstrap.min.css' rel='stylesheet'>
        <style>
            .custom-bg {
                background-color: #fafafa; /* Màu xám rất nhạt */
                background-image: linear-gradient(to right, #fafafa, #f5f5f5); /* Gradient từ #fafafa đến #f5f5f5 */
                border-radius: 5px;
            }
            .card-body .first p {
                width: 350px;
            }
            .custom-border {
                border: 2px dashed #000; /* Đường viền nét đứt màu đen */
                border-radius: 15px; /* Bo góc */
                padding: 15px; /* Khoảng cách bên trong */
                margin-bottom: 15px; /* Khoảng cách bên ngoài dưới */
            }
            .gradient-background {
                background-image: linear-gradient(to right ,#5EBABA, #3A7BD5);
                border-radius: 5px;
            }
            .gradient-background:hover{
                color: white;
                transition: 0.6s ease;
            }
            .contain-price {
                border-left: 0.5px solid lightslategrey;
            }
            .mini-info:hover {
                z-index: 2;
                transform: translateY(-5%);
                transition: transform 0.6s ease-in-out;
            }
            .mini-info {
                z-index: 2;
                cursor: pointer;
                transition: transform 0.8s ease-in-out;
            }
            .card-body .row {
                margin-bottom: 10px;
            }

            .card-body .row .col-8 {
                text-align: left;
            }

            .card-body .row .col-4 {
                text-align: left;
            }
            .first {
                width: 400px;
            }
            /* Giảm khoảng cách giữa các hàng */
            .info-row {
                padding-bottom: 5px; /* Thay đổi khoảng cách giữa các hàng */
            }

            /* Loại bỏ padding mặc định */
            .info-row .col-4,
            .info-row .col-8 {
                margin-top: 0;
                margin-bottom: 0;
            }
            #confirmBtn, #cancelBtn {
                color: black;
                background-image: linear-gradient(to right,#5EBABA, #3A7BD5);
            }
            #confirmBtn:hover,
            #cancelBtn:hover {
                color: whitesmoke;
                transition: 0.5s ease;
                transform: translateY(-5px);
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <br>
        <div class="container">
            <div class="row mt-5 justify-content-center align-items-center">
                <div class="col-md-6">
                    <div class="card mb-5 border-0 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
                        <div class="container p-3">
                            <div id="carouselExampleIndicators" class="carousel slide" >
                                <div class="carousel-indicators">
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="0" class="active" aria-current="true" aria-label="Slide 1"></button>
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="1" aria-label="Slide 2"></button>
                                    <button type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide-to="2" aria-label="Slide 3"></button>
                                </div>
                                <div class="carousel-inner">
                                    <div class="carousel-item active">
                                        <img src="img_Home/best -2.jpg" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="img_Home/best -1.jpg" class="d-block w-100" alt="...">
                                    </div>
                                    <div class="carousel-item">
                                        <img src="img_Home/best -3.jpg" class="d-block w-100" alt="...">
                                    </div>
                                </div>
                                <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="prev">
                                    <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Previous</span>
                                </button>
                                <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleIndicators" data-bs-slide="next">
                                    <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                    <span class="visually-hidden">Next</span>
                                </button>
                            </div>
                        </div>
                        <div class="card-body mini-info">
                            <c:forEach var="bill" items="${requestScope.list_bill}" varStatus="status">
                                <c:if test="${status.index == 0}">
                                    <div class="container gradient-background fs-6">
                                        <div class="row p-lg-2 justify-content-center align-items-center text-center">
                                            <p class="fw-bold">Hotel Brand</p>  
                                            <p>${bill.hotel_Name}</p>
                                        </div>
                                        <div class="row p-lg-2 justify-content-center align-items-center text-center">
                                            <div class="col-md-4">
                                                <p class="fw-bold">Check In Date</p>
                                                <p>${bill.checkInDate}</p>
                                            </div>
                                            <div class="col-md-4">
                                                <p class="fw-bold">Customer</p>
                                                <p>${account.name}</p>
                                            </div>
                                            <div class="col-md-4">
                                                <p class="fw-bold">Check Out Date</p>
                                                <p>${bill.checkOutDate}</p>
                                            </div>
                                        </div>
                                    </div>
                                </c:if>
                            </c:forEach>

                        </div>
                    </div>
                </div>
                <div class="col-md-6">
                    <div class="card mb-5 h-1000 ">
                        <div class="card-body ">
                            <div class="row gradient-background m-1" style="height: 5px">
                            </div>
                            <div class="row">
                                <div class="col-md-5">
                                    <div class="card border-0">
                                        <div class="card-body first">
                                            <h2 class="fw-bold">TRAVELINK</h2>
                                            <p class="text-muted border-bottom">BILLING TO COMPANY</p>

                                            <div class="row info-row">
                                                <div class="col-4 fw-bold lh-1 ">Name:</div>
                                                <div class="col-8 fw-normal text-muted lh-1 px-0 ">Travelink</div>
                                            </div>
                                            <div class="row info-row">
                                                <div class="col-4 fw-bold lh-1">Contact:</div>
                                                <div class="col-8 fw-normal text-muted lh-1 px-0">+123456789</div>
                                            </div>
                                            <div class="row info-row">
                                                <div class="col-4 fw-bold lh-1">Email:</div>
                                                <div class="col-8 fw-normal text-muted lh-1 px-0">group1swp391@fpt.edu.vn</div>
                                            </div>
                                            <div class="row info-row">
                                                <div class="col-4 fw-bold lh-1">Address:</div>
                                                <div class="col-8 fw-normal text-muted lh-1 px-0">Khu đô thị FPT, TP Đà Nẵng</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-md-3">
                                    <!-- Container trống -->
                                </div>
                                <div class="col-md-4 justify-content-end align-items-end">
                                    <div class="card border-0">
                                        <div class="card-body" style="margin-top: 20px;
                                             padding: 0;">
                                            <p class="fw-bold lh-1">BOOKING BILL</p>
                                            <p class="fw-bold lh-1">Date: ${requestScope.list_bill[0].reservationDate}</p>
                                        </div>
                                    </div>
                                </div>
                            </div> 
                            <div class="row">
                                <div class="container">
                                    <div class="card border-0">
                                        <div class="card-body justify-content-center align-items-center text-center">
                                            <table class="table table-striped table-hover text-center">
                                                <thead>
                                                    <tr>
                                                        <th scope="col" class="col-1">No.</th>
                                                        <th scope="col" class="col-4">Room Name</th>
                                                        <th scope="col" class="col-3">Quantity</th>
                                                        <th scope="col" class="col-4">Price</th>
                                                    </tr>
                                                </thead>
                                                <tbody>
                                                    <c:forEach var="bill" items="${requestScope.list_bill}">
                                                        <tr>
                                                            <th scope="row" class="col-1">1</th>
                                                            <td class="col-4">${bill.room_Name}</td>
                                                            <td class="col-3">${bill.amount}</td>
                                                            <td class="col-4 formatted-price">${bill.room_price}</td>
                                                        </tr>
                                                    </c:forEach>
                                                </tbody>
                                            </table>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="card border-0">
                                    <div class="card-body">
                                        <!-- Phần dưới bên phải -->
                                        <div class="row">
                                            <div class="col-md-6 ">
                                                <div class="card-body p-2">
                                                    <p class="fw-bold lh-1">Name: <span class="fw-normal text-muted lh-1">${account.name}</span></p>
                                                    <p class="fw-bold lh-1">Contact: <span class="fw-normal text-muted lh-1">${account.phoneNumber}</span></p>
                                                    <p class="fw-bold lh-1">Email: <span class="fw-normal text-muted lh-1">${account.email}</span></p>
                                                    <c:if test="${account.address != ''}">
                                                        <p class="fw-bold lh-1">Address: <span class="fw-normal text-muted lh-1">${account.address}</span></p>
                                                    </c:if>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mt-2">
                                                <div class="row border-bottom">
                                                    <div class="col">
                                                        <p class="fw-bold lh-1">Sub Total:</p>

                                                    </div>
                                                    <div class="col">
                                                        <c:forEach var="bill" items="${requestScope.list_bill}" varStatus="status">
                                                            <c:if test="${status.index == 0}">
                                                                <p class="text-muted fw-normal lh-1 ps-4 formatted-price">${bill.total_price}</p>

                                                            </c:if>
                                                        </c:forEach>
                                                    </div>
                                                </div>
                                                <c:forEach var="bill" items="${requestScope.list_bill}" varStatus="status">
                                                    <c:if test="${status.index == 0}">
                                                        <div class="row mt-2">
                                                            <div class="col">
                                                                <p class="fw-bold lh-1">Grand Total</p>
                                                            </div>
                                                            <div class="col">
                                                                <p class="text-muted fw-normal lh-1 ps-4 formatted-price">${bill.total_price}</p>
                                                            </div>
                                                        </div>
                                                    </c:if>
                                                </c:forEach>
                                            </div>
                                        </div>
                                        <!-- Phần dưới bên trái -->
                                        <div class="row">
                                            <div class="col-md-6 mt-2">
                                                <div class="card-body p-2">
                                                    <h5 class="fw-bold lh-1">Customer Signature</h5>
                                                    <br><br><br><br><br>
                                                </div>
                                            </div>
                                            <div class="col-md-6 mt-2">
                                                <div class="card-body p-2">
                                                    <div class="form-check">
                                                        <input class="form-check-input" onclick="hideShowButton(1)" type="radio" name="lang" id="exampleRadios1" value="option1" checked>
                                                        <label class="form-check-label" for="exampleRadios1">
                                                            Agree the Terms & Privacy
                                                        </label>
                                                    </div>
                                                    <div class="form-check">
                                                        <input class="form-check-input" onclick="hideShowButton(2)" type="radio" name="lang" id="exampleRadios2" value="option2">
                                                        <label class="form-check-label" for="exampleRadios2">
                                                            Decline the Terms & Privacy
                                                        </label>
                                                    </div>
                                                </div>
                                                <div class="row">
                                                    <div class="card-body p-2">
                                                        <div class="row p-2 justify-content-center align-items-center">
                                                            <div class="col-md-6">
                                                                <c:set var="cancelButtonDisplayed" value="false" />
                                                                <c:forEach var="bill" items="${requestScope.list_bill}" varStatus="status">
                                                                    <c:if test="${!cancelButtonDisplayed}">
                                                                        <div class="mb-2" id="btn-cancel">
                                                                            <c:choose>
                                                                                <c:when test="${bill.status != 'Paid'}">
                                                                                    <a class="btn btn-primary w-100 disabled" id="cancelBtn" aria-disabled="true">Cancel</a>
                                                                                </c:when>
                                                                                <c:otherwise>
                                                                                    <a class="btn btn-primary w-100 btnCancel" id="cancelBtn">Cancel</a>
                                                                                    <div class="toast-container position-fixed bottom-0 end-0 p-3">
                                                                                        <!-- Toast -->
                                                                                        <div id="liveToast" class="toast" role="alert" aria-live="assertive" aria-atomic="true">
                                                                                            <div class="toast-header">
                                                                                                <img src="${account.avatarURL}" class="img-fluid rounded me-2" alt="..." style="height: 30px; width: 30px">
                                                                                                <strong class="me-auto">${account.name}</strong>
                                                                                                <small id="toastTime" class="text-muted"></small>
                                                                                                <button type="button" class="btn-close" data-bs-dismiss="toast" aria-label="Close"></button>
                                                                                            </div>
                                                                                            <div class="toast-body">
                                                                                                ${account.name} has requested to cancel their bill!
                                                                                            </div>
                                                                                        </div>
                                                                                    </div>
                                                                                </c:otherwise>
                                                                            </c:choose>
                                                                        </div>
                                                                        <c:set var="cancelButtonDisplayed" value="true" />
                                                                    </c:if>
                                                                </c:forEach>
                                                            </div>

                                                            <div class="col-md-6">
                                                                <div class="mb-2" id="btn-confirm">
                                                                    <a href="ExportBillServlet" class="btn btn-primary w-100 btnCancel text-light" id="cancelBtn">Download</a>
                                                                </div>
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="row gradient-background" style="height: 20px">
                                        </div>
                                    </div>
                                </div> 
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="bootstrap_js/js/bootstrap.min.js"></script>
        <script src="bootstrap_js/js/bootstrap.bundle.min.js"></script>
        <script>
                                                            // Lấy tất cả các phần tử có class là 'formatted-price'
                                                            var elements = document.getElementsByClassName('formatted-price');

                                                            // Lặp qua từng phần tử để định dạng giá trị
                                                            for (var i = 0; i < elements.length; i++) {
                                                                var roomPrice = parseInt(elements[i].innerText.trim()); // Chuyển đổi sang số nguyên
                                                                var formattedPrice = (roomPrice / 1000).toFixed(3).replace('.', '.') + ' VNĐ';
                                                                elements[i].innerText = formattedPrice; // Đưa vào HTML
                                                            }
                                                            //Display Button Cancel and Confirm
                                                            function hideShowButton(val) {
                                                                if (val == 1) {
                                                                    document.getElementById('btn-confirm').style.display = 'block';
                                                                }
                                                                if (val == 2) {
                                                                    document.getElementById('btn-confirm').style.display = 'none';
                                                                }
                                                            }
                                                            ;

                                                            //Display Toast
                                                            const toastTrigger = document.getElementById('confirmBtn')
                                                            const toastLiveExample = document.getElementById('liveToast')

                                                            if (toastTrigger) {
                                                                const toastBootstrap = bootstrap.Toast.getOrCreateInstance(toastLiveExample)
                                                                toastTrigger.addEventListener('click', () => {
                                                                    toastBootstrap.show()
                                                                })
                                                            }
                                                            ;

                                                            
        </script>
    </body>
</html>