<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Chat, java.util.List" %>

<%
    List<Chat> chatList = (List<Chat>) session.getAttribute("chatList");
%>

<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <title>掲示板一覧</title>
    <script src="https://code.jquery.com/jquery-3.3.1.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
	<script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
    
</head>


<script>
$(document).ready(function() {
    displayAllTitles();

    $('#select').on('change', function(){
        $.ajax({
            url: 'ChatControlServlet',
            type: "POST",
            data: {
                categoryId: $(this).val(),
                table: 'chat',
                flag: '一覧'
            }
        }).done(function(html){
            $("#table-container").html(html);
            setLinkClickEvent();
        }).fail(function() {
            alert("通信エラーが発生しました。");
        });
    });

    function displayAllTitles() {
        var allTitlesHtml = "";
        <% for (Chat chat : chatList) { %>
            allTitlesHtml += "<form action='ChatControlServlet' method='post'>";
            allTitlesHtml += "<input type='hidden' name='boardId' value='<%= chat.getBoardId() %>'>";
            allTitlesHtml += "<input type='hidden' name='userId' value='<%= chat.getUserId() %>'>";
            allTitlesHtml += "<input type='hidden' name='title' value='<%= chat.getTitle() %>'>";
            allTitlesHtml += "<input type='hidden' name='categoryId' value='<%= chat.getCategoryId() %>'>";
            allTitlesHtml += "<div class='d-grid gap-2 col-6 mx-auto'><button class=' btn btn-light' type='submit' style='width: 100%; margin-bottom: 10px;' class='title-button'><%= chat.getTitle() %></button></div>";
            allTitlesHtml += "</form>";
        <% } %>
        $("#table-container").html(allTitlesHtml);
    }
    function setLinkClickEvent() {
        $(".title-button").click(function() {
            $(this).closest("form").submit();
        });
    }
});
</script>

<body>

<header>
    <h1>掲示板一覧</h1>
</header>


<div class="nav">
  <div class="new">
    <form action="ChatControlServlet" method="get">
        <button type="submit" class="btn btn-info btn-lg">新規投稿</button>
        <input type="hidden"  name="flag" value="createChat"> 
    </form>
  </div>
  
  <div class="edit">
    <form action="ChatControlServlet" method="get">
        <button type="submit" class="btn btn-info btn-lg">投稿編集</button>
        <input type="hidden" name="flag" value="chatEdit">
    </form>
  </div>
</div>
<div class="selecter">
	<label for="select">カテゴリー:</label>
	<select id="select" name="category">
	    <option value="1">国語</option>
	    <option value="2">数学</option>
	    <option value="3">社会</option>
	    <option value="4">歴史</option>
	    <option value="5">理科</option>
	    <option value="6">国司対策</option>
	    <option value="7">AI</option>
	    <option value="8">設定</option>
	    <option value="9">プログラム</option>
	    <option value="10">言語</option>
	    <option value="11">その他</option>
	</select>
</div>

<div id="table-container">
    <!-- 表が表示されるエリア -->
</div>

<footer>
    <p>web_app</p>
</footer>


</body>
<style>
        body {
       		display: flex;
       		flex-direction: column;
            padding: 20px;
            margin: 0;
            min-height: 100vh;
            position: relative;
        }
        
        header {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            width: 100%;
            top: 0;
            z-index: 1000;
        }
        
        footer {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            width: 100%;
            position: relative
            bottom: 0;
            z-index: 1000;
            text-align: center;
        }
        
        .nav {
		    text-align: center;
		    justify-content: center; 
		}
		
		.nav .new,
		.nav .edit {
		    margin: 5px;
		    padding: 5px;
		}
		
		
		.selecter{
			text-align: center;
		    justify-content: center;
		}
		
		 label {
        	margin-right: 5px;
	    }
	
	    select {
	        width: 100px; 
	    }
    </style>
</html>

