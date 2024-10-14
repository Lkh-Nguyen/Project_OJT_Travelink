<%-- 
    Document   : Admin_Hotel_Partner
    Created on : Jun 30, 2024, 10:39:46 AM
    Author     : DUYAN
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
                        <h1 class="h3 mb-4 text-gray-800">Hotel Partners</h1>

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
                                                <th class="text-center">Hotel Name</th>
                                                <th class="text-center">Owner Name</th>
                                                <th class="text-center">Email</th>
                                                <th class="text-center">Phone</th>
                                                <th class="text-center">Location</th>
                                                <th class="text-center">Status</th>
                                                <th class="text-center">Action</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="partner" items="${requestScope.partnerList}" >
                                                <tr>
                                                    <td>${partner.hotel.name}</td>
                                                    <td>${partner.account.name}</td>
                                                    <td>${partner.account.email}</td>
                                                    <td>${partner.account.phoneNumber}</td>
                                                    <td>${partner.province}</td>
                                                    <td class="text-primary fw-bold">${partner.hotel.status}</td>
                                                    <td class="text-center">
                                                        <button class="btn btn-primary btn-sm" onclick="location.href = 'AdminHotelInformationServlet?hotel_ID=${partner.hotel.hotel_ID}'">View hotel</button>
                                                        <form action="AdminLockHotelServlet" method="get">
                                                            <c:if test="${partner.hotel.status != 'LOCKED'}">
                                                                <button class="btn btn-danger btn-sm">Lock</button>
                                                                <input type="hidden" name="hotelID" value="${partner.hotel.hotel_ID}">
                                                                <input type="hidden" name="type" value="Lock">
                                                            </c:if>
                                                             <c:if test="${partner.hotel.status eq 'LOCKED'}">
                                                                <button class="btn btn-danger btn-sm">UnLock</button>
                                                                <input type="hidden" name="hotelID" value="${partner.hotel.hotel_ID}">
                                                                <input type="hidden" name="type" value="UnLock">
                                                            </c:if>
                                                        </form>
                                                        
                                                    </td>
                                                </tr>
                                                <!-- Add more rows as needed -->
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
            function lockHotel(button) {
                const row = button.closest('tr');
                const statusCell = row.querySelector('.status');
                const lockButton = row.querySelector('.lock-hotel');
                const hotelInfoCells = row.querySelectorAll('.hotel-info');
                
                if (lockButton.textContent.trim() === 'Lock') {
                    // Change status to Locked
                    statusCell.textContent = 'Locked';
                    statusCell.classList.remove('text-primary');
                    statusCell.classList.add('text-danger');

                    // Change button text to Unlock
                    lockButton.textContent = 'Unlock';
                    lockButton.classList.remove('btn-danger');
                    lockButton.classList.add('btn-success');

                    // Dim the hotel info cells
                    hotelInfoCells.forEach(cell => {
                        cell.style.opacity = '0.5';
                    });
                } else {
                    // Change status to Partner
                    statusCell.textContent = 'Partner';
                    statusCell.classList.remove('text-danger');
                    statusCell.classList.add('text-primary');

                    // Change button text to Lock
                    lockButton.textContent = 'Lock';
                    lockButton.classList.remove('btn-success');
                    lockButton.classList.add('btn-danger');

                    // Restore the hotel info cells opacity
                    hotelInfoCells.forEach(cell => {
                        cell.style.opacity = '1';
                    });
                }
            }
        </script>
    </body>
</html>
