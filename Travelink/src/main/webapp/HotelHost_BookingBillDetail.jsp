<%-- 
    Document   : HotelHost_BookingRevenue
    Created on : Jun 15, 2024, 11:27:08 PM
    Author     : HELLO
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_BookingBillDetail.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>

        <%@include file="Header_HotelHost.jsp" %>
        <div class="col-md-1" style="margin-left: 50px">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2">
                <img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
            </a>
        </div>
        <div class="container">
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

                                    <div class="row info-row mb-2">
                                        <div class="col-3 fw-bold lh-1 ">Name:</div>
                                        <div class="col-8 fw-normal text-muted lh-1 px-0 ">Travelink</div>
                                    </div>
                                    <div class="row info-row mb-2">
                                        <div class="col-3 fw-bold lh-1">Contact:</div>
                                        <div class="col-8 fw-normal text-muted lh-1 px-0">+123456789</div>
                                    </div>
                                    <div class="row info-row mb-2">
                                        <div class="col-3 fw-bold lh-1">Email:</div>
                                        <div class="col-8 fw-normal text-muted lh-1 px-0">group1swp391@fpt.edu.vn</div>
                                    </div>
                                    <div class="row info-row mb-2">
                                        <div class="col-3 fw-bold lh-1">Address:</div>
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
                                    <p class="fw-bold lh-1">Reservation ID:<span class="fw-normal text-muted lh-1"> ${requestScope.list_bill.get(0).reservationID}</span>
                                    <p class="fw-bold lh-1">Date Booking: 
                                        <span class="fw-normal text-muted lh-1">
                                            ${requestScope.list_bill.get(0).reservationDate}
                                        </span>
                                    </p>
                                    <p class="fw-bold lh-1">
                                        Status: 
                                        <c:if test="${requestScope.list_bill.get(0).status == 'FEEDBACKED'}">
                                            <span class="badge text-bg-info">FEEDBACKED</span>
                                        </c:if>
                                        <c:if test="${requestScope.list_bill.get(0).status == 'CANCEL'}">
                                            <span class="badge text-bg-danger">CANCEL</span>
                                        </c:if>
                                        <c:if test="${requestScope.list_bill.get(0).status == 'PAID'}">
                                            <span class="badge text-bg-success">PAID</span>
                                        </c:if>
                                        <c:if test="${requestScope.list_bill.get(0).status == 'FINISHED'}">
                                            <span class="badge text-bg-primary">FINISHED</span>
                                        </c:if>
                                        <c:if test="${requestScope.list_bill.get(0).status == 'PROCESSING'}">
                                            <span class="badge text-bg-warning">PROCESSING</span>
                                        </c:if>
                                        <c:if test="${requestScope.list_bill.get(0).status == 'REFUNDING'}">
                                            <span class="badge text-bg-warning">REFUNDING</span>
                                        </c:if>
                                    </p>
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
                                                <th scope="col" class="col-1">NO</th>
                                                <th scope="col" class="col-3">Room Name</th>
                                                <th scope="col" class="col-1">Quantity</th>
                                                <th scope="col" class="col-3">Price</th>
                                                <th scope="col" class="col-2">Check In</th>
                                                <th scope="col" class="col-2">Check Out</th>
                                            </tr>
                                        </thead>
                                        <tbody>

                                            <c:forEach items="${requestScope.list_bill}" var="c" varStatus="status">
                                                <tr>
                                                    <th scope="row" class="col-1">${status.index + 1} </th>
                                                    <td class="col-3">${c.room_Name}</td>
                                                    <td class="col-1">${c.amount}</td>
                                                    <td class="col-3"><span class="formatted-price">${requestScope.list_bill.get(0).room_price}</span> VND</td>
                                                    <td class="col-2">${requestScope.list_bill.get(0).checkInDate}</td>
                                                    <td class="col-2">${requestScope.list_bill.get(0).checkOutDate}</td>
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
                                        <h3 class="fw-bold">Information Customer:</h3>
                                        <div class="card-body p-2">

                                            <div class="row info-row mb-2">
                                                <div class="col-4 fw-bold lh-1 ">Name Customer:</div>
                                                <div class="col-7 fw-normal text-muted lh-1 px-0 ">${requestScope.user.name}</div>
                                            </div>

                                            <div class="row info-row mb-2">
                                                <div class="col-4 fw-bold lh-1 ">PhoneNumber:</div>
                                                <div class="col-7 fw-normal text-muted lh-1 px-0 ">${requestScope.user.phoneNumber}</div>
                                            </div>
                                            <div class="row info-row mb-2">
                                                <div class="col-4 fw-bold lh-1">Email Customer:</div>
                                                <div class="col-7 fw-normal text-muted lh-1 px-0">${requestScope.user.email}</div>
                                            </div>
                                            <c:if test="${requestScope.user.address != null}">
                                                <div class="row info-row mb-2">
                                                    <div class="col-4 fw-bold lh-1">Address Customer:</div>
                                                    <div class="col-7 fw-normal text-muted lh-1 px-0">${requestScope.user.address}</div>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.user.cmnd != null}">
                                                <div class="row info-row mb-2">
                                                    <div class="col-4 fw-bold lh-1">Contact Customer:</div>
                                                    <div class="col-7 fw-normal text-muted lh-1 px-0">${requestScope.user.cmnd}</div>
                                                </div>
                                            </c:if>
                                        </div>
                                    </div>
                                    <div class="col-md-6 mt-2">
                                        <div class="row">
                                            <div class="col-md-5">
                                                <h3 class="fw-bold lh-1">Total Price:</h3>
                                            </div>
                                            <div class="col-md-6">
                                                <h3 class="text-muted fw-normal lh-1 ps-4" style="margin-left: -70px"><span class="formatted-price">${requestScope.list_bill.get(0).total_price}</span> VNĐ</h3>
                                            </div>

                                        </div>
                                    </div>
                                    <a href="ExportBillServlet" class="btn btn-secondary">Download PDF <i class='bx bxs-download'></i></a>
                                    <div class="row gradient-background" style="height: 20px">
                                    </div>
                                </div>
                            </div> 
                        </div>
                    </div>
                </div>
            </div>
            <%@include file="Footer.jsp" %>


            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
            <script src="bootstrap_css/bootstrap.min.js"></script>
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
            </script>
    </body>
</html>
