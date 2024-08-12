<%-- 
    Document   : forgetPass
    Created on : Oct 31, 2022, 1:43:51 AM
    Author     : trant
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU" crossorigin="anonymous">
        <link rel="stylesheet" href="./assets/logincss.css">
        <title>Forget Password</title>
    </head>
    <body>
       <div class="col-md-6 offset-md-3">
                    <span class="anchor" id="formResetPassword"></span>
                    <hr class="mb-5">

                    <!-- form card reset password -->
                    <div class="card card-outline-secondary">
                        <div class="card-header">
                            <h3 class="mb-0">Password Reset</h3>
                        </div>
                        <div class="card-body">
                            <form class="form" role="form" autocomplete="off" action="sendemail" method="post">
                                <div class="form-group">
                                    <label for="inputResetPasswordEmail">Email</label>
                                    <input type="email" class="form-control" id="inputResetPasswordEmail" required="" name="email">
                                    <span id="helpResetPasswordEmail" class="form-text small text-muted">
                                            Password reset instructions will be sent to this email address.
                                        </span>
                                </div>
                                <h6>${errorNotExitMail}</h6>
                                <div class="form-group">
                                    <!-- Back Button -->
                                    <a href="${pageContext.request.contextPath}/login" class="btn btn-secondary btn-lg float-left">Back</a>
                                    <!-- Reset Button -->
                                    <button type="submit" class="btn btn-success btn-lg float-right">Reset</button>
                                </div>
                            </form>
                        </div>
                    </div>
                    <!-- /form card reset password -->

                </div>

        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
