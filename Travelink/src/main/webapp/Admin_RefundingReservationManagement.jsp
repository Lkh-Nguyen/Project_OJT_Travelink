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
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

        <style>
            /* Optional: Add some custom styling */
            #searchForm {
                background-color: #f8f9fa; /* Light background color */
                padding: 20px; /* Add padding */
                border-radius: 8px; /* Rounded corners */
                box-shadow: 0 4px 8px rgba(0,0,0,0.1); /* Subtle shadow */
            }
            .form-group label {
                font-weight: bold; /* Make labels bold */
            }
            .btn {
                padding: 10px 20px; /* Adjust button padding */
                font-size: 16px; /* Increase button font size */
            }
        </style>
        <script>
            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const status = urlParams.get('status');
                const message = urlParams.get('message');
                if (status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: message,
                        confirmButtonText: 'OK'
                    });
                } else if (status === 'failure') {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Operation failed: ' + message,
                        confirmButtonText: 'OK'
                    });
                }
            };
            document.addEventListener('DOMContentLoaded', function () {
                // Định dạng ngày giờ
                function formatDate(dateString) {
                    const options = {year: 'numeric', month: '2-digit', day: '2-digit', hour: '2-digit', minute: '2-digit', second: '2-digit'};
                    const date = new Date(dateString);
                    return new Intl.DateTimeFormat('en-US', options).format(date);
                }

                // Định dạng các phần tử chứa ngày giờ
                const refundTimes = document.querySelectorAll('[data-type="refundTime"]');
                refundTimes.forEach(element => {
                    element.textContent = formatDate(element.textContent);
                });
            });
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
                                <form action="AdminRefundingReservationServlet" method="get" id="searchForm">
                                    <div class="form-row">
                                        <div class="form-group col-md-6 mb-2">
                                            <label for="startDate">Start Date</label>
                                            <input type="date" class="form-control" id="startDate" name="startDate">
                                        </div>
                                        <div class="form-group col-md-6 mb-2">
                                            <label for="endDate">End Date</label>
                                            <input type="date" class="form-control" id="endDate" name="endDate">
                                        </div>
                                    </div>
                                    <button type="submit" class="btn btn-primary btn-block">Search</button>
                                </form>
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
                                                <th class="text-center">Reservation ID</th>
                                                <th class="text-center">Account ID</th>
                                                <th class="text-center">Status</th>
                                                <th class="text-center">Refunding Time</th>
                                                <th class="text-center">Cancel Time</th>

                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody class="text-center">
                                            <c:forEach var="r" items="${requestScope.reservation_lists}">
                                                <tr>
                                                    <td>${r.reservationId}</td>
                                                    <td>${r.accountId}</td>
                                                    <td>${r.status}</td>
                                                    <td data-type="refundTime">${r.refundTime}</td>
                                                    <td data-type="refundTime">${r.cancelDate}</td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${r.status == 'PAID'}">
                                                                <button type="submit" class="btn btn-success btn-sm" disabled="">Paid</button>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <form method="post" action="AdminRefundingReservationServlet" >
                                                                    <input type="hidden" name="accountId" value="${r.accountId}">
                                                                    <input type="hidden" name="reservationId" value="${r.reservationId}">
                                                                    <button type="submit" class="btn btn-primary btn-sm">Pay</button>
                                                                </form>
                                                            </c:otherwise>
                                                        </c:choose>
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
        <%@include file="Footer.jsp" %>
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
        </script>
    </body>
</html>
