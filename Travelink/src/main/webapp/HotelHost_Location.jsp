<%-- 
    Document   : HotelHost_Location
    Created on : Jul 16, 2024, 1:41:20 AM
    Author     : MSI
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form action="HotelHost_AddWardServlet" method="post">
            <select name="provinceID">
                <c:if test="${province != null}">
                    <option value="${province.province_ID}">${province.name}</option>
                </c:if>
                <c:forEach var="province" items="${provinceList}">                   
                    <option value="${province.province_ID}">${province.name}</option>
                </c:forEach>
            </select>
            <c:if test="${districtList != null}">
                <select name="districtID">
                    <c:if test="${district != null}">
                        <option value="${district.district_ID}">${district.name}</option>
                    </c:if>
                <c:forEach var="district" items="${districtList}">
                    <option value="${district.district_ID}">${district.name}</option>
                </c:forEach>
            </select>
            </c:if>
             <c:if test="${wardList != null}">
                <select name="wardID">
                <c:forEach var="ward" items="${wardList}">
                    <option value="${ward.ward_ID}">${ward.name}</option>
                </c:forEach>
            </select>
            </c:if>
            <button>submit<button>
        </form>
        
        
    </body>
</html>
