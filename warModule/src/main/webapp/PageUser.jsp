<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="application/javascript" src="js/UserJS.js"></script>
    <link href="css/Usercss.css" rel="stylesheet" type="text/css">
    <title>Page User</title>
</head>
<body>
<div style="text-align: center"> Info User</div>
<br>
<table>
    <tr>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
    </tr>
    <tr>
        <td>${sessionScope.user.login}</td>
        <td>${sessionScope.user.password}</td>
        <td>${sessionScope.user.role}</td>
    </tr>
</table>
<c:if test="${sessionScope.user.role == 'ADMIN'}">
    <form>
        <input value="List" type="button" name="List" onclick="checkList()">
    </form>
    <br>
    <form>
        <input type="button" name="Add User" value="Add User" onclick="checkReg()">
    </form>
    <br>
    <form>
        <input type="button" value="Add Admin" onclick="checkAdmin()">
    </form>
    <br>
    <form>
        <input type="button" name="Delete User" value="Delete User" onclick="checkCome()">
    </form>
    <br>
</c:if>
<form method="post" action="userOut">
    <button value="OUT" type="submit" id="buttonOut">OUT</button>
</form>
<table id="tableUser" style="display: none">
    <tr>
        <th>Login</th>
        <th>Password</th>
        <th>Role</th>
        <th>delete</th>
    </tr>
    <c:forEach items="${userList}" var="user">
        <tr>
            <td>${user.login}</td>
            <td>${user.password}</td>
            <td>${user.role}</td>
            <td><form method="post" action="deleteUser">
                <button type="submit" name="id" value="${user.id}">delete</button>
            </form></td>
        </tr>
    </c:forEach>
</table>
<div style="display: none" id="divReg">
    <div>Add</div>
    <form action="userCR" method="post">
        <p>Login</p>   <input type="text" name="login" value="" placeholder="login">
        <p>Password</p> <input type="text" name="password" value="" placeholder="password">
        <br>
        <button type="submit" value="AddUser" name="users" id="buttonAddUser">Add</button>
    </form>
</div>
<div style="display: none" id="divCome">
    <div>Delete</div>
    <form action="userCR" method="post">
        <p>Login</p>   <input type="text" name="login" value="" placeholder="login">
        <p>Password</p> <input type="text" name="password" value="" placeholder="password">
        <br>
        <button type="submit" value="DeleteUser" name="users" id="buttonDeleteUser">Delete</button>
    </form>
</div>
<div style="display: none" id="checkAdmin">
    <div>Add Admin</div>
    <form action="userCR" method="post">
        <p>Login</p>   <input type="text" name="login" value="" placeholder="login">
        <p>Password</p> <input type="text" name="password" value="" placeholder="password">
        <br>
        <button type="submit" value="AddAdmin" name="users" id="buttonAddADmin">Add Admin</button>
    </form>
</div>
<div>${requestScope.result}</div>
</body>
</html>
<style type="text/css">
    table, th, td {
        margin-left: 700px;
        text-align: center;
        border: 1px solid black;
    }
</style>