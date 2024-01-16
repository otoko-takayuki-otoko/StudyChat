<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.ChatContent, model.Account, java.util.List, java.util.Collections, java.util.Comparator" %>

<%
    List<ChatContent> chatContentList = (List<ChatContent>) session.getAttribute("chatContentList");
    Account account = (Account)session.getAttribute("account");
    
    ChatContent chatContent = chatContentList.get(0);
    String boardId = chatContent.getBoardId();
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    <title>Chat Content List</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    
</head>
<body>

<header class="bg-primary text-white p-3">
	<button class="back-button" onclick="window.location.href='ChatControlServlet'">
            <a href="#" class="previous text-white">&laquo; 戻る</a>
     </button>
	<h2 class="text-center">掲示板</h2>
</header>

<div class="modal fade" id="commentModal" tabindex="-1" role="dialog" aria-labelledby="commentModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="commentModalLabel">新しいコメントを書く</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form action="ChatEditServlet" method="post">
        <input type="hidden" name="flag" value="newComment">
        <input type="hidden" name="userId" value="<%= account.getUserId() %>">
        <input type="hidden" name="boardId" value="<%= boardId %>">
          <div class="form-group">
            <label for="comment">コメント:</label>
            <textarea class="form-control" id="comment" name="comment" rows="3" required></textarea>
          </div>
          <button type="submit" class="btn btn-primary">コメントする</button>
        </form>
      </div>
    </div>
  </div>
</div>


<button type="button" class="btn btn-info" data-toggle="modal" data-target="#commentModal">
  新しいコメント
</button>

<%
// chatContentListを投稿時間で降順ソートする
Collections.sort(chatContentList, Comparator.comparing(ChatContent::getSentdatetime).reversed());

for (ChatContent content : chatContentList) {
%>
  <div class="comment">
    <div class="content-item <%= content.getUserId().equals(account.getUserId()) ? "own-comment" : "" %>">
        <p><strong>投稿時間:</strong> <%= content.getSentdatetime() %></p>
        <div class="comment-text">
            <%= content.getComment() %>
        </div>
    </div>
  </div>
<% } %>

</body>

<style>
		header{
			margin:10px;
		}
		
        body {
            padding: 20px;
            margin: 0;
            min-height: 100vh;
            position: relative;
        }
        .content-item {
            margin-bottom: 10px;
            padding: 10px;
            border-radius: 10px;
            background-color: #f0f8ff; 
            width: 70%;
            margin: auto;
        }
        
        .comment-text {
            font-size: 16px;
        }
        /* 自分が投稿したコメントの背景色 */
        .own-comment {
            background-color: #c8e6c9; /* Light green color */
            width: 70%;
            margin: auto;
        }
        .comment{
        	margin: 10px;
        }
        .back-button,
        a.back-button {
            color: #fff;
            background-color: #eb6100;
        }
    </style>
    
</html>
