<%-- 
    Document   : error
    Created on : 20-may-2014, 12:00:30
    Author     : FO-Mañana
--%>

<%@page isErrorPage="true" contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>error page</title>
    </head>
    <body>
        <h1>Error page</h1>
        <!--todo -->
        <p> ${view}</p>
        <p><%= exception.toString()%></p>
    </body>
</html>
