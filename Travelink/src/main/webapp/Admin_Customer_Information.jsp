<%-- 
    Document   : Admin_Customer_Information
    Created on : Jun 30, 2024, 11:02:49 AM
    Author     : DUYAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="java.util.*" %>
<%@page import="com.travelink.Model.*" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Travelink - Customer Management</title>

        <!-- Custom fonts for this template-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i" rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="vendor/css/sb-admin-2.min.css" rel="stylesheet">
        <style>
            .equal {
                flex: 1;
                flex-direction: column;
            }
        </style>
    </head>
    <body id="page-top">

        <!-- Page Wrapper -->
        <div id="wrapper">
            <!-- Sidebar -->
            <%@include file="Admin_Sidebar.jsp" %>
            <!-- End of Sidebar -->
            <!-- Content Wrapper -->
            <div id="content-wrapper" class="d-flex flex-column">
                <!-- Main Content -->
                <div id="content">
                    <!-- Topbar -->
                    <%@include file="Admin_Header.jsp" %>
                    <!-- End of Topbar -->
                    <!-- Begin Page Content -->
                    <div class="container-fluid">
                        <!-- Page Heading -->
                        <div class="d-sm-flex align-items-center justify-content-between mb-4">
                            <h1 class="h3 mb-0 text-gray-800">Customer Information</h1>
                        </div>

                        <div class="row d-flex">
                            <!-- Left Sidebar - Customer's Information -->
                            <div class="col-lg-4 mb-4 d-flex">
                                <div class="card shadow mb-4 equal">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Customer Information</h6>
                                    </div>
                                    <c:set var="account" value="${account}"/>
                                    <div class="card-body">
                                        <img class="img-fluid mb-3" src="${account.avatarURL}">
                                        <p><strong>Name:</strong> ${account.name}</p>
                                        <p><strong>Email:</strong> ${account.email}</p>
                                        <c:choose>
                                            <c:when test="${empty account.phoneNumber}">
                                                <p><strong>Phone:</strong> N/A</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p><strong>Phone:</strong> ${account.phoneNumber}</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${empty account.address}">
                                                <p><strong>Address:</strong> N/A</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p><strong>Address:</strong> ${account.address}</p>
                                            </c:otherwise>
                                        </c:choose>
                                        <c:choose>
                                            <c:when test="${empty account.dateOfBirth}">
                                                <p><strong>Date of birth:</strong> N/A</p>
                                            </c:when>
                                            <c:otherwise>
                                                <p><strong>Date of birth:</strong> ${account.dateOfBirth}</p>
                                            </c:otherwise>
                                        </c:choose>
                                    </div>
                                </div>
                            </div>

                            <!-- Right Sidebar - Customer's Additional Information -->
                            <div class="col-lg-8 mb-4 d-flex">
                                <div class="card shadow mb-4 equal">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Customer Additional Information</h6>
                                    </div>
                                    <div class="card-body" style="
                                         display: flex;
                                         flex-direction: column;
                                         justify-content: space-evenly;
                                         ">
                                        <p><strong>Customer ID:</strong> ${account.account_ID}</p>
                                        <c:if test="${account.status == 1}">
                                            <p><strong>Status:</strong> Active</p>
                                        </c:if>
                                        <c:if test="${account.status != 1}">
                                            <p><strong>Status:</strong> Locked</p>
                                        </c:if>
                                        <button class="btn btn-primary" data-toggle="modal" data-target="#feedbackModal">
                                            View feedback
                                        </button>
                                    </div>
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
        <script src="vendor/bootstrap/js/bootstrap.bundle.min.js"></script>

        <!-- Core plugin JavaScript-->
        <script src="vendor/jquery-easing/jquery.easing.min.js"></script>

        <!-- Custom scripts for all pages-->
        <script src="vendor/js/sb-admin-2.min.js"></script>


        <!-- Modal for Feedback -->
        <div class="modal fade" id="feedbackModal" tabindex="-1" role="dialog" aria-labelledby="feedbackModalLabel"
             aria-hidden="true">
            <div class="modal-dialog modal-dialog-scrollable modal-lg" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="feedbackModalLabel">Feedback</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <table id="feedbackTable" class="table table-bordered table-hover" style="width: 100%">
                            <thead>
                                <tr>
                                    <th>Hotel Name</th>
                                    <th>Rating</th>
                                    <th>Comment</th>
                                    <th>Date</th>
                                </tr>
                            </thead>
                            <tbody>

                                <%
                                    List<Feedback> feedbackList = (List<Feedback>) request.getAttribute("feedbackList");
                                    for (Feedback feedback : feedbackList) {
                                        Hotel hotel = feedback.getHotel(feedback.getFeedbackID());
                                %>
                                <tr>
                                    <td><%= (hotel != null ? hotel.getName() : "") %></td>
                                    <td><%= feedback.getRating() %>/5</td>
                                    <td><%= feedback.getDescription() %></td>
                                    <td><%= feedback.getDate() %></td>
                                </tr>
                                <% } %>

                                <!-- Add more rows as needed -->
                            </tbody>
                        </table>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
                    </div>
                </div>
            </div>
        </div>
        <!-- Include DataTables scripts -->
        <script src="vendor/datatables/jquery.dataTables.min.js"></script>
        <script src="vendor/datatables/dataTables.bootstrap4.min.js"></script>
        <!-- Initialize DataTables -->
        <script>
            $(document).ready(function () {
                // Initialize Booked Hotels DataTable
                $('#bookedHotelsTable').DataTable({
                    paging: true,
                    searching: false, // Optionally disable searching
                    info: true // Optionally display info
                });

                // Initialize Feedback DataTable
                $('#feedbackTable').DataTable({
                    paging: true,
                    searching: false, // Optionally disable searching
                    info: true // Optionally display info
                });
            });
        </script>
    </body>
</html>
