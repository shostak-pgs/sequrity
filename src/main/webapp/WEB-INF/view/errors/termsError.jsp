<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Error!</title>
    <style type="text/css">
    #greeting {
        margin-left: 10%;
        margin-right: 10%;
        background: #fc0;
        padding: 10px;
        text-align: center;
    }
</style>
<style type="text/css">
    #form {
        margin-left: 10%;
        margin-right: 10%;
        background: #01DF01;
        padding: 10px;
        text-align: center;
    }
</style>
</head>
<body>
<div id="greeting">
    <h3>Oops!</h3>
</div>
<div id="form">
    <p>You shouldn't be here</p>
    <p>Please, agree with the terms of the service first and enter your name</p>
    <p><a href="${pageContext.request.contextPath}/">Start page</a></p>
</div>
</body>
</html>