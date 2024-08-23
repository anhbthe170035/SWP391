<%-- 
    Document   : feedback
    Created on : Aug 18, 2024, 4:07:00 PM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Send Feedback</title>
        <style>
            body {
                font-family: Arial, sans-serif;
                margin: 20px;
                background-color: #f4f4f4;
            }
            .form-container {
                max-width: 600px;
                margin: 0 auto;
                background-color: #fff;
                padding: 20px;
                border-radius: 8px;
                box-shadow: 0 0 10px rgba(0,0,0,0.1);
            }
            h1 {
                text-align: center;
                margin-bottom: 20px;
                color: #333;
            }
            .form-group {
                margin-bottom: 15px;
            }
            .form-group label {
                display: block;
                margin-bottom: 5px;
                font-weight: bold;
            }
            .form-group input, .form-group select {
                width: 100%;
                padding: 8px;
                border: 1px solid #ddd;
                border-radius: 4px;
            }
            .form-group input[type="date"] {
                padding: 6px;
            }
            .form-group .error {
                color: red;
                font-size: 0.875em;
            }
            .form-group .submit-btn {
                background-color: #007bff;
                color: #fff;
                border: none;
                padding: 10px 20px;
                border-radius: 4px;
                cursor: pointer;
                font-size: 16px;
            }
            .form-group .submit-btn:hover {
                background-color: #0056b3;
            }
            .back-link {
                display: inline-block;
                margin-top: 20px;
                text-decoration: none;
                color: #007bff;
            }
            .back-link:hover {
                text-decoration: underline;
            }
            .star {
                font-size: 10vh;
                cursor: pointer;
            }

            .one {
                color: rgb(255, 0, 0);
            }

            .two {
                color: rgb(255, 106, 0);
            }

            .three {
                color: rgb(251, 255, 120);
            }

            .four {
                color: rgb(255, 255, 0);
            }

            .five {
                color: rgb(24, 159, 14);
            }
        </style>
    </head>
    <body>
        <div class="form-container">
            <h1>Send Feedback</h1>

            <c:if test="${not empty error}">
                <p class="error">${error}</p>
            </c:if>

            <form action="sendFeedback" method="post">
                <div class="card">
                    <h1>JavaScript Star Rating</h1>
                    <br />
                    <span onclick="gfg(1)"
                          class="star">★
                    </span>
                    <span onclick="gfg(2)"
                          class="star">★
                    </span>
                    <span onclick="gfg(3)"
                          class="star">★
                    </span>
                    <span onclick="gfg(4)"
                          class="star">★
                    </span>
                    <span onclick="gfg(5)"
                          class="star">★
                    </span>
                    <h3 id="output">
                        Rating is: 0/5
                    </h3>
                </div>
                
                <div class="form-group">
                    <label for="username">Feedback: </label>
                    <input type="text" id="username" name="username" required />
                </div>
            </form>
        </div>

    </body>
</html>
