<%-- 
    Document   : HotelHost_BookingRevenue
    Created on : Jun 15, 2024, 11:27:08 PM
    Author     : HELLO
--%>
<%@page import="java.util.List" %>
<%@page import="com.travelink.Model.*" %>
<%@page import="java.util.*" %>
<%@page import="java.text.DecimalFormat" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link rel="stylesheet" href="bootstrap_css/bootstrap.min.css">
        <link rel="stylesheet" href="css/HotelHost_Feedback.css">
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.3/css/all.min.css">
        <link href='https://unpkg.com/boxicons@2.1.4/css/boxicons.min.css' rel='stylesheet'>
        <link rel="stylesheet" href="css/listPage.css">
    </head>
    <body>

        <%@include file="Header_HotelHost.jsp" %>
        <div class="col-md-1" style="margin-left: 50px">
            <a href="homeHotelHostServlet" class="btn btn-outline-primary mt-2">
                <img src="img_Hotel/back.svg" alt="Back Icon" style="width: 1rem; height: 1rem;" class="me-2">Back
            </a>
        </div>
        <div class="container mt-5">
            <div class="card"> 
                <div class="card-body">
                    <h5 class="card-title mb-3" style="font-weight: bold">More Reviews from Other Guests in "${requestScope.hotel.name}"</h5>
                    <h6 class="card-subtitle mb-2 text-muted" style="font-weight: bold">Overall Rating & Reviews</h6>
                    <%
                        // Khởi tạo biến tổng rating và số lượng phần tử
                        double totalRating = 0;
                        int count = 0;
                        int count1 = 0;
                        int count2 = 0;
                        int count3 = 0;
                        int count4 = 0;
                        int count5 = 0;
                        // Duyệt qua danh sách feedback
                        List<Feedback> listFeedback = (List<Feedback>) request.getAttribute("listFeedback");
                        if (listFeedback != null) {
                            for (Feedback feedback : listFeedback) {
                                // Lấy giá trị rating của từng phần tử feedback
                                int rating = ((Feedback) feedback).getRating();
                                // Cộng vào tổng rating và tăng số lượng phần tử
                                totalRating += rating;
                                count++;
                                
                                int rating1 = feedback.getRating();
        
                                // Tăng giá trị của biến đếm tương ứng
                                switch (rating1) {
                                    case 1:
                                        count1++;
                                        break;
                                    case 2:
                                        count2++;
                                        break;
                                    case 3:
                                        count3++;
                                        break;
                                    case 4:
                                        count4++;
                                        break;
                                    case 5:
                                        count5++;
                                        break;
                                    default:
                                        // Không làm gì nếu rating không nằm trong phạm vi 1 đến 5
                                        break;
                                }
                            }
                            
                        }

                        // Tính averageRating (tránh chia cho 0 bằng cách kiểm tra count)
                        double averageRating = (count > 0) ? (totalRating / count) : 0;
                        DecimalFormat df = new DecimalFormat("#.##");
                        String formattedAverageRating = df.format(averageRating);
                        String percentage1 = String.valueOf((int) Math.ceil((count1 * 100.0) / count)) + "%";
                            String percentage2 = String.valueOf((int) Math.ceil((count2 * 100.0) / count)) + "%";
                            String percentage3 = String.valueOf((int) Math.ceil((count3 * 100.0) / count)) + "%";
                            String percentage4 = String.valueOf((int) Math.ceil((count4 * 100.0) / count)) + "%";
                            String percentage5 = String.valueOf((int) Math.ceil((count5 * 100.0) / count)) + "%";
                    %>

                    <h6 style="font-size: 12px;margin-top: -5px;margin-left: 5px;margin-bottom: 10px">From ${fn:length(requestScope.listFeedback)} verified guests reviews</h6>
                    <div class="row mb-4">
                        <div class="col-md-3">
                        </div>
                        <div class="col-md-1 rating-circle me-4">
                            <span><%= formattedAverageRating.toString()  %></span>
                        </div>
                        <div class= "col-md-6">
                            <div class="rating-details">
                                <div class="rating-bar mb-1">
                                    <div class="col-md-2">Fantastic</div>
                                    <div class="col-md-4 progress flex-grow-1 ms-2">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: <%= percentage5 %>"></div>
                                    </div>
                                    <div class="col-md-3 ms-2"><%= count5 %> </div>
                                </div>
                            </div>
                            <div class="rating-details">
                                <div class="rating-bar mb-1">
                                    <div class="col-md-2">Very Good</div>
                                    <div class="col-md-4 progress flex-grow-1 ms-2">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: <%= percentage4 %>;"></div>
                                    </div>
                                    <div class="col-md-3 ms-2"><%= count4 %></div>
                                </div>
                                <div class="rating-bar mb-1">
                                    <div class="col-md-2">Satisfying</div>
                                    <div class="col-md-4 progress flex-grow-1 ms-2">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: <%= percentage3 %>;"></div>
                                    </div>
                                    <div class="col-md-3 ms-2"><%= count3 %></div>
                                </div>
                                <div class="rating-bar mb-1">
                                    <div class="col-md-2">Average</div>
                                    <div class="col-md-4 progress flex-grow-1 ms-2">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: <%= percentage2 %>;"></div>
                                    </div>
                                    <div class="col-md-3 ms-2"><%= count2 %></div>
                                </div>
                                <div class="rating-bar mb-1">
                                    <div class="col-md-2">Poor</div>
                                    <div class="col-md-4 progress flex-grow-1 ms-2">
                                        <div class="progress-bar bg-warning" role="progressbar" style="width: <%= percentage1 %>;"></div>
                                    </div>
                                    <div class="col-md-3 ms-2"><%= count1 %></div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-10">
                            <form action="HotelHost_FeedbackServlet" method="post" class="d-flex">
                                 <span class="mt-1 me-2">Name:</span>
                                <select class="form-select" id="statusSelect" name="hotelID">
                                    <c:forEach items="${requestScope.hotels}" var="c">
                                        <option value="${c.hotel_ID}" ${c.hotel_ID == requestScope.hotelID ? 'selected="selected"' : ''}>${c.name}</option>
                                    </c:forEach>
                                </select>
                                 <span class="mt-1 me-2" style="margin-left: 20px">Filter:</span>
                                <select class="form-select me-2" style="width: 50%;height: 40px"  name="orderBy">
                                    <option value="1" ${requestScope.orderBy == "1" ? 'selected="selected"' : ''}>Most Recent</option>
                                    <option value="2" ${requestScope.orderBy == "2" ? 'selected="selected"' : ''}>Score (Low to High)</option>
                                    <option value="3" ${requestScope.orderBy == "3" ? 'selected="selected"' : ''}>Score (High to Low)</option>
                                </select>
                                <button type="submit" class="btn btn-outline-secondary">Filter</button>
                                <input type="hidden" name="star" value="${requestScope.star}"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                            </form>
                        </div>
                    </div>
                    <div class="row mb-3">
                        <div class="col-md-2">
                            <form action="HotelHost_FeedbackServlet" method="post">
                                <c:if test="${requestScope.star == '0'}">
                                    <button type="submit" class="form-control" style="border: 1px solid orangered;color: orangered">All feedbacks</button>
                                </c:if>
                                <c:if test="${requestScope.star != '0'}">
                                    <button type="submit" class="form-control">All feedbacks</button>
                                </c:if>
                                <input type="hidden" name="star" value="0"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                                <input type="hidden" name="orderBy" value="1"/>
                            </form>
                        </div>
                        <div class="col-md-2">
                            <form action="HotelHost_FeedbackServlet" method="post">
                                <c:if test="${requestScope.star == '5'}">
                                    <button type="submit" class="form-control" style="border: 1px solid orangered;color: orangered">5 start( <%= count5 %> )</button>
                                </c:if>
                                <c:if test="${requestScope.star != '5'}">
                                    <button type="submit" class="form-control">5 start( <%= count5 %> )</button>
                                </c:if>
                                <input type="hidden" name="star" value="5"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                                <input type="hidden" name="orderBy" value="1"/>
                            </form>
                        </div>
                        <div class="col-md-2">
                            <form action="HotelHost_FeedbackServlet" method="post">
                                <c:if test="${requestScope.star == '4'}">
                                    <button type="submit" class="form-control" style="border: 1px solid orangered;color: orangered">4 start( <%= count4 %> )</button>
                                </c:if>
                                <c:if test="${requestScope.star != '4'}">
                                    <button type="submit" class="form-control">4 start( <%= count4 %> )</button>
                                </c:if>
                                <input type="hidden" name="star" value="4"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                                <input type="hidden" name="orderBy" value="1"/>
                            </form>
                        </div>
                        <div class="col-md-2">
                            <form action="HotelHost_FeedbackServlet" method="post">
                                <c:if test="${requestScope.star == '3'}">  
                                    <button type="submit" class="form-control" style="border: 1px solid orangered;color: orangered">3 start( <%= count3 %> )</button>
                                </c:if>
                                <c:if test="${requestScope.star != '3'}">
                                    <button type="submit" class="form-control">3 start( <%= count3 %> )</button>
                                </c:if>
                                <input type="hidden" name="star" value="3"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                                <input type="hidden" name="orderBy" value="1"/>
                            </form>
                        </div>
                        <div class="col-md-2">
                            <form action="HotelHost_FeedbackServlet" method="post">
                                <c:if test="${requestScope.star == '2'}">
                                    <button type="submit" class="form-control" style="border: 1px solid orangered;color: orangered">2 start( <%= count2 %> )</button>
                                </c:if>
                                <c:if test="${requestScope.star != '2'}">
                                    <button type="submit" class="form-control">2 start( <%= count2 %> )</button>
                                </c:if>
                                <input type="hidden" name="star" value="2"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                                <input type="hidden" name="orderBy" value="1"/>
                            </form>
                        </div>
                        <div class="col-md-2">
                            <form action="HotelHost_FeedbackServlet" method="post">
                                <c:if test="${requestScope.star == '1'}">
                                    <button type="submit" class="form-control" style="border: 1px solid orange;color: orange">1 start( <%= count1 %> )</button>     
                                </c:if>
                                <c:if test="${requestScope.star != '1'}">
                                    <button type="submit" class="form-control">1 start( <%= count1 %> )</button>
                                </c:if>
                                <input type="hidden" name="star" value="1"/>
                                <input type="hidden" name="hotelID" value="${requestScope.hotelID}"/>
                                <input type="hidden" name="orderBy" value="1"/>
                            </form>
                        </div>    
                    </div>
                    <c:if test="${requestScope.star == 0}">
                        <c:forEach items="${requestScope.listFeedback}" var="c">
                            <div class="review mb-3" id="fb" style="border: 1px solid rgb(227,227,227);border-radius: 5px">
                                <div class="d-flex">
                                    <div class="avatar"><img src="${c.getAccount(c.feedbackID).avatarURL}"></div>
                                    <div class="ms-3">
                                        <div class="row">
                                            <h6 class="mb-0">${c.getAccount(c.feedbackID).name}</h6>
                                            <span class="text-muted" style="float: right">${c.date}</span>
                                        </div>
                                        <c:if test="${c.rating == 1}">
                                            <div class="rating mt-1">                                            
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bx-star'></i>
                                                <i class='bx bx-star'></i>
                                                <i class='bx bx-star'></i>
                                                <i class='bx bx-star'></i>
                                            </div>
                                        </c:if>
                                        <c:if test="${c.rating == 2}">
                                            <div class="rating mt-1">                                            
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bx-star'></i>
                                                <i class='bx bx-star'></i>
                                                <i class='bx bx-star'></i>
                                            </div>
                                        </c:if>

                                        <c:if test="${c.rating == 3}">
                                            <div class="rating mt-1">                                            
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bx-star'></i>
                                                <i class='bx bx-star'></i>
                                            </div>
                                        </c:if>
                                        <c:if test="${c.rating == 4}">
                                            <div class="rating mt-1">                                            
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bx-star'></i>
                                            </div>
                                        </c:if>
                                        <c:if test="${c.rating == 5}">
                                            <div class="rating mt-1">                                            
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                                <i class='bx bxs-star'></i>
                                            </div>
                                        </c:if>

                                        <p>${c.description}</p>
                                        <div><span class="badge bg-light text-dark">${c.likesCount} liked this view</span> <span class="badge bg-light text-dark">${c.dislikesCount} disliked this view</span></div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:if>
                    <c:if test="${requestScope.star != 0}">
                        <c:forEach items="${requestScope.listFeedback}" var="c">
                            <c:if test="${requestScope.star == c.rating}">
                                <div class="review mb-3" id="fb" style="border: 1px solid rgb(227,227,227);border-radius: 5px">
                                    <div class="d-flex">
                                        <div class="avatar"><img src="${c.getAccount(c.feedbackID).avatarURL}"></div>
                                        <div class="ms-3">
                                            <div class="row">
                                                <h6 class="mb-0">${c.getAccount(c.feedbackID).name}</h6>
                                                <span class="text-muted" style="float: right">${c.date}</span>
                                            </div>
                                            <c:if test="${c.rating == 1}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>
                                            <c:if test="${c.rating == 2}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>

                                            <c:if test="${c.rating == 3}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>
                                            <c:if test="${c.rating == 4}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bx-star'></i>
                                                </div>
                                            </c:if>
                                            <c:if test="${c.rating == 5}">
                                                <div class="rating mt-1">                                            
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                    <i class='bx bxs-star'></i>
                                                </div>
                                            </c:if>

                                            <p>${c.description}</p>
                                            <div><span class="badge bg-light text-dark">${c.likesCount} liked this view</span> <span class="badge bg-light text-dark">${c.dislikesCount} disliked this view</span></div>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </c:forEach>
                    </c:if>
                    <c:if test="${not empty requestScope.listFeedback}">
                        <c:if test="${fn:length(requestScope.listFeedback) > 2}">
                            <ul class="listPage"></ul>
                        </c:if>
                    </c:if>
                </div>
            </div>

            <%@include file="Footer.jsp" %>


            <!-- Bootstrap JS and dependencies -->
            <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.5.3/dist/umd/popper.min.js"></script>
            <script src="bootstrap_css/bootstrap.min.js"></script>
            <script>
                //phân trang
                let thisPage = 1;
                let limit = 3;
                let list = document.querySelectorAll(".review");
                let maxPageVisible = 5; // Số trang tối đa hiển thị cùng một lúc

                function loadItem() {
                    let beginGet = limit * (thisPage - 1);
                    let endGet = limit * thisPage - 1;
                    list.forEach((item, key) => {
                        if (key >= beginGet && key <= endGet) {
                            item.style.display = "block";
                        } else {
                            item.style.display = "none";
                        }
                    });
                    listPage();
                }
                loadItem();

                function listPage() {
                    let count = Math.ceil(list.length / limit);
                    let listPageElem = document.querySelector('.listPage');
                    listPageElem.innerHTML = '';

                    let maxPage = Math.min(count, maxPageVisible);
                    let startPage = Math.max(thisPage - Math.floor(maxPageVisible / 2), 1);
                    let endPage = startPage + maxPage - 1;

                    if (endPage > count) {
                        endPage = count;
                        startPage = Math.max(endPage - maxPage + 1, 1);
                    }

                    if (thisPage != 1) {
                        let prev = document.createElement('li');
                        prev.innerText = 'PREV';
                        prev.setAttribute('onclick', "changePage(" + (thisPage - 1) + ")");
                        listPageElem.appendChild(prev);
                    }

                    for (let i = startPage; i <= endPage; i++) {
                        let newPage = document.createElement('li');
                        newPage.innerText = i;
                        if (i == thisPage) {
                            newPage.classList.add('active');
                        }
                        newPage.setAttribute('onclick', "changePage(" + i + ")");
                        listPageElem.appendChild(newPage);
                    }

                    if (thisPage != count) {
                        let next = document.createElement('li');
                        next.innerText = 'NEXT';
                        next.setAttribute('onclick', "changePage(" + (thisPage + 1) + ")");
                        listPageElem.appendChild(next);
                    }
                }

                function changePage(i) {
                    thisPage = i;
                    loadItem();
                    let scrollPosition = window.scrollY;
                    window.scrollTo(0, scrollPosition);
                }
            </script>
    </body>
</html>
