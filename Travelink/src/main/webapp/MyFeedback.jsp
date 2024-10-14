<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>My Feedback</title>
        <link href="bootstrap_css/bootstrap.min.css" rel="stylesheet">
        <link rel="stylesheet" href="css/listPage.css">
        <link rel="stylesheet" href="css/Alter.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link href="https://cdn.jsdelivr.net/npm/remixicon@3.4.0/fonts/remixicon.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons/font/bootstrap-icons.css">
    </head>
    <style>
        .col-md-5,.col-md-7 {
            transition: transform 0.3s ease-in-out, box-shadow 0.3s ease;
        }

        .col-md-5,.col-md-7:hover {
            transform: translateY(-3px);
            cursor: pointer;
        }
    </style>
    <body>
        <%@include file="Header.jsp" %>
        <div class="container my-5">
            <a href="My_Account_Update.jsp" class="btn btn-outline-primary mt-2 mb-5"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <c:if test="${not empty feedbacks}">
                <c:forEach var="f" items="${feedbacks}">
                    <c:set var="id" value="${f.feedbackID}"/>
                    <div class="row feedback mb-4" id="feedback-${id}">
                        <div class="col-md-5">
                            <div class="card h-100 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
                                <div class="card-body">
                                    <div class="row">
                                        <div class="col-md-4"><img src="${f.getHotel(id).getAnURL()}" alt="Hotel Image" class="img-fluid" style="width: 10rem; height: 8rem;"></div>
                                        <div class="col-md-8">
                                            <p class="card-text"><span class="fw-bold">Hotel Brand: </span>${f.getHotel(id).name}</p>
                                            <p class="card-text"><span class="fw-bold">Address: </span>${f.getHotel(id).address}</p>
                                            <p class="card-text"><span class="fw-bold">Overview: </span>
                                                <img src="img_Hotel/star.svg" alt="Update" style="width: 15px; height: 15px; margin-right: 5px;">
                                                <img src="img_Hotel/star.svg" alt="Update" style="width: 15px; height: 15px; margin-right: 5px;">
                                                <img src="img_Hotel/star.svg" alt="Update" style="width: 15px; height: 15px; margin-right: 5px;">
                                                <img src="img_Hotel/star.svg" alt="Update" style="width: 15px; height: 15px; margin-right: 5px;">
                                                <img src="img_Hotel/none-star.svg" alt="Update" style="width: 15px; height: 15px; margin-right: 5px;">
                                            </p>
                                            <p class="card-text text-success fw-medium"><i class="bi bi-check"></i> Free canellation</p>
                                            <p class="card-text text-success fw-medium"><i class="bi bi-check"></i> Full Facility <span class="text-muted text-success">- for customer</span></p>
                                            <hr>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="col-md-7">
                            <div class="card h-100 shadow-lg p-3 mb-5 bg-body-tertiary rounded">
                                <div class="card-body">
                                    <h4 class="card-title d-flex align-items-center">
                                        <img src="${f.getAccount(id).avatarURL}" alt="Avatar" class="rounded-circle me-3" style="width: 50px; height: 50px;">
                                        ${f.getAccount(id).name}
                                    </h4>
                                    <div class="mb-3">
                                        Date: ${f.date}
                                    </div>
                                    <div class="card-subtitle mt-2 mb-2 rating-container">
                                        Rating: 
                                        <c:forEach var="i" begin="1" end="5">
                                            <i class="bx ${i <= f.rating ? 'bxs-star' : 'bx-star'} star-icon" data-rating="${i}" style="color: orange;"></i>
                                        </c:forEach>
                                    </div>
                                    <div class="row mb-3">
                                        <p class="card-text description">${f.description}</p>
                                    </div>
                                </div>
                                <div class="row p-3 justify-content-end mt-auto">
                                    <div class="col-md-9">
                                        <button class="btn btn-primary me-4 update-btn" data-id="${id}">
                                            <img src="img_Hotel/update.svg" alt="Update" style="width: 20px; height: 20px; margin-right: 5px;"> Update
                                        </button>
                                        <button class="btn btn-danger me-4 delete-btn" data-id="${id}">
                                            <img src="img_Hotel/delete.svg" alt="Delete" style="width: 20px; height: 20px; margin-right: 5px;"> Delete
                                        </button>
                                        <button class="btn btn-success me-4 save-btn d-none" data-id="${id}">
                                            <img src="img_Hotel/create.svg" alt="Save" style="width: 20px; height: 20px; margin-right: 5px;"> Save
                                        </button>
                                        <button class="btn btn-secondary me-4 cancel-btn d-none" data-id="${id}">
                                            <img src="img_Hotel/cancel_invoice.svg" alt="Cancel" style="width: 20px; height: 20px; margin-right: 5px;"> Cancel
                                        </button>
                                    </div>
                                    <div class="col-md-3">
                                        <span class="me-3"><i class="bx bx-like"></i> ${f.likesCount}</span>
                                        <span><i class="bx bx-dislike"></i> ${f.dislikesCount}</span>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </c:if>
            <c:if test="${empty feedbacks}">
                <div class="alert alert-warning" role="alert">
                    You have not left any feedback yet.
                </div>
            </c:if>
            <c:if test="${not empty feedbacks}">
                <ul class="listPage"></ul>
            </c:if>
        </div>
        
        <c:if test="${requestScope.success != null}">
            <div id="status-message" style="background-color: rgb(233,251,233);height: 70px;" class="hidden">
                <div style="display: flex">
                    <div style="width: 20%">
                        <i class='bx bxs-check-circle' style="font-size: 50px;color:green;margin-top: 0px"></i>
                    </div>
                    <div style="width: 80%; text-align: start">
                        <h3 style="color:green;margin-top: 5px; margin-bottom: -2px;font-weight:550">Success</h3>
                        <p style="color: black;font-size: 14px">${success}</p>
                    </div>
                </div>           
            </div>
        </c:if>
        <script src="https://unpkg.com/boxicons@2.1.4/dist/boxicons.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
        <script>
            $(document).ready(function () {
                var currentRating;

                $('.update-btn').click(function () {
                    var feedbackID = $(this).data('id');
                    var feedbackDiv = $('#feedback-' + feedbackID);
                    var description = feedbackDiv.find('.description').text().trim();
                    currentRating = feedbackDiv.find('.star-icon.bxs-star').length;

                    feedbackDiv.find('.description').html('<textarea class="form-control">' + description + '</textarea>');
                    feedbackDiv.find('.star-icon').removeClass('bx-star bxs-star').addClass('bx-star');
                    feedbackDiv.find('.star-icon').each(function (index) {
                        if (index < currentRating) {
                            $(this).addClass('bxs-star');
                        }
                    });
                    $(this).addClass('d-none');
                    feedbackDiv.find('.save-btn, .cancel-btn').removeClass('d-none');
                });
                $('.delete-btn').click(function () {
                    var feedbackID = $(this).data('id');
                    $.post('DeleteFeedbackServlet', {
                        feedbackID: feedbackID
                    }, function (response) {
                        $('#feedback-' + feedbackID).remove();
                    });
                });

                $('.star-icon').click(function () {
                    var feedbackDiv = $(this).closest('.rating-container');
                    var rating = $(this).data('rating');
                    feedbackDiv.find('.star-icon').removeClass('bxs-star').addClass('bx-star');
                    feedbackDiv.find('.star-icon').each(function (index) {
                        if (index < rating) {
                            $(this).addClass('bxs-star');
                        }
                    });
                });

                $('.save-btn').click(function () {
                    var feedbackID = $(this).data('id');
                    var feedbackDiv = $('#feedback-' + feedbackID);
                    var newDescription = feedbackDiv.find('textarea').val().trim();
                    var newRating = feedbackDiv.find('.star-icon.bxs-star').length;

                    $.post('UpdateFeedbackServlet', {
                        feedbackID: feedbackID,
                        description: newDescription,
                        rating: newRating,
                        success: ""
                    }, function (response) {
                        location.reload();
                    });
                });

                $('.cancel-btn').click(function () {
                    var feedbackID = $(this).data('id');
                    location.reload();
                });
            });
            //Pagination
            let thisPage = 1;
            let limit = 8;
            let list = document.querySelectorAll(".feedback");
            function loadItem() {
                let beginGet = limit * (thisPage - 1);
                let endGet = limit * thisPage - 1;
                list.forEach((item, key) => {
                    if (key >= beginGet && key <= endGet) {
                        item.style.display = "flex";
                    } else {
                        item.style.display = "none";
                    }
                });
                listPage();
            }
            loadItem();
            function listPage() {
                let count = Math.ceil(list.length / limit);
                document.querySelector('.listPage').innerHTML = '';

                if (thisPage != 1) {
                    let prev = document.createElement('li');
                    prev.innerText = 'PREV';
                    prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
                    document.querySelector('.listPage').appendChild(prev);
                }
                for (i = 1; i <= count; i++) {
                    let newPage = document.createElement('li');
                    newPage.innerText = i;
                    if (i == thisPage) {
                        newPage.classList.add('active');
                    }
                    newPage.setAttribute('onclick', "changePage(" + i + ")");
                    document.querySelector('.listPage').appendChild(newPage);
                }

                if (thisPage != count) {
                    let next = document.createElement('li');
                    next.innerText = 'NEXT';
                    next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
                    document.querySelector('.listPage').appendChild(next);
                }
            }
            function changePage(i) {
                thisPage = i;
                loadItem();
                let scrollPosition = window.scrollY;
                window.scrollTo(0, scrollPosition);
            }
        </script>
        <script src="js/Alter.js"></script>
        <%@include file="Footer.jsp" %>
    </body>
</html>
