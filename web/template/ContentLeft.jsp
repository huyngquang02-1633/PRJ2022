<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Index</title>
        <%
            String path = request.getContextPath();
        %>
        <link href="<%=path%>/css/style.css" rel="stylesheet"/>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.5.0/Chart.min.js"></script>
    </head>
    <div id="container">
        <div id="header">
            <div id="logo-admin">
                Ecommerce Admin
            </div>
            <div id="banner-admin">
                <ul>
                    <li><a href="<%=path%>/account/signin">SignOut</a></li>
                </ul>
            </div>
        </div>
        <div id="content">
            <div id="content-left">
                <ul>
                    <a href="<%=path%>/admin/dashboard"><li>Dashboard</li></a>
                    <a href="<%=path%>/admin/order"><li>Orders</li></a>
                    <a href="<%=path%>/admin/product"><li>Products</li></a>
                    <a href="<%=path%>/admin/customer"><li>Customers</li></a>
                </ul>
            </div>