<%-- 
    Document   : Admin_Hotel_Information
    Created on : Jun 30, 2024, 10:54:55 AM
    Author     : DUYAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <title>Travelink - Hotel Management</title>

    <!-- Custom fonts for this template-->
    <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" type="text/css">
    <link
        href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
        rel="stylesheet">

    <!-- Custom styles for this template-->
    <link href="vendor/css/sb-admin-2.min.css" rel="stylesheet">
    <!-- Custom card-->
    <style>
        .equal{
            flex: 1;
            flex-direction: column;
        }
    </style>
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
                            <h1 class="h3 mb-0 text-gray-800">Hotel Information</h1>
                        </div>
                        <c:set var="hotel" value="${hotel}" />
                        <c:set var="hotelImage" value="${hotelImage}" />
                        <c:set var="account" value="${account}" />

                        <div class="row d-flex">
                            <!-- Left Sidebar - Hotel Host's Information -->
                            <div class="col-lg-4 mb-4 d-flex">
                                <div class="card shadow mb-4 equal">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Hotel Host Information</h6>
                                    </div>
                                    <div class="card-body">
                                        <img src="${account.avatarURL}" class="img-fluid mb-3"/>
                                        <p><strong>Name:</strong> ${account.name}</p>
                                        <p><strong>Email:</strong> ${account.email}</p>
                                        <p><strong>Phone:</strong> ${account.phoneNumber}</p>
                                        <p><strong>Address:</strong> ${account.address}</p>
                                    </div>
                                </div>
                            </div>

                            <!-- Right Sidebar - Hotel's Information -->
                            <div class="col-lg-8 mb-4 d-flex equal">
                                <div class="card shadow mb-4">
                                    <div class="card-header py-3">
                                        <h6 class="m-0 font-weight-bold text-primary">Hotel Information</h6>
                                    </div>
                                    <div class="card-body">
                                        <img src="${hotelImage}" class="img-fluid mb-3"/>
                                        <p><strong>Hotel ID:</strong> ${hotel.hotel_ID}</p>
                                        <p><strong>Name:</strong> ${hotel.name}</p>
                                        <p><strong>Email:</strong> ${hotel.email}</p>
                                        <p><strong>Phone Number:</strong> ${hotel.phoneNumber}</p>
                                        <p><strong>Location:</strong> ${hotel.address}</p>
                                        <p><strong>Status:</strong> ${hotel.status}</p>
                                        <p><strong>Description:</strong> ${hotel.description}</p>
                                        <p><strong>Checkin time:</strong> ${hotel.checkInTimeStart} - ${hotel.checkInTimeEnd}</p>
                                        <p><strong>Checkout time:</strong> ${hotel.checkOutTimeStart} - ${hotel.checkOutTimeEnd}</p>
                                        <p><strong>Star:</strong> ${hotel.star}</p>
                                        <p><strong>Rating:</strong> ${hotel.rating}</p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <!-- End of Page Content -->
                    <button class="btn btn-outline-info position-sticky" onclick="window.history.back()"><i class="fas fa-arrow-left"></i>Back</button>
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
</html>
