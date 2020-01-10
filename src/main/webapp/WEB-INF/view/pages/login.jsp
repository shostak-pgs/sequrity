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
    <h3>Welcome to Online Shop! You Win Today!</h3>
</div>
<div id="form">
    <h4>Login with Username and Password</h4>
    <form action="${loginUrl}" method="post">
        <p><input type="text" name="username" size="20%" placeholder="Enter your name"></p>
        <p><input type="password" id="password" name="password" size="20%" placeholder="Enter your name" > </p>
        <b><td colspan='2'><input type="submit" id="subterm" align="center" value="Login"></b>
    </form>
</div>
</body>
</html>
