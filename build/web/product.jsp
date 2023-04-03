<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="template/ContentLeft.jsp" %>

<div id="content-right">
    <div class="path-admin">PRODUCTS LIST</b></div>
    <div class="content-main">
        <div id="content-main-dashboard">
            <div id="product-title-header">
                <div id="product-title-1" style="width: 25%;">
                    <b>Filter by Catetory:</b>
                    <form action="<%=path%>/admin/filter" method="post">
                        <select name="ddlCategory">
                            <option value="0" selected="selected">---- Select ----</option>
                            <c:forEach items="${ListC}" var="category">
                                <c:choose>
                                    <c:when test="${category.getCategoryID() == categoryToFilter}">
                                        <option value="${category.getCategoryID()}" selected="selected"><c:out value="${category.getCategoryName()}"/></option>
                                    </c:when>
                                    <c:otherwise>
                                        <option value="${category.getCategoryID()}"><c:out value="${category.getCategoryName()}"/></option>
                                    </c:otherwise>
                                </c:choose>                               
                            </c:forEach>
                        </select>
                        <input type="submit" value="Filter">


                        </div>
                        <div id="product-title-2" style="width: 55%;">
                                <input type="text" name="txtSearch"  placeholder="Enter product name to search" value="${productSearch}">
                            <input type="submit" value="Search">
                            </form>
                        </div>
                        <div id="product-title-3" style="width: 20%;">
                            <a href="<%=path%>/admin/create">Create a new Product</a>
                            <form action="">
                                <label for="upload-file">Import .xls or .xlsx file</label>
                                <input type="file" name="file" id="upload-file" />
                            </form>
                        </div>
                </div>
                <div id="order-table-admin">
                    <table id="orders">
                        <tr>
                            <th>ProductID</th>
                            <th>ProductName</th>
                            <th>UnitPrice</th>
                            <th>Unit</th>
                            <th>UnitsInStock</th>
                            <th>Category</th>
                            <th>Discontinued</th>
                            <th></th>
                        </tr>
                        <c:forEach items="${listP}" var="o">
                            <c:url value="/admin/edit" var="Edit">
                                <c:param name="id" value="${o.getProducts().getProductID()}" />
                            </c:url>
                            <c:url value="/admin/delete" var="Delete">
                                <c:param name="id" value="${o.getProducts().getProductID()}" />
                            </c:url>
                            <tr>
                                <td><a href="<%=path%>/product/detail?txtId=${o.getProducts().getProductID()}">${o.getProducts().getProductID()}</a></td>
                                <td>${o.getProducts().getProductName()}</td>
                                <td>${o.getProducts().getUnitPrice()}</td>
                                <td>${o.getProducts().getUnitsOnOrder()}</td>
                                <td>${o.getProducts().getUnitsInStock()}</td>
                                <td>${o.getCategory().getCategoryName()}</td>
                                <td>${o.getProducts().isDiscontinued()}</td>
                                <td>
                                    <a href="${Edit}">Edit</a> &nbsp; | &nbsp; 
                                    <a onclick="return Check()" href="${Delete}">Delete</a>
                                </td>
                            </tr>    
                        </c:forEach>
                    </table>
                </div>
                <div id="paging">
                    <div class="pagination">
                        <c:if test="${tag>1 && sessionScope.pagingStatus == 1}">
                            <a href="<%=path%>/admin/product?index=${tag-1}">Previous</a>
                        </c:if>
                        <c:if test="${tag>1 && sessionScope.pagingStatus == 2}">
                            <a href="<%=path%>/admin/filter?index=${tag-1}&ddlCategory=${categoryToFilter}&status=${sessionScope.status}&txtSearch=${productSearch}">Previous</a>
                        </c:if>
                        <c:forEach begin="1" end="${endP}" var="i">
                            <c:if test="${sessionScope.pagingStatus == 1}">
                                <c:url var="paging" value="/admin/product">
                                    <c:param name="index" value="${i}"/>

                                </c:url>
                            </c:if>
                            <c:if test="${sessionScope.pagingStatus == 2}">
                                <c:url var="paging" value="/admin/filter">
                                    <c:param name="index" value="${i}"/>
                                    <c:param name="ddlCategory" value="${categoryToFilter}"/>
                                    <c:param name="status" value="${sessionScope.status}"/>
                                    <c:param name="txtSearch" value="${productSearch}"/>
                                </c:url>
                            </c:if>

                            <c:choose>
                                <c:when test="${tag == i}">
                                    <a style="background-color: burlywood" href="${paging}">${i}</a>
                                </c:when>
                                <c:otherwise>
                                    <a href="${paging}">${i}</a>
                                </c:otherwise>
                            </c:choose>

                        </c:forEach>
                        <c:if test="${tag<endP && sessionScope.pagingStatus == 1}">
                            <a href="<%=path%>/admin/product?index=${tag+1}">Next</a>
                        </c:if>
                        <c:if test="${tag<endP && sessionScope.pagingStatus == 2}">
                            <a href="<%=path%>/admin/filter?index=${tag+1}&ddlCategory=${categoryToFilter}&status=${sessionScope.status}&txtSearch=${productSearch}">Next</a>
                        </c:if>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>
</div>

<script>
    function Check(){
        return confirm("Are you sure to cancel this order"); 
    }
</script>

