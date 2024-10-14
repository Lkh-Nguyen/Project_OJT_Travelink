<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.Date" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Create New Feedback</title>
        <link href="bootstrap_css/bootstrap.min.css" rel="stylesheet">
        <style>
            .rating {
                display: flex;
                flex-direction: row-reverse;
                justify-content: center;
            }
            .rating input {
                display: none;
            }
            .rating label {
                position: relative;
                width: 1em;
                font-size: 3vw;
                color: #FFD700;
                cursor: pointer;
            }
            .rating label::before {
                content: "\2605";
                position: absolute;
                opacity: 0.3;
            }
            .rating label:hover::before,
            .rating label:hover ~ label::before,
            .rating input:checked ~ label::before {
                opacity: 1 !important;
            }
            .container {
                max-width: 600px;
                margin: auto;
                padding: 20px;
                border-radius: 5px;
                background-color: #f9f9f9;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.3);
            }
            .form-label {
                font-size: 1.5em;
            }
            .form-control {
                font-size: 1.2em;
                border: 2px solid #ccc;
                border-radius: 5px;
            }
            .form-control:focus {
                border-color: #4d90fe;
                outline: none;
            }

            .form-control.error {
                border-color: red;
            }


            .btn-primary {
                font-size: 1.5em;
                background-color: #4d90fe;
                border: none;
                border-radius: 5px;
            }
            .btn-secondary {
                font-size: 1.5rem;
                border: none;
                border-radius: 5px;
            }
            .btn-primary:hover {
                background-color: #357ae8;
            }

            .error-message {
                color: red;
                font-size: 1em;
                margin-top: 5px;
                display: none;
            }

            .character-count {
                font-size: 1em;
                text-align: right;
                color: #000;
            }
            #charCount{
                color: #000;
            }
        </style>
    </head>
    <body>
        <div class="container mt-5">
            <h2>Create New Feedback</h2>
            <form id="feedbackForm" action="SubmitFeedbackServlet" method="post">
                <div class="mb-3">
                    <label for="rating" class="form-label fs-3">Rating</label>
                    <div class="rating">
                        <input type="radio" id="star5" name="rating" value="5" required/><label for="star5" title="5 stars"></label>
                        <input type="radio" id="star4" name="rating" value="4" required/><label for="star4" title="4 stars"></label>
                        <input type="radio" id="star3" name="rating" value="3" required/><label for="star3" title="3 stars"></label>
                        <input type="radio" id="star2" name="rating" value="2" required/><label for="star2" title="2 stars"></label>
                        <input type="radio" id="star1" name="rating" value="1" required/><label for="star1" title="1 star"></label>
                    </div>
                </div>
                <div class="mb-3 mt-5">
                    <label for="description" class="form-label fs-3">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="7" required></textarea>
                    <div class="character-count" id="charCount">0/200</div>
                    <div class="error-message" id="error-message">Description cannot exceed 200 characters.</div>
                </div>
                <input type="hidden" id="date" name="date" value="<%= new java.text.SimpleDateFormat("yyyy-MM-dd").format(new Date()) %>">
                <input type="hidden" id="reservationID" name="reservationID" value="${param.reservation_ID}">
                <input type="hidden" id="hotel_ID" name="hotel_ID" value="${param.hotel_ID}">
                <div class="d-flex justify-content-around ">
                    <button type="submit" class="btn btn-primary fw-bold" id="comfirmBtn">Submit</button>
                    <button type="button" class="btn btn-secondary fw-bold" onclick="window.history.back()">Cancel</button>
                </div>
            </form>
        </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0-beta3/js/all.min.js"></script>
        <script src="js/Create Feedback.js">
        </script>
    </body>
</html>
