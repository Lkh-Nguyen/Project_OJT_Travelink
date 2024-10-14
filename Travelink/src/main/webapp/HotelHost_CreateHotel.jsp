<%-- 
    Document   : HotelHost_CreateHotel
    Created on : Jun 16, 2024, 11:01:00 AM
    Author     : admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Upload và Xem File</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <style>
            .file-container {
                border: 1px solid #ddd;
                padding: 10px;
                margin-bottom: 20px;
            }
            .file-link {
                color: #007bff;
                text-decoration: none;
                cursor: pointer;
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container">
            <div class="card bg-secondary-subtle h-100 shadow p-lg-4 mb-5 bg-body-tertiary border-0 equal-height justify-content-center align-items-center" style="border-radius: 30px;">
                <div class="card h-100 shadow p-lg-5 mt-3 mb-3 bg-body-tertiary border-0 equal-height">
                    <div class="card-header">
                        <h3>UPLOAD FILES</h3>
                    </div>
                    <div class="row mt-3">
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Upload Image</h6>
                                    <input type="file" class="form-control-file" id="imageInput" accept="image/*">
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Identity card or passport</h6>
                                    <input type="file" class="form-control-file" id="passportInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Business registration certificate</h6>
                                    <input type="file" class="form-control-file" id="businessInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Certificate of hotel management</h6>
                                    <input type="file" class="form-control-file" id="managementInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Architectural drawings of the hotel</h6>
                                    <input type="file" class="form-control-file" id="architectInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Fire safety certificate</h6>
                                    <input type="file" class="form-control-file" id="fireInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                        </div>
                        <div class="col-md-4">
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Food safety certificate (if applicable)</h6>
                                    <input type="file" class="form-control-file" id="foodInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Construction permit (if applicable)</h6>
                                    <input type="file" class="form-control-file" id="constructionInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                            <div class="card mb-4">
                                <div class="card-body">
                                    <h6 class="card-title">Tax and financial documents</h6>
                                    <input type="file" class="form-control-file" id="taxInput" accept="application/pdf,image/*">
                                </div>
                            </div>
                        </div>
                    </div>

                    <div class="row mt-3">
                        <div class="col-md-4">
                            <div class="file-container" id="fileContainer">
                                <h6 class="card-title">Document  File</h6>
                                <a class="file-link text-decoration-none" id="fileLink" href="#" target="_blank">HotelHostRulesDocument.pdf</a>
                            </div>
                        </div>
                        <div class="col-md-4"></div>
                        <div class="col-md-4">
                            <button type="button" class="btn btn-outline-primary btn-block" id="btn-update">Cancel</button>
                            <button type="button" class="btn btn-primary btn-block" id="btn-next">Save</button>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <!-- Modal Structure -->
        <div class="modal fade" id="emailModal" tabindex="-1" aria-labelledby="emailModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="emailModalLabel">Enter Your Email</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" id="btn-close" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        <div class="mb-3">
                            <label for="emailInput" class="form-label">Email address</label>
                            <input type="email" class="form-control" id="emailInput" placeholder="Enter your email">
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-primary" id="btn-send">Send</button>
                    </div>
                </div>
            </div>
        </div>

        <%@include file="Footer.jsp" %>
        <script>
            document.getElementById('btn-next').addEventListener('click', function () {
                var emailModal = new bootstrap.Modal(document.getElementById('emailModal'));
                emailModal.show();
            });
            document.getElementById('btn-send').addEventListener('click', function () {
                var emailModal = bootstrap.Modal.getInstance(document.getElementById('emailModal'));
                emailModal.hide();
            });
            document.getElementById('btn-close').addEventListener('click', function () {
                var emailModal = bootstrap.Modal.getInstance(document.getElementById('emailModal'));
                emailModal.hide();
            });

            // Xử lý khi người dùng chọn file
            document.querySelectorAll('.form-control-file').forEach(input => {
                input.addEventListener('change', function (e) {
                    var file = e.target.files[0]; // Lấy file đã chọn

                    // Kiểm tra xem đã chọn file hay chưa
                    if (file) {
                        var reader = new FileReader();
                        reader.onload = function (e) {
                            var fileLink = document.getElementById('fileLink');
                            fileLink.textContent = file.name;
                            fileLink.setAttribute('href', e.target.result);

                            // Hiển thị file trong iframe nếu là PDF
                            if (file.type.includes('pdf')) {
                                var fileContainer = document.getElementById('fileContainer');
                                fileContainer.innerHTML = `<h6 class="card-title">PDF Document</h6>
                                                         <div>
                                                             <embed src="${e.target.result}" type="application/pdf" width="100%" height="600px" />
                                                         </div>`;
                            } else if (file.type.includes('image')) {
                                var fileContainer = document.getElementById('fileContainer');
                                fileContainer.innerHTML = `<h6 class="card-title">Image Preview</h6>
                                                         <div>
                                                             <img src="${e.target.result}" class="img-fluid" alt="Image Preview" />
                                                         </div>`;
                            } else {
                                console.log('Unsupported file type.');
                            }
                        };
                        reader.readAsDataURL(file);
                    } else {
                        console.log('No file chosen.');
                    }
                });
            });
        </script>
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.2/dist/umd/popper.min.js"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    </body>
</html>
