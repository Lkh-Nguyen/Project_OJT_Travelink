<%-- 
    Document   : account
    Created on : May 15, 2024, 8:59:13 PM
    Author     : HELLO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Password Account</title>

        <link rel="stylesheet" href="css/Right_My_Account.css">
        <link rel="stylesheet" href="css/Left_My_Account.css">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="css/Alter.css">

        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            /*Change password*/
            .disabled {
                pointer-events: none;
                opacity: 0.6;
            }
            button, input[type="submit"] {
                cursor: not-allowed;
            }
            .enabled {
                pointer-events: auto;
                opacity: 1;
            }
            button.enabled, input[type="submit"].enabled {
                cursor: pointer;
            }
            .password-container {
                position: relative;
                display: inline-block;
            }

            .toggle-password {
                user-select: none;
                position: absolute;
                right: 10px;
                top: 50%;
                transform: translateY(-50%);
                cursor: pointer;
                font-size: 18px;
                color: #888;
                transition: color 0.3s;
            }

            .toggle-password:hover {
                color: #333;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <!-- Dùng để đăng xuất-->
        <div id="overlay"></div>
        <div id="logoutConfirm">
            <h2>Logging Out</h2>
            <p>Oh, no! You’ll miss a lot of things by logging out: Traveloka Points, 
                Passenger Quick Pick, Price Alerts, and other member-only benefits.
                Are you sure want to log out?</p>
            <button id="confirmYes"><a href="logout">Yes</a></button>
            <button id="confirmNo"><a href="#">No</a></button>
        </div>
        <!-- Dùng để đăng xuất-->




        <div id="mid_container">
            <div id="left">
                <div id="header_left">
                    <div>
                        <img src="${account.avatarURL}" alt="alt"/>
                    </div>
                    <div style="margin-left: 10px">
                        <h1>${account.name}</h1>
                        <p>Google</p>
                    </div>
                </div>  
                <div id="header_list">
                    <div class="list1">
                        <a href="#"><i class='bx bx-cog'></i> <b>My Account</b></a>
                    </div>
                    <div class="list0">
                        <a href="View_Avatar.jsp"><i class='bx bx-image-add' ></i> <b>View Avatar</b></a>
                    </div>
                    <div class="list0">
                        <a href="MyFeedbackServlet"><i class='bx bx-calendar' ></i> <b>My Feedback</b></a>
                    </div>
                    <div class="list0">
                        <a href="All_Hotel_Service"><i class='bx bx-money-withdraw'></i> <b>Booking History</b></a>
                    </div>
                    <div class="list0">
                        <a href="ListFavoriteHotel"><i class='bx bx-heart-circle' ></i> <b>Favorite Hotel</b></a>
                    </div>
                    <div class="list0">
                        <a href="#"><i class='bx bx-log-out' ></i><b id="logoutButton">Logging Out</b></a>
                    </div>
                </div>
            </div>
            <div id="right">
                <h1>Settings</h1>
                <div id="list_right">
                    <ul>
                        <li id="li2"><a href="My_Account_Update.jsp">Account Information</a></li>
                            <c:if test="${sessionScope.account.password != null}">
                            <li id="li1">Password & Security</li>
                            </c:if>
                    </ul>
                </div>

                <div id="person_data1">
                    <div id="pd_h2">
                        <h2>Change Password</h2>
                    </div>

                    <!-- Change -->
                    <div id="pd_data">
                        <form id="passwordForm" action="ChangeAccountPasswordServlet" method="post">
                            <div class="pd_flex">
                                <div class="flex1">
                                    <p>Old Password</p>
                                    <div class="password-container">
                                        <input type="password" name="password" id="oldpassword"  placeholder="Enter Old Password In Here" required minlength="8">
                                        <span id="toggleOldPassword" class="toggle-password" onclick="togglePasswordVisibility('oldpassword', 'toggleOldPassword')">&#128065;</span>
                                    </div>
                                </div>
                            </div>
                            <div class="pd_flex">
                                <div class="flex1"> 
                                    <p>New Password</p>
                                    <div class="password-container">
                                        <input type="password" name="newpassword" id="newpassword" placeholder="Enter New Password In Here" required minlength="8">
                                        <span id="toggleNewPassword" class="toggle-password" onclick="togglePasswordVisibility('newpassword', 'toggleNewPassword')">&#128065;</span>
                                    </div>

                                </div>
                                <div class="flex1">
                                    <p>Enter Again New Password</p>
                                    <div class="password-container">
                                        <input type="password" name="re_newpassword" id="re_newpassword" placeholder="Enter Again New Password In Here" required minlength="8">
                                        <span id="toggleReNewPassword" class="toggle-password" onclick="togglePasswordVisibility('re_newpassword', 'toggleReNewPassword')">&#128065;</span>
                                    </div>
                                </div>
                            </div>
                            <c:if test="${requestScope.error != null}">
                                <div id="status-message" style="background-color: rgb(253,233,231);height: 70px;" class="hidden">
                                    <div style="display: flex">
                                        <div style="width: 20%">
                                            <i class='bx bxs-error-circle' style="font-size: 50px;color: red;margin-top: 0px"></i>
                                        </div>
                                        <div style="width: 80%;text-align: start">
                                            <h3 style="color: red;margin-top: 5px;font-weight: 550 ">Error</h3>
                                            <p style="color: black;margin-top: -15px;font-size: 14px">${error}</p>
                                        </div>
                                    </div>           
                                </div>
                            </c:if>
                            <c:if test="${requestScope.updateStatus != null}">
                                <div id="status-message" style="background-color: rgb(233,251,233)" class="hidden">
                                    <div style="display: flex">
                                        <div style="width: 20%">
                                            <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                                        </div>
                                        <div style="width: 80%; text-align: start">
                                            <h3 style="color:green;margin-top: 5px;font-weight: 550 ">Success</h3>
                                            <p style="color: black;margin-top: -15px;font-size: 14px">${updateStatus}</p>
                                        </div>
                                    </div>           
                                </div>
                            </c:if>
                            <c:if test="${requestScope.updateMessage != null}">
                                <div id="status-message" style="background-color: rgb(233,251,233)" class="hidden">
                                    <div style="display: flex">
                                        <div style="width: 20%">
                                            <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                                        </div>
                                        <div style="width: 80%; text-align: start">
                                            <h3 style="color:green;margin-top: 5px;font-weight: 550 ">Success</h3>
                                            <p style="color: black;margin-top: -15px;font-size: 14px">${updateMessage}</p>
                                        </div>
                                    </div>           
                                </div>
                            </c:if>
                            <div class="pd_button">
                                <button type="button" id="cancelButton" class="disabled" onclick="cancel()">Cancel</button>
                                <input type="submit" id="saveButton" class="disabled" value="Save"/>
                            </div>
                        </form>
                    </div>
                    <!---->
                </div>
                <div id="person_data2" style="display: none">
                    <div id="pd_h2">
                        <h2>Delete Account</h2>             
                    </div>
                    <div id="pd_h2_content">
                        <p>Once your account is deleted, you won't be able to get your data back. This operation cannot be undone.</p>
                        <form>
                            <input type="submit" value="Delete">
                        </form>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp"%>
        <script src="js/Alter.js"></script>
        <script>
                                    function cancel() {
                                        location.reload();
                                        event.preventDefault();
                                    }
                                    document.getElementById("logoutButton").addEventListener("click", function () {
                                        document.getElementById("overlay").style.display = "block";
                                        var logoutConfirm = document.getElementById("logoutConfirm");
                                        logoutConfirm.style.display = "block"; // Hiển thị khung xác nhận
                                        setTimeout(function () {
                                            logoutConfirm.classList.add("active");
                                        }, 50);
                                    });

                                    document.getElementById("confirmNo").addEventListener("click", function () {
                                        var logoutConfirm = document.getElementById("logoutConfirm");
                                        logoutConfirm.classList.remove("active");
                                        setTimeout(function () {
                                            logoutConfirm.style.display = "none"; // Ẩn khung xác nhận
                                            document.getElementById("overlay").style.display = "none";
                                        }, 500);
                                    });

                                    document.getElementById("overlay").addEventListener("click", function () {
                                        var logoutConfirm = document.getElementById("logoutConfirm");
                                        logoutConfirm.classList.remove("active");
                                        setTimeout(function () {
                                            logoutConfirm.style.display = "none"; // Ẩn khung xác nhận
                                            document.getElementById("overlay").style.display = "none";
                                        }, 500);
                                    });



                                    /*Change password*/

                                    document.addEventListener('DOMContentLoaded', function () {
                                        const form = document.getElementById('passwordForm');
                                        const newPassword = document.getElementById('newpassword');
                                        const reNewPassword = document.getElementById('re_newpassword');
                                        const saveButton = document.getElementById('saveButton');
                                        const cancelButton = document.getElementById('cancelButton');

                                        function checkPasswords() {
                                            if (newPassword.value.trim() !== '' && reNewPassword.value.trim() !== '') {
                                                saveButton.classList.remove('disabled');
                                                saveButton.classList.add('enabled');
                                                cancelButton.classList.remove('disabled');
                                                cancelButton.classList.add('enabled');
                                            } else {
                                                saveButton.classList.remove('enabled');
                                                saveButton.classList.add('disabled');
                                                cancelButton.classList.remove('enabled');
                                                cancelButton.classList.add('disabled');
                                            }
                                        }

                                        newPassword.addEventListener('input', checkPasswords);
                                        reNewPassword.addEventListener('input', checkPasswords);

                                        // Initial check in case some fields are pre-filled
                                        checkPasswords();
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
