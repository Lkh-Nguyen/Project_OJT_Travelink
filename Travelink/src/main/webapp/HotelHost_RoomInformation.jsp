<%-- 
    Document   : HotelHost_HotelService
    Created on : Jun 15, 2024, 3:35:18 PM
    Author     : admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_HotelService.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>

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
            .btn {
                border-radius: 0.375rem; /* Rounded corners */
                font-weight: 600; /* Bold text */
                text-transform: uppercase; /* Uppercase text */
                padding: 0.5rem 1rem; /* Padding */
                transition: all 0.3s ease; /* Smooth transition */
            }

            /* Primary Button */
            .btn-primary {
                background-color: #007bff;
                border: 1px solid #007bff;
                color: white;
            }

            .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            /* Outline Primary Button */
            .btn-outline-primary {
                background-color: transparent;
                border-color: #007bff;
                color: #007bff;
            }

            .btn-outline-primary:hover {
                background-color: #007bff;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }

            /* Danger Button */
            .btn-danger {
                background-color: #dc3545;
                border: 1px solid #dc3545;
                color: white;
            }

            .btn-danger:hover {
                background-color: #c82333;
                border-color: #bd2130;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(220, 53, 69, 0.25);
            }

            /* Outline Danger Button */
            .btn-outline-danger {
                background-color: transparent;
                border-color: #dc3545;
                color: #dc3545;
            }

            .btn-outline-danger:hover {
                background-color: #dc3545;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(220, 53, 69, 0.25);
            }

            /* Secondary Button */
            .btn-secondary {
                background-color: #6c757d;
                border: 1px solid #6c757d;
                color: white;
            }

            .btn-secondary:hover {
                background-color: #5a6268;
                border-color: #545b62;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(108, 117, 125, 0.25);
            }

            /* Outline Secondary Button */
            .btn-outline-secondary {
                background-color: transparent;
                border-color: #6c757d;
                color: #6c757d;
            }

            .btn-outline-secondary:hover {
                background-color: #6c757d;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(108, 117, 125, 0.25);
            }

            /* Modal Button Styles */
            .modal-footer .btn-primary {
                background-color: #007bff;
                border-color: #007bff;
                color: white;
            }

            .modal-footer .btn-primary:hover {
                background-color: #0056b3;
                border-color: #0056b3;
                color: white;
                box-shadow: 0 0 0 0.2rem rgba(0, 123, 255, 0.25);
            }
        </style>
        <script>
            window.onload = function () {
                const urlParams = new URLSearchParams(window.location.search);
                const status = urlParams.get('status');
                const message = urlParams.get('message');
                if (status === 'success') {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: message,
                        confirmButtonText: 'OK'
                    });
                } else if (status === 'failure') {
                    Swal.fire({
                        icon: 'error',
                        title: 'Error',
                        text: 'Operation failed: ' + message,
                        confirmButtonText: 'OK'
                    });
                }
            }
        </script>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container-fluid">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2">
                <img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
            </a>

            <div class="container mt-5">
                <div class="card">
                    <h5 class="card-header">
                        Select Hotel
                    </h5>
                    <div class="card-body">
                        <form action="UpdateHotelRoomServlet" method="get">
                            <div class="form-group">
                                <select id="hotelSelect" name="hotel_ID" class="form-control" onchange="this.form.submit()">
                                    <c:forEach var="hotel" items="${hotel_list}">
                                        <option value="${hotel.hotel_ID}" ${hotel.hotel_ID == hotel_id ? 'selected' : ''}>
                                            ${hotel.name}
                                        </option>
                                    </c:forEach>
                                </select>
                            </div>
                            <input type="hidden" name="selected_hotel_ID" id="selected_hotel_ID" value="${hotel_id}"/>
                        </form>
                    </div>
                </div>
            </div>



            <div class="row p-4">
                <div class="col-md-12">
                    <div class="card">
                        <h5 class="card-header">
                            <div class="d-flex align-items-center">
                                <img src="img_Hotel/add.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                <h4>${requestScope.msg}</h4>
                                <h5 class="card-title mb-0">Add New Room</h5>
                            </div>
                        </h5>
                        <div class="card-body">
                            <form action="UpdateHotelRoomServlet" method="post" id="addRoomForm">
                                <input type="hidden" value="${requestScope.hotel_id}" name="hotel_ID">
                                <div class="form-group">
                                    <label for="HotelID">Hotel ID</label>
                                    <input type="text" class="form-control" id="newHotelID" name="hotel_ID" value="${requestScope.hotel_id}" readonly>
                                </div>
                                <div class="form-group">
                                    <label for="newHotelName">Name</label>
                                    <input type="text" class="form-control" id="name" name="name" required="">
                                </div>
                                <div class="form-group">
                                    <label for="newRoomCapacity">Capacity</label>
                                    <input type="number" min="1" class="form-control" id="newRoomCapacity" name="capacity" required="">
                                </div>
                                <div class="form-group">
                                    <label for="newRoomDescription">Description</label>
                                    <input type="text" class="form-control" id="newRoomDescription" name="description" required="">
                                </div>
                                <div class="form-group">
                                    <label for="newRoomTotalRooms">Total Rooms</label>
                                    <input type="number" min="1" class="form-control" id="newRoomTotalRooms" name="totalRooms" required="">
                                </div>
                                <div class="form-group">
                                    <label for="newRoomPrice">Price</label>
                                    <input type="number" min="1" class="form-control" id="newRoomPrice" name="price" required="">
                                </div>
                                <div class="form-group mb-3">
                                    <label for="modalStatus">Status</label>
                                    <select class="form-control" id="modalStatus" name="status" required="">
                                        <option value="ACTIVE">ACTIVE</option>
                                        <option value="INACTIVE">INACTIVE</option>
                                    </select>
                                </div>
                                <button type="submit" class="btn btn-primary">Add Room</button>
                                <input type="hidden" name="action" value="add">
                            </form>
                            <div>
                                <p>${requestScope.error}</p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row p-4 d-flex align-items-center row-compact">
                <div class="col-md-12">
                    <div class="card">
                        <h5 class="card-header">
                            <div class="d-flex align-items-center">
                                <img src="img_Hotel/list.svg" alt="List Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                <h5 class="card-title mb-0">Room Information</h5>
                            </div>
                        </h5>
                        <div class="card-body">
                            <table id="hotelTable" class="table-hover table align-items-center justify-content-center">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col">No.</th>
                                        <th scope="col">NAME</th>
                                        <th scope="col">ROOM ID</th>
                                        <th scope="col">CAPACITY</th>
                                        <th scope="col">TOTAL ROOMS</th>
                                        <th scope="col">PRICE</th>
                                        <th scope="col">STATUS</th>
                                        <th scope="col">ACTION</th>
                                    </tr>
                                </thead>
                                <c:forEach var="h" items="${requestScope.room_list}" varStatus="loopStatus">
                                    <tbody class="table-group-divider align-items-center text-center mt-2">
                                        <tr>
                                            <td>${(currentPage - 1) * recordsPerPage + loopStatus.index + 1}</td>
                                            <td>${h.name}</td>
                                            <td>${h.room_ID}</td>
                                            <td>${h.capacity}</td>
                                            <td>${h.totalRooms}</td>
                                            <td>${h.price}</td>
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
                                                <form class="row m-1 p-1" action="UpdateHotelRoomServlet" method="post" id="cancelForm">
                                                    <button type="button" class="btn btn-outline-primary mb-1 w-100 cancel-button update-button" 
                                                            data-hotel-id="${h.hotel_ID}" data-name-room="${h.name}" data-description="${h.roomDescription}"
                                                            data-capacity="${h.capacity}" data-room-id="${h.room_ID}" data-total-rooms="${h.totalRooms}"
                                                            data-price="${h.price}" data-status="${h.status}" data-reservation-id="${entry.key}">
                                                        Update Detail
                                                    </button>
                                                    <input type="hidden" name="action" value="update"/>
                                                </form>       
                                                <div class="row m-2">
                                                    <a class="btn btn-outline-primary" href="UpdateHotelBedServlet?room_ID=${h.room_ID}&hotel_ID=${h.hotel_ID}">
                                                        Bed Detail
                                                    </a>
                                                </div>

                                                <div class="row m-2">
                                                    <a class="btn btn-outline-primary" href="UpdateImageRoom?room_ID=${h.room_ID}">
                                                        Room Image Detail
                                                    </a>
                                                </div>

                                            </td>
                                        </tr>
                                    <div class="modal fade" id="confirmCancelModal" tabindex="-1" role="dialog" aria-labelledby="confirmCancelModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title text-light" id="confirmCancelModalLabel">Update Room</h5>
                                                </div>
                                                <div class="modal-body justify-content-center">
                                                    <form id="updateForm" action="UpdateHotelRoomServlet" method="post">
                                                        <div class="form-group">
                                                            <label for="modalHotelID">Hotel ID</label>
                                                            <input type="text" class="form-control" id="modalHotelID" name="hotelid" readonly>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="modalName">Name</label>
                                                            <input type="text" class="form-control" id="modalName" name="name" readonly>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="modalCapacity">Capacity</label>
                                                            <input type="number" min="0" class="form-control" id="modalCapacity" name="capacity">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="modalDescription">Description</label>
                                                            <input type="text" class="form-control" id="modalDescription" name="description" readonly>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="modalRoomID">Room ID</label>
                                                            <input type="text" class="form-control" id="modalRoomID" name="roomid" readonly>
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="modalTotalRooms">Total Rooms</label>
                                                            <input type="number" min="0" class="form-control" id="modalTotalRooms" name="totalRooms">
                                                        </div>
                                                        <div class="form-group">
                                                            <label for="modalPrice">Price</label>
                                                            <input type="number" min="1" class="form-control" id="modalPrice" name="price">
                                                        </div>
                                                        <div class="form-group mb-3">
                                                            <label for="modalStatus">Status</label>
                                                            <select class="form-control" id="modalStatus" name="status">
                                                                <option value="ACTIVE">ACTIVE</option>
                                                                <option value="INACTIVE">INACTIVE</option>
                                                            </select>
                                                        </div>
                                                        <input type="hidden" name="action" value="update">
                                                        <button type="submit" class="btn btn-primary">Save changes</button>
                                                        <input type="hidden" name="hotel_ID" id="modalHotelID" value="">
                                                        <input type="hidden" name="room_ID" id="modalRoomID" value="">
                                                    </form>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    </tbody>
                                </c:forEach>
                            </table>
                            
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
                                        // Initialize DataTable
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
                                        $('.update-button').on('click', function () {
                                            var hotelId = $(this).data('hotel-id');
                                            var name = $(this).data('name-room');
                                            var capacity = $(this).data('capacity');
                                            var roomId = $(this).data('room-id');
                                            var description = $(this).data('description');
                                            var totalRooms = $(this).data('total-rooms');
                                            var price = $(this).data('price');
                                            var status = $(this).data('status');

                                            $('#modalHotelID').val(hotelId);
                                            $('#modalName').val(name);
                                            $('#modalCapacity').val(capacity);
                                            $('#modalDescription').val(description);
                                            $('#modalRoomID').val(roomId);
                                            $('#modalTotalRooms').val(totalRooms);
                                            $('#modalPrice').val(price);
                                            $('#modalStatus').val(status);

                                            $('#updateModal').modal('show');
                                        });
                                    });

                                    $(document).ready(function () {
                                        $('.delete-button').on('click', function () {
                                            var hotelId = $(this).data('hotel-id');
                                            var roomId = $(this).data('room-id');

                                            $('#modalHotelID').val(hotelId);
                                            $('#modalRoomID').val(roomId);

                                            $('#updateModal').modal('show');
                                        });
                                    });

                                   


        </script>
    </body>
</html>
