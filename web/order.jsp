<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/ContentLeft.jsp" %>
<div id="content-right">
    <div class="path-admin">ORDERS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="order-title">
                <b>Filter by Order date:</b>
                <form action="<%=path%>/admin/filterdate" method="post">
                    From: <input type="date" name="txtStartOrderDate"/ value="${sessionScope.txtStartOrderDate}">
                    To: <input type="date" name="txtEndOrderDate"/ value="${sessionScope.txtEndOrderDate}">
                    <input type="submit" value="Filter">
                </form>
            </div>
            <div id="order-table">
                <table id="orders">
                    <tr>
                        <th>OrderID</th>
                        <th>OrderDate</th>
                        <th>RequiredDate</th>
                        <th>ShippedDate</th>
                        <th>Employee</th>
                        <th>Customer</th>
                        <th>Freight($)</th>
                        <th>Status</th>
                    </tr>
                    <c:forEach items="${listO}" var="o">
                        <tr>
                            <td><a href="order-detail.html?id=5">${o.getOrder().getOrderID()}</a></td>
                            <td>${o.getOrder().getOrderDate()}</td>
                            <td>${o.getOrder().getRequiredDate()}</td>
                            <td>${o.getOrder().getShippedDate()}</td>
                            <td>${o.getEmployee().getFirstName()}</td>
                            <td>${o.getCustomer().getContactName()}</td>
                            <td>${o.getOrder().getFreight()}</td>

                            <c:if test="${o.getOrder().getShippedDate()!=null}">
                                <td style="color: green;">Completed</td>
                            </c:if>
                            <c:if test="${o.getOrder().getShippedDate()==null}">
                                <c:choose>
                                    <c:when test="${o.getOrder().getRequiredDate()!=null}">
                                        <td> 
                                            <p style="color: blue">Pending | <a style="color: red" href="<%=path%>/admin/cancel?oid=${o.getOrder().getOrderID()}">Cancel</a></p>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td> 
                                            <p style="color: red"> Order canceled </p>
                                        </td>
                                    </c:otherwise>
                                </c:choose>

                            </c:if>
                        </tr>      
                    </c:forEach>
                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <c:if test="${tag>1 && sessionScope.pagingStatus == 3}">
                        <a href="<%=path%>/admin/order?index=${tag-1}">Previous</a>
                    </c:if>
                    <c:if test="${tag>1 && sessionScope.pagingStatus == 4}">
                        <a href="<%=path%>/admin/filterdate?index=${tag-1}&fromdate=${sessionScope.txtStartOrderDate}&todate=${sessionScope.txtEndOrderDate}">Previous</a>
                    </c:if>
                    <c:forEach begin="1" end="${endP}" var="i">
                        <c:if test="${sessionScope.pagingStatus == 3}">
                            <c:url var="paging" value="/admin/order">
                                <c:param name="index" value="${i}"/>

                            </c:url>
                        </c:if>
                        <c:if test="${sessionScope.pagingStatus == 4}">
                            <c:url var="paging" value="/admin/filterdate">
                                <c:param name="index" value="${i}"/>
                                <c:param name="fromdate" value="${sessionScope.txtStartOrderDate}"/>
                                <c:param name="todate" value="${sessionScope.txtEndOrderDate}"/>
                            </c:url>
                        </c:if>                  
                        <c:choose>
                            <c:when test="${tag==i}">
                                <a style="background-color: burlywood" href="${paging}">${i}</a>
                            </c:when>
                            <c:otherwise>
                                <a href="${paging}">${i}</a>
                            </c:otherwise>
                        </c:choose>
                    </c:forEach>

                    <c:if test="${tag<endP && sessionScope.pagingStatus == 3}">
                        <a href="<%=path%>/admin/order?index=${tag+1}">Next</a>
                    </c:if>
                    <c:if test="${tag<endP && sessionScope.pagingStatus == 4}">
                        <a href="<%=path%>/admin/filterdate?index=${tag+1}&fromdate=${sessionScope.txtStartOrderDate}&todate=${sessionScope.txtEndOrderDate}">Next</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>