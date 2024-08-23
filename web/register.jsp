<%-- 
    Document   : register
    Created on : Aug 22, 2024, 9:03:24 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/logincss.css">
        <title>LAPTOP SHOP</title>
    </head>
    <body>
        <div id="logreg-forms">
            <form action="register" class="form-register" style="display: block" method="post">
                <h1 class="h3 mb-3 font-weight-normal" style="text-align: center"> Register</h1>
                <input type="text" id="username" class="form-control" placeholder="Username" required autofocus="" name="username">
                <input type="password" id="password" class="form-control" placeholder="Password" required autofocus="" name="password">
                <input type="password" id="user-repeatpass" class="form-control" placeholder="Repeat Password" required autofocus="" name="re-password">
                <input type="text" id="name" class="form-control" placeholder="Name" required="" autofocus="" name="name">
                <label>Gender</label>
                <div class="input-group">
                    <select name="gender" class="form-control form-control-lg border-left-0">
                        <option value="1">Male</option>
                        <option value="0">Female</option>
                    </select>
                </div>
                <input type="email" id="email" class="form-control" placeholder="Email" required autofocus="" name="email">
                <input type="date" id="dob" class="form-control" placeholder="Dob" required autofocus="" name="date">
                <input type="text" id="phone" class="form-control" placeholder="Phone" required autofocus="" name="phone">
                <label for="Image">Image</label>
                <input type="img" class="form-control" id="Image" required="" value="" name="Image" accept="image/*">
                <div class="dropdown">
                    <a class="btn btn-secondary dropdown-toggle" role="button" id="dropdownMenuLink" data-bs-toggle="dropdown" aria-expanded="false">
                        Choose role
                    </a>
                    <ul class="dropdown-menu" aria-labelledby="dropdownMenuLink">
                        <li><a class="dropdown-item" value="0">Action</a></li>
                        <li><a class="dropdown-item" value="1">Another action</a></li>
                        <li><a class="dropdown-item" value="2">Something else here</a></li>
                    </ul>
                </div>
                <h6>${error}</h6>
                <button class="btn btn-primary btn-block" type="submit"><i class="fas fa-user-plus"></i> Register</button>

            </form>
            <br>

        </div>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

    </body>
</html>
