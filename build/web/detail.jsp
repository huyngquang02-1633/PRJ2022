<%@include file="template/header.jsp" %>
<c:set var="p" value="${detail.getProducts()}"/>
<c:set var="c" value="${detail.getCategory()}"/>
<c:url var="img" value="/img/1.jpg"/>
<div id="content-detail">
                <div id="content-title">
                    <a href="<%=path%>/home">Home</a> >
                    <a href="<%=path%>/category?cid=${c.getCategoryID()}">${c.getCategoryName()}</a> >
                </div>
                <div id="product">
                    <div id="product-name">
                        <h2>${p.getProductName()}</h2>
                        <div id="product-detail">
                            <div id="product-detail-left">
                                <div id="product-img">
                                    <img src="${img}"/>
                                </div>
                                <div id="product-img-items">
                                    <div><a href="#"><img src="${img}"></a></div>
                                    <div><a href="#"><img src="${img}"></a></div>
                                    <div><a href="#"><img src="${img}"></a></div>
                                    <div><a href="#"><img src="${img}"></a></div>
                                </div>
                            </div>
                            <div id="product-detail-right">
                                <div id="product-detail-right-content">
                                    <div id="product-price">${p.getUnitPrice()} $</div>
                                    <c:if test="${p.getUnitsInStock()>0}">
                                    <div id="product-status">In stock: ${p.getUnitsInStock()}</div>
                                    </c:if>
                                    <c:if test="${p.getUnitsInStock()<=0}">
                                    <div id="productt-status">Out stock: ${p.getUnitsInStock()}</div>
                                    </c:if>
                                    <div id="product-detail-buttons">
                                        <div id="product-detail-button">
                                            <c:if test="${p.getUnitsInStock()>0}">
                                            <a href="<%=path%>/shopping/buy?num=1&tid=${p.getProductID()}"><input type="button" value="BY NOW"></a>
                                            <a href="<%=path%>/shopping/buy?tid=${p.getProductID()}"><input type="button" value="ADD TO CART" style="background-color: #fff; color:red;border: 1px solid gray;"></a>
                                            </c:if>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div id="info-detail">
                    <div id="info-detail-title">
                        <h2>Information deltail</h2>
                        <div style="margin:10px auto;">
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Illum, debitis. Asperiores soluta eveniet eos accusantium doloremque cum suscipit ducimus enim at sapiente mollitia consequuntur dicta quaerat, sunt voluptates autem. Quam!
                            Lorem ipsum dolor, sit amet consectetur adipisicing elit. Rem illum autem veritatis maxime corporis quod quibusdam nostrum eaque laborum numquam quos unde eveniet aut, exercitationem voluptatum veniam fugiat, debitis esse?
                            Lorem ipsum dolor sit amet consectetur adipisicing elit. Distinctio eligendi ratione vitae nobis numquam dolorum assumenda saepe enim cumque blanditiis, deleniti neque voluptate vel ducimus in omnis harum aut nisi.
                        </div>
                    </div>
                </div>
            </div>
<%@include file="template/footer.jsp" %>