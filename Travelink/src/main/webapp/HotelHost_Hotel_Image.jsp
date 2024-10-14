<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room Image</title>
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
            * {
                font-family: Montserrat, sans-serif;
            }
            .row-compact {
                margin-bottom: 1rem; /* Adjust as needed */
            }
            #pieChart, #myChart {
                width: 100% !important;
                height: 100% !important;
            }
            .equal-height {
                display: flex;
                flex-direction: column;
                justify-content: space-between;
            }
            .card-flex {
                display: flex;
                flex: 1 1 auto;
                flex-direction: column;
            }
            #submit_Input{
                margin-top:10px;
                user-select: none;
                width: 100px;
                height: 30px;
                background-color: white;
                border: 1px solid grey;
                cursor: pointer;
            }
            #edit_img button:hover{
                background-color: rgb(250,250,250);
            }
            #submit_Input:hover{
                background-color: rgb(250,250,250);
            }

            .pd_button{
                float: right;
                margin-right: 20px;
            }
            .pd_button button{
                width: 100px;
                height: 40px;
                background-color: rgb(247,249,250);
                margin-right: 20px;
                border: 0px;
                color: rgb(1,148,243);
                border-radius: 5px;
                font-size: 20px;
                font-weight: bold;
                cursor: pointer;
            }
            .pd_button button a{
                color: rgb(1,148,243);
            }
            .pd_button button a:hover{
                background-color: rgb(242,243,243);
            }
            .pd_button button a:active{
                border: 5px solid rgb(1,148,243);
            }
            .pd_button input{
                width: 100px;
                height: 30px;
                border:0px;
                border-radius: 5px;
                background-color: rgb(1,148,243);
                color:white;
                font-size: 20px;
                font-weight: bold;
                cursor: pointer;
            }

            .pd_button input:hover{
                background-color: rgb(0,124,232);
            }
            .pd_button input:active{
                border: 5px solid rgb(1,148,243);
            }
            #ImgAvatar {
                display: none; /* Ẩn mặc định */
                border-radius: 10px;
                position: fixed;
                bottom: 250px;
                left: 50%;
                transform: translateX(-50%);
                background-color: #fff;
                padding: 20px;
                border: 1px solid #ccc;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
                z-index: 1000;
                transition: bottom 0.5s ease;
            }

            #ImgAvatar img {
                width: 500px;
                height: 500px;
                cursor: pointer;
            }

            #ImgAvatar .close-btn {
                position: absolute;
                top: 10px;
                right: 10px;
                background: transparent;
                color: #000;
                border: none;
                font-size: 24px;
                font-weight: bold;
                cursor: pointer;
                z-index: 1001;
            }
            #ImgAvatar .close-btn:hover {
                color: grey; /* Change color on hover */
                transform: scale(1.2); /* Slightly enlarge the button on hover */
            }

            .image-preview-container {
                margin-top: 10px;
                text-align: center;
            }

            .image-preview-container img {
                width: 100%;
                height: 100%;
                object-fit: cover;
                border: 1px solid #ddd;
                padding: 5px;
                border-radius: 5px;
            }
            .image-preview-container {
                width: 200px; /* Adjust the size as needed */
                height: 150px; /* Adjust the size as needed */
                border: 1px solid #ccc;
                overflow: hidden;
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
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container-fluid">
            <a href="UpdateHotelInformationServlet" class="btn btn-outline-primary mt-2"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <div class="row p-4 d-flex align-items-center row-compact">
                <div class="col-md-12">
                    <div class="card">
                        <h5 class="card-header">
                            <div class="d-flex align-items-center">
                                <img src="img_Hotel/list.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">
                                <h5 class="card-title mb-0">Hotel Information</h5>
                            </div>
                        </h5>
                        <div class="card-body">
                            <table id="hotelTable" class="table-hover table align-items-center justify-content-center">
                                <thead>
                                    <tr class="text-center">
                                        <th scope="col-md-1">No.</th>
                                        <th scope="col-md-6">IMAGE</th>                                        
                                        <th></th>
                                        <th scope="col-md-2">ACTION</th>
                                    </tr>
                                </thead>
                                <tbody class="text-center">
                                    <c:forEach var="hotelImg" items="${requestScope.imageList}" varStatus="status">
                                        <tr>
                                            <td>${status.index+1}</td>
                                            <td><img src="${hotelImg.url}" style="width: 20rem; height: 250px;"/></td>
                                            <td>
                                                <c:if test="${update == hotelImg.hotelImageID}">
                                                    <form method="post" action="UploadImageAvatar" enctype="multipart/form-data" onsubmit="return validateForm(event)">
                                                        <input type="file" id="file-add" name="file" size="60" accept=".jpeg,.jpg,.png" onchange="previewImage2(event, 'add')"/><br/>
                                                        <p>Maximum file size is 1 MB.<br>Format: .JPEG, .PNG</p>
                                                        <div class="image-preview-container d-flex justify-content-center align-items-center" id="preview-add">
                                                            <!-- Image preview will be displayed here -->
                                                        </div>
                                                        <input id="submit_Input-add" style="margin-top:10px" type="submit" value="Upload"/>
                                                        <div id="error-message-add">Please select a file to upload.</div>
                                                        <input type="hidden" name="uploadhotelhost" value="2.4"/>
                                                        <input type="hidden" name="hotelImgID" value="${hotelImg.hotelImageID}">
                                                        <input type="hidden" name="hotelID" value="${hotelImg.hotelID}">
                                                    </form>
                                                    <form method="post" action="UpdateAvatar">
                                                        <c:choose>
                                                            <c:when test="${not empty uploadedFilePath}">
                                                                <input type="hidden" name="urlAvatar" value="/Travelink/img_Avatar/${uploadedFilePath}"/>
                                                                <div class="pd_button">
                                                                    <button><a href="View_Avatar.jsp">Cancel</a></button>
                                                                    <input type="submit" value="Save"/>
                                                                </div>  
                                                                <input type="hidden" name="hotelImgID" value="${hotelImg.hotelImageID}">
                                                                <input type="hidden" name="hotelID" value="${hotelImg.hotelID}">
                                                                <input type="hidden" name="uploadhotelhost" value="2.4"/>
                                                            </c:when>
                                                            <c:otherwise>
                                                                <input type="hidden" name="urlAvatar" value=""/>
                                                                <div class="pd_button">
                                                                    <button style="pointer-events: none;cursor: not-allowed;opacity: 0.6;">Cancel</button>
                                                                    <input type="submit" value="Save" style="pointer-events: none;cursor: not-allowed;opacity: 0.6;"/>
                                                                </div>
                                                            </c:otherwise>
                                                        </c:choose>
                                                    </form>
                                                </c:if>
                                            </td>
                                            <td>
                                                <form action="HotelHost_ViewHotelImgServlet" method="post">
                                                    <button class="btn btn-outline-primary mb-3" style="width: 10rem;">Update</button>
                                                    <input type="hidden" name="hotelImgID" value="${hotelImg.hotelImageID}">
                                                    <input type="hidden" name="hotelID" value="${hotelImg.hotelID}">
                                                </form >
                                                <form action="HotelHost_DeleteHotelImageServlet" method="post">
                                                    <button class="btn btn-outline-danger" style="width: 10rem;">Delete</button>
                                                    <input type="hidden" name="hotelID" value="${hotelImg.hotelID}">
                                                    <input type="hidden" name="hotelImgID" value="${hotelImg.hotelImageID}">
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>

                                </tbody>


                            </table>
                            <div class="row m-2">
                                <div class="form-group mb-2">
                                    <label for="roomID">Hotel ID</label>
                                    <input type="text" class="form-control" id="newRoomID" name="roomID" value="${hotelID}" readonly="">
                                </div>
                                <!-- Form upload image -->
                                <form method="post" action="UploadImageAvatar" enctype="multipart/form-data" onsubmit="return validateForm(event)">
                                    <input type="file" id="file-add" name="file" size="60" accept=".jpeg,.jpg,.png" onchange="previewImage2(event, 'add')"/><br/>
                                    <p>Maximum file size is 1 MB.<br>Format: .JPEG, .PNG</p>
                                    <div class="image-preview-container d-flex justify-content-center align-items-center" id="preview-add">
                                        <!-- Image preview will be displayed here -->
                                    </div>
                                    <input id="submit_Input-add" style="margin-top:10px" type="submit" value="Upload"/>
                                    <div id="error-message-add">Please select a file to upload.</div>
                                    <input type="hidden" name="hotelID" value="${hotelID}">
                                    <input type="hidden" name="uploadhotelhost" value="2.5"/>
                                </form>
                                <form method="post" action="UpdateAvatar">
                                    <c:choose>
                                        <c:when test="${not empty uploadedFilePath}">
                                            <input type="hidden" name="urlAvatar" value="/Travelink/img_Avatar/${uploadedFilePath}"/>
                                            <div class="pd_button">
                                                <button><a href="View_Avatar.jsp">Cancel</a></button>
                                                <input type="submit" value="Save"/>
                                            </div>  

                                            <input type="hidden" name="hotelID" value="${hotelID}">
                                            <input type="hidden" name="uploadhotelhost" value="2.5"/>
                                        </c:when>
                                        <c:otherwise>
                                            <input type="hidden" name="urlAvatar" value=""/>
                                            <div class="pd_button">
                                                <button style="pointer-events: none;cursor: not-allowed;opacity: 0.6;">Cancel</button>
                                                <input type="submit" value="Save" style="pointer-events: none;cursor: not-allowed;opacity: 0.6;"/>
                                            </div>
                                        </c:otherwise>
                                    </c:choose>
                                </form>

                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
                                    

        <c:if test="${requestScope.updateStatus != null}">
            <script>
                const updateStatus = '<%= request.getAttribute("updateStatus") %>';
                if (updateStatus && updateStatus.trim() !== '') {
                    Swal.fire({
                        icon: 'success',
                        title: 'Success',
                        text: updateStatus,
                        confirmButtonText: 'OK'
                    });
                }

            </script>
        </c:if>
        <%@include file="Footer.jsp" %>
        <script src="https://cdn.datatables.net/1.11.5/js/jquery.dataTables.min.js"></script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/2.5.2/umd/popper.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/chart.js"></script>
        <script>
                $(document).ready(function () {
                    $('#hotelTable').DataTable();
                });

                $(document).on('click', '.update-button', function () {
                    var imageId = $(this).data('image-id');
                    var modalId = '#updateModal-' + imageId;
                    $(modalId).modal('show');
                });

                $(document).on('click', '.delete-button', function () {
                    var imageId = $(this).data('image-id');
                    // Handle delete functionality here
                    // You might want to show a confirmation dialog or redirect to a delete servlet
                });

                function previewImage(event, imageId) {
                    var reader = new FileReader();
                    reader.onload = function () {
                        var preview = document.getElementById('preview-' + imageId);
                        preview.innerHTML = '<img src="' + reader.result + '" alt="Image preview">';
                    };
                    reader.readAsDataURL(event.target.files[0]);
                }

                function uploadImage(imageId) {
                    var fileInput = document.getElementById('file-' + imageId);
                    var file = fileInput.files[0];
                    var formData = new FormData();
                    formData.append('file', file);
                    formData.append('roomImageID', imageId); // Assuming you need to send the image ID

                    var xhr = new XMLHttpRequest();
                    xhr.open('POST', 'UploadImageAvatar', true);

                    xhr.onload = function () {
                        if (xhr.status === 200) {
                            // Update the image source on the page with the new image URL
                            var response = JSON.parse(xhr.responseText);
                            var preview = document.getElementById('preview-' + imageId);
                            preview.innerHTML = '<img src="' + response.newImageUrl + '" alt="Image preview" style="width: 150px; height: 150px; object-fit: cover;">';


                            // Optionally, display a success message
                            alert('Image uploaded successfully!');
                        } else {
                            // Display error message
                            var errorMessage = document.getElementById('error-message-' + imageId);
                            errorMessage.textContent = 'Error uploading image. Please try again.';
                        }
                    };

                    xhr.send(formData);
                }

                function validateForm(event, imageID) {
                    const fileInput = document.getElementById('file-' + imageID);
                    const errorMessage = document.getElementById('error-message-' + imageID);
                    if (!fileInput.files.length) {
                        event.preventDefault();
                        errorMessage.style.display = 'block';
                        return false;
                    }
                    errorMessage.style.display = 'none';
                    return true;
                }

                function previewImage2(event, id) {
                    var input = event.target;
                    var previewContainer;

                    if (id === 'add') {
                        previewContainer = document.getElementById('preview-add');
                    } else {
                        previewContainer = document.getElementById(`preview-${id}`);
                    }

                    if (input.files && input.files[0]) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            previewContainer.innerHTML = '<img src="' + e.target.result + '" alt="Image preview" style="max-width: 100%; max-height: 200px;"/>';
                        };
                        reader.readAsDataURL(input.files[0]);
                    } else {
                        previewContainer.innerHTML = '';
                    }
                }

        </script>
    </body>
</html>
