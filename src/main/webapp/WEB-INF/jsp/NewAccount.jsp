<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>新規アカウント登録</title>
 	<script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script
</head>
<body>

<div class="container">
    <h2 class="mt-5">Web_app</h2>
    
    <div class="mt-4">
        <h2>新規登録</h2>
        
        <form action="AccountRegisterServlet" method="post">
            <div class="form-group">
                <label for="accountName">名前:</label>
                <input type="text" class="form-control" id="accountName" name="accountName">
            </div>
            
            <div class="form-group">
                <label for="password">PW:</label>
                <input type="password" class="form-control" id="password" name="password">
            </div>
            
            <button type="submit" class="btn btn-primary" name="new">Login</button>
        </form>
    </div>
</div>

</body>
</html>
