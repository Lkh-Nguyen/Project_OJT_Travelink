<%-- 
    Document   : HotelHost_Profile
    Created on : Jun 16, 2024, 12:35:22 AM
    Author     : admin
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
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2 mb-4"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height justify-content-center align-items-center" style="border-radius: 30px;">
                <ul class="nav nav-underline mb-4">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Information</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HotelHost_ViewAvatar.jsp">View Avatar</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HotelHost_ChangePassword.jsp">Change Password</a>
                    </li>
                </ul>
                <div class="card h-100 shadow p-lg-5 mb-5 bg-body-tertiary border-0 equal-height" style="border-radius: 30px;">
                    <div class="row">
                        <div class="col-md-1">

                        </div>
                        <div class="col-md-4">
                            <div class="card shadow p-lg-1 mb-5 bg-body-tertiary">
                                <div class="card-header">
                                    Hotel Host Information
                                </div>
                                <div class="card-body">
                                    <div class="d-flex align-items-center">
                                        <img src="${account.avatarURL}" alt="Special Icon" style="width: 2rem; height: 2rem; border-radius: 50%;" class="me-2">
                                        <p class="card-title mb-0">${account.name}</p>
                                    </div>
                                </div>
                            </div>
                        </div>

                        <div class="col-md-6">
                            <form action="UpdateAccountServlet" method="post">
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Name</span>
                                            <input type="text" name="name" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${account.name}" required>
                                        </div>
                                    </div>
                                    <div class="col-md-6">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Email</span>
                                            <input type="text" name="email" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${account.email}" readonly required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-6">
                                        <div class="col-md-12 d-flex">
                                            <div class="form-check">
                                                <input class="form-check-input" type="radio" name="gender" id="flexRadioDefault1" ${account.gender == 77?"checked":""} required value="Male">
                                                <label class="form-check-label" for="flexRadioDefault1">
                                                    Male
                                                </label>
                                            </div>
                                            <div class="form-check" style="margin-left: 70px;">
                                                <input class="form-check-input" type="radio" name="gender" id="flexRadioDefault2" ${account.gender == 70?"checked":""} required value="Female">
                                                <label class="form-check-label" for="flexRadioDefault2">
                                                    Female
                                                </label>
                                            </div>

                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-3">
                                    <div class="col-md-12">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">BirthDate</span>
                                            <input type="date" name="DOB" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${account.dateOfBirth}" required>
                                        </div>
                                    </div>
                                </div>

                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">PhoneNumber</span>
                                            <input type="text" name="phone" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${account.phoneNumber}" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">CMND</span>
                                            <input type="text" name="CMND" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" value="${account.cmnd}" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="input-group">
                                            <span class="input-group-text">Address</span>
                                            <textarea class="form-control" aria-label="With textarea" name="address" style="resize: none" required>${account.address}</textarea>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-4 d-flex justify-content-center align-items-center">
                                    <div class="col-md-6 justify-content-center align-items-center">
                                    </div>
                                    <div class="col-md-6 justify-content-center align-items-center">
                                        <button type="button" class="btn btn-outline-primary btn-block" id="btn-update" onclick="cancel()">Cancel</button>
                                        <button type="submit" class="btn btn-primary btn-block" id="btn-update" >Save</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-1">

                        </div>
                    </div>
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
        <script>
                                            function cancel() {
                                                location.reload();
                                                event.preventDefault();
                                            }
        </script>
    </body>
</html>


