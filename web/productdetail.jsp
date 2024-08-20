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
     <title>Detail of Product</title>
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
        .product{
            display: flex;
            justify-content: space-around;
            
        }
        
    </style>
    <form action="Search" method="Get">
       <p><input type="text" name="name" value=""/>
       <input type="submit" value="Search"> 
  </form>  
      <h2 style="text-align: center"> Detail of Product </h2>
      <div class="container">
    
     <%
        for(ProductDetail x: lst) {
      %>
      <div class="product">
          <div><img src="img/472_dell_inspiron_5401_2__1_.png" style="height: 400px;width: 400px"></div>
          <div><h4>Name: <%= x.getName() %> </a></h4>
          <h4>Model: <%= x.getPid() %></h4>
          <h4>Brand: <%= x.getBrand() %></h4>
          <h4 style="color:red; text-decoration: line-through"><%= x.getPrice() %>$</h4>
          <h4>Price: <%= x.getPrice() - x.getSale() %>$</h4>
          <h4>Color: <%= x.getColor() %> </h4>
          <h4>CPU: <%= x.getCpu() %> </h4>
          <h4>CPU_specs <%= x.getCpu_specs() %> </h4>
          <h4>Ram: <%= x.getRam() %> </h4>
          <h4>Ram max: <%= x.getRam_max() %> </h4>
          <h4>Gpu: <%= x.getGpu() %> </h4>
          <h4>Storage: <%= x.getStorage() %> </h4>
          <h4>Monitor: <%= x.getMonitor() %> </h4>
          <h4>Description: <%= x.getDescription() %> </h4></div>
          
          
      </div>
      <% } %>  
      </div>
    <p><button onclick='window.history.go(-1);'>Back</button>
  </body>
</html>
