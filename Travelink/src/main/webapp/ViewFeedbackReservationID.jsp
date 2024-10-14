<%-- 
    Document   : ViewFeedbackReservationID
    Created on : Jun 28, 2024, 3:14:16 PM
    Author     : HELLO
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="css/Right_My_Account.css">
        <link rel="stylesheet" href="css/HotelHost_Feedback.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <style>
            .hotel-card {
                transition: transform 0.3s ease-in-out;
            }

            .hotel-card:hover {
                transform: scale(1.05);
            }

            .modal-content {
                border-radius: 15px;
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .modal-header {
                background-color: #f8f9fa;
                border-bottom: 1px solid #dee2e6;
                border-radius: 15px 15px 0 0;
            }

            .modal-title {
                color: #495057;
                font-weight: bold;
            }

            .modal-body {
                padding: 20px;
                background-color: #fff;
            }

            .review {
                border: 1px solid #e3e3e3;
                border-radius: 10px;
                padding: 15px;
                background-color: #f9f9f9;
                box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
                transition: transform 0.3s ease-in-out, box-shadow 0.3s ease-in-out;
            }

            .review:hover {
                transform: scale(1.02);
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
            }

            .avatar img {
                border-radius: 50%;
                width: 60px;
                height: 60px;
                object-fit: cover;
            }

            .avatar {
                margin-right: 15px;
            }

            .review .row {
                display: flex;
                justify-content: space-between;
                align-items: center;
                margin-bottom: 10px;
            }

            .review h6 {
                margin: 0;
                color: #333;
                font-weight: bold;
            }

            .review .text-muted {
                font-size: 0.85rem;
            }

            .rating i {
                color: #ffc107;
                font-size: 1.2rem;
                margin-right: 2px;
            }

            .rating i.bx-star {
                color: #e0e0e0;
            }

            .review p {
                margin: 10px 0;
                font-size: 1rem;
                color: #555;
            }

            .badge {
                font-size: 0.85rem;
                margin-right: 5px;
            }

            .bg-light {
                background-color: #f8f9fa !important;
            }

            .text-dark {
                color: #343a40 !important;
            }
            #comeback{
                margin-left: 50px;
                margin-bottom: 20px;
                margin-top: 20px;
            }
            #comeback button{
                width: 70px;
                height: 30px;
            }
            #comeback button:hover{
                background-color: white;
            }
        </style>
    </head>
    <body>
        <div id="comeback">
            <button type="button" onclick="history.back()"><img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</button>
        </div>
        <div class="modal fade " id="confirmCancelModal">
            <div class="modal-dialog d-flex justify-content-center align-items-center" role="document" style="height: 100vh">
                <div class="modal-content" >
                    <div class="modal-header">       
                    </div>
                    <div class="modal-body justify-content-center">
                        <div class="modal-body">
                            <h2 class="modal-title" id="confirmCancelModalLabel">View Feedback (Reservation ID: ${requestScope.feedback.reservation_ID})</h2>
                            <div class="review mb-3" id="fb" style="border: 1px solid rgb(227,227,227);border-radius: 5px">
                                <div class="d-flex">

                                    <div class="avatar" ><img src="${requestScope.feedback.getAccount(requestScope.feedback.feedbackID).avatarURL}" style="width: 100px;height: 100%"></div>
                                    <div class="ms-3">
                                        <div class="row" style="margin-bottom: -10px;">
                                            <h3 class="mb-0">${requestScope.feedback.getAccount(requestScope.feedback.feedbackID).name}</h3>
                                        </div>
                                        <div class="row">
                                            <h4 class="text-muted" style="font-weight: 500"><span style="font-weight: bold">Date Feedback:</span> ${requestScope.feedback.date}</h4>
                                        </div>
                                        <div class="row" style="margin-top: -10px;">
                                            <c:if test="${requestScope.feedback.rating == 1}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.feedback.rating == 2}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>

                                            <c:if test="${requestScope.feedback.rating == 3}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.feedback.rating == 4}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>
                                            <c:if test="${requestScope.feedback.rating == 5}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                </div>
                                            </c:if>
                                        </div>
                                        <p>${requestScope.feedback.description}</p>
                                        <div>
                                            <span class="badge bg-light text-dark" style="font-size: 20px;margin-right: 20px;">${requestScope.feedback.likesCount} <i class='bx bxs-like'></i></span> 
                                            <span class="badge bg-light text-dark" style="font-size: 20px">${requestScope.feedback.dislikesCount} <i class='bx bxs-dislike' ></i></span>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
