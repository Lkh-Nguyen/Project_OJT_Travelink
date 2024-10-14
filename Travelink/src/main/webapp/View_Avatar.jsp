<%-- 
Document   : account
Created on : May 15, 2024, 8:59:13 PM
Author     : HELLO
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View Avatar</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="css/Left_My_Account.css">
        <link rel="stylesheet" href="css/View_Avata.css">
        <link rel="stylesheet" href="css/Alter.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <!-- Dùng để đăng xuất-->
        <style>
            /*Image*/
            #ImgAvatar {
                display: none; /* Ẩn mặc định */
                border-radius: 10px;
                position: fixed;
                bottom: 250px;
                left: 50%;
                transform: translateX(-50%);
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                z-index: 1000;
                transition: bottom 0.5s ease;
            }
            #ImgAvatar img{

                width: 500px;
                height: 500px;
                cursor: pointer;
            }
            #logoutConfirm.active {
                bottom: 50%;
                transform: translate(-50%, 50%);
            }

        </style>
        <!-- Dùng để đăng xuất-->
    </head>
    <body>
        <%@include file="Header.jsp" %>
        <!-- Dùng để đăng xuất-->
        <div id="overlay"></div>
        <%
            String uploadedFilePath = (String) request.getAttribute("uploadedFilePath");
        %>
        <div id="logoutConfirm">
            <h2>Logging Out</h2>
            <p>Oh, no! You’ll miss a lot of things by logging out: Travelink Points, 
                Passenger Quick Pick, Price Alerts, and other member-only benefits.
                Are you sure want to log out?</p>
            <button id="confirmYes"><a href="logout">Yes</a></button>
            <button id="confirmNo"><a href="#">No</a></button>
        </div>
        <!-- Dùng để đăng xuất-->

        <div id="ImgAvatar">

            <%
                if (uploadedFilePath == null || uploadedFilePath.isEmpty()) {
            %>
            <img src="${account.avatarURL}" alt="alt"/>
            <%
                }
            %>
            <%
                if (uploadedFilePath != null && !uploadedFilePath.isEmpty()) {
            %>
            <img src="img_Avatar/<%= uploadedFilePath %>" alt="alt"/>
            <%
                }
            %>
        </div>
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
                    <div class="list0">
                        <a href="My_Account_Update.jsp"><i class='bx bx-cog'></i> <b>My Account</b></a>
                    </div>
                    <div class="list1">
                        <a href="#"><i class='bx bx-image-add' ></i> <b>View Avatar</b></a>
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
                <div id="data_img">
                    <div id="pd_h2">
                        <h2>View Avatar</h2>
                    </div>
                    <div id="edit_img" style="user-select: none;">
                        <% if (uploadedFilePath == null || uploadedFilePath.isEmpty()) { %>
                        <img src="${account.avatarURL}" alt="123"/><br>
                        <% } else { %>
                        <img src="img_Avatar/<%= uploadedFilePath %>" alt="123"/><br>
                        <% } %>
                        <button id="seeAvatar" onclick="hello()">View Avatar</button>
                        <form method="post" action="UploadImageAvatar" enctype="multipart/form-data" onsubmit="return validateForm(event)">
                            <input type="file" name="file" size="60" accept=".jpeg,.jpg,.png"/><br/>
                            <p>Maximum file size is 1 MB.<br>Format: .JPEG, .PNG</p>
                            <input id="submit_Input" style="margin-top:10px" type="submit" value="Upload"/>
                            <div id="error-message">Please select a file to upload.</div>
                        </form>

                        <form method="post" action="UpdateAvatar">
                            <c:choose>
                                <c:when test="${not empty uploadedFilePath}">
                                    <input type="hidden" name="urlAvatar"  value="/Travelink/img_Avatar/${uploadedFilePath}"/>
                                    <div class="pd_button">
                                        <button><a href="View_Avatar.jsp">Cancel</a></button>
                                        <input type="submit" value="Save"/>
                                    </div>  
                                </c:when>
                                <c:otherwise>
                                    <input type="hidden" name="urlAvatar" value=""/>
                                    <div class="pd_button">
                                        <button style="pointer-events: none;cursor: not-allowed;opacity: 0.6;">Cancel</button>
                                        <input type="submit" value="Save" style="pointer-events: none;cursor: not-allowed;opacity: 0.6;"/>
                                    </div>
                                </c:otherwise>
                            </c:choose>
                        </form>
                        <c:if test="${requestScope.updateStatus != null}">
                            <div id="status-message" style="background-color: rgb(233,251,233)" class="hidden">
                                <div style="display: flex">
                                    <div style="width: 20%">
                                        <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                                    </div>
                                    <div style="width: 80%;text-align: start">
                                        <h3 style="color:green;margin-top: 5px;font-weight: 550">Success</h3>
                                        <p style="color: black;margin-top: -15px;font-size: 14px">${updateStatus}</p>
                                    </div>
                                </div>           
                            </div>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp"%>
        <script>
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
                var ImgAvatar = document.getElementById("ImgAvatar");
                ImgAvatar.classList.remove("active");
                logoutConfirm.classList.remove("active");
                setTimeout(function () {
                    ImgAvatar.style.display = "none"; // Ẩn khung xác nhận
                    document.getElementById("overlay").style.display = "none";
                }, 500);
                setTimeout(function () {
                    logoutConfirm.style.display = "none"; // Ẩn khung xác nhận
                    document.getElementById("overlay").style.display = "none";
                }, 500);
            });


            document.getElementById("seeAvatar").addEventListener("click", function () {
                document.getElementById("overlay").style.display = "block";
                var ImgAvatar = document.getElementById("ImgAvatar");
                ImgAvatar.style.display = "block"; // Hiển thị khung xác nhận
                setTimeout(function () {
                    ImgAvatar.classList.add("active");
                }, 50);
            });

            function validateForm(event) {
                var fileInput = document.querySelector('input[type="file"]');
                var errorMessage = document.getElementById('error-message');
                var data_img = document.getElementById("data_img");
                if (!fileInput.value) {
                    errorMessage.style.display = 'block';
                    data_img.style.height = "515px";
                    return false;
                }
                errorMessage.style.display = 'none';
                data_img.style.height = "480px";
                return true;
            }

        </script>
        <script src="js/Alter.js"></script>
    </body>
</html>