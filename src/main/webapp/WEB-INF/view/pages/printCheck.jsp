<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang=\"en\">
    <head>
        <title>Select goods</title>
        <style type="text/css">

            #greetingStyle {
                margin-left: 10%;
                margin-right: 10%;
                background: #fc0;
                padding: 10px;
                text-align: center;
            }
        </style>
        <style type="text/css">
            #formStyle {
                margin-left: 10%;
                margin-right: 10%;
                background: #01DF01;
                padding: 10px;
                text-align: center;
            }
        </style>
    </head>
    <body>
        <div id="greetingStyle">
          <h3>Dear ${name}, your order:</h3>
        </div>

        <div id="formStyle">
          <p>${goodsList}</p>
          <p>Total price: $ ${price}</p>
        </div>
    </body>
</html>