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
        <title>Dash Board</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_HotelService.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
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
            <a href="UpdateHotelRoomServlet?hotelID=${requestScope.hotel_ID}" class="btn btn-outline-primary mt-2"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>


            <!-- Add Bed Form -->
            <div class="row p-4">
                <div class="col-md-12">
                    <div class="card">
                        <h5 class="card-header">
                            <div class="d-flex align-items-center">
                                <img src="img_Hotel/add.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                <h5 class="card-title mb-0">Add New Bed</h5>
                            </div>
                        </h5>
                        <div class="card-body">
                            <form id="addBedForm" action="UpdateHotelBedServlet" method="post">
                                <div class="form-group">
                                    <label for="addRoomId">Room ID</label>
                                    <input type="text" class="form-control" id="addRoomId" name="room_ID" value="${requestScope.room_ID}" readonly>
                                    <input type="hidden" name="room_ID" value="${requestScope.room_ID}">
                                    <input type="hidden" name="hotel_ID" value="${requestScope.hotel_ID}">
                                </div>
                                <div class="form-group">
                                    <label for="bedSelect">Select Bed</label>
                                    <select class="form-control" id="bedSelect">
                                        <option value="1" data-name-bed="Giường Đơn" data-description="Rộng 90 - 130 cm" data-url="URL_for_single_bed_image" >Giường Đơn</option>
                                        <option value="2" data-name-bed="Giường Đôi" data-description="Rộng 131 - 150 cm" data-url="URL_for_double_bed_image">Giường Đôi</option>
                                        <option value="3" data-name-bed="Giường lớn (cỡ King)" data-description="Rộng 151 - 180 cm" data-url="URL_for_king_bed_image">Giường lớn (cỡ King)</option>
                                        <option value="4" data-name-bed="Giường cực lớn (cỡ Super-king)" data-description="Rộng 181 - 210 cm" data-url="URL_for_super_king_bed_image">Giường cực lớn (cỡ Super-king)</option>
                                    </select>
                                </div>
                                <div class="form-group">
                                    <label for="addName">Name</label>
                                    <input type="text" class="form-control" id="addName" name="name-bed" readonly="">
                                </div>
                                <div class="form-group">
                                    <label for="addDescription">Description</label>
                                    <input type="text" class="form-control" id="addDescription" name="description" readonly="">
                                </div>
                                <div class="form-group">
                                    <label for="newAmount">Amount</label>
                                    <input type="number" min="1" class="form-control" id="amount" name="amount" required="">
                                </div>
                                <div class="form-group mb-2">
                                    <label for="addURL">URL</label>
                                    <input type="text" class="form-control" id="addURL" name="url" readonly="">
                                </div>
                                <button type="submit" class="btn btn-primary">Add Bed</button>
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
                                <img src="img_Hotel/list.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                <h5 class="card-title mb-0">Bed Information</h5>
                            </div>
                        </h5>
                        <div class="card-body">
                            <table id="hotelTable" class="table-hover table align-items-center justify-content-center">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col">No.</th>
                                        <th scope="col">BED ID</th>
                                        <th scope="col">NAME</th>
                                        <th scope="col">DESCRIPTION</th>
                                        <th scope="col">URL</th>
                                        <th scope="col">ACTION</th>
                                    </tr>
                                </thead>
                                <c:forEach var="b" items="${requestScope.beds_list}" varStatus="loopStatus">
                                    <tbody class="table-group-divider align-items-center justify-content-center text-center mt-2">
                                        <tr>
                                            <th scope="row">${loopStatus.index + 1}</th>
                                            <td>${b.bed_ID}</td>
                                            <td>${b.name}</td>
                                            <td>${b.description}</td>
                                            <td>${b.url}</td>
                                            <td>
                                                <form class="row m-1 p-1" action="#" method="#" id="cancelForm">
                                                    <button type="button" class="btn btn-outline-primary mb-1 w-100 cancel-button update-button" data-room-id ="${requestScope.room_ID}"
                                                            data-bed-id ="${b.bed_ID}" data-name ="${b.name}" data-description="${b.description}" data-url="${b.url}"
                                                            ">
                                                        Update
                                                    </button>
                                                    <input type="hidden" name="hotel_ID" value="${h.hotel_ID}"/>
                                                    <input type="hidden" name="action" value="update"/>
                                                </form>
                                            </td>
                                        </tr>
                                    <div class="modal fade" id="confirmCancelModal" tabindex="-1" role="dialog" aria-labelledby="confirmCancelModalLabel" aria-hidden="true">
                                        <div class="modal-dialog" role="document">
                                            <div class="modal-content">
                                                <div class="modal-header">
                                                    <h5 class="modal-title" id="confirmCancelModalLabel">Information Bed Of Room: ${requestScope.room_ID}</h5>
                                                </div>
                                                <div class="modal-body justify-content-center">
                                                    <div class="modal-body">
                                                        <form id="updateForm" action="UpdateHotelRoomServlet" method="post">
                                                            <div class="form-group">
                                                                <label for="modalRoomId">Room ID</label>
                                                                <input type="text" class="form-control" id="modalRoomID" name="roomid" readonly="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="modalBedId">Bed ID</label>
                                                                <input type="text" class="form-control" id="modalBedID" name="bedid" readonly="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="modalName">Name</label>
                                                                <input type="text" class="form-control" id="modalName" name="name" readonly="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="modalDescription">Description</label>
                                                                <input type="text" class="form-control" id="modalDescription" name="description" readonly="">
                                                            </div>
                                                            <div class="form-group">
                                                                <label for="modalURL">URL</label>
                                                                <input type="text" class="form-control" id="modalURL" name="url" readonly="">
                                                            </div>
                                                            <br>
                                                        </form>
                                                    </div>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.5.2/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script>
            //ModalUpdate
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
            });

            function redirectToUpdatePage() {
                window.location.href = 'HotelHost_UpdateRoom.jsp';
            }

            function initializePage() {
                // Initialize DataTable
                $('#hotelTable').DataTable();

                // Handle bed select and input population for Add Bed form
                $('#bedSelect').on('change', function () {
                    var selectedOption = $(this).find('option:selected');
                    $('#addName').val(selectedOption.data('name-bed'));
                    $('#addDescription').val(selectedOption.data('description'));
                    $('#addURL').val(selectedOption.data('url'));
                });
            }
            function handleUpdateButtonClick() {
                $('.update-button').on('click', function () {
                    var name = $(this).data('name');
                    var roomId = $(this).data('room-id');
                    var bedId = $(this).data('bed-id');
//                    var amount = $(this).data('amount');
                    var description = $(this).data('description');
                    var url = $(this).data('url');

                    // Populate the update modal form with the selected data
                    $('#modalName').val(name);
                    $('#modalDescription').val(description);
                    $('#modalRoomID').val(roomId);
                    $('#modalURL').val(url);
                    $('#modalBedID').val(bedId);
//                    $('#modalAmount').val(amount);

                    // Show the update modal
                    $('#updateModal').modal('show');
                });
            }
            function handleDeleteButtonClick() {
                $('.delete-button').on('click', function () {
                    var formToSubmit = $(this).closest('form');
                    $('#confirmDeleteModal').modal('show');
                    $('#confirmDeleteButton').on('click', function () {
                        formToSubmit.submit();
                    });
                });
            }
            $(document).ready(function () {
                initializePage();
                handleUpdateButtonClick();
                handleDeleteButtonClick();
            });

        </script>
    </body>
</html>
