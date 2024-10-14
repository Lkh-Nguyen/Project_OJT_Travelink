<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Room</title>
        <link rel="icon" href="img_Home/logo.png">
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.4/css/all.min.css" rel="stylesheet">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="https://cdn.datatables.net/1.11.5/css/jquery.dataTables.min.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/sweetalert2@11/dist/sweetalert2.min.css">
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            .container {
                max-width: 600px;
                margin-top: 50px;
                background: #ffffff;
                padding: 30px;
                border-radius: 8px;
                box-shadow: 0 4px 8px rgba(0,0,0,0.1);
            }
            .form-control::placeholder {
                color: #6c757d;
                opacity: 1; /* Firefox */
            }
        </style>
    </head>
    <body>
        <%@include file="Header_HotelHost.jsp" %>
        <div class="container">
            <a href="UpdateHotelInformationServlet" class="btn btn-outline-primary mt-2 mb-4"><img src="img_Hotel/back.svg" alt="Special Icon" style="width: 1rem; height: 1rem;" class="me-2">Back</a>
            <h2 class="mb-4 text-center">Add New Hotel</h2>

            <div class="modal fade" id="confirmCancelModal" tabindex="-1" role="dialog" aria-labelledby="confirmCancelModalLabel" aria-hidden="true">
                <div class="modal-dialog" role="document">
                    <div class="modal-content">
                        <div class="modal-header">
                            <h5 class="modal-title" id="confirmCancelModalLabel">Information of Location</h5>
                            <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                <span aria-hidden="true">&times;</span>
                            </button>
                        </div>
                        <div class="modal-body">
                            <form action="HotelHost_AddWardServlet" method="post">
                                <div class="form-group">
                                    <label for="provinceSelect">Province</label>
                                    <select id="provinceSelect" name="provinceID" class="form-control" onchange="this.form.submit()">
                                        <c:if test="${province != null}">
                                            <option value="${province.province_ID}">${province.name}</option>
                                        </c:if>
                                        <c:forEach var="province" items="${provinceList}">                   
                                            <option value="${province.province_ID}">${province.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <c:if test="${districtList != null}">
                                    <div class="form-group">
                                        <label for="districtSelect">District</label>
                                        <select id="districtSelect" name="districtID" class="form-control" onchange="this.form.submit()">
                                            <c:if test="${district != null}">
                                                <option value="${district.district_ID}">${district.name}</option>
                                            </c:if>
                                            <c:forEach var="district" items="${districtList}">
                                                <option value="${district.district_ID}">${district.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                                <c:if test="${wardList != null}">
                                    <div class="form-group">
                                        <label for="wardSelect">Ward</label>
                                        <select id="wardSelect" name="wardID" class="form-control" onchange="this.form.submit()">
                                            <c:forEach var="ward" items="${wardList}">
                                                <option value="${ward.ward_ID}">${ward.name}</option>
                                            </c:forEach>
                                        </select>
                                    </div>
                                </c:if>
                            </form>
                        </div>
                    </div>
                </div>
            </div>


            <form action="HotelHost_AddHotelServlet" method="post">
                <c:if test="${status != null}">
                    <p>${status}</p>
                </c:if>
                <label for="ward_ID">Location</label>
                <div class="input-group mb-3">
                    <c:if test="${ward != null}">
                        <input class="form-control" id="ward_ID" value="${ward.name}">
                    </c:if>
                    
                    <button type="button" class="btn btn-primary cancel-button update-button">
                        Choose Location
                    </button>
                </div>


                <div class="form-group">
                    <label for="name">Hotel Name</label>
                    <input value="${hotel.name}" type="text" class="form-control" id="name" name="name" placeholder="Enter hotel name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email</label>
                    <input value="${hotel.email}" type="email" class="form-control" id="email" name="email" placeholder="Enter hotel email" required>
                </div>
                
                <div class="form-group">
                    <label for="star">Star</label>
                    <input value="${hotel.star}" type="number" class="form-control" id="star" name="star" min="1" max="5" placeholder="Enter star rating" required>
                </div>
                <div class="form-group">
                    <label for="phoneNumber">Phone Number</label>
                    <input value="${hotel.phoneNumber}" type="tel" class="form-control" id="phoneNumber" name="phoneNumber" placeholder="Enter phone number" required>
                </div>
                <div class="form-group">
                    <label for="description">Description</label>
                    <textarea class="form-control" id="description" name="description" rows="4" placeholder="Enter description">${hotel.description}</textarea>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="checkInTimeStart">Check-In Time Start</label>
                        <input value="${hotel.checkInTimeStart}" type="time" class="form-control" id="checkInTimeStart" name="checkInTimeStart" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="checkInTimeEnd">Check-In Time End</label>
                        <input value="${hotel.checkInTimeEnd}" type="time" class="form-control" id="checkInTimeEnd" name="checkInTimeEnd" required>
                    </div>
                </div>
                <div class="form-row">
                    <div class="form-group col-md-6">
                        <label for="checkOutTimeStart">Check-Out Time Start</label>
                        <input value="${hotel.checkOutTimeStart}" type="time" class="form-control" id="checkOutTimeStart" name="checkOutTimeStart" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="checkOutTimeEnd">Check-Out Time End</label>
                        <input value="${hotel.checkOutTimeEnd}" type="time" class="form-control" id="checkOutTimeEnd" name="checkOutTimeEnd" required>
                    </div>
                </div>
                <div class="form-group mb-2">
                    <label for="address">Address</label>
                    <input value="${hotel.address}" type="text" class="form-control" id="address" name="address" placeholder="Enter address" required>
                </div>



                <div class="form-group">
                    <label for="facility">Facility List</label> <hr>
                    <c:forEach var="facility" items="${facilityList}">
                        <input type="checkbox" name="${facility.name}" value="${facility.facilityID}"> ${facility.name} <hr>
                    </c:forEach>
                    <button type="submit" class="btn btn-primary btn-block">Add Hotel</button>
                    <input type="hidden" name="ward_ID" value="${wardID}">
                </div>

            </form>
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
        <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.4/dist/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
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
        </script>
    </body>
</html>
