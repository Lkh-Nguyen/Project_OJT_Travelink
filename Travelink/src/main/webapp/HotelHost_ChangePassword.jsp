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
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2 mb-4"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height justify-content-center align-items-center" style="border-radius: 30px;">
                <ul class="nav nav-underline">
                    <li class="nav-item">
                        <a class="nav-link" href="HotelHost_Profile.jsp">Information</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HotelHost_ViewAvatar.jsp">View Avatar</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">Change Password</a>
                    </li>
                </ul>
                <div class="card w-75 h-100 shadow p-lg-5 mb-5 mt-3 bg-body-tertiary border-0 equal-height" style="border-radius: 30px;">
                    <div class="row">
                        <div class="col-md-1"></div>
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
                            <form action="ChangeAccountPasswordServlet" method="post">
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Old Password</span>
                                            <input type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="password" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">New Password</span>
                                            <input type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="newpassword" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row">
                                    <div class="col-md-12">
                                        <div class="input-group mb-3">
                                            <span class="input-group-text" id="inputGroup-sizing-default">Re-enter New Password</span>
                                            <input type="password" class="form-control" aria-label="Sizing example input" aria-describedby="inputGroup-sizing-default" name="re_newpassword" required>
                                        </div>
                                    </div>
                                </div>
                                <div class="row mt-4 d-flex align-items-center">
                                    <div class="col-md-6 ">
                                        <button type="button" class="btn btn-outline-primary btn-block w-100" id="btn-cancel" onclick="cancel()">Cancel</button>
                                    </div>
                                    <div class="col-md-6 ">
                                        <button type="submit" class="btn btn-primary btn-block w-100" id="btn-next">Next</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                        <div class="col-md-1"></div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.5.2/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
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
