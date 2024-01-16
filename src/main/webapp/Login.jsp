<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>ログイン</title>
    <style>
        body {
            background-color: #f0f8ff; /* Light blue color */
        }
        .header-footer {
            background-color: #add8e6; /* Light blue color */
            padding: 10px;
            text-align: center;
        }
        .container {
            margin-top: 50px;
        }
    </style>
</head>
<body>

    <div class="header-footer">
        <h1>Login</h1>
    </div>

    <div class="container">
        <form action="AccountRegisterServlet" method="post">
            <div class="form-group">
                <label for="accountName">名前:</label>
                <input type="text" class="form-control" name="accountName">
            </div>
            <div class="form-group">
                <label for="password">PW:</label>
                <input type="password" class="form-control" name="password">
            </div>
            <input type="hidden" name="flag" value="login">
            <button type="submit" class="btn btn-primary">Login</button>
        </form>

        <form action="AccountRegisterServlet" method="get">
            <button type="submit" class="btn btn-secondary mt-3">新規登録</button>
        </form>
    </div>

    <div class="header-footer mt-5">
        <p>Footer content goes here.</p>
    </div>

    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</body>
</html>
