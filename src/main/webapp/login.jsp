<%--解决页面乱码--%>
<%@page contentType="text/html; UTF-8" pageEncoding="UTF-8" isELIgnored="false" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>index</title>
</head>

<body>
<form action="${pageContext.request.contextPath}/user/login" method="post">
    <p>用户名：<input type="text" name="username"></p>
    <p>密码  ：<input type="text" name="password"></p>
    <p>验证码: <input type="text" name="verifyCode"><img src="${pageContext.request.contextPath}/user/getImage" alt=""></p>
    <input type="submit" value="登录">
</body>
</html>
