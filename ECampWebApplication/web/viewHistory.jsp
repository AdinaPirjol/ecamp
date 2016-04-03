<%@page import="java.util.Date"%>
<%@page import="java.util.List"%>
<%@page import="ws.Booking"%>
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
        <% List<Booking> bookings = (List<Booking>)request.getAttribute("bookings");
        if(bookings.size()==0){%>
            No records available.
        <%} for(Booking b : bookings) {%>
            <li class="list-group-item">
                <table class="table">
                    <tr class="row">
                        <td><%=b.getCampOffer().getCamp().getCampName()%>, <%=b.getCampOffer().getCamp().getLocation()%> - <%=b.getCampOffer().getCamp().getDaysPeriod()%> days, begins <%=b.getCampOffer().getStartDate()%>
                        </td>
                        <%Date today = new Date();
                        if(b.getCampOffer().getStartDate().toGregorianCalendar().getTime().before(today)) {%>
                            <td>
                                <span><a href="/ECampWebApplication/feedback?campOfferId=<%=b.getCampOffer().getId()%>">Give feedback</a></span>
                            </td>
                            <td>
                                <span><a href="/ECampWebApplication/postPictures?campOfferId=<%=b.getCampOffer().getId()%>">Post photos</a></span>
                            </td>
                        <%}%>
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
