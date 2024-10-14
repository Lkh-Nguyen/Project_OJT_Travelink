<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Avatar Management</title>
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/View_Avata.css">
        <link rel="stylesheet" href="css/Alter.css">
        <link rel="stylesheet" href="css/Left_My_Account.css">
        <style>
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

            #ImgAvatar img {
                width: 500px;
                height: 500px;
                cursor: pointer;
            }

            #ImgAvatar .close-btn {
                position: absolute;
                top: 10px;
                right: 10px;
                background: transparent;
                color: #000;
                border: none;
                font-size: 24px;
                font-weight: bold;
                cursor: pointer;
                z-index: 1001;
            }
            #ImgAvatar .close-btn:hover {
                color: grey; /* Change color on hover */
                transform: scale(1.2); /* Slightly enlarge the button on hover */
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
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
            <!-- Close button -->
            <button class="close-btn" onclick="closeAvatar()">×</button>
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
        <div class="container">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2 mb-4"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 justify-content-center align-items-center" style="border-radius: 30px;">
                <ul class="nav nav-underline mb-4">
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="HotelHost_Profile.jsp">Information</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" aria-current="page" href="#">View Avatar</a>
                    </li>
                    <li class="nav-item">
                        <a class="nav-link" href="HotelHost_ChangePassword.jsp">Change Password</a>
                    </li>
                </ul>
                <!-- Avatar Upload Section -->
                <div class="card w-75 h-100 shadow p-lg-5 mb-5 mt-3 bg-body-tertiary border-0" style="border-radius: 30px;">
                    <div class="row">
                        <div id="edit_img" style="user-select: none;">
                            <% if (uploadedFilePath == null || uploadedFilePath.isEmpty()) { %>
                            <img src="${account.avatarURL}" alt="123"/><br>
                            <% } else { %>
                            <img src="img_Avatar/<%= uploadedFilePath %>" alt="123"/><br>
                            <% } %>
                            <button id="seeAvatar" style="width: 120px;height: 35px">View Avatar</button>
                            <form method="post" action="UploadImageAvatar" enctype="multipart/form-data" onsubmit="return validateForm(event)">
                                <input type="file" name="file" size="60" accept=".jpeg,.jpg,.png"/><br/>
                                <p>Maximum file size is 1 MB.<br>Format: .JPEG, .PNG</p>
                                <input id="submit_Input" style="margin-top:10px" type="submit" value="Upload"/>
                                <div id="error-message">Please select a file to upload.</div>
                                <input type="hidden" name="uploadhotelhost" value="2.1"/>
                            </form>

                            <form method="post" action="UpdateAvatar">
                                <c:choose>
                                    <c:when test="${not empty uploadedFilePath}">
                                        <input type="hidden" name="urlAvatar"  value="/Travelink/img_Avatar/${uploadedFilePath}"/>
                                        <div class="pd_button">
                                            <button><a href="HotelHost_ViewAvatar.jsp" style="text-decoration: none">Cancel</a></button>
                                            <input type="submit" value="Save"/>
                                        </div>  
                                        <input type="hidden" name="uploadhotelhost" value="2.1"/>
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
        </div>
        <%@include file="Footer.jsp" %>

        <script>
            document.getElementById("overlay").addEventListener("click", function () {
                closeAvatar();
            });

            document.getElementById("seeAvatar").addEventListener("click", function () {
                document.getElementById("overlay").style.display = "block";
                var ImgAvatar = document.getElementById("ImgAvatar");
                ImgAvatar.style.display = "block"; // Hiển thị khung xác nhận
                setTimeout(function () {
                    ImgAvatar.classList.add("active");
                }, 50);
            });

            function closeAvatar() {
                var ImgAvatar = document.getElementById("ImgAvatar");
                ImgAvatar.classList.remove("active");
                setTimeout(function () {
                    ImgAvatar.style.display = "none"; // Ẩn khung xác nhận
                    document.getElementById("overlay").style.display = "none";
                }, 500);
            }

            function validateForm(event) {
                var fileInput = document.querySelector('input[name="file"]');
                var errorMessage = document.getElementById('error-message');
                if (!fileInput.value) {
                    errorMessage.style.display = 'block';
                    event.preventDefault();
                    return false;
                }
                errorMessage.style.display = 'none';
                return true;
            }
        </script>

        <script src="js/Alter.js"></script>
    </body>
</html>
