<%-- 
    Document   : jsp_syntax
    Created on : 21-09-2022, 13:26:46
    Author     : ADMIN
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="java.util.ArrayList" %>
<%@page import="DAL.Person" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <!--cac cu phap khai bao cac bien, doi tuong, ham trong jsp-->
        <%!
            //khai bao bien
            String name = "Nguyen Quang Huy";
            //khai bao 1 doi tuong
            //Person p = new Person();
            //Dinh nghia 1 ham(phuong thuc)
            public String getName(){
                return name;
            }

            //khai bao mot mang chua ten 3 sinh vien
            String [] stuList = {"Huy", "Thao", "Roty"};
        %>
        
        <!--cu phap nhung bieu thuc vao trang jsp-->
        <h3>Welcome : <%= getName() %> </h3>
        <hr/>
        <ol>
            <!--su dung cu phap scriplet de sly logic java trong jsp-->
            <%
                for(int i=0;i<3;i++){
            %>
                <li><%= stuList[i] %></li>
            <%
                }
            %>
        </ol>
        <%
            ArrayList<Person> list = new ArrayList<>();
            list.add(new Person("SE001"));
            list.add(new Person("SE002"));
            list.add(new Person("SE003"));
        %>
        
        <%
        for(Person p : list){
        %>
        <li><%=p.getRoll()%></li>
        <%
            }
        %>
        
    </body>
</html>
