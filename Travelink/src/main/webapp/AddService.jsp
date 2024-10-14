<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@page import="java.util.*,com.travelink.Model.*,com.travelink.Database.*,java.util.stream.Collectors" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Add Services</title>
        <!-- Bootstrap CSS -->
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/css/bootstrap.min.css" rel="stylesheet">
        <style>
            .is-invalid {
                border-color: #dc3545 !important;
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <a href="javascript:history.back()" class="btn btn-outline-primary">Back</a>    
        <div class="container mt-4">
            <%
                int hotelId = 0;
                List<Service> hotelServices = new ArrayList<>();
                List<Service> allServices = new ArrayList<>();
                List<Service> result = new ArrayList<>();
                try {
                    hotelId = Integer.parseInt(request.getParameter("hotelId"));
                    hotelServices = (List<Service>) ServiceDB.getServiceByHotelID(hotelId);
                    allServices = (List<Service>) session.getAttribute("allServices");
                } catch (Exception e) {
                    out.println("<div class='alert alert-danger'>Error fetching data: " + e.getMessage() + "</div>");
                }

                final Set<Integer> serviceIds = new HashSet<>();
                if (hotelServices != null) {
                    serviceIds.addAll(hotelServices.stream()
                            .map(Service::getServiceID)
                            .collect(Collectors.toSet()));
                }
                if (allServices != null) {
                    result = allServices.stream()
                            .filter(service -> !serviceIds.contains(service.getServiceID()))
                            .collect(Collectors.toList());
                }
                request.setAttribute("result", result);
            %>
            <h2>Add Services for Hotel:</h2>
            <div class="alert alert-success">If you want the service included with the room in the package, enter price as 0 </div>
            <form method="post" action="AddServiceServlet" class="needs-validation" novalidate>
                <c:forEach items="${result}" var="service">
                    <div class="mb-3">
                        <div class="form-check">
                            <input class="form-check-input" type="checkbox" value="${service.serviceID}" id="service${service.serviceID}" name="selectedServices" onclick="togglePriceInput(this)">
                            <label class="form-check-label" for="service${service.serviceID}">
                                ${service.name}
                            </label>
                            <input type="text" class="form-control mt-1 service-price" id="service${service.serviceID}Price" name="service${service.serviceID}Price" placeholder="Enter price"
                                   pattern="[0-9]*" title="Please enter a valid integer number (e.g., 100)" disabled>
                            <div class="invalid-feedback">Please enter a valid integer number.</div>
                        </div>
                    </div>
                </c:forEach>

                <!-- Hidden input for hotelId -->
                <input type="hidden" name="hotelId" value="<%= hotelId %>">

                <!-- Submit button with Bootstrap styling -->
                <button type="submit" class="btn btn-primary">Add Services</button>
            </form>
        </div>

        <!-- Bootstrap JS bundle (optional if you need Bootstrap JS features) -->
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.6/dist/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.0/dist/js/bootstrap.min.js"></script>
        <script>
                                function togglePriceInput(checkbox) {
                                    var priceInput = checkbox.parentElement.querySelector('.service-price');
                                    if (checkbox.checked) {
                                        priceInput.removeAttribute('disabled');
                                        priceInput.required = true;
                                    } else {
                                        priceInput.setAttribute('disabled', 'disabled');
                                        priceInput.value = '';
                                        priceInput.required = false;
                                        priceInput.classList.remove('is-invalid');
                                    }
                                }

                                // JavaScript for validating the form
                                (function () {
                                    'use strict';

                                    // Fetch all the forms we want to apply custom Bootstrap validation styles to
                                    var forms = document.querySelectorAll('.needs-validation');

                                    // Loop over them and prevent submission
                                    Array.prototype.slice.call(forms)
                                            .forEach(function (form) {
                                                form.addEventListener('submit', function (event) {
                                                    if (!form.checkValidity()) {
                                                        event.preventDefault();
                                                        event.stopPropagation();
                                                    }

                                                    form.classList.add('was-validated');
                                                }, false);
                                            });
                                })();
        </script>
    </body>
</html>
