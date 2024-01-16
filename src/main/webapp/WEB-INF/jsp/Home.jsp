<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="model.Account" %>
    
<%
    Account account = (Account)session.getAttribute("account");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>Home</title>
    <style>
        body {
            background-color: #f0f8ff; /* Light blue color */
        }
        .container {
            margin-top: 50px;
            text-align: center;
        }
        .header-footer {
            background-color: #add8e6; /* Light blue color */
            padding: 10px;
            text-align: center;
        }
    </style>
</head>
<body>

    <div class="header-footer">
        <h1>確認</h1>
    </div>

    <div class="container">
        <h2><%= account.getAccountName() %>さん、ログインを確認しました</h2>
        
        <form action="ChatControlServlet" method="get">
            <button type="submit" class="btn btn-primary">掲示板</button>
        </form>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
