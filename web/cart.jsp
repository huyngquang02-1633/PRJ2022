<%@include file="template/header.jsp" %>
<c:set var="a" value="${account}" />
<c:set var="c" value="${customer}" />
<div id="cart">
    <div id="cart-title">
        <h3>SHOPPING CART</h3>
    </div>
    <div id="cart-content">
        <c:if test="${ordermsg!=null}">
            <p style="color: red">${msg2}</p>
        </c:if>
        <c:forEach items="${sessionScope.cart.getItems()}" var="o">
            <div class="cart-item">
                <div class="cart-item-infor">
                    <div class="cart-item-img">
                        <img src="<%=path%>/img/1.jpg"/>
                    </div>
                    <div class="cart-item-name">
                        <a href="<%=path%>/product/detail?txtId=${o.getProduct().getProductID()}">${o.getProduct().getProductName()}</a>
                    </div>
                    <div class="cart-item-price">
                        ${o.getPrice()}
                    </div>
                    <div class="cart-item-button">
                        <form action="<%=path%>/shopping/buy?tid=${o.getProduct().getProductID()}" method="post">
                            <input style="background-color: tomato" type="submit" value="Remove" />
                        </form>
                    </div>
                </div>
                <div class="cart-item-function">
                    <a href="<%=path%>/shopping/buy?num=-3&tid=${o.getProduct().getProductID()}">-</a>  
                    <a href="<%=path%>/shopping/buy?num=3&tid=${o.getProduct().getProductID()}">+</a>
                    <input type="text" value="${o.getQuantity()}" disabled/>
                </div>
            </div>
        </c:forEach>
    </div>
    <div id="cart-summary">
        <div id="cart-summary-content">Total amount: <span style="color:red">${sessionScope.total}</span></div>
    </div>

    <form method="post" action="<%=path%>/shopping/order">

        <div id="customer-info">
            <c:if test="${msgInfo!=null}">
                <p style="color: red">${msgInfo}</p>
            </c:if>
            <div id="customer-info-content">
                <h3>CUSTOMER INFORMATION:</h3>
                <c:if test="${msg!=null}">
                    <p style="color: red">${msg}</p>
                </c:if>
                <div id="customer-info-detail">

                    <div id="customer-info-left">

                        <div style="display: none">CustomerID: <br/><input type="text" name="txtCustomerID" value="${c.getCustomerID()}"></div>
                        <input type="text" placeholder="Company name *" name="txtCompanyName" value="${c.getCompanyName()}"/><br/>
                        <input type="text" placeholder="Contact name *" name="txtContactName" value="${c.getContactName()}"/><br/>
                    </div>
                    <div id="customer-info-right">
                        <input type="text" placeholder="Contact title *" name="txtCompanyTitle" value="${c.getContactTitle()}"/><br/>
                        <input type="text" placeholder="Address *" name="txtAddress" value="${c.getAddress()}"/><br/>
                        <input type="date" name="requidate">
                        ${wrong}
                    </div>
                </div>
            </div>
        </div>
        <div id="customer-info">
            <div id="customer-info-content">
                <h3>PAYMENT METHODS:</h3>
                <div id="customer-info-payment">
                    <div>
                        <input type="radio" name="rbPaymentMethod" checked/>
                        Payment C.O.D - Payment on delivery
                    </div>
                    <div>
                        <input type="radio" name="rbPaymentMethod" disabled/>
                        Payment via online payment gateway
                    </div>
                </div>
            </div>
        </div>
        <div id="cart-order">
            <input type="submit" value="ORDER" />
        </div>
    </form>
</div>
<%@include file="template/footer.jsp" %>