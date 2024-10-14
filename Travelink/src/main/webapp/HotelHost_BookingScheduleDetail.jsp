<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_BookingSchedule.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        
        <div class="col-md-1" style="margin-left: 50px">
            <button type="button" class="btn btn-outline-secondary mt-3 mb-3 w-50" onclick="history.back()">Back</button>
        </div>
        <div class="container">
            <div class="card mb-5 h-1000 ">
                <div class="card-body ">
                    <div class="row gradient-background m-1" style="height: 5px">
                    </div>

                    <div class="row">
                        <div class="container">
                            <div class="card border-0">
                                <h3 style="margin-left: 20px">Check Room Date: 13/06/2024</h3>
                                <div class="card-body justify-content-center align-items-center text-center">
                                    <table class="table table-striped table-hover text-center">
                                        <thead>
                                            <tr>
                                                <th scope="col" class="col-1">ID Room</th>
                                                <th scope="col" class="col-3">Room Name</th>
                                                <th scope="col" class="col-2">Checkin Date</th>
                                                <th scope="col" class="col-2">CheckoutDate</th>
                                                <th scope="col" class="col-1">Total Day</th>
                                                <th scope="col" class="col-2">ID Reservation</th>
                                                
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <tr>
                                                <th scope="row" class="col-1">101</th>
                                                <td scope="row" class="col-3">Deluxe Suite</td>
                                                <td scope="row" class="col-2">12/03/2024</td>
                                                <td scope="row" class="col-2">15/03/2024</td>
                                                <td scope="row" class="col-1">2 Days</td>
                                                <td scope="row" class="col-2"><button type="button" class="btn btn-outline-dark w-50">15</button></td>
                                            </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="card border-0">
                            <div class="card-body">
                                <h3>Information Customer Booking</h3>
                                <div class="row">
                                    <div class="col-md-6 ">
                                        <div class="card-body p-3">
                                            <div class="row" style="margin-bottom: 10px">
                                                <div class="col-2 fw-bold lh-1">Name:</div>
                                                <div class="col-8 fw-normal text-muted lh-1">Lê Kim Hoàng Nguyên</div>
                                            </div>
                                            <div class="row" style="margin-bottom: 10px">
                                                <div class="col-2 fw-bold lh-1">CMND:</div>
                                                <div class="col-8 fw-normal text-muted lh-1">46412395202</div>
                                            </div>
                                            <div class="row" style="margin-bottom: 10px">
                                                <div class="col-2 fw-bold lh-1">Contact:</div>
                                                <div class="col-8 fw-normal text-muted lh-1">0934726073</div>
                                            </div>
                                            <div class="row" style="margin-bottom: 10px">
                                                <div class="col-2 fw-bold lh-1">Email:</div>
                                                <div class="col-8 fw-normal text-muted lh-1">lkhnguyen3006@gmail.com</div>
                                            </div>
                                            <div class="row" style="margin-bottom: 10px">
                                                <div class="col-2 fw-bold lh-1">Address:</div>
                                                <div class="col-8 fw-normal text-muted lh-1">650 Trần Cao Vân, Thanh Khê, Đà Nẵng.</div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div> 
                    </div>
                </div>
            </div>
        </div>


        <%@include file="Footer.jsp" %>        

        <!-- Bootstrap JS and dependencies -->
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>

        <script src="bootstrap_css/bootstrap.min.js"></script>
    </body>
</html>
