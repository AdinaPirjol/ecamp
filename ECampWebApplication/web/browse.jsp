<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.text.DateFormat"%>
<%@page import="ws.OptionalTrip"%>
<%@page import="ws.Camp"%>
<%@page import="ws.CampOffer"%>
<%@page import="java.util.List"%>
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
        <h1>List of available camp offers:</h1>
        
        <%List<CampOffer> campOffers = (List<CampOffer>)request.getAttribute("campOffers");
        if(campOffers.size() == 0) {%>
            There are no available offers at the moment.
        <%}%>
            
        <% for(CampOffer campOffer : campOffers) {
            Camp c = campOffer.getCamp(); %>
            <li class="list-group-item">
                <table class="table">
                    <tr class="row">
                        <td>
                            <%=c.getCampName()%>, <%=c.getLocation()%> - <%=c.getDaysPeriod()%> days, begins <%=campOffer.getStartDate()%>
                        </td>
                        <td>
                            <span><a href="/ECampWebApplication/viewCamp?campOfferId=<%=campOffer.getId()%>">View</a></span>
                        </td>
                    </tr>
                </table>
            </li>
        <%}%>
    </div>
</body>
    <script type="text/javascript" src="scripts/jquery-2.1.4.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript" src="scripts/bootstrap.min.js"></script>
    <script type="text/javascript" src="scripts/validation.js"></script>
</html>
