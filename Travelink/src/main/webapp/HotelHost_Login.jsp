<%-- 
    Document   : HotelHost_Login
    Created on : Jun 21, 2024, 12:26:23 PM
    Author     : DUYAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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
        <section class="vh-100" style="background-color: #9A616D;">
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

                                <div class="col-md-12 col-lg-7 d-flex align-items-center">
                                    <div class="card-body p-4 p-lg-5 text-black">

                                        <form action="LoginAccountServlet" method="post">
                                            <div class="d-flex align-items-center mb-3 pb-1">
                                                <i class="fas fa-cubes fa-2x me-3" style="color: #ff6219;"></i>
                                                <span class="h1 fw-bold mb-0">Travelink Partner</span>
                                            </div>

                                            <h5 class="fw-normal mb-3 pb-3" style="letter-spacing: 1px;">Sign into your account</h5>
                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="form2Example17">Email address</label>
                                                <input type="email" id="form2Example17" class="form-control form-control-lg" name="email" required />
                                            </div>
                                            <div data-mdb-input-init class="form-outline mb-4">
                                                <label class="form-label" for="form2Example27">Password</label>
                                                <input type="password" id="form2Example27" class="form-control form-control-lg"  name="password" required/>
                                                <i class="fas fa-eye toggle-password" onclick="togglePassword('form2Example27')"></i>
                                            </div>
                                            <div class="d-flex justify-content-between align-items-center mb-4">
                                                <div class="form-check">
                                                    <input class="form-check-input" type="checkbox" value="" id="form1Example3"/>
                                                    <label class="form-check-label" for="form1Example3"> Remember me </label>
                                                </div>
                                                <a href="Forgot_Password.jsp" id="forgetPassword">Forgot password?</a>
                                            </div>
                                            <c:if test="${requestScope.errorLogin != null}">
                                                <p class="text-danger">${requestScope.errorLogin}</p>
                                            </c:if>
                                            <p class="mb-2 pb-lg-2" style="color: #393f81;">Don't have an account? 
                                                <a href="HotelHost_Register.jsp" id="loginRegister">Register here</a>
                                            </p>
                                            <input type="hidden" name="role" value="2">
                                            <div class="pt-1 mb-4">
                                                <input data-mdb-button-init data-mdb-ripple-init class="btn btn-dark btn-lg btn-block" type="submit" value="Login"/>
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

