<%-- 
    Document   : Header_HotelHost
    Created on : Jun 14, 2024, 9:29:44 PM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/Home_HotelHost.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
            * {
                font-family: Montserrat, sans-serif;
            }

            .button {
                position: relative;
                transition: all 0.3s ease-in-out;
                box-shadow: 0px 10px 20px rgba(0, 0, 0, 0.2);
                padding-block: 0.5rem;
                padding-inline: 1.25rem;
                background-color: rgb(0 107 179);
                border-radius: 9999px;
                display: flex;
                align-items: center;
                justify-content: center;
                color: #ffff;
                gap: 10px;
                font-weight: bold;
                border: 3px solid #ffffff4d;
                outline: none;
                overflow: hidden;
                font-size: 15px;
            }

            .icon {
                width: 24px;
                height: 24px;
                transition: all 0.3s ease-in-out;
            }

            .button:hover {
                transform: scale(1.05);
                border-color: #fff9;
            }

            .button:hover .icon {
                transform: translate(4px);
            }

            .button:hover::before {
                animation: shine 1.5s ease-out infinite;
            }

            .button::before {
                content: "";
                position: absolute;
                width: 100px;
                height: 100%;
                background-image: linear-gradient(
                    120deg,
                    rgba(255, 255, 255, 0) 30%,
                    rgba(255, 255, 255, 0.8),
                    rgba(255, 255, 255, 0) 70%
                    );
                top: 0;
                left: -100px;
                opacity: 0.6;
            }

            @keyframes shine {
                0% {
                    left: -100px;
                }

                60% {
                    left: 100%;
                }

                to {
                    left: 100%;
                }
            }

        </style>
    </head>
    <body>
        <!-- Phần navbar -->
        <nav class="navbar navbar-expand-lg navbar-light bg-light justify-content-md-around">
            <div class="card bg-light border-0" id="titleHotel">
                <div class="row justify-content-center align-items-center">
                    <div class="col-auto">
                        <a href="homeHotelHostServlet"><img src="img_Home/logo.png" width="50" height="60" class="d-inline-block align-top" alt=""></a>
                    </div>
                    <div class="col-auto">
                        <div class="nav__logo"><a href="homeHotelHostServlet">Trave<span>link</span></a></div>
                    </div>
                </div>
            </div>
            <div class="ml-2 justify-content-end align-content-end align-items-end">
                <c:set var="account" value="${sessionScope.account}"></c:set>
                <c:if test="${not empty account and account.role == 2}">
                    <ul class="navbar-nav">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle d-flex align-items-center btn btn-outline-secondary p-2" href="#" id="userDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                <img src="${account.avatarURL}" width="30" height="30" class="rounded-circle m-1" alt="">
                                <span class="ml-2">${account.name}<i class='bx bxs-check-shield px-1' style="font-size: 1rem; color: green;"></i></span>
                            </a>
                            <div class="dropdown-menu dropdown-menu-right" aria-labelledby="userDropdown">
                                <a class="dropdown-item" href="HotelHost_Profile.jsp">Show Profile</a>
                                <a class="dropdown-item" href="logout">Logout</a>
                            </div>
                        </li>
                    </ul>
                </c:if>
                <c:if test="${empty account || account.role != 2}">
                    <button class="button" onclick="location.href = 'HotelHost_Login.jsp'">
                        GET STARTED
                        <svg fill="currentColor" viewBox="0 0 24 24" class="icon">
                        <path clip-rule="evenodd" d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm4.28 10.28a.75.75 0 000-1.06l-3-3a.75.75 0 10-1.06 1.06l1.72 1.72H8.25a.75.75 0 000 1.5h5.69l-1.72 1.72a.75.75 0 101.06 1.06l3-3z" fill-rule="evenodd"></path>
                        </svg>
                    </button>
                </c:if>
            </div>
        </nav>

        <!-- Phần hàng thứ nhì -->
        <div class="row bg-light border-top p-3">
            <div class="col-md-1 d-flex justify-content-around align-items-center" id="containIcon">
                <i class='bx bx-home' style='font-size: 2rem;'></i>
            </div>
            <div class="col-md-7 d-flex align-items-center">
                Welcome back, <span class="px-1 fw-semibold">${account.name}<i class='bx bxs-check-shield px-1' style="font-size: 1rem; color: green;"></i></span>
            </div>
            <c:if test="${not empty account}">
                <div class="col-md-4 d-flex align-items-center justify-content-end">
                    <i class="bx bx-signal-4" style="font-size: 2rem; color: #007bff;"></i>
                    <span><a href="ChatServlet" style="font-weight: bold">Contact with Admin</a></span>
                </div>
            </c:if>
        </div>
    </body>
</html>



