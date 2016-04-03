<%@page import="ws.Customer"%>

<nav class="navbar navbar-default navbar-fixed-top" role="navigation">

        <div class="container-fluid">
            <div class="navbar-header logo">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#nav-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button><a class="navbar-brand" href="index.jsp"><span>
                        <img src="img/logo.png" style="max-width:50px; margin-top: -7px;">E-Camp</span>
                </a>
            </div>   
            <div class="collapse navbar-collapse" id="nav-collapse">
                <ul class="nav navbar-nav">
                    <%if (session.getAttribute("customer") != null) {
                        Customer c = (Customer) session.getAttribute("customer");%>
                        
                        <li class="btn btn-toolbar" role="presentation">Hello, <%=c.getName()%></li>
                        <li class="btn btn-toolbar" role="presentation"><a href="/ECampWebApplication/logout">Logout</a></li>
                    <%} else {%>
                        <li class="btn btn-toolbar" role="presentation"><a href="login.jsp">Login</a></li>
                        <li class="btn btn-toolbar" role="presentation"><a href="register.jsp">Register</a></li>
                    <%}%>
                </ul>
            </div> 
        </div> <!-- Container fluid-->
    </nav>
    <hr><hr><hr>