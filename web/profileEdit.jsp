<%@include file="template/header.jsp" %>
<c:set var="a" value="${account}" />
<c:set var="c" value="${customer}" />
<div id="content-left">
    <h3 style="font-weight: normal;">Welcome ${c.getContactName()}</h3>
    <h3>Account Management</h3>
    <ul>
        <a href="profile.html"><li>Personal information</li></a>
    </ul>
    <h3>My order</h3>
    <ul>
        <a href="profile1.html"><li>All orders</li></a>
        <a href="#"><li>Canceled order</li></a>
    </ul>
</div>
<div id="content-right">
    <div class="path">Personal information</b></div>
    <form action="<%=path%>/account/profile" method="post">
        <div class="content-main">

            <div id="profile-content">
                <div class="profile-content-col">
                    <div style="display: none">CustomerID: <br/><input type="text" name="txtCustomerID" value="${c.getCustomerID()}"></div>
                    <div>Company name: <br/><input type="text" name="txtCompanyName" value="${c.getCompanyName()}"></div>
                    <div>Contact name: <br/><input type="text" name="txtContactName" value="${c.getContactName()}"></div>
                    <div>
                        <input type="submit" value="Edit info"/>
                    </div>
                </div>
                <div class="profile-content-col">
                    <div>Company title: <br/><input type="text" name="txtCompanyTitle" value="${c.getContactTitle()}"></div>
                    <div>Address: <br/><input type="text" name="txtAddress" value="${c.getAddress()}"></div>
                </div>
                <div class="profile-content-col">
                    <div>Email: <br/>${a.getEmail()}</div>
                </div>
            </div>

        </div>
    </form>
</div>
<%@include file="template/footer.jsp" %>