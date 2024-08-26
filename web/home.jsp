<%-- 
    Document   : home
    Created on : Aug 11, 2024, 10:17:52 PM
    Author     : Admin
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.9.1/font/bootstrap-icons.css">
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/style.css"/>
        <title>LAPTOP SHOP</title>
    </head>
    <body>
        
        <jsp:include page="header.jsp"></jsp:include>


        <div class="slider row">
            <div class="col-md-3">

                <div class="nav-menu">
                    <h2>Category</h2>
                    <ul class="product-list">
                        <c:forEach items="${listCategory}" var="i">
                            <li class="list-group-item "><a href="category?cid=${i.cid}" class="nav-link ">${i.cname}<span class="sr-only">(current)</span></a></li>
                            </c:forEach>
                    </ul>
                </div>
            </div>
            <div class="col-md-9">
                <div id="carouselExampleControls" class="carousel slide" data-ride="carousel">
                    <div class="carousel-inner">
                        <div class="carousel-item active">
                            <img src="./assets/Slider/acer-aspire-lite-15-51m-55nb-i5.jpg"
                                 class="d-block" width="100" height="300" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./assets/Slider/apple-macbook-air-2015-11-6inch.jpg" class="d-block" width="100" height="300" alt="...">
                        </div>
                        <div class="carousel-item">
                            <img src="./assets/Slider/dell-inspiron-15-3520-i5.jpg" class="d-block" width="100" height="300" alt="...">
                        </div>
                    </div>
                    <a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="sr-only">Previous</span>
                    </a>
                    <a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="sr-only">Next</span>
                    </a>
                </div>
            </div>
        </div>

        <div class="content row">
            <c:forEach items="${listProduct}" var="product">
                <div class="col-md-4">
                    <div class="product-item">
                        <a class="text name" href="product?pid=${product.pid}">${product.name}</a>
                        <div class="text price">${product.description}</div>
                        
                        <!-- Details Button -->
                        <a class="buy" href="product?pid=${product.pid}">Details</a>
                        
                        <!-- SKU Dropdown -->
                        <c:if test="${not empty skusMap[product.pid]}">
                            <form action="addcart" method="get" class="form-inline">
                                <div class="form-group mx-sm-3 mb-2">
                                    <select name="sku" class="form-control">
                                        <c:forEach items="${skusMap[product.pid]}" var="sku">
                                            <option value="${sku}">${sku}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                                <input type="hidden" name="pid" value="${product.pid}"/>
                                <button type="submit" class="btn btn-primary mb-2">Add to Cart</button>
                            </form>
                        </c:if>
                    </div>
                </div>
            </c:forEach>
        </div>        
        <jsp:include page="footer.jsp"></jsp:include>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/popper.js@1.14.7/dist/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
        crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.3.1/dist/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
        crossorigin="anonymous"></script>
        <script src="./assets/js/code.js"></script>
    </body>
</html>
