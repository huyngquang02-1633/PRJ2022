<%@include file="template/header.jsp" %>
<c:set var="a" value="${account}" />
<c:set var="c" value="${customer}" />
<div id="content-left">
    <h3 style="font-weight: normal;">Welcome ${c.getContactName()}</h3>
    <h3>Account Management</h3>
    <ul>
        <a href="<%=path%>/account/profile"><li>Personal information</li></a>
    </ul>
    <h3>My order</h3>
    <ul>
        <a href="<%=path%>/account/profile1"><li>All orders</li></a>
        <a href="#"><li>Canceled order</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path">LIST ORDERS</b></div>
    <c:forEach items="${order}" var="o">
        <c:set var="i" value="${i+1}"/>
        <div class="content-main">

            <div id="profile-content-order">
                <div>
                    <div class="profile-order-title">
                        <div class="profile-order-title-left">
                            <div>Order creation date: ${o.getOrderDate()}</div>
                            <div>Order: <a href="#">#${i}</a></div>
                        </div>
                        <div class="profile-order-title-right">
                            <c:if test="${o.getShippedDate()!=null}">
                                <td style="color: green;">Completed</td>
                            </c:if>
                            <c:if test="${o.getShippedDate()==null}">
                                <c:choose>
                                    <c:when test="${o.getRequiredDate()!=null}">
                                        <td> 
                                            <p onclick="return Check()" style="color: blue">Pending | <a style="color: red" href="<%=path%>/admin/cancel?oid=${o.getOrderID()}">Cancel</a></p>
                                        </td>
                                    </c:when>
                                    <c:otherwise>
                                        <td> 
                                            <p style="color: red"> Order canceled </p>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:if>
                        </div>
                    </div>
                            <c:forEach items="${OrderDAO.getOrderDetailByOrderId(o.getOrderID())}" var="od">
                        <div class="profile-order-content">
                            <div class="profile-order-content-col1">
                                <a href="detail.html"><img src="<%=path%>/img/2.jpg" width="100%"/></a>
                            </div>
                            <div class="profile-order-content-col2">${OrderDAO.getProductNameByProductId(od.getProductID())}</div>
                            <div class="profile-order-content-col3">Quantity: ${od.getQuantity()}</div>
                            <div class="profile-order-content-col4">${od.getUnitPrice()} $</div>
                        </div>
                    </c:forEach>
                </div>                       
            </div>

        </div>
    </c:forEach>
</div>
<%@include file="template/footer.jsp" %>

<script>
    function Check(){
        return confirm("Are you sure to cancel this order"); 
    }
</script>