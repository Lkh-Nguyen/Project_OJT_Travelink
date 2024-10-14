<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

        <!-- Custom styles for lock/unlock functionality -->
        <style>
            .blurred {
                /* filter: blur(5px); */
                opacity: 0.5;
            }
            .locked {
                color: red;
                font-weight: bold;
            }

            .filter-btn {
                display: inline-block;
                padding: 8px 16px;
                margin-right: 10px;
                font-size: 14px;
                font-weight: bold;
                text-transform: uppercase;
                border: 2px solid #4e73df;
                color: #4e73df;
                background-color: transparent;
                cursor: pointer;
                transition: all 0.3s ease;
            }

            .filter-btn:hover {
                background-color: #4e73df;
                color: #fff;
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
                        <h1 class="h3 mb-4 text-gray-800">Customer Account List</h1>

                        <!-- Filter Buttons -->
                        <div style="margin-bottom: 20px;">
                            <button class="filter-btn" onclick="filterByStatus('Active')">Active Customers</button>
                            <button class="filter-btn" onclick="filterByStatus('Locked')">Locked Customers</button>
                            <button class="filter-btn" onclick="showAllCustomers()">Show All</button>
                        </div>
                        <!-- Customer Account List Table -->
                        <div class="card shadow mb-4">
                            <div class="card-header py-3">
                                <h6 class="m-0 font-weight-bold text-primary">Customer Account List</h6>
                            </div>
                            <div class="card-body">
                                <div class="table-responsive">
                                    <table class="table table-bordered" id="dataTable" width="100%" cellspacing="0">
                                        <thead>
                                            <tr>
                                                <th>Customer Name</th>
                                                <th>Email</th>
                                                <th>Phone Number</th>
                                                <th>Address</th>
                                                <th>Date of birth</th>
                                                <th>Status</th>
                                                <th>Actions</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <!-- Sample data for demonstration purposes -->
                                            <c:forEach var="account" items="${accountList}">
                                                <tr>
                                                    <c:choose>
                                                        <c:when test="${empty account.name}">
                                                            <td class="customer-info">N/A</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="customer-info">${account.name}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${empty account.email}">
                                                            <td class="customer-info">N/A</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="customer-info">${account.email}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${empty account.phoneNumber}">
                                                            <td class="customer-info">N/A</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="customer-info">${account.phoneNumber}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${empty account.address}">
                                                            <td class="customer-info">N/A</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="customer-info">${account.address}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <c:choose>
                                                        <c:when test="${empty account.dateOfBirth}">
                                                            <td class="customer-info">N/A</td>
                                                        </c:when>
                                                        <c:otherwise>
                                                            <td class="customer-info">${account.dateOfBirth}</td>
                                                        </c:otherwise>
                                                    </c:choose>
                                                    <td class="customer-info">
                                                        <c:if test="${account.status == 1}">
                                                            <span class="status text-primary">Active</span>
                                                        </c:if>
                                                        <c:if test="${account.status != 1}">
                                                            <span class="status text-secondary">Locked</span>
                                                        </c:if>
                                                    </td>
                                                    <td>
                                                        <form action="AdminLockCustomerServlet" method="Post">
                                                            <button class="btn btn-primary btn-sm" onclick="location.href = 'AdminCustomerInformationServlet?account_ID=${account.account_ID}'" type="button">View Profile</button>
                                                            <input type="hidden" value="${account.account_ID}" name="account_ID">
                                                            <button class="btn btn-danger btn-sm lock-btn" type="Submit">Lock</button>
                                                        </form>
                                                    </td>

                                                </tr>
                                            </c:forEach>
                                            <!-- End of sample data -->
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

        <!-- Custom script to handle lock/unlock functionality -->
        <script>
                                                                document.addEventListener('DOMContentLoaded', function () {
                                                                    // Function to initialize lock status on page load
                                                                    initializeLockStatus();

                                                                    // Function to initialize lock status of each customer account
                                                                    function initializeLockStatus() {
                                                                        const lockButtons = document.querySelectorAll('.lock-btn');

                                                                        lockButtons.forEach(button => {
                                                                            const row = button.closest('tr');
                                                                            const statusCell = row.querySelector('.status');
                                                                            const customerInfoCells = row.querySelectorAll('.customer-info');

                                                                            // Determine initial status and set button appearance accordingly
                                                                            if (statusCell.textContent.trim() === 'Locked') {
                                                                                setLockedAppearance(button, statusCell, customerInfoCells);
                                                                            } else {
                                                                                setActiveAppearance(button, statusCell, customerInfoCells);
                                                                            }
                                                                        });
                                                                    }

                                                                    // Function to set appearance when status is Locked
                                                                    function setLockedAppearance(button, statusCell, customerInfoCells) {
                                                                        statusCell.textContent = 'Locked';
                                                                        statusCell.classList.remove('text-primary');
                                                                        statusCell.classList.add('text-danger');

                                                                        button.textContent = 'Unlock';
                                                                        button.classList.remove('btn-danger');
                                                                        button.classList.add('btn-success');

                                                                        customerInfoCells.forEach(cell => {
                                                                            cell.classList.add('blurred');
                                                                        });
                                                                    }

                                                                    // Function to set appearance when status is Active
                                                                    function setActiveAppearance(button, statusCell, customerInfoCells) {
                                                                        statusCell.textContent = 'Active';
                                                                        statusCell.classList.remove('text-danger');
                                                                        statusCell.classList.remove('text-secondary');
                                                                        statusCell.classList.add('text-primary');
                                                                        button.textContent = 'Lock';
                                                                        button.classList.remove('btn-success');
                                                                        button.classList.add('btn-danger');
                                                                        customerInfoCells.forEach(cell => {
                                                                            cell.classList.remove('blurred');
                                                                        });
                                                                    }
                                                                });

                                                                function filterByStatus(status) {
                                                                    const rows = document.querySelectorAll('#dataTable tbody tr');

                                                                    rows.forEach(row => {
                                                                        const statusCell = row.querySelector('.status');

                                                                        if (status === 'Active' && statusCell.textContent.trim() === 'Active') {
                                                                            row.style.display = 'table-row';
                                                                        } else if (status === 'Locked' && statusCell.textContent.trim() === 'Locked') {
                                                                            row.style.display = 'table-row';
                                                                        } else {
                                                                            row.style.display = 'none';
                                                                        }
                                                                    });
                                                                }

                                                                function showAllCustomers() {
                                                                    const rows = document.querySelectorAll('#dataTable tbody tr');

                                                                    rows.forEach(row => {
                                                                        row.style.display = 'table-row';
                                                                    });
                                                                }
        </script>

    </body>
</html>
