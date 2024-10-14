<%-- 
    Document   : HotelHostDashBoard
    Created on : Jun 14, 2024, 9:49:27 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@page import="com.travelink.Model.*" %>
<%@page import="com.travelink.Database.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_DashBoard.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
            body {
                font-family: Montserrat, sans-serif;
            }
            .row-compact {
                margin-bottom: 1rem;
            }
            #pieChart, #myChart {
                width: 100% !important;
                height: 100% !important;
            }
            .card-flex {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
                height: 100%;
            }
            .card-body {
                display: flex;
                align-items: center;
            }
            .card-img {
                width: 2rem;
                height: 2rem;
            }
            .card-title {
                font-size: 1.5rem;
            }
            .card-subtitle {
                font-size: 0.875rem;
                color: #6c757d;
            }
            .chart-container {
                position: relative;
                height: 100%;
                width: 100%;
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container-fluid py-0">
            <a href="homeHotelHostServlet"_Hot class="btn btn-outline-primary mt-2">
                <img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
            </a>
            <a href="HotelHostMonthlyPaymentServlet"_Hot class="btn btn-outline-primary mt-2">
                Hotel Monthly Payment
            </a>
            <form action="DashboardServlet" method="GET" class="container my-4">
                <div class="row">
                    <div class="col-md-4 mb-3">
                        <label for="month" class="form-label">Month</label>
                        <select id="month" name="month" class="form-select">
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

                    <div class="col-md-4 mb-3">
                        <label for="year" class="form-label">Year</label>
                        <select id="year" name="year" class="form-select">
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

                    <div class="col-md-4 mb-3">
                        <label for="hotelSelect" class="form-label">Hotel</label>
                        <select id="hotelSelect" name="hotel_ID" class="form-select">
                            <%
                                List<Hotel> hotelList = (List<Hotel>) request.getAttribute("hotelList");
                                int selectedHotelID = request.getParameter("hotel_ID") != null ? Integer.parseInt(request.getParameter("hotel_ID")) : -1;

                                for (Hotel hotel : hotelList) {
                                    int hotelID = hotel.getHotel_ID();
                                    String selected = (hotelID == selectedHotelID) ? "selected" : "";
                            %>
                            <option value="<%= hotelID %>" <%= selected %>><%= hotel.getName() %></option>
                            <%
                                }
                            %>
                        </select>
                    </div>
                </div>
                <div class="d-flex justify-content-center">
                    <button type="submit" class="btn btn-primary">Search</button>
                </div>
            </form>
            <!-- row chính 1 -->
            <div class="row p-4 m-3 d-flex justify-content-center align-items-center row-compact">
                <div class="col-md-3">
                    <div class="card justify-content-center align-items-center shadow p-3 bg-body-tertiary  border-0 h-100" id="cardFirst">
                        <div class="card-body" >
                            <div class="row">
                                <div class="col-md-2 mt-4">
                                    <img src="img_Hotel/document.svg" alt="Invoice Icon" style="width: 2rem; height: 2rem;">
                                </div>
                                <div class="col-md-10 mt-2 px-3">
                                    <h3>${totalReservations}</h3>
                                    <p style="font-size: 15px">Total Reservations</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card justify-content-center align-items-center shadow p-3  bg-body-tertiary  border-0 h-100" id="cardFirst">
                        <div class="card-body " >
                            <div class="row">
                                <div class="col-md-2 mt-4">
                                    <img src="img_Hotel/paid_invoice.svg" alt="Invoice Icon" style="width: 2rem; height: 2rem; color: white">
                                </div>
                                <div class="col-md-10 mt-2 px-3">
                                    <h3>${paidReservations}</h3>
                                    <p style="font-size: 15px">Paid Reservations</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card justify-content-center align-items-center shadow p-3  bg-body-tertiary  border-0 h-100" id="cardFirst">
                        <div class="card-body" >
                            <div class="row">
                                <div class="col-md-2 mt-4">
                                    <img src="img_Hotel/cancel_invoice.svg" alt="Invoice Icon" style="width: 2rem; height: 2rem;">
                                </div>
                                <div class="col-md-10 mt-2 px-3">
                                    <h3>${cancelReservations}</h3>
                                    <p style="font-size: 15px">Cancel Reservations</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
                    <div class="card justify-content-center align-items-center shadow p-3  bg-body-tertiary border-0 h-100" id="cardFirst">
                        <div class="card-body" >
                            <div class="row">
                                <div class="col-md-2 mt-4">
                                    <img src="img_Hotel/sent_invoice.svg" alt="Invoice Icon" style="width: 2rem; height: 2rem;">
                                </div>
                                <div class="col-md-10 mt-2 px-3">
                                    <h3>${refundReservations}</h3>
                                    <p style="font-size: 15px">Refund Reservations</p>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <!-- row chính 2 -->
            <div class="row p-4 m-3 py-0 my-0 mt-0 d-flex justify-content-center align-items-center row-compact">
                <div class="col-md-9">
                    <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height" style="border-radius: 30px;" >
                        <div class="row p-4">
                            <div class="col-md-6">
                                <div class="card border-0 mt-3 card-flex" id="cardFirst">
                                    <div class="card-body d-flex flex-column justify-content-end">
                                        <div class="row">
                                            <div class="col-md-7 p-lg-5">
                                                <h3>${balance} VND</h3>
                                                <div class="text-bottom">
                                                    <p class="text-muted mb-0">Balance</p>
                                                </div>
                                            </div>
                                            <div class="col-md-5">
                                                <div class="btn-change">
                                                    <p class="rotate-text text-dark-emphasis">Change</p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-md-6">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="card card-flex">
                                            <div class="card-body">
                                                <canvas id="myChart" width="500" height="550"></canvas>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="col-md-3">
                    <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height" style="border-radius: 30px" >
                        <div class="card-body card-flex">
                            <canvas id="pieChart" width="300" height="300"></canvas>
                        </div>
                    </div>
                </div>
            </div>


            <canvas id="lineChart" width="300" height="300"></canvas>
        </div>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
            var month = '${param.month}';
            var monthLabel = month ? month : '${requestScope.month}';
            var year = '${param.year}';
            var yearLabel = year ? year : '${requestScope.year}';
            // Function to convert month number to month name
            function getMonthName(month) {
                const monthNames = [
                    "January", "February", "March", "April", "May", "June",
                    "July", "August", "September", "October", "November", "December"
                ];
                return monthNames[month - 1];
            }

            // Convert month number to month name if month is not 0
            var pieChartLabel;
            if (monthLabel != 0) {
                pieChartLabel = 'Income Distribution in ' + getMonthName(parseInt(monthLabel)) + ' ' + yearLabel;
            } else {
                pieChartLabel = 'Income Distribution in ' + yearLabel;
            }

            // Khởi tạo biểu đồ tròn
            var pieCtx = document.getElementById('pieChart').getContext('2d');
            var pieChart = new Chart(pieCtx, {
                type: 'pie',
                data: {
                    labels: ['Paid Reservation Income', 'Refunded Reservation Income'],
                    datasets: [{
                            data: [${paidReservationIncome}, ${refundedReservationIncome}],
                            backgroundColor: [
                                'rgba(255, 99, 132, 0.2)',
                                'rgba(54, 162, 235, 0.2)',
                                'rgba(75, 192, 192, 0.2)'
                            ],
                            borderColor: [
                                'rgba(255, 99, 132, 1)',
                                'rgba(54, 162, 235, 1)',
                                'rgba(75, 192, 192, 1)'
                            ],
                            borderWidth: 1
                        }]
                },
                options: {
                    responsive: false, // Disable responsiveness to control the size manually
                    maintainAspectRatio: false, // Disable aspect ratio to allow custom width and height
                    plugins: {
                        title: {
                            display: true,
                            text: pieChartLabel
                        }
                    }
                }
            });


            // Khởi tạo biểu đồ khác
            var ctx = document.getElementById('myChart').getContext('2d');
            var myChart = new Chart(ctx, {
                type: 'bar',
                data: {
                    labels: ['January', 'February', 'March', 'April', 'May', 'June', 'July', 'August', 'September', 'October', 'November', 'December'],
                    datasets: [{
                            label: 'Monthly Revenue Report ' + yearLabel,
                            data: ${barDataString},
                            backgroundColor: 'rgba(255, 99, 132, 0.2)',
                            borderColor: 'rgba(255, 99, 132, 1)',
                            borderWidth: 1
                        }]
                },
                options: {
                    responsive: false,
                    maintainAspectRatio: false
                }
            });

            

        </script>
    </body>
</html>
