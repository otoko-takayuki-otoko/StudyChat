<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Chat, java.util.List" %>

<%
    List<Chat> chatList = (List<Chat>) session.getAttribute("chatList");
%>
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>投稿編集</title>
     <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>
<body style="background-color: #fff;">

<header class="bg-primary text-white p-3">
	<button class="back-button" onclick="window.location.href='ChatControlServlet'">
            <a href="#" class="previous text-white">&laquo; 戻る</a>
     </button>
	<h2 class="text-center">投稿編集</h2>
</header>

<div class="container mt-4">
    <table class="table">
        <thead>
            <tr>
                <th>タイトル</th>
                <th>操作</th>
            </tr>
        </thead>
        <tbody>
			 <% for (Chat chat : chatList) { %>
				    <tr>
				        <td><%= chat.getTitle() %></td>
				        <td>
				            <form style="display:inline-block;" onsubmit="return confirmDelete();" action="ChatEditServlet" method="post">
				                <input type="hidden" name="boardId" value="<%= chat.getBoardId() %>">
				                <button type="submit" class="btn btn-danger">削除</button>
				                <input type="hidden" name="flag" value="delete">
				            </form>
				            <form style="display:inline-block;" action="ChatEditServlet" method="post">
				                <input type="hidden" name="boardId" value="<%= chat.getBoardId() %>">
				                <button type="submit" class="btn btn-secondary" name="flag" value="change">変更</button>
				                <input type="hidden" name="flag" value="change">
				            </form>
			        </td>
			    </tr>
			<% } %>
        </tbody>
    </table>
</div>

</body>

<script>
    function confirmDelete() {
        return confirm("本当に削除してもよろしいですか？");
    }
</script>

<style>


.back-button,
        a.back-button {
            color: #fff;
            background-color: #eb6100;
        }
</style>

</html>

