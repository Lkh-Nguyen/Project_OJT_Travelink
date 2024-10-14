<%-- 
    Document   : Error
    Created on : Jun 6, 2024, 1:22:08 PM
    Author     : DUYAN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="com.travelink.Model.*" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <title>Error</title>
    </head>
    <body>


        <section class="bg-light">
            <div class="container-fluid">
                <div class="row row-cols-1 justify-content-center py-5">
                    <div class="col-xxl-7 mb-4">
                        <div class="lc-block">
                            <script src="https://unpkg.com/@lottiefiles/lottie-player@latest/dist/lottie-player.js"></script>
                            <lottie-player src="https://assets9.lottiefiles.com/packages/lf20_u1xuufn3.json" class="mx-auto" background="transparent" speed="1" loop="" autoplay=""></lottie-player>
                        </div>
                    </div><!-- /col -->
                    <div class="col text-center">
                        <div class="lc-block">
                            <div class="lc-block mb-4">
                                <div editable="rich">
                                    <p class="rfs-11 fw-light"> The page you are looking for was moved, removed or might never existed.</p>
                                </div>
                            </div>
                            <%
                                // Assuming the account object is stored in the session
                                Account account = (Account) session.getAttribute("account");
                                String href = "homeCustomerServlet"; // Default URL

                                if (account != null) {
                                    int role = account.getRole();
                                    if (role == 2) {
                                        href = "homeHotelHostServlet";
                                    }
                                    if (role == 3){
                                        href = "AdminDashBoardServlet";
                                    }
                                }
                            %>

                            <div class="lc-block">
                                <a class="btn btn-lg btn-primary" href="<%= href %>" role="button">Back to homepage</a>
                            </div>

                        </div>
                    </div><!-- /col -->
                </div>

            </div>
        </section>
    </body>
</html>
