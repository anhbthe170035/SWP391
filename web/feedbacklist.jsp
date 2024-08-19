<%-- 
    Document   : feedbacklist
    Created on : Aug 19, 2024, 12:10:09 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="entity.Feedback" %>
<%@page import="java.util.*" %>
<!DOCTYPE html>
<%
  List<Feedback> flist = (List<Feedback>) request.getAttribute("flist");
%>    
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>List of Feedback</title>
    </head>
    <body>
        <style>
            .header{
                height: 100px;
                text-align: center;
                margin: auto;
                line-height: 100px;
                font-size: 80px;
                color: blue;
            }
            .container{
                display: grid;
                grid-template-columns: auto auto auto;
            }

        </style>
        <h2 style="text-align: center"> List of Product </h2>
        <div class="container">
            <%
          for(Feedback f : flist) {
            %>
            <div>
                <h4><%= f.getFeedbackid() %> </h4>
                <h4><%= f.getOrderId() %></h4>
                <h4><%= f.getSku() %></h4>
                <h4><%= f.getFeedback() %></h4>
                <h4><%= f.getStar() %></h4>
            </div>
            <% } %>  
        </div>
        <p><button onclick='window.history.go(-1);'>Back</button>
    </body>
</html>
