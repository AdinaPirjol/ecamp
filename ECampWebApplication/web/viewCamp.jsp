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
            
        <% CampOffer campOffer = (CampOffer)request.getAttribute("campOffer");
        Camp c = campOffer.getCamp();%>
                        
            <section id="topic-header">
        <div class="container">
            <div class="row">
                <div class="col-md-7">
                    <h4><%=c.getCampName()%>, <%=c.getLocation()%> </h4>
                    <p></p>
                </div>	<!-- /.col-md-4 -->

            </div>	<!-- /.row -->
        </div>	<!-- /.container-->
    </section><!-- /Section -->

    <div class="container">
        <div class="panel">
            <div class="panel-title">
                <h4><%=c.getDaysPeriod()%> days, begins <%=campOffer.getStartDate()%></h4>
            </div>
            <div class ="panel-body"
                 <div class="row">
                    <div class="col-md-7">
                        <p class="price-label"><span class="glyphicon glyphicon-usd" aria-hidden="true"></span> Price:</p>
                    </div>
                    <div class="col-md-5">
                        <p class="price"><%=c.getPrice()%>$$</p>
                    </div>
                    <div class="col-md-7">
                        <p class="price-label"><span aria-hidden="true"></span> Location:</p>
                    </div>
                    <div class="col-md-5">
                        <p class="price"><%=c.getLocation()%></p>
                    </div>
                    <div class="col-md-7">
                        <p class="price-label"><span aria-hidden="true"></span> Seats</p>
                    </div>
                    <div class="col-md-5">
                        <p class="price"><%=campOffer.getRemainingSeats()%> / <%=campOffer.getTotalSeats()%></p>
                    </div>
                    </div>
                    <div class="col-md-7">
                        <p class="price-label"><span aria-hidden="true"></span> Extra equipment:</p>
                    </div>
                    <div class="col-md-5">
                        <p class="price"><%=c.getExtraEquipment()%>$$</p>
                    </div>
                    <div class="col-md-7">
                        <p class="price-label"><span aria-hidden="true"></span> Optional trips:</p>
                    </div>
                    <div class="col-md-5">
                         <% List<OptionalTrip> op = campOffer.getOptionalTrips();
                        double tripPrice = 0;
                        for(OptionalTrip o:op) { %>
                            <p class="price-label"><span aria-hidden="true"></span> <%=o.getName()%> - <%=o.getDescription()%>, <%=o.getPrice()%>$$    </p>
                        <%tripPrice += o.getPrice();}
                        session.setAttribute("optionalPrice",tripPrice);%>
                    </div>
                </div>
            </div>
        </div>

       
        <div class="container">
            <div class="panel">
                <div class="panel-body">

                </div>
                <div class="panel-footer">
                    <a data-toggle="modal" class="view-link" data-target="#modalBooking" href="#">
                        <i class="fa fa-plus-circle"></i>
                        Buy
                    </a>
                </div>
            </div>
        </div>


        <div class="modal fade" id="modalBooking" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
            <div class="modal-dialog" style="width: 600px">
                <div class="modal-content">

                    <div class="modal-header">
                        <button type="button" class="close" data-dismiss="modal" aria-hidden="true">×</button>
                        <h4 class="modal-title" id="myModalLabel">Booking</h4>
                    </div> <!-- /.modal-header -->
                    <div class="modal-body">
                        <form class="form-horizontal" role="form"  method= "post" action="/ECampWebApplication/booking?campOfferId=<%=campOffer.getId()%>" >
                            <table class="table-responsive">
                                <thead><th>Want it</th><th>Price</th></thead>
                                <tbody>
                                <td>
                                    <input type="checkbox" name="tripCheckbox" id="tripCheckbox" value="tripCheckbox"><span>Optional Trips</span>
                                </td>
                                <td>
                                    <h5><%=session.getAttribute("optionalPrice")%></h5>
                                </td>
                                </tbody>
                            </table>
                            <div class="form-group">
                                <label class="control-label">Children Number:</label>
                                <input class="form-control" type="number" name="childrenNumber" required="true"value="">
                            </div>
                            <div class="form-group">
                                <label class="control-label">Children Names (separated by spaces):</label>
                                <input class="form-control" type="text" name="childrenNames" required="true"value="">
                            </div>
                            <div class="form-group">
                                <label class="control-label">Payment Method:</label>
                                <input class="form-control" type="text" disabled value="Cash on Delivery">
                            </div> 
                            <div class="form-group">
                                <label class="control-label">Total Price:</label>
                                <input class="form-control" type="number" disabled value="<%=c.getPrice()%>"></label>
                            </div>
                            <div class="form-group">
                                <label class="control-label"></label>
                                <div class="modal-footer">
                                    <input type="submit" class="btn btn-primary" value="Buy">
                                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                                </div>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
    </div>
</body>
    <script type="text/javascript" src="scripts/jquery-2.1.4.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
    <script type="text/javascript" src="scripts/bootstrap.min.js"></script>
    <script type="text/javascript" src="scripts/validation.js"></script>
</html>
