<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Online Shop</title>
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
    <style>
        #terms
        {
            margin-left: 8%;
        }
    </style>
    <style>
        #subterm
        {
            width: 20%;
        }
    </style>
</head>
<body>
<div id="greeting">
    <h2>Create new Account NOW!!! </h2>
</div>
<div id="form">
    <form action="createUserServlet/createNewUser" method="post">
        <p><input type="text" name="name" size="20%" placeholder="Enter your name"></p>
        <p><input type="text" name="password" size="20%" placeholder="Enter your password"></p>
        <p><input type="radio" id="terms" name="term" value="agreed"> I agree with the terms of service!!!</p>
        <b><input type="submit" id="subterm" align="center" value="Enter"></b>
    </form>
</div>
</body>
</html>
