<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Admin Dashboard</title>
    <link href="css/admin/admin.css" rel="stylesheet">
</head>
<body>
<nav class="navbar navbar-expand-sm navbar-dark fixed-top">
    <div class="container-fluid">
        <div class="navbar-collapse" id="collapsibleNavbar">
            <div class="nav-item">
                <a class="nav-link exit-link" href="/">Exit</a>
            </div>
        </div>
    </div>
</nav>
<table>
    <thead>
    <tr>
        <th>Member Number</th>
        <th>User ID</th>
        <th>Username</th>
        <th>Password</th>
        <th>Address</th>
        <th>Phone</th>
        <th>Gender</th>
        <th>Hobbies</th>
        <th>Action</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="member" items="${admin}">
        <tr>
            <td>${member.membernum}</td>
            <td>${member.userid}</td>
            <td>${member.username}</td>
            <td>${member.userpassword}</td>
            <td>${member.address}</td>
            <td>${member.phone}</td>
            <td>${member.gender}</td>
            <td>${member.hobby}</td>
            <td>
                <form action="/member" method="post">
                    <input type="hidden" name="action" value="delete" />
                    <input type="hidden" name="membernum" value="${member.membernum}" />
                    <input type="submit" value="Delete" class="delete-btn" />
                </form>
            </td>
        </tr>
    </c:forEach>

    </tbody>
</table>

</body>
</html>
