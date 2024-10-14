<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="jakarta.servlet.http.HttpSession" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP 1</title>
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="icon" href="img_Home/logo.png">
        <style>
            @import url("https://fonts.googleapis.com/css2?family=Poppins:wght@400;500;600;700;800&display=swap");

            :root {
                --primary-color: #3d5cb8;
                --primary-color-dark: #334c99;
                --text-dark: #0f172a;
                --text-light: #64748b;
                --extra-light: #f1f5f9;
                --white: #ffffff;
                --max-width: 1200px;
            }


            .btns {
                cursor: pointer;
                user-select: none;
                position: relative;
            }

            .btns .btn {
                display: flex;
                align-items: center;
                text-decoration: none;
                color: #333;
                /*                background-color: #fff;*/
                padding: 10px 15px;
                /*                border: 1px solid #ddd;*/
                border-radius: 5px;
                /*                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);*/
                transition: background-color 0.3s, box-shadow 0.3s;
            }
            .btns .btn:hover {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
            }

            .loginBtn {
                font-size: 17px;
                white-space: nowrap;         /* Prevent line breaks */
                overflow: hidden;            /* Hide overflow content */
                text-overflow: ellipsis;     /* Display "..." when content overflows */
                max-width: 400px;            /* Set a maximum width */
            }
            .btn img {
                border-radius: 50%;
                margin-right: 10px;
                width: 30px;
                height: 30px;
            }

            .dropdown {
                display: none;
                position: absolute;
                top: 50px;
                left: 0;
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                z-index: 1;
                min-width: 200px;
            }

            .dropdown a {
                display: flex;
                align-items: center;
                padding: 10px 15px;
                text-decoration: none;
                color: #333;
                font-size: 1.2rem;
                transition: background-color 0.3s;
            }

            .dropdown a:hover {
                background-color: #f0f0f0;
            }

            .dropdown a i {
                margin-right: 10px;
            }

            .show {
                display: block;
            }


            nav {
                max-width: 100%;
                margin: auto;
                padding: 1rem;
                display: flex;
                align-items: center;
                justify-content: space-between;
                gap: 1rem;
                background-color: rgba(0, 0, 0, 0.05);
            }

            .nav__logo {
                font-size: 1.8rem;
                font-weight: 600;
                color: var(--text-dark);
            }
            .nav_logo a{
                text-decoration: none;
            }
            .nav__logo a:active{

                color:blue;
            }
            span{
                color:var(--primary-color-dark)
            }
            a{
                text-decoration: none;
            }
            .nav__links {
                list-style: none;
                display: flex;
                align-items: center;
                gap: 4rem;
                margin: 0 3rem 0 4rem;
            }

            .link a {
                font-size: 1.2rem;
                font-weight: 550;
                color: var(--text-light);
                transition: 0.3s;
            }

            .link a:hover {
                color: var(--text-dark);
            }

            #overlay1 {
                position: fixed;
                top: 0;
                left: 0;
                width: 100%;
                height: 100%;
                background-color: rgba(0, 0, 0, 0.5)    ;
                display: none;
                z-index: 999;
            }

            #logoutConfirm1 {
                display: none; /* Ẩn mặc định */
                border-radius: 10px;
                width: 280px;
                position: fixed;
                bottom: -300px;
                left: 50%;
                transform: translateX(-50%);
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                z-index: 1000;
                transition: bottom 0.5s ease;
            }
            #logoutConfirm1 h2{
                margin-top:-10px;
            }
            #logoutConfirm1 p{
                font-size: 20px;
                color: grey;
            }
            #logoutConfirm1 button{
                background-color:rgb(247,249,250);
                border: 0px;
                margin-bottom: 15px;
                width: 100%;
                height: 40px;
            }
            #logoutConfirm1 button:hover{
                background-color:rgb(242,243,243);
            }
            #logoutConfirm1 button:active{
                border: 3px solid rgb(1,148,243);
            }
            #logoutConfirm1 button a{
                font-size: 17px;
                user-select: none;
                color: rgb(1,148,243);
                text-decoration: none;
            }

            #logoutConfirm1.active {
                bottom: 50%;
                transform: translate(-50%, 50%);
            }

            .btn1 {
                padding: 0.75rem 2rem;
                outline: none;
                border: none;
                font-size: 1rem;
                font-weight: 600;
                color: var(--white);
                background-color: var(--primary-color);
                border-radius: 2rem;
                cursor: pointer;
                transition: 0.3s;
            }

            .btn1:hover {
                background-color: var(--primary-color-dark);
            }

            .loginBtn1 a{
                color: white;
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
            /*            Notification*/
            .notification {
                position: relative;
                cursor: pointer;
            }

            #dropdownMenu {
                display: none; /* Hide by default */
                position: absolute;
                background-color: white;
                min-width: 200px;
                box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);
                z-index: 1;
            }

            .notification-item {
                color: black;
                padding: 12px 16px;
                text-decoration: none;
                display: block;
            }

            .notification-item:hover {
                background-color: #f1f1f1;
            }

            .notification .bx-bell {
                font-size: 1.5rem;
                color: var(--text-dark);
            }

            .notification #notificationCount {
                position: absolute;
                top: -5px;
                right: -5px;
                background-color: red;
                color: white;
                border-radius: 50%;
                padding: 2px 6px;
                font-size: 0.5rem;
            }

            .notification-dropdown {
                display: none;
                position: absolute;
                top: 30px;
                right: 0;
                background-color: #fff;
                border: 1px solid #ddd;
                border-radius: 5px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                z-index: 1;
                min-width: 200px;
                max-height: 300px;
                overflow-y: auto;
            }

            .notification-dropdown .notification-item {
                padding: 10px;
                border-bottom: 1px solid #ddd;
            }

            .notification-dropdown .notification-item:last-child {
                border-bottom: none;
            }

            .notification-dropdown .notification-item p {
                margin: 0;
            }

            .notification-dropdown .notification-item span {
                font-size: 0.8rem;
                color: var(--text-light);
            }
            #notificationDiv{
                max-height: 15rem;
            }

        </style>
    </head>
    <body>
        <!-- Dùng để đăng xuất-->
        <div id="overlay1"></div>
        <div id="logoutConfirm1">
            <h2>Logging Out</h2>
            <p>Oh, no! You’ll miss a lot of things by logging out: Travelink Points, 
                Passenger Quick Pick, Price Alerts, and other member-only benefits.
                Are you sure want to log out?</p>
            <button id="confirmYes1"><a href="logout">Yes</a></button>
            <button id="confirmNo1"><a href="#">No</a></button>
        </div>
        <!-- Dùng để đăng xuất-->
        <header>
            <nav>
                <div class="nav__logo"><a href="homeCustomerServlet">Trave<span>link</span></a></div>
                <ul class="nav__links">
                    <li class="link"><a href="homeCustomerServlet">Home</a></li>
                        <c:if test="${not empty account and account.role == 1}">
                        <li class="link"><a href="ChatServlet">Contact</a></li>
                        </c:if>
                    <li class="link"><a href="All_Hotel_Service">Transaction History</a></li>
                    <li class="link"><a href="MyFeedbackServlet">My Feedback</a></li>
                    <li class="link"><a href="ListFavoriteHotel">Favorite Hotels</a></li>
                </ul>
                <c:set var="account" value="${sessionScope.account}"></c:set>
                <c:if test="${not empty account and account.role == 1}">
                    <!-- Notification bell -->
                    <div class="notification">
                        <i class='bx bx-bell' id="notificationBell"></i>
                        <span id="notificationCount">${requestScope.notificationCount}</span>
                        <div id="notificationDropdownMenu" class="dropdown">
                            <div id="notificationDiv" style="height: 10rem; overflow-y: auto;">
                                <c:forEach items="${requestScope.notifications}" var="n">
                                    <div class="notification-item">${n.message}</div>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <div class="btns">
                        <a class="btn loginBtn" style="margin-right: 40px;" onclick="toggleDropdown()"><img src="${account.avatarURL}" alt="alt"/>${account.name}</a>
                        <div id="userDropdownMenu" class="dropdown">
                            <a href="My_Account_Update.jsp"><i class='bx bx-user'></i>Edit Profile</a>
                            <a href="#" id="logoutButton1"><i class='bx bx-log-out'></i>Logging Out</a>
                        </div>
                    </div>
                </c:if>
                <c:if test="${empty account or account.role != 1}">
                    <button class="button" onclick="forward()">
                        GET STARTED
                        <svg fill="currentColor" viewBox="0 0 24 24" class="icon">
                        <path clip-rule="evenodd" d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm4.28 10.28a.75.75 0 000-1.06l-3-3a.75.75 0 10-1.06 1.06l1.72 1.72H8.25a.75.75 0 000 1.5h5.69l-1.72 1.72a.75.75 0 101.06 1.06l3-3z" fill-rule="evenodd"></path>
                        </svg>
                    </button>
                    <button class="button" onclick="location.href = 'Home_HotelHost.jsp'">
                        BECOME PARTNER
                        <svg fill="currentColor" viewBox="0 0 24 24" class="icon">
                        <path clip-rule="evenodd" d="M12 2.25c-5.385 0-9.75 4.365-9.75 9.75s4.365 9.75 9.75 9.75 9.75-4.365 9.75-9.75S17.385 2.25 12 2.25zm4.28 10.28a.75.75 0 000-1.06l-3-3a.75.75 0 10-1.06 1.06l1.72 1.72H8.25a.75.75 0 000 1.5h5.69l-1.72 1.72a.75.75 0 101.06 1.06l3-3z" fill-rule="evenodd"></path>
                        </svg>
                    </button>
                </c:if>

            </nav>
        </header>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
                        function forward() {
            <% request.getSession().setAttribute("role", 1); %>
                            location.href = 'Form_Login.jsp';
                        }

                        function toggleDropdown() {
                            document.getElementById("userDropdownMenu").classList.toggle("show");
                        }

                        // Close the dropdown menu if the user clicks outside of it
                        window.onclick = function (event) {
                            if (!event.target.matches('.loginBtn')) {
                                var dropdowns = document.getElementsByClassName("dropdown");
                                for (var i = 0; i < dropdowns.length; i++) {
                                    var openDropdown = dropdowns[i];
                                    if (openDropdown.classList.contains('show')) {
                                        openDropdown.classList.remove('show');
                                    }
                                }
                            }
                        }

                        document.getElementById("logoutButton1").addEventListener("click", function () {
                            document.getElementById("overlay1").style.display = "block";
                            var logoutConfirm = document.getElementById("logoutConfirm1");
                            logoutConfirm.style.display = "block"; // Show confirmation box
                            setTimeout(function () {
                                logoutConfirm.classList.add("active");
                            }, 50);
                        });

                        document.getElementById("confirmNo1").addEventListener("click", function () {
                            var logoutConfirm = document.getElementById("logoutConfirm1");
                            logoutConfirm.classList.remove("active");
                            setTimeout(function () {
                                logoutConfirm.style.display = "none"; // Hide confirmation box
                                document.getElementById("overlay1").style.display = "none";
                            }, 500);
                        });

                        document.getElementById("overlay1").addEventListener("click", function () {
                            var logoutConfirm = document.getElementById("logoutConfirm1");
                            logoutConfirm.classList.remove("active");
                            setTimeout(function () {
                                logoutConfirm.style.display = "none"; // Hide confirmation box
                                document.getElementById("overlay1").style.display = "none";
                            }, 500);
                        });

                        // Handle notification dropdown
                        document.getElementById('notificationBell').addEventListener('click', function () {
                            var dropdownMenu = document.getElementById('notificationDropdownMenu');
                            if (dropdownMenu.style.display === "none" || dropdownMenu.style.display === "") {
                                dropdownMenu.style.display = "block";
                            } else {
                                dropdownMenu.style.display = "none";
                            }
                        });

                        // Close the dropdown if the user clicks outside of it
                        window.onclick = function (event) {
                            if (!event.target.matches('#notificationBell')) {
                                var dropdowns = document.getElementsByClassName("dropdown");
                                for (var i = 0; i < dropdowns.length; i++) {
                                    var openDropdown = dropdowns[i];
                                    if (openDropdown.style.display === "block") {
                                        openDropdown.style.display = "none";
                                    }
                                }
                            }
                        }
                        //Handle Scroll
                        var notificationBell = document.getElementById('notificationBell');
                        notificationBell.onclick = function () {
                            scrollToBottom();

                        }
                        $(document).ready(function () {
                            $.ajax({
                                url: 'NotificationServlet',
                                type: 'GET',
                                data: {},
                                success: function (res) {
                                    var notificationCount = res.notificationCount;
                                    $('#notificationCount').text(notificationCount);
                                },
                                error: function (xhr, status, error) {
                                    console.error('Error fetching notifications:', error);
                                }
                            });
                        });
                        var notificationDiv = document.getElementById('notificationDiv');
                        function scrollToBottom() {
                            notificationDiv.scrollTop = notificationDiv.scrollHeight;
                        }
                        // Automatically scroll to bottom on new notifications
                        var observer = new MutationObserver(scrollToBottom);
                        observer.observe(notificationDiv, {childList: true});

        </script>
    </body>
</html>
