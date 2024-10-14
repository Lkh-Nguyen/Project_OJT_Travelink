<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <title>Transaction Success</title>
    <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
            font-family: 'Roboto', sans-serif;
            display: flex;
            flex-direction: column;
            justify-content: center;
            min-height: 100vh;
            margin: 0;
        }
        .content {
            flex: 1;
            display: flex;
            justify-content: center;
            align-items: center;
        }
        .container {
            max-width: 600px;
        }
        .success-message {
            border: 1px solid #e6e6e6;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            padding: 30px;
            border-radius: 10px;
            animation: fadeIn 1s ease-in-out;
        }
        .success-message h1 {
            font-size: 2.5rem;
            color: #28a745;
        }
        .success-message p {
            font-size: 1.2rem;
            color: #333333;
        }
        .btn-home {
            background-color: #28a745;
            color: white;
            padding: 12px 30px;
            border-radius: 50px;
            box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
            transition: background-color 0.3s ease, transform 0.3s ease;
            font-size: 1.1rem;
            font-weight: 500;
            text-align: center;
            display: inline-block;
        }
        .btn-home:hover {
            background-color: #218838;
            transform: translateY(-2px);
        }
        .icon {
            font-size: 4rem;
            color: #28a745;
        }
        @keyframes fadeIn {
            from { opacity: 0; }
            to { opacity: 1; }
        }
    </style>
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@400;500;700&display=swap" rel="stylesheet">
</head>
<body>
    <%@include file="Header.jsp" %>
    <div class="content">
        <div class="container text-center">
            <div class="success-message">
                <h1><span><i class='bx bxs-check-circle' style="color: rgba(4, 117, 4, 0.858)"></i></span>Transaction Successful!</h1>
                <p>Thank you for your transaction.</p>    
                <p>You can view your bill in the <strong>Booking History</strong>.</p>
                <div class="row">
                    <div class="col-md-6">
                        <a href="homeCustomerServlet" class="btn btn-home mt-4">Return to Home Page</a>
                    </div>
                    <div class="col-md-6">
                        <a href="NotPaid_Hotel_Service" class="btn btn-home mt-4">Going Booking History</a>
                    </div>
                </div>
            </div>
        </div>
    </div>
    <%@include file="Footer.jsp" %>
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
    <script src="bootstrap_js/bootstrap.min.js"></script>
    <script src="https://kit.fontawesome.com/a076d05399.js"></script>
</body>
</html>
