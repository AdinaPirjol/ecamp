<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@page session="true" %>
<%@page import="ws.Customer"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width">
        <link rel="stylesheet" href="css/bootstrap-theme.min.css" type="text/css">
        <link rel="stylesheet" href="css/bootstrap.min.css" type="text/css">
        <link rel="stylesheet" href="css/style.css" type="text/css">
        <link href="css/style.css" rel="stylesheet">
        <link href="css/errorcss.css" rel="stylesheet">
        <link href="css/font-awesome.min.css" rel="stylesheet">
        <title>E-Camp</title>
    </head>
    <body>
        
    <%@include file="header.jsp" %>
 
    <div class="container">
        <%if (session.getAttribute("customer") != null) {%>
        <a href="/ECampWebApplication/browse"><button class="btn btn-primary">Browse Camp Offers</button></a><br>
            <a href="/ECampWebApplication/viewHistory"><button class="btn btn-primary">View History</button></a>
        <%}else{%>
            You are not logged in! Log in or register to view available actions.
        <%}%>
    </div>
</body>
<script type="text/javascript" src="scripts/jquery-2.1.4.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script type="text/javascript" src="scripts/bootstrap.min.js"></script>
<script type="text/javascript" src="scripts/validation.js"></script>
</html>
