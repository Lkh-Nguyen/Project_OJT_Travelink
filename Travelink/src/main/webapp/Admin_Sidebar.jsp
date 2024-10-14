<%-- 
    Document   : Admin_Sidebar
    Created on : Jun 30, 2024, 10:12:58 AM
    Author     : DUYAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Admin Sidebar</title>
        <!-- Custom fonts for this template-->
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.5.2/css/all.min.css" rel="stylesheet" type="text/css">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet">

        <!-- Custom styles for this template-->
        <link href="vendor/css/sb-admin-2.min.css" rel="stylesheet">
    </head>
    <body>
        <ul class="navbar-nav bg-gradient-custom sidebar sidebar-dark accordion" id="accordionSidebar">

            <!-- Sidebar - Brand -->
            <a class="sidebar-brand d-flex align-items-center justify-content-center" href="AdminDashBoardServlet">
                <div class="sidebar-brand-icon w-25">
                    <!-- <i class="fas fa-laugh-wink"></i> -->
                    <img src="img_Home/logo.png" class="w-100">
                </div>
                <div class="sidebar-brand-text mx-3">Travelink</div>
            </a>

            <!-- Divider -->
            <hr class="sidebar-divider my-0">

            <!-- Nav Item - Dashboard -->
            <li class="nav-item my-2 active">
                <a class="nav-link" href="AdminDashBoardServlet">
                    <i class="fas fa-fw fa-tachometer-alt"></i>
                    <span>Dashboard</span></a>
            </li>

            <!-- Divider -->
            <hr class="sidebar-divider">

            <!-- Nav Item - Hotel Management -->
            <li class="nav-item my-3">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseHotelManagement"
                   aria-expanded="true" aria-controls="collapseHotelManagement">
                    <i class="fas fa-fw fa-hotel"></i>
                    <span>Hotel Management</span>
                </a>
                <div id="collapseHotelManagement" class="collapse" aria-labelledby="headingHotelManagement"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="AdminAcceptPendingServlet">Pending hotel account</a>
                        <a class="collapse-item" href="AdminHotelPartnerServlet">Hotel partner</a>
                        <a class="collapse-item" href="AdminMonthlyPaymentServlet">Hotel Monthly Payment</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Customer Account Management -->
            <li class="nav-item my-3">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapseCustomerAccountManagement"
                   aria-expanded="true" aria-controls="collapseCustomerAccountManagement">
                    <i class="fas fa-fw fa-user"></i>
                    <span>Customer Account Management</span>
                </a>
                <div id="collapseCustomerAccountManagement" class="collapse" aria-labelledby="headingCustomerAccountManagement"
                     data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="AdminCustomerListServlet">Customer List</a>
                        <a class="collapse-item" href="AdminRefundingReservationServlet">Pay For Customer</a>
                    </div>
                </div>
            </li>

            <!-- Nav Item - Feedback -->
            <li class="nav-item my-3">
                <a class="nav-link collapsed" href="#" data-toggle="collapse" data-target="#collapsePromotion"
                   aria-expanded="true" aria-controls="collapsePromotion">
                    <i class="fas fa-fw fa-tags"></i>
                    <span>Feedback</span>
                </a>
                <div id="collapsePromotion" class="collapse" aria-labelledby="headingPromotion" data-parent="#accordionSidebar">
                    <div class="bg-white py-2 collapse-inner rounded">
                        <a class="collapse-item" href="Admin_ViewReportFeedback">Report Feedback</a>
                    </div>
                </div>
            </li>
             <!-- Nav Item - Contact -->
            <li class="nav-item my-2 active">
                <a class="nav-link" href="ChatServlet">
                    <i class="fa-regular fa-address-book"></i>
                    <span>Contact</span></a>
            </li>
            <!-- Divider -->
            <hr class="sidebar-divider d-none d-md-block">

            <!-- Sidebar Toggler (Sidebar) -->
            <div class="text-center d-none d-md-inline">
                <button class="rounded-circle border-0" id="sidebarToggle"></button>
            </div>
        </ul>
        <!-- Bootstrap core JavaScript-->
        <script src="vendor/jquery/jquery.min.js"></script>
        <script src="bootstrap_js/js/bootstrap.bundle.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            // Get the current URL path
            var currentPage = window.location.pathname.split('/').pop();

            // Find all nav items
            var navItems = document.querySelectorAll('.nav-item');

            // Loop through nav items to find the one matching the current page
            navItems.forEach(function (navItem) {
                var pages = navItem.getAttribute('data-page').split(' ');
                if (pages.includes(currentPage)) {
                    navItem.classList.add('active');
                    if (navItem.querySelector('.collapse')) {
                        var collapse = navItem.querySelector('.collapse');
                        collapse.classList.add('show');
                    }
                }
            });
        </script>
    </body>
</html>
