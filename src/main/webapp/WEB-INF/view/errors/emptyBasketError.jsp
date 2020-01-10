<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Nothing were chosen!</title>
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
    <p>You didn't choose anything</p>
    <p>Click to the link to follow the select page</p>
    <p><a href="${pageContext.request.contextPath}/goodsAddServlet">Goods select page</a></p>
</div>
</body>
</html>