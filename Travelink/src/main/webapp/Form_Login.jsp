<!DOCTYPE html>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html lang="en">
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="css/Form_Login.css">
        <link rel="stylesheet" href="css/Alter.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <title>Login Form</title>
    </head>

    <body>
        <!--Sau thay bằng button Login của HomePage-->
        <div class="contain-a-button">
            <a class="a-button" href="homeCustomerServlet">
                <i class="fa fa-arrow-left"></i> Back
            </a>
        </div>

        <div class="total-header">
            <div class="div-title">
                <h1 style="color: white; font-size: 100px;">Fuel Mind Travel</h1>
                <div class="contain-p">
                    <p style="color: white; font-size: 20px;">Enthusiastically extend extensive customer service before best
                        breed convergence completely</p>
                </div>
            </div>
            <div class="contain-row">
                <div class="div-form-button">
                    <button class="ghost" id="showFormButton">Login</button>
                </div>
                <div class="div-form-button">
                    <button class="ghost" id="showFormButton-register">Register</button>
                </div>
            </div>
        </div>

        <div id="overlayBackground"></div>

        <div class="container" id="container">
            <div class="form-container sign-up-container">
                <form action="register" method="post">
                    <input type="hidden" name="role" value="1">
                    <h1>Create Account</h1>
                    <div class="form-field">
                        <input type="text" class="form-input" name="name"  required>
                        <label for="name" class="form-label">Name</label>
                    </div>
                    <div class="form-field">
                        <input type="text" class="form-input" name="phone" required>
                        <label for="phone" class="form-label">Phone number</label>
                    </div>
                    <div class="form-field">
                        <input type="email" class="form-input" name="email" required>
                        <label for="email" class="form-label">Gmail</label>
                    </div>
                    <div class="form-field">
                        <input type="password" class="form-input" name="password" required minlength="8" id="registerPassword">
                        <label for="password" class="form-label">Password</label>
                        <span id="toggleRegisterPassword" class="toggle-password" style="font-size: 15px;user-select: none" onclick="togglePasswordVisibility('registerPassword', 'toggleRegisterPassword')">&#128065;</span>
                    </div>
                    <p>--------- or login/register with ----------</p>
                    <div class="login-way">
                        <a href="https://www.facebook.com/?stype=lo&deoia=1&jlou=AfdibM459Jj0fHXyRGCbN67BSjzCcRXoOwcdxr2C5Nnq-zgmztRY-JtyHe_JO7uB1rTyY--yUuuaiSm38bnVzOTt0_gOTCNpPVh-dg_auVFy6w&smuh=6096&lh=Ac_9OgUQcQFatzL53wo"
                           class="icon-text-container">
                            <img src="img_Form/logo-fb.jpg" alt="Facebook Logo" class="login-logo">
                            <span class="text-login">Continue with Facebook</span>
                        </a>
                    </div>
                    <div class="login-way">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://35.197.147.187.nip.io/Travelink/LoginGoogleHandler&response_type=code&client_id=753524792966-f76rldgp0a9n3ui96hg8e5pida66ho94.apps.googleusercontent.com&approval_prompt=force"
                           class="icon-text-container">
                            <!-- <i class="fab fa-google-plus-g"></i> -->
                            <img src="img_Form/logo-gg.jpg" alt="Google Logo" class="login-logo">
                            <span class="text-login">Continue with Google</span>
                        </a>
                    </div>
                    <button class="btn-login" id="register">Sign Up</button>
                </form>
                <!-- Nút đóng form -->
                <button class="close-form-button" id="closeButton">
                    <i class="fas fa-times close-icon" id="closeIcon"></i>
                </button>
            </div>
            <div class="form-container sign-in-container">
                <form action="LoginAccountServlet" method="post">
                    <h1>Login</h1>
                    <div class="form-field">
                        <input type="email" name="email" class="form-input" autocomplete="off">
                        <label for="email" class="form-label">Gmail</label>
                    </div>
                    <div class="form-field">
                        <input type="password" name="password" class="form-input" autocomplete="off" id="loginPassword">
                        <label for="password" class="form-label">Password</label>
                        <span id="toggleLoginPassword" class="toggle-password" style="font-size: 15px;user-select: none" onclick="togglePasswordVisibility('loginPassword', 'toggleLoginPassword')">&#128065;</span>
                    </div>
                    <input type="hidden" name="role" value="1">
                    <a class="forgot-pass" href="Forgot_Password.jsp">Forgot your password?</a>
                    <p>--------- or login/register with ----------</p>
                    <div class="login-way">
                        <a href="https://accounts.google.com/o/oauth2/auth?scope=email%20profile%20openid&redirect_uri=http://35.197.147.187.nip.io/Travelink/LoginGoogleHandler&response_type=code&client_id=753524792966-f76rldgp0a9n3ui96hg8e5pida66ho94.apps.googleusercontent.com&approval_prompt=force"
                           class="icon-text-container">
                            <!-- <i class="fab fa-google-plus-g"></i> -->
                            <img src="img_Form/logo-gg.jpg" alt="Google Logo" class="login-logo">
                            <span class="text-login">Continue with Google</span>
                        </a>
                    </div>
                    <input type="hidden" name="role" value="1">
                    <button class="btn-login" id="login">Sign In</button>

                </form>
            </div>
            <div class="overlay-container">
                <div class="overlay">
                    <div class="overlay-panel overlay-left">
                        <h1>Login</h1>
                        <p>By registering, you agree to our <a href="#">Terms & Conditions</a> and
                            that you have read our <a href="#"> Privacy
                                Notice.</a></p>
                        <button class="ghost" id="signIn">Sign In</button>
                    </div>
                    <div class="overlay-panel overlay-right">
                        <h1>Sign Up</h1>
                        <p>By registering, you agree to our <a href="#">Terms & Conditions</a> and
                            that you have read our <a href="#"> Privacy
                                Notice.</a></p>
                        <button class="ghost" id="signUp">Sign Up</button>
                    </div>
                </div>
            </div>

        </div>

        <c:if test="${requestScope.errorLogin != null}">
            <div id="status-message" style="background-color: rgb(253,233,231);height: 80px; margin-top: 0px;" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-error-circle' style="font-size: 50px;color: red;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%;text-align: start">
                        <h3 style="color: red;margin-top: 5px;font-weight: 550 ">Error</h3>
                        <p style="color: black;margin-top: -15px;font-size: 14px">${errorLogin}</p>
                    </div>
                </div>           
            </div>
        </c:if>
        <c:if test="${requestScope.successMessage != null}">
            <div id="status-message" style="background-color: rgb(233,251,233);height: 80px; margin-top: 0px;" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%; text-align: start">
                        <h3 style="color:green;margin-top: 5px;font-weight: 550 ">Success</h3>
                        <p style="color: black;margin-top: -15px;font-size: 14px">${successMessage}</p>
                    </div>
                </div>           
            </div>
        </c:if>
        <c:if test="${requestScope.updateMessage != null}">
            <div id="status-message" style="background-color: rgb(233,251,233); margin-top: 0px;" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%;text-align: start">
                        <h3 style="color:green;margin-top: 5px;font-weight: 550 ">Success</h3>
                        <p style="color: black;margin-top: -15px;font-size: 14px">${updateMessage}</p>
                    </div>
                </div>           
            </div>
        </c:if>
        <script src="js/Alter.js"></script>
        <script>
                            container.classList.add("left-panel-active");

                            //Khi nhấn vào button login => hiện container và hiện lớp overlay làm mờ
                            document.getElementById('showFormButton').onclick = function () {
                                container.classList.add("left-panel-active");
                                container.classList.remove("right-panel-active");
                                container.style.display = 'block';
                                overlayBackground.style.display = 'block';
                                // Thêm lớp 'show' để kích hoạt hiệu ứng trượt
                                setTimeout(() => {
                                    container.classList.add('show');
                                }, 150);
                            }

                            document.getElementById('overlayBackground').onclick = function () {
                                // Ẩn form và overlay
                                container.classList.remove('show');
                                setTimeout(() => {
                                    container.style.display = 'none';
                                    overlayBackground.style.display = 'none';
                                }, 500); // Thời gian chờ cho animation hoàn thành
                            }

                            // Để cho overlay trượt qua phải và trái
                            document.getElementById('signUp').onclick = function () {
                                container.classList.add("right-panel-active");
                            }
                            document.getElementById('signIn').onclick = function () {
                                container.classList.remove("right-panel-active");
                            }

                            document.getElementById('closeButton').onclick = function () {
                                // Ẩn form và overlay
                                container.classList.remove('show');
                                setTimeout(() => {
                                    container.style.display = 'none';
                                    overlayBackground.style.display = 'none';
                                }, 500); // Thời gian chờ cho animation hoàn thành
                            }


                            document.getElementById('showFormButton-register').onclick = function () {
                                // Kiểm tra nếu container đã có lớp 'right-panel-active'
                                container.classList.add("right-panel-active");
                                container.classList.remove("left-panel-active");
                                container.style.display = 'block';
                                overlayBackground.style.display = 'block';
                                // Thêm lớp 'show' để kích hoạt hiệu ứng trượt
                                setTimeout(() => {
                                    container.classList.add('show');
                                }, 150);
                            }

                            //Phần login_fix input
                            document.addEventListener('DOMContentLoaded', function () {
                                const inputs = document.querySelectorAll('.form-input');

                                inputs.forEach(input => {
                                    const label = input.nextElementSibling;

                                    // Không nhập gì thì label về top = 50% là về giữa input nếu không thì lên trên
                                    if (input.value.trim() === '') {
                                        label.style.top = '50%';
                                        label.style.color = '#999';
                                    } else {
                                        label.style.top = '5px';
                                        label.style.color = '#999';
                                    }


                                    input.addEventListener('input', function () {
                                        if (this.value.trim() !== '') {
                                            label.style.top = '5px';
                                            label.style.color = '#999';
                                        } else {
                                            label.style.top = '50%';
                                            label.style.color = '#999';
                                        }
                                    });


                                    input.addEventListener('focus', function () {
                                        label.style.top = '5px';
                                        label.style.color = '#999';
                                    });

                                    //blur là khi không còn focus
                                    input.addEventListener('blur', function () {
                                        if (this.value.trim() === '') {
                                            label.style.top = '50%';
                                            label.style.color = '#999';
                                        }
                                    });
                                });
                            });


                            function togglePasswordVisibility(passwordFieldId, toggleButtonId) {
                                const passwordField = document.getElementById(passwordFieldId);
                                const toggleButton = document.getElementById(toggleButtonId);

                                if (passwordField.type === "password") {
                                    passwordField.type = "text";
                                    toggleButton.innerHTML = "&#128064;"; // change icon to an open eye
                                } else {
                                    passwordField.type = "password";
                                    toggleButton.innerHTML = "&#128065;"; // change icon back to a closed eye
                                }
                            }
        </script>
    </body>

</html>