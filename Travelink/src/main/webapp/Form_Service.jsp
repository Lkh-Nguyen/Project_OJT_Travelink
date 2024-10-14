<%-- 
    Document   : FormService
    Created on : Jun 24, 2024, 9:44:33 AM
    Author     : DUYAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_HotelService.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
            * {
                font-family: Montserrat, sans-serif;
            }
            .row-compact {
                margin-bottom: 1rem; /* Adjust as needed */
            }
            #pieChart, #myChart {
                width: 100% !important;
                height: 100% !important;
            }
            .equal-height {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }
            .card-flex {
                display: flex;
                flex: 1 1 auto;
                flex-direction: column;
            }
            #btn-update {
                width: 100px;
            }
            .form-group {
                margin-bottom: 15px;
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container-fluid">
            <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height justify-content-center align-items-center" style="border-radius: 30px;">
                <ul class="nav nav-underline mb-4">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="HotelHost_UpdateHotelInformation.jsp">Update Hotel</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="HotelHost_UpdateRoom.jsp">Update Room</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Update Service</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HotelHost_AddRoom.jsp">Add Hotel</a>
                    </li>
                </ul>
                <div class="card w-100 h-100 shadow p-lg-5 mb-5 bg-body-tertiary border-0 equal-height" style="border-radius: 30px;">
                    <form id="bookingForm" action='#' method='#'>
                        <h4 class="text-center mb-3">SERVICE INFORMATION</h4>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Service ID</span>
                                    <input type="text" class="form-control" placeholder="service id" aria-label="serviceid" aria-describedby="basic-addon1" readonly="">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Service Name</span>
                                    <input type="text" class="form-control" placeholder="service name" aria-label="servicename" aria-describedby="basic-addon1">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Service Price</span>
                                    <input type="text" class="form-control" placeholder="service price" aria-label="serviceprice" aria-describedby="basic-addon1">
                                </div>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-md-12">
                                <div class="input-group mb-3">
                                    <span class="input-group-text" id="basic-addon1">Facility Name</span>
                                    <input type="text" class="form-control" placeholder="facility name" aria-label="serviceprice" aria-describedby="basic-addon1">
                                </div>
                            </div>
                        </div>
                        <div class="row mt-4 justify-content-center align-items-center">
                            <div class="col-md-4 justify-content-center align-items-center"></div>
                            <div class="col-md-4 d-flex justify-content-center align-items-center">
                                <button type="button" class="btn btn-outline-primary btn-block" id="btn-cancel">Cancel</button>
                                <button type="submit" class="btn btn-primary btn-block" id="btn-update" style="margin-left:10px;">Save</button>
                            </div>
                            <div class="col-md-4 justify-content-center align-items-center"></div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
    </body>
</html>



