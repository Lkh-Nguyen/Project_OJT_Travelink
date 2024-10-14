<%-- 
    Document   : Admin_Customer_List
    Created on : Jun 30, 2024, 10:45:35 AM
    Author     : DUYAN
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Travelink - Customer Management</title>

        <!-- Custom fonts for this template-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="vendor/css/sb-admin-2.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="css/Alter.css" rel="stylesheet">
        <style>
            .custom-button {
                width: 100px;  /* Điều chỉnh giá trị này theo nhu cầu của bạn */
                height: 40px;
                margin-top: 10px;/* Điều chỉnh giá trị này theo nhu cầu của bạn */
            }

        </style>
    </head>
    <body id="page-top">


        <c:if test="${requestScope.cancelFeedback != null}">
            <div id="status-message" style="background-color: rgb(233,251,233);height: 100px;margin-top: 10px" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%; text-align: start">
                        <h3 style="color:green;margin-top: 5px; margin-bottom: -2px;font-weight:550">Success</h3>
                        <p style="color: black;font-size: 14px">${cancelFeedback}</p>
                    </div>
                </div>           
            </div>
        </c:if>
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
                        <h1 class="h3 mb-4 text-gray-800">Report Feedback Detail</h1>

                        <!-- Customer Account List Table -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Report Feedback Detail</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>NO.</th>
                                                <th>Name Account</th>
                                                <th>Date Report</th>
                                                <th>Reason</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="c" items="${requestScope.list}" varStatus="status">
                                                <!-- Sample data for demonstration purposes -->
                                                <tr>
                                                    <td>${status.index + 1}</td>
                                                    <td>${c.getAccount(c.accountId).name}</td>
                                                    <td>${c.reportTime}</td>
                                                    <td>${c.reason}</td>
                                                </tr>
                                                <!-- End of sample data -->
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

        <script src="js/Alter.js"></script>
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
    </body>
</html>
