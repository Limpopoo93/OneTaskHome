<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script type="application/javascript" src="js/UserJS.js"></script>
   <link href="css/Usercss.css" rel="stylesheet" type="text/css">
    <title>Index</title>
</head>
<body>
<div id="divHead" style="text-align: center">Welcome to Page</div>
<br>
<form>
   <input type="button" name="comeIn" value="ComeIn" onclick="checkCome()">
</form>
<br>
<form>
    <input type="button" name="comeIn" value="Registration" onclick="checkReg()">
</form>
<div style="display: none; text-align: center" id="divReg">
    <div>Registration</div>
    <form action="registration" method="post">
        <p>Login</p>   <input type="text" name="login" value="" placeholder="login">
        <p>Password</p> <input type="text" name="password" value="" placeholder="password">
        <br>
        <button type="submit" id="buttonRegUser">ADD</button>
    </form>
</div>
<div style="display: none;text-align: center" id="divCome">
    <div>Come In</div>
    <form action="user" method="post">
        <p>Login</p>   <input type="text" name="login" value="" placeholder="login">
        <p>Password</p> <input type="text" name="password" value="" placeholder="password">
        <br>
        <button type="submit" id="buttonComeUser">Come In</button>
    </form>
</div>
<div>${requestScope.result}</div>
</body>
</html>

