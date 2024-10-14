<%-- 
    Document   : HotelHost_Register
    Created on : Jun 21, 2024, 12:28:45 PM
    Author     : DUYAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Hotel Host</title>
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_FormLoginRegister.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            .form-outline {
                position: relative;
            }
            .toggle-password {
                position: absolute;
                top: 70%;
                right: 20px;
                transform: translateY(-50%);
                cursor: pointer;
            }
        </style>
    </head>
    <body>

        <section style="background-color: #9A616D;height: 105%">
            <div class="contain-a-button">
                <a class="a-button" href="Home_HotelHost.jsp">
                    <i class="fa fa-arrow-left"></i> Back
                </a>
            </div>
            <div class="container py-5 h-100">
                <div class="row d-flex justify-content-center align-items-center h-100">
                    <div class="col col-xl-10">
                        <div class="card" style="border-radius: 1rem;">
                            <div class="row g-0">
                                <div class="col-md-12 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">

                                        <form action="register" method="post">

                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Travelink Partner</span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign up your account</h5>
                                            <div class="row">
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example17">Name</label>
                                                    <input type="text" id="form2Example17" class="form-control form-control-lg" name="name" required/>
                                                </div>
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example17">Phone Number</label>
                                                    <input type="text" id="form2Example17" class="form-control form-control-lg" name="phone" required />
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example17">Email</label>
                                                    <input type="email" id="form2Example17" class="form-control form-control-lg" name="email" required />
                                                </div>

                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example27">CMND</label>
                                                    <input type="text" id="form2Example27" class="form-control form-control-lg" name="cmnd" required />
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="password">Password</label>
                                                    <input type="password" id="password" class="form-control form-control-lg"  name="password" required/>
                                                    <i class="fas fa-eye toggle-password" onclick="togglePassword('password')"></i>
                                                </div>
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="passwordConfirm">Password Confirm</label>
                                                    <input type="password" id="passwordConfirm" class="form-control form-control-lg"  name="passwordConfirm" required/>
                                                    <i class="fas fa-eye toggle-password" onclick="togglePassword('passwordConfirm')"></i>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example27">Gender</label><br>
                                                    <input type="radio" name="gender" value="Male"> Male<br>
                                                    <input type="radio" name="gender" value="Female" checked> Female
                                                </div>
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example27">Date Of Birthday</label>
                                                    <input type="date" id="form2Example27" class="form-control form-control-lg" name="DOB" required/>
                                                </div>
                                            </div>
                                            <div class="row">
                                                <div data-mdb-input-init class="col form-outline mb-4">
                                                    <label class="form-label" for="form2Example27">Address</label><br>
                                                    <textarea id="address" name="address" rows="4" cols="65" style="resize: none;font-size: 15px" class="form-control form-control-lg" required></textarea>
                                                </div>
                                                <div data-mdb-input-init class="col form-outline mb-4">

                                                </div>
                                            </div>
                                            <p class="mb-1 pb-lg-2" style="color: #393f81;">Have an account? 
                                                <a href="HotelHost_Login.jsp" id="loginRegister">Login here</a>
                                            </p>
                                            <input type="hidden" name="role" value="2">
                                            <div class="pt-1">
                                                <input data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-lg btn-block" type="submit" value="Register"/>
                                            </div>                           
                                        </form>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>

        <script>
            function togglePassword(id) {
                var x = document.getElementById(id);
                var icon = x.nextElementSibling;
                if (x.type === "password") {
                    x.type = "text";
                    icon.classList.remove('fa-eye');
                    icon.classList.add('fa-eye-slash');
                } else {
                    x.type = "password";
                    icon.classList.remove('fa-eye-slash');
                    icon.classList.add('fa-eye');
                }
            }
        </script>
    </body>
</html>

