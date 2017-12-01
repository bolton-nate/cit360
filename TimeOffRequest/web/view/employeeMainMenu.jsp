<%-- 
    Document   : employeeMainMenu
    Created on : Nov 15, 2017, 6:27:42 PM
    Author     : natebolton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Time Off - Main Menu</title>
    </head>
    <body>
        <h1>Main Menu</h1>
        <ul>
            <li><a href="/TimeOffRequest/menuControl?action=viewRequest">View My Requests</a></li>
            <li><a href="/TimeOffRequest/menuControl?action=submitRequest">Submit Request</a></li>
            <li><a href="/TimeOffRequest/menuControl?action=logoff">Log Off</a></li>
        </ul>

    </body>
</html>
