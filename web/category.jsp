<%@include file="template/header.jsp" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<div id="content-left">
    <h3>CATEGORY</h3>
    <ul>

        <c:forEach items="${ListC}" var="o">

            <c:url value="/category" var="category">
                <c:param name="cid" value="${o.getCategoryID()}"/>  
            </c:url>

            <a href="${category}"><li>${o.getCategoryName()}</li></a>
                </c:forEach>

    </ul>
</div>
<div id="content-right">
    <div class="path">${CategoryName}</b></div>
    <div class="content-mainn">
        <c:forEach var="o" items="${ListP}">
            <div class="productt">
                <a href="<%=path%>/product/detail?txtId=${o.getProductID()}"><img src="<%=path%>/img/1.jpg" width="100%"/></a>
                <div class="name"><a href="<%=path%>/product/detail?txtId=${o.getProductID()}">${o.getProductName()}</a></div>
                <div class="price">${o.getUnitPrice()}</div>
                <c:if test="${o.getUnitsInStock()>0}">
                    <div><a href="<%=path%>/shopping/buy?&tid=${o.getProductID()}">Buy now</a></div>
                </c:if>
            </div>
        </c:forEach>
    </div>

    <div id="paging">
        <div class="pagination">
            <c:if test="${tag>1}">
                <a href="<%=path%>/category?cid=${cate}&index=${tag-1}">Previous</a>
            </c:if>
            <c:forEach begin="1" end="${endP}" var="i">
                <c:if test="${tag==i}">
                    <a style="background-color: burlywood" href="<%=path%>/category?cid=${cate}&index=${i}">${i}</a>
                </c:if>
                <c:if test="${tag!=i}">
                    <a href="<%=path%>/category?cid=${cate}&index=${i}">${i}</a>
                </c:if>
            </c:forEach>
            <c:if test="${tag<endP}">
                <a href="<%=path%>/category?cid=${cate}&index=${tag+1}">Next</a>
            </c:if>
        </div>
    </div>
</div>
<%@include file="template/footer.jsp" %>