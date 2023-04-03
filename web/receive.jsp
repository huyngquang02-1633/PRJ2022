<%-- 
    Document   : receive
    Created on : 21-09-2022, 13:51:46
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--xu ly request tu send_request.html gui bang phuong thuc 'get'/-->
        <%
            //Tuong tu: doGet
//            String name = request.getParameter("name");
//            int age = Integer.parseInt(request.getParameter("age"))+10;
            
            //tuong tu doPost()
            String name = request.getParameter("txtName");
            int age = Integer.parseInt(request.getParameter("txtAge"))+10;
        %>
        
        <h3>Person infomations</h3>
        Name: <%= name %> <br>
        Age: <%= age %> <br>

    </body>
</html>
