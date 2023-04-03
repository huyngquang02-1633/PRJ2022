<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/ContentLeft.jsp" %>

<div id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">

                    <form action="<%=path%>/admin/customersearch" method="post">

                </div>
                <div id="product-title-2" style="width: 55%;">
                    <input type="text" name="txtSearch"  placeholder="Enter CustomerID to search" value="${productSearch}">
                    <input type="submit" value="Search"> <br>
                    </form>
                </div>
            </div>
            <div id="order-table-admin">
                <table id="orders">
                    <tr>
                        <th>CustomerID</th>
                        <th>CompanyName</th>
                        <th>ContactName</th>
                        <th>ContactTitle</th>
                        <th>Address</th>
                        <th></th>
                    </tr>
                    <c:forEach items="${listC}" var="o">
                        <tr>
                            <td><a href="">${o.getCustomerID()}</a></td>
                            <td>${o.getCompanyName()}</td>
                            <td>${o.getContactName()}</td>
                            <td>${o.getContactTitle()}</td>
                            <td>${o.getAddress()}</td>
                            <td>
                                <a onclick="return Check()" href="<%=path%>/admin/deletecustomer?cid=${o.getCustomerID()}">Delete</a>
                            </td>
                        </tr>    
                    </c:forEach>
                </table>
            </div>
            <div id="paging">
                <div class="pagination">
                    <c:if test="${tag>1 && sessionScope.customerStatus == 1}">
                        <a href="<%=path%>/admin/customer?index=${tag-1}">Previous</a>
                    </c:if>
                    <c:if test="${tag>1 && sessionScope.customerStatus == 2}">
                        <a href="<%=path%>/admin/customersearch?index=${tag-1}&cid=${productSearch}">Previous</a>
                    </c:if>
                    <c:forEach begin="1" end="${endP}" var="i">
                        <c:if test="${sessionScope.customerStatus == 1}">
                            <c:url var="paging" value="/admin/customer">
                                <c:param name="index" value="${i}"/>
                            </c:url>   
                        </c:if>
                        <c:if test="${sessionScope.customerStatus == 2}">
                            <c:url var="paging" value="/admin/customersearch">
                                <c:param name="index" value="${i}"/>
                                <c:param name="cid" value="${productSearch}"/>
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
                    <c:if test="${tag<endP && sessionScope.customerStatus == 1}">
                        <a href="<%=path%>/admin/customer?index=${tag+1}">Next</a>
                    </c:if>
                    <c:if test="${tag<endP && sessionScope.customerStatus == 2}">
                        <a href="<%=path%>/admin/customersearch?index=${tag+1}&cid=${productSearch}">Next</a>
                    </c:if>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
<script>
    function Check(){
        return confirm("Are you sure to cancel this order"); 
    }
</script>

