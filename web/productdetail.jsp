<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import = "entity.Product" %>
<%@page import = "java.util.*" %>
<%@page import = "entity.ProductDetail" %>
<!DOCTYPE html>
<%
  List<ProductDetail> lst = (List<ProductDetail>) request.getAttribute("lst");
%>    
<html>
  <head>
     <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
     <title>List of Product</title>
  </head>
  <body>
      
        <style>
        .header{
        height: 100px;
        text-align: center;
        margin: auto;
        line-height: 100px;
        font-size: 80px;
        color: blue;}
        .container{
            display: grid;
            grid-template-columns: auto auto auto;
        }
        
    </style>
    <form action="Search" method="Get">
       <p><input type="text" name="name" value=""/>
       <input type="submit" value="Search"> 
  </form>  
      <h2 style="text-align: center"> List of Product </h2>
      <div class="container">
    
     <%
        for(ProductDetail x: lst) {
      %>
      <div>
          <img src="img/472_dell_inspiron_5401_2__1_.png" style="height: 400px;width: 400px">
          <h4><%= x.getName() %> </a></h4>
          <h4><%= x.getPid() %></h4>
          <h4><%= x.getBrand() %></h4>
          <h4 style="color:red; text-decoration: line-through"><%= x.getPrice() %>$</h4>
          <h4><%= x.getPrice() - x.getSale() %>$</h4>
      </div>
      <% } %>  
      </div>
    <p><button onclick='window.history.go(-1);'>Back</button>
  </body>
</html>
