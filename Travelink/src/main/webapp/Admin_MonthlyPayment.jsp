<%-- 
    Document   : Admin_Hotel_Partner
    Created on : Jun 30, 2024, 10:39:46 AM
    Author     : DUYAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html" pageEncoding="UTF-8" %>
<%@ page import="java.time.LocalDateTime, java.time.format.DateTimeFormatter" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Travelink - Hotel Management</title>

        <!-- Custom fonts for this template-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="vendor/css/sb-admin-2.min.css" rel="stylesheet">

        <script>
            function formatDate(dateString) {
                const options = {year: 'numeric', month: 'long', day: 'numeric'};
                const date = new Date(dateString);
                return date.toLocaleDateString('en-US', options);
            }

            function filterHotels() {
                let input = document.getElementById('hotelSearch').value.toLowerCase();
                let items = document.querySelectorAll('#hotelList .list-group-item');

                items.forEach(item => {
                    let text = item.textContent.toLowerCase();
                    item.style.display = text.includes(input) ? 'block' : 'none';
                });

                // Show the hotel list if there are matching items, otherwise hide it
                const hotelList = document.getElementById('hotelList');
                hotelList.style.display = items.some(item => item.style.display === 'block') ? 'block' : 'none';
            }

            function selectHotel(id, name) {
                document.getElementById('selectedHotelID').value = id;
                document.getElementById('hotelSearch').value = name;
                document.getElementById('hotelList').style.display = 'none'; // Hide the list after selection
            }
        </script>
    </head>
    <body id="page-top">

        
        <!-- Page Wrapper -->
        <div id="wrapper">
            <%@include file="Admin_Sidebar.jsp" %>
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <%@include file="Admin_Header.jsp" %>
                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <h1 class="h3 mb-4 text-gray-800">Hotel Partners</h1>

                        <div class="row">
                            <div class="col-md-12">
                                <!-- Select for Month -->
                                <form action="AdminMonthlyPaymentServlet" method="get">
                                    <div class="form-group m-1">
                                        <label for="month" class="form-label">Month</label>
                                        <select id="month" name="month" class="form-control">
                                            <%
                                                int selectedMonth = request.getParameter("month") != null ? Integer.parseInt(request.getParameter("month")) : 0;
                                                String[] monthNames = {"All", "January", "February", "March", "April", "May", "June", 
                                                                       "July", "August", "September", "October", "November", "December"};

                                                for (int i = 0; i <= 12; i++) {
                                                    if (i == selectedMonth) {
                                                        out.println("<option value=\"" + i + "\" selected>" + monthNames[i] + "</option>");
                                                    } else {
                                                        out.println("<option value=\"" + i + "\">" + monthNames[i] + "</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>

                                    <!-- Select for Year -->
                                    <div class="form-group m-1">
                                        <label for="year" class="form-label">Year</label>
                                        <select id="year" name="year" class="form-control">
                                            <%
                                                int currentYear = java.util.Calendar.getInstance().get(java.util.Calendar.YEAR);
                                                int selectedYear = request.getParameter("year") != null ? Integer.parseInt(request.getParameter("year")) : currentYear;
                                                for (int year = 2000; year <= currentYear; year++) {
                                                    if (year == selectedYear) {
                                                        out.println("<option value=\"" + year + "\" selected>" + year + "</option>");
                                                    } else {
                                                        out.println("<option value=\"" + year + "\">" + year + "</option>");
                                                    }
                                                }
                                            %>
                                        </select>
                                    </div>

                                    <!-- Select for Hotel -->
                                    <div class="form-group m-1">
                                        <label for="hotelSearch">Search Hotel:</label>
                                        <input type="text" id="hotelSearch" class="form-control" placeholder="Type to search" onkeyup="filterHotels()" 
                                               value="${param.hotel_Name}">
                                        <!-- Danh sách khách sạn -->
                                        <ul id="hotelList" class="list-group mt-2" style="display: none;">
                                            <c:forEach var="hotel" items="${requestScope.hotels}">
                                                <li class="list-group-item" data-id="${hotel.hotel_ID}" onclick="selectHotel('${hotel.hotel_ID}', '${hotel.name}')">
                                                    ${hotel.name}
                                                </li>
                                            </c:forEach>
                                        </ul>
                                        <input type="hidden" id="selectedHotelID" name="hotel_ID" value="${hotel_id}">
                                        <!-- Nút gửi form -->
                                        <div class="d-flex justify-content-center mt-3">
                                            <button type="submit" class="btn btn-primary">Search</button>
                                        </div>
                                    </div>
                                </form>

                                <div class="form-group">
                                    <label for="HotelID">Hotel ID</label>
                                    <input type="text" class="form-control" id="newHotelID" name="hotel_ID" value="${requestScope.hotel_id}" readonly="">
                                </div>
                            </div>
                        </div>

                        <!-- Hotel Partners Table -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Hotel Partners</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th class="text-center">Payment ID</th>
                                                <th class="text-center">Month</th>
                                                <th class="text-center">Year</th>
                                                <th class="text-center">Amount</th>
                                                <th class="text-center">Status</th>
                                                <th class="text-center">Payment Time</th>
                                                <th class="text-center">Hotel ID</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center">
                                            <c:forEach var="payment" items="${requestScope.pay_lists}">
                                                <tr>
                                                    <td>${payment.monthlyPaymentId}</td>
                                                    <td>${payment.month}</td>
                                                    <td>${payment.year}</td>
                                                    <td>${payment.amount}</td>
                                                    <td>${payment.status}</td>
                                                    <td id="paymentTime_${payment.monthlyPaymentId}">${payment.paymentTime}</td>
                                                    <td>${payment.hotel_ID}</td>
                                                    <td>
                                                        <div class="d-flex justify-content-center">
                                                            <c:choose>
                                                                <c:when test="${payment.status eq 'Paid'}">
                                                                    <button class="btn btn-primary btn-sm" disabled >Pay</button>
                                                                </c:when>
                                                                <c:otherwise>
                                                                    <form action="AdminMonthlyPaymentServlet" method="post">
                                                                        <input type="hidden" name="paymentTime" value="${payment.paymentTime}"/>
                                                                        <input type="hidden" name="paymentYear" value="${payment.year}"/>
                                                                        <input type="hidden" name="paymentMonth" value="${payment.month}"/>
                                                                        <input type="hidden" name="paymentAmount" value="${payment.amount}"/>
                                                                        <input type="hidden" name="paymentStatus" value="${payment.status}"/>
                                                                        <input type="hidden" name="hotelID" value="${payment.hotel_ID}"/>
                                                                        <input type="hidden" name="paymentID" value="${payment.monthlyPaymentId}"/>
                                                                        <button class="btn btn-primary btn-sm" >Pay</button>
                                                                    </form>
                                                                </c:otherwise>
                                                            </c:choose>
                                                        </div>
                                                    </td>
                                                </tr>
                                            </c:forEach>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>

                    </div>
                    <!-- End of Page Content -->

                </div>
                <!-- End of Main Content -->

            </div>
            <!-- End of Content Wrapper -->

        </div>
        <!-- End of Page Wrapper -->
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="bootstrap_js/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="vendor/js/sb-admin-2.min.js"></script>
        <!-- Page level plugins -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>

        <!-- Page level custom scripts -->
        <script src="vendor/demo/datatables-demo.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <script>
                                                    document.addEventListener('DOMContentLoaded', function () {
                                                        const paymentTimes = document.querySelectorAll('[id^="paymentTime_"]');
                                                        paymentTimes.forEach(element => {
                                                            const formattedDate = formatDate(element.textContent);
                                                            element.textContent = formattedDate;
                                                        });
                                                    });

                                                    // Lưu giá trị hotelSearch vào localStorage khi gửi form
                                                    document.querySelector('form').addEventListener('submit', function () {
                                                        localStorage.setItem('hotelSearchValue', document.getElementById('hotelSearch').value);
                                                    });

                                                    // Khôi phục giá trị hotelSearch từ localStorage khi trang tải lại
                                                    window.addEventListener('load', function () {
                                                        const savedValue = localStorage.getItem('hotelSearchValue');
                                                        if (savedValue) {
                                                            document.getElementById('hotelSearch').value = savedValue;
                                                            localStorage.removeItem('hotelSearchValue'); // Remove the saved value if you don't need it anymore
                                                        }
                                                    });

                                                    // Show the hotel list when the input is focused
                                                    document.getElementById('hotelSearch').addEventListener('focus', function () {
                                                        document.getElementById('hotelList').style.display = 'block';
                                                    });

                                                    // Hide the hotel list when the input loses focus
                                                    document.getElementById('hotelSearch').addEventListener('blur', function () {
                                                        setTimeout(() => {
                                                            document.getElementById('hotelList').style.display = 'none';
                                                        }, 100); // Delay to allow click event to register
                                                    });

                                                    // Sort hotels
                                                    ocument.addEventListener('DOMContentLoaded', function () {
                                                        const hotelList = document.getElementById('hotelList');
                                                        const items = Array.from(hotelList.getElementsByClassName('list-group-item'));

                                                        // Sắp xếp các phần tử
                                                        items.sort((a, b) => {
                                                            const nameA = a.getAttribute('data-name').toLowerCase();
                                                            const nameB = b.getAttribute('data-name').toLowerCase();
                                                            return nameA.localeCompare(nameB);
                                                        });

                                                        // Thêm các phần tử đã sắp xếp vào danh sách
                                                        items.forEach(item => hotelList.appendChild(item));
                                                    });
        </script>
    </body>
</html>
