<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ page import="model.Chat, java.util.List" %>

<%
    Chat chat = (Chat)session.getAttribute("chat");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>編集</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script
</head>
<body>

<header class="bg-primary text-white p-3">
	<button class="back-button" onclick="window.location.href='ChatControlServlet'">
            <a href="#" class="previous text-white">&laquo; 戻る</a>
     </button>
	<h2 class="text-center">タイトル編集</h2>
</header>


<div class="container mt-3">
    <form action="ChatEditServlet" method="post">
        <div class="form-group">
            <label for="title">タイトル:</label>
            <input type="text" class="form-control" id="title" name="title" value="<%= chat.getTitle() %>">
        </div>
        <input type="hidden" name="boardId" value="<%= chat.getBoardId() %>">
        <input type="hidden" name="flag" value="update">
        <button type="submit" class="btn btn-primary">変更</button>
    </form>
</div>

</body>

<style>
.back-button,
        a.back-button {
            color: #fff;
            background-color: #eb6100;
        }
</style>

</html>
