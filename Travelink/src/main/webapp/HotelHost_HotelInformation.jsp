<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Dash Board</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_HotelService.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/bootstrap-icons/1.10.0/font/bootstrap-icons.min.css" rel="stylesheet">
        <style>
            @import url('https://fonts.googleapis.com/css?family=Montserrat:400,800');
            body {
                font-family: Montserrat, sans-serif;
                background-color: #f8f9fa;
                color: #343a40;
            }
            .container-fluid {
                padding: 2rem;
            }
            .btn-outline-primary {
                border-color: #007bff;
                color: #007bff;
                transition: all 0.3s;
            }
            .btn-outline-primary:hover {
                background-color: #007bff;
                color: #fff;
            }
            .card {
                box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
                border-radius: 8px;
                margin-bottom: 2rem;
            }
            .card-header {
                background-color: #007bff;
                color: white;
                border-radius: 8px 8px 0 0;
                padding: 1rem;
            }
            .card-title {
                margin-bottom: 0;
            }
            .table {
                margin-bottom: 0;
            }
            .table th, .table td {
                vertical-align: middle;
                padding: 1rem;
            }
            .table thead th {
                background-color: #007bff;
                color: white;
                border: none;
            }
            .table-hover tbody tr:hover {
                background-color: #e9ecef;
            }
            .btn {
                transition: all 0.3s;
            }
            .btn-outline-primary {
                border-color: #007bff;
                color: #007bff;
            }
            .btn-outline-primary:hover {
                background-color: #007bff;
                color: white;
            }
            .btn-outline-danger {
                border-color: #dc3545;
                color: #dc3545;
            }
            .btn-outline-danger:hover {
                background-color: #dc3545;
                color: white;
            }
            .btn-outline-secondary {
                border-color: #6c757d;
                color: #6c757d;
            }
            .btn-outline-secondary:hover {
                background-color: #6c757d;
                color: white;
            }
            .modal-header {
                background-color: #007bff;
                color: white;
            }
            .modal-footer .btn-primary {
                background-color: #007bff;
                border: none;
            }
            .status-dot {
                height: 10px;
                width: 10px;
                border-radius: 50%;
                display: inline-block;
                margin-right: 5px;
            }
            .status-active {
                background-color: green;
            }
            .status-inactive {
                background-color: red;
            }
            .no-resize-textarea {
                resize: none;
            }
            .pagination .page-item .page-link {
                color: #007bff;
            }

            .pagination .page-item.active .page-link {
                background-color: #007bff;
                border-color: #007bff;
                color: white;
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container-fluid">
            <a href="homeHotelHostServlet"_Hot class="btn btn-outline-primary mt-2">
                <img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
            </a>
            <div class="row p-4 d-flex align-items-center">
                <div class="col-md-12">
                    <div class="card">
                        <h5 class="card-header">
                            <div class="d-flex align-items-center">
                                <img src="img_Hotel/list.svg" alt="List Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                <span>Hotel Information</span>
                            </div>
                        </h5>
                        <div class="card-body">
                            <c:if test="${addStatus != null}">
                                <p>${addStatus}</p>
                            </c:if>
                            <c:choose>
                                <c:when test="${not empty requestScope.hotel_list}">
                                    <form action="HotelHost_AddHotelServlet" method="get" id="cancelForm">
                                        <button type="submit" class="btn btn-secondary mb-3 add-button">
                                            <i class="bi bi-plus-circle"></i> Add Hotel
                                        </button>
                                        <input type="hidden" name="hotel_ID" value="${h.hotel_ID}"/>
                                        <input type="hidden" name="action" value="add"/>
                                    </form>
                                    <table id="hotelTable" class="table-hover table align-items-center justify-content-center">
                                        <thead>
                                            <tr class="text-center">
                                                <th scope="col">No.</th>
                                                <th scope="col">NAME</th>
                                                <th scope="col">EMAIL</th>
                                                <th scope="col">STAR</th>
                                                <th scope="col">PHONE</th>
                                                <th scope="col">DESCRIPTION</th>
                                                <th scope="col">ADDRESS</th>
                                                <th scope="col">STATUS</th>
                                                <th scope="col">ACTION</th>
                                            </tr>
                                        </thead>
                                        <tbody class="table-group-divider align-items-center text-center">
                                            <c:forEach var="h" items="${requestScope.hotel_list}" varStatus="loopStatus">
                                                <tr>
                                                    <td>${loopStatus.index + 1}</td>
                                                    <td>${h.name}</td>
                                                    <td>${h.email}</td>
                                                    <td>
                                                        <div style="width: 8rem;">
                                                            <c:if test="${h.star == 5}">
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                            </c:if>
                                                            <c:if test="${h.star == 4}">
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star'></i>
                                                            </c:if>
                                                            <c:if test="${h.star == 3}">
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star'></i>
                                                                <i class='bx bxs-star'></i>
                                                            </c:if>
                                                            <c:if test="${h.star == 2}">
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star'></i>
                                                                <i class='bx bxs-star'></i>
                                                                <i class='bx bxs-star'></i>
                                                            </c:if>
                                                            <c:if test="${h.star == 1}">
                                                                <i class='bx bxs-star' style="color: yellow"></i>
                                                                <i class='bx bxs-star'></i>
                                                                <i class='bx bxs-star'></i>
                                                                <i class='bx bxs-star'></i>
                                                                <i class='bx bxs-star'></i>
                                                            </c:if>
                                                        </div>
                                                    </td>
                                                    <td>${h.phoneNumber}</td>
                                                    <td class="text-start">
                                                        <div class="card-text">${h.description}</div>
                                                    </td>
                                                    <td>
                                                        <div style="width: 15rem;">${h.address}</div>
                                                    </td>
                                                    <td>
                                                        <c:choose>
                                                            <c:when test="${h.status == 'ACTIVE'}">
                                                                <span class="status-dot status-active"></span>ACTIVE
                                                            </c:when>
                                                            <c:otherwise>
                                                                <span class="status-dot status-inactive"></span>INACTIVE
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </td>
                                                    <td>
                                                        <!-- Không cần để ý form này, thực hiện trong form trong MODAL -->
                                                        <form class="row m-1 p-1" action="HotelHost_UpdateHotelServlet" method="get">
                                                            <button class="btn btn-outline-primary mb-1 w-100">
                                                                Detail
                                                            </button>
                                                            <input type="hidden" name="hotelID" value="${h.hotel_ID}">
                                                        </form>
                                                        <form class="row m-1 p-1" action="HotelHost_ViewHotelImgServlet" method="get">
                                                            <button class="btn btn-outline-primary mb-1 w-100">
                                                                Image 
                                                            </button>
                                                            <input type="hidden" name="hotelID" value="${h.hotel_ID}">
                                                        </form>                                                         

                                                        <c:if test="${partner.hotel.status != 'LOCKED'}">
                                                            <form class="row m-1 p-1" action="HotelHost_DeleteHotelServlet" method="get" id="cancelForm">        
                                                                <button class="btn btn-outline-danger mb-1 w-100 delete-button">
                                                                    <c:if test="${h.status == 'ACTIVE'}">
                                                                        INACTIVE
                                                                    </c:if>
                                                                    <c:if test="${h.status == 'INACTIVE'}">
                                                                        ACTIVE
                                                                    </c:if>
                                                                </button>
                                                                <input type="hidden" name="hotelID" value="${h.hotel_ID}"/>

                                                            </form>
                                                        </c:if>
                                                        <form action="HotelHost_ViewHotelInformationServlet" method="get">
                                                            <button  class="btn btn-outline-secondary mb-1 w-100 add-button">
                                                                View Hotel Information
                                                            </button>
                                                            <input type="hidden" name="hotelID" value="${h.hotel_ID}"/>
                                                        </form>

                                                    </td>
                                                </tr>

                                                <!-- Modal Update-->
                                            <div class="modal fade" id="confirmCancelModal" tabindex="-1" role="dialog" aria-labelledby="confirmCancelModalLabel" aria-hidden="true">
                                                <div class="modal-dialog" role="document">
                                                    <div class="modal-content">
                                                        <div class="modal-header">
                                                            <h5 class="modal-title" id="confirmCancelModalLabel">Update Hotel</h5>
                                                        </div>
                                                        <div class="modal-body justify-content-center">

                                                            <!-- Nhập form thực hiện update -->
                                                            <form id="updateForm" action="HotelHost_UpdateHotelServlet" method="get">
                                                                <div class="form-group">
                                                                    <label for="modalHotelID">Hotel ID</label>
                                                                    <input type="text" class="form-control" id="modalHotelID" name="hotelid" readonly>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="modalName">Name</label>
                                                                    <input type="text" class="form-control" id="modalName" name="name" >
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="modalDescription">Description</label>
                                                                    <textarea class="form-control no-resize-textarea" id="modalDescription" name="description" rows="3"></textarea>
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="modalEmail">Email</label>
                                                                    <input type="text" class="form-control" id="modalEmail" name="email" >
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="modalPhone">Phone</label>
                                                                    <input type="text" class="form-control" id="modalPhone" name="totalRooms">
                                                                </div>
                                                                <div class="form-group">
                                                                    <label for="modalAddress">Address</label>
                                                                    <input type="text" class="form-control" id="modalAddress" name="price">
                                                                </div>
                                                                <div class="form-group mb-3">
                                                                    <label for="modalStatus">Status</label>
                                                                    <select class="form-control" id="modalStatus" name="status">
                                                                        <option value="ACTIVE">ACTIVE</option>
                                                                        <option value="INACTIVE">INACTIVE</option>
                                                                    </select>
                                                                </div>
                                                                <button type="submit" class="btn btn-primary">Save changes</button>

                                                                <!-- Đá dữ liệu tùy ý -->
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                        </c:forEach>
                                        </tbody>
                                    </table>
                                    <ul class="pagination justify-content-center mt-3"></ul>
                                </c:when>
                                <c:otherwise>
                                    <div class="alert alert-warning" role="alert">
                                        No hotels available.
                                    </div>
                                </c:otherwise>
                            </c:choose>

                        </div>
                    </div>
                </div>
            </div>
        </div>
        <%@include file="Footer.jsp" %>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.5.2/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script>
            $(document).ready(function () {
                $('#hotelTable').DataTable();
            });

            document.addEventListener('DOMContentLoaded', () => {
                const cancelButtons = document.querySelectorAll('.cancel-button');
                const confirmCancelButton = document.getElementById('confirmCancelButton');
                let formToSubmit;

                cancelButtons.forEach(button => {
                    button.addEventListener('click', () => {
                        formToSubmit = button.closest('form');
                        $('#confirmCancelModal').modal('show');
                    });
                });

                confirmCancelButton.addEventListener('click', () => {
                    formToSubmit.submit();
                });
            });
            function redirectToUpdatePage() {
                window.location.href = 'HotelHost_UpdateRoom.jsp';
            }
            $(document).ready(function () {
                var rowsPerPage = 5;
                var rows = $('#hotelTable tbody tr');
                var rowsCount = rows.length;
                var pageCount = Math.ceil(rowsCount / rowsPerPage);
                var numbers = $('.pagination');
                for (var i = 0; i < pageCount; i++) {
                    numbers.append('<li class="page-item"><a class="page-link" href="#">' + (i + 1) + '</a></li>');
                }

                $('.pagination li:first-child').addClass('active');
                displayRows(1);
                $('.pagination li').on('click', function (e) {
                    e.preventDefault();
                    var $this = $(this);
                    var pageIndex = $this.index() + 1;
                    $('.pagination li').removeClass('active');
                    $this.addClass('active');
                    displayRows(pageIndex);
                });
                function displayRows(index) {
                    var start = (index - 1) * rowsPerPage;
                    var end = start + rowsPerPage;
                    rows.hide();
                    rows.slice(start, end).show();
                }
            });
            $(document).ready(function () {
                $('.update-button').on('click', function () {
                    var hotelId = $(this).data('hotel-id');
                    var name = $(this).data('name-room');
                    var email = $(this).data('email');
                    var description = $(this).data('description');
                    var phone = $(this).data('phone');
                    var address = $(this).data('address');
                    var status = $(this).data('status');
                    $('#modalHotelID').val(hotelId);
                    $('#modalName').val(name);
                    $('#modalEmail').val(email);
                    $('#modalDescription').val(description);
                    $('#modalPhone').val(phone);
                    $('#modalAddress').val(address);
                    $('#modalStatus').val(status);
                    $('#updateModal').modal('show');
                });
            });
            $(document).ready(function () {
                $('#hotelTable').DataTable({
                    paging: false // Disable default DataTable pagination
                });

                var rowsPerPage = 3; // Number of rows to display per page
                var rows = $('#hotelTable tbody tr');
                var rowsCount = rows.length;
                var pageCount = Math.ceil(rowsCount / rowsPerPage);
                var numbers = $('.pagination');

                // Add pagination numbers
                for (var i = 0; i < pageCount; i++) {
                    numbers.append('<li class="page-item"><a class="page-link" href="#">' + (i + 1) + '</a></li>');
                }

                // Show the first set of rows and mark the first pagination number as active
                $('.pagination li:first-child').addClass('active');
                displayRows(1);

                // Add click event to pagination numbers
                $('.pagination li').on('click', function (e) {
                    e.preventDefault();
                    var $this = $(this);
                    var pageIndex = $this.index() + 1;
                    $('.pagination li').removeClass('active');
                    $this.addClass('active');
                    displayRows(pageIndex);
                });

                // Function to display rows for the selected page index
                function displayRows(index) {
                    var start = (index - 1) * rowsPerPage;
                    var end = start + rowsPerPage;
                    rows.hide();
                    rows.slice(start, end).show();
                }
            });
        </script>
    </body>
</html>
