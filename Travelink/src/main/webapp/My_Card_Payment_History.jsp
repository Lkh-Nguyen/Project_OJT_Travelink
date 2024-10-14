<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Booking History</title>
        <link rel="stylesheet" href="css/Right_My_Account.css">
        <link rel="stylesheet" href="css/Left_My_Account.css">
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="css/Alter.css">
        <link rel="stylesheet" href="css/Loader.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href='bootstrap_css/bootstrap.min.css' rel='stylesheet'>
        <style>
            /* Định dạng chung cho card */
            .hotel-card {
                transition: transform 0.3s ease;
            }

            .hotel-card:hover {
                transform: translateY(-5px);
            }

            /* Định dạng nội dung trong card */
            .hotel-card .card-body {
                height: 100%;
                display: flex;
                flex-direction: column;
            }

            .hotel-card .card-title {
                font-size: 1.25rem;
                font-weight: bold;
                margin-bottom: 0.5rem;
            }

            .hotel-card .card-text {
                margin-bottom: 0.5rem;
            }

            .hotel-card .btn-primary {
                margin-top: auto;
                /* Đưa button xuống dưới nếu nội dung ngắn */
                align-self: center;
            }

            .card {
                width: 300px;
                /* Đặt chiều rộng cố định cho card */
                border: 1px solid rgb(0, 0, 0);

            }

            .card-body {
                padding: 1rem;
                /* Đặt padding cho card-body */
            }

            .card-title {
                font-size: 1.25rem;
                /* Kích thước tiêu đề cố định */
                font-weight: bold;
                /* Font-weight cho tiêu đề */
            }

            .card-text {
                font-size: 1rem;
                /* Kích thước văn bản cố định */
            }

            .card-text {
                overflow: hidden;
                /* Ẩn văn bản dư thừa */
                text-overflow: ellipsis;
                /* Hiển thị dấu elipsis cho văn bản dài */
                display: -webkit-box;
                /* Sử dụng display -webkit-box để có thể sử dụng line-clamp */
                -webkit-line-clamp: 3;
                /* Đặt số dòng hiển thị cụ thể (ví dụ 3 dòng) */
                -webkit-box-orient: vertical;
                /* Chiều dọc cho box */
            }

            .card:hover {
                box-shadow: 0 8px 16px rgba(0, 0, 0, 0.2);
                transition: transform 0.5s ease;
            }
            /*Change*/
            .disabled {
                pointer-events: none;
                opacity: 0.6;
            }

            button,
            input[type="submit"] {
                cursor: not-allowed;
            }

            .enabled {
                pointer-events: auto;
                opacity: 1;
            }

            button.enabled,
            input[type="submit"].enabled {
                cursor: pointer;
            }


            .btn-primary:active {
                background-color: rgb(247, 249, 250);
            }
            .btn-primary:hover {
                background-color: rgb(247,249,250);
                cursor: pointer;
            }
            .list0_r{
                justify-content: center;
                user-select: none; /* Ngăn chặn người dùng bôi đậm nội dung */
                display: flex;
                justify-items: center;
                align-items: center;
                width: 100px;
                height: 50px;
                padding-left: 20px;
            }
            .list0_r i{
                font-size: 23px;
                margin-right: 15px;
                color: rgb(1,148,243);
                font-weight: 400;
            }
            .list0_r b{
                font-size: 23px;
                color: black;
                font-weight: 400;
            }
            .list0_r a{
                text-decoration: none;
            }
            .list0_r:hover{
                background-color: rgb(247,249,250);
                cursor: pointer;
            }
            .list0_r:active, .list0_r:active b {
                background-color: rgb(247, 249, 250);
                color: grey;
            }


            .list1_r{
                user-select: none; /* Ngăn chặn người dùng bôi đậm nội dung */
                pointer-events: none;
                display: flex;
                justify-items: center;
                align-items: center;
                width: 100px;
                height: 50px;
                padding-left: 20px;
                background-color: rgb(1,148,243);
                color: white;
            }
            .list1_r i{
                font-size: 23px;
                margin-right: 15px;
                color: white;
                font-weight: 400;
            }
            .list1_r b{
                font-size: 23px;
                color: white;
                font-weight: 400;
            }
            .list1_r a{
                text-decoration: none;
            }
            .list1_r a:hover {
                color:white;
            }
            #header_list_right{
                padding-top: 10px;
                display: flex;
            }
            .btn-history:hover {
                background-color: #0d6efd;
                color: white;
                transition: transform 1.0s ease;
            }
            .container {
                width: 700px;
            }
            .custom-bg {
                background-color: #fafafa; /* Màu xám rất nhạt */
                background-image: linear-gradient(to right, #fafafa, #f5f5f5); /* Gradient từ #fafafa đến #f5f5f5 */
                border-radius: 5px;
            }
        </style>
    </head>
    <body>
        <%@include file="Header.jsp" %>

        <div id="overlay"></div>
        <div id="logoutConfirm">
            <h2>Logging Out</h2>
            <p>Oh, no! You’ll miss a lot of things by logging out: Traveloka Points, Passenger Quick Pick, Price Alerts, and other member-only benefits. Are you sure want to log out?</p>
            <button id="confirmYes"><a href="logout">Yes</a></button>
            <button id="confirmNo"><a href="#">No</a></button>
        </div>

        <div id="mid_container">
            <div id="left">
                <div id="header_left">
                    <div>
                        <img src="${account.avatarURL}" alt="alt" />
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
                    <div class="list0">
                        <a href="View_Avatar.jsp"><i class='bx bx-image-add'></i> <b>View Avatar</b></a>
                    </div>
                    <div class="list0">
                        <a href="MyFeedbackServlet"><i class='bx bx-calendar'></i> <b>My Feedback</b></a>
                    </div>
                    <div class="list1">
                        <a href="My_Card_Payment_History.jsp"><i class='bx bx-money-withdraw'></i> <b>Booking History</b></a>
                    </div>
                    <div class="list0">
                        <a href="ListFavoriteHotel"><i class='bx bx-heart-circle'></i> <b>Favorite Hotel</b></a>
                    </div>
                    <div class="list0">
                        <a href="#"><i class='bx bx-log-out'></i><b id="logoutButton">Logging Out</b></a>
                    </div>
                </div>
            </div>
            <div id="right">
                <div class="container mb-5">
                    <h1 class="fw-bold fs-1">History</h1>
                    <div class="card-body w-100">
                        <div class="row mb-3">
                            <div class="col-md-2 d-flex align-items-center justify-content-center">
                                <a href="All_Hotel_Service" class="list0_r btn-history w-100 text-center py-2 btn-history d-inline-flex focus-ring py-1 px-2 text-decoration-none border rounded-2" style="--bs-focus-ring-x: 10px; --bs-focus-ring-y: 10px; --bs-focus-ring-blur: 4px">ALL</a>
                            </div>
                            <div class="col-md-3 d-flex align-items-center justify-content-center">
                                <a class="list0_r btn-history w-100 text-center py-2 btn-history d-inline-flex focus-ring py-1 px-2 text-decoration-none border rounded-2" style="--bs-focus-ring-x: 10px; --bs-focus-ring-y: 10px; --bs-focus-ring-blur: 4px" href="Paid_Hotel_Service">FINISHED</a>
                            </div>
                            <div class="col-md-2 d-flex align-items-center justify-content-center">
                                <a class="list0_r btn-history w-100 text-center py-2 btn-history d-inline-flex focus-ring py-1 px-2 text-decoration-none border rounded-2" style="--bs-focus-ring-x: 10px; --bs-focus-ring-y: 10px; --bs-focus-ring-blur: 4px" href="NotPaid_Hotel_Service">PAID</a>
                            </div>
                            <div class="col-md-3 d-flex align-items-center justify-content-center">
                                <a class="list0_r btn-history w-100 text-center py-2 btn-history d-inline-flex focus-ring py-1 px-2 text-decoration-none border rounded-2" style="--bs-focus-ring-x: 10px; --bs-focus-ring-y: 10px; --bs-focus-ring-blur: 4px" href="NotYet_Hotel_Service">PROCESSING</a>
                            </div>
                            <div class="col-md-2 d-flex align-items-center justify-content-center">
                                <a class="list0_r btn-history w-100 text-center py-2 btn-history d-inline-flex focus-ring py-1 px-2 text-decoration-none border rounded-2" style="--bs-focus-ring-x: 10px; --bs-focus-ring-y: 10px; --bs-focus-ring-blur: 4px" href="Cancel_Hotel_Service">CANCEL</a>
                            </div>
                        </div>
                    </div>

                    <c:if test="${empty groupedBills}">
                        <div id="noHistory" class="alert alert-danger text-center w-100">
                            You don't have any transaction!
                        </div>
                    </c:if>

                    <c:if test="${not empty groupedBills}">
                        <div class="row p-4">
                            <c:forEach var="entry" items="${groupedBills}">
                                <div class="col-md-6 mb-4 hotel-card">
                                    <div class="card h-100 border rounded shadow">
                                        <div class="card-body d-flex flex-column custom-bg">
                                            <h5 class="card-title mb-3">
                                                <i class='bx bxs-hotel'></i> Reservation ID: ${entry.key}
                                            </h5>
                                            <p class="card-text">
                                                <i class='bx bx-hotel'></i> Hotel Name: ${entry.value[0].hotel_Name}
                                            </p>
                                            <p class="card-text">
                                                <i class='bx bx-calendar-check'></i> Check-In: ${entry.value[0].checkInDate}
                                            </p>
                                            <p class="card-text">
                                                <i class='bx bx-calendar-x'></i> Check-Out: ${entry.value[0].checkOutDate}
                                            </p>
                                            <p class="card-text">
                                                <i class='bx bx-dollar-circle'></i> Total Price: ${entry.value[0].total_price}
                                            </p>
                                            <c:if test="${entry.value[0].status == 'PAID'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-success">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'FINISHED'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-primary">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'FEEDBACKED'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-info">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'CANCEL'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-danger">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'PROCESSING'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-warning">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'NOT PAID'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-warning">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'REFUNDING'}">
                                                <p class="card-text">
                                                    <i class='bx bx-check-square'></i>
                                                    <span class="badge text-bg-warning">${entry.value[0].status}</span>
                                                </p>
                                            </c:if>
                                            <a href="MyBillPaymentServlet?reservation_ID=${entry.key}" class="btn btn-outline-primary mt-auto">
                                                <i class='bx bx-detail'></i> View Details
                                            </a>
                                            <c:if test="${entry.value[0].status == 'FINISHED'}">
                                                <hr>
                                                <div class="row m-1"> 
                                                    <a class="btn btn-outline-primary mb-2" href="CreateFeedback.jsp?reservation_ID=${entry.key}&hotel_ID=${entry.value[0].hotel_ID}">
                                                        <i class="fa-regular fa-comment"></i> Feedback
                                                    </a>
                                                </div>
                                            </c:if>
                                            <c:if test="${entry.value[0].status == 'FEEDBACKED'}">
                                                <hr>
                                                <div class="row"> 
                                                    <div class="col-md-12">

                                                        <form action="ViewFeedbackReservationIDServlet" method="post">
                                                            <input type="hidden" name="reservationID" value="${entry.key}"/>
                                                            <input type="hidden" name="role" value="1"/>
                                                            <button class="btn btn-outline-primary mt-auto cancel-button update-button w-100">
                                                                View Feedback
                                                            </button> 
                                                        </form>
                                                    </div>
                                                </div> 
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </c:forEach>
                        </div>
                    </c:if>
                    <nav class="justify-content-center align-items-center bg-white">
                        <ul class="pagination"></ul>
                    </nav>
                </div>
            </div>
        </div>

        <%@include file="Footer.jsp" %>

        <script>
            document.addEventListener('DOMContentLoaded', () => {
                const logoutButton = document.getElementById('logoutButton');
                const overlay = document.getElementById('overlay');
                const logoutConfirm = document.getElementById('logoutConfirm');
                const confirmYes = document.getElementById('confirmYes');
                const confirmNo = document.getElementById('confirmNo');

                logoutButton.addEventListener('click', (e) => {
                    e.preventDefault();
                    overlay.style.display = 'block';
                    logoutConfirm.style.display = 'block';
                });

                confirmYes.addEventListener('click', () => {
                    // Handle confirmation of logout
                    overlay.style.display = 'none';
                    logoutConfirm.style.display = 'none';
                });

                confirmNo.addEventListener('click', (e) => {
                    e.preventDefault();
                    overlay.style.display = 'none';
                    logoutConfirm.style.display = 'none';
                });
            });
            //Logout confirmation
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

            // Pagination
            let thisPage = 1;
            let limit = 4; // 2 items per page
            let list = document.querySelectorAll(".hotel-card");

            function loadItem() {
                let beginGet = limit * (thisPage - 1);
                let endGet = limit * thisPage - 1;

                list.forEach((item, key) => {
                    if (key >= beginGet && key <= endGet) {
                        item.style.display = "block";
                    } else {
                        item.style.display = "none";
                    }
                });

                listPage();
            }

            loadItem();

            function listPage() {
                let count = Math.ceil(list.length / limit);
                document.querySelector('.pagination').innerHTML = '';

                if (thisPage != 1) {
                    let prev = document.createElement('li');
                    prev.classList.add('page-item');
                    prev.innerHTML = '<a class="page-link" href="#" onclick="changePage(' + (thisPage - 1) + ')">Previous</a>';
                    document.querySelector('.pagination').appendChild(prev);
                }

                for (let i = 1; i <= count; i++) {
                    let li = document.createElement('li');
                    li.classList.add('page-item');
                    if (i == thisPage) {
                        li.classList.add('active');
                    }
                    li.innerHTML = '<a class="page-link" href="#" onclick="changePage(' + i + ')">' + i + '</a>';
                    document.querySelector('.pagination').appendChild(li);
                }

                if (thisPage != count) {
                    let next = document.createElement('li');
                    next.classList.add('page-item');
                    next.innerHTML = '<a class="page-link" href="#" onclick="changePage(' + (thisPage + 1) + ')">Next</a>';
                    document.querySelector('.pagination').appendChild(next);
                }
            }

            function changePage(page) {
                thisPage = page;
                loadItem();
            }

            // Initially load the first page
            window.onload = function () {
                loadItem();
            };

        </script>
    </body>
</html>
