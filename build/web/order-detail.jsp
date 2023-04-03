<%@include file="template/header.jsp" %>
<div id="container">
        <div id="header">
            <div id="logo-admin">
                Ecommerce Admin
            </div>
            <div id="banner-admin">
                <ul>
                    <li><a href="#">SignOut</a></li>
                </ul>
            </div>
        </div>
        <div id="content">
            <div id="content-left">
                <ul>
                    <a href="dashboard.html"><li>Dashboard</li></a>
                    <a href="order.html"><li>Orders</li></a>
                    <a href="product.html"><li>Products</li></a>
                    <a href="#"><li>Customers</li></a>
                </ul>
            </div>
            <div id="content-right">
                <div class="path-admin">ORDER DETAIL</b></div>
                <div class="content-main">
                    <div id="content-main-dashboard">
                        <div>
                            <div class="profile-order-title">
                                <div class="profile-order-title-left">
                                    <div>OrderID: #5</div>
                                    <div>Order creation date: 12-10-2022</div>
                                </div>
                                <div class="profile-order-title-right">
                                    <span style="color: green;">Completed</span>
                                </div>
                            </div>
                            <div class="profile-order-content" style="background-color: white;">
                                 <div class="profile-order-content-col1">
                                    <a href="detail.html"><img src="img/2.jpg" width="100%"/></a>
                                 </div>
                                 <div class="profile-order-content-col2">Product 12</div>
                                 <div class="profile-order-content-col3">Quantity: 1</div>
                                 <div class="profile-order-content-col4">1000 $</div>
                            </div>
                            <div class="profile-order-content" style="background-color: white;">
                                <div class="profile-order-content-col1">
                                   <a href="detail.html"><img src="img/1.jpg" width="100%"/></a>
                                </div>
                                <div class="profile-order-content-col2">Product 1</div>
                                <div class="profile-order-content-col3">Quantity: 2</div>
                                <div class="profile-order-content-col4">2000 $</div>
                           </div>
                            
                        </div>
                    </div>
                </div>
            </div>
<%@include file="template/footer.jsp" %>