<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="model.Chat, java.util.List" %>

<%
    List<ChatContent> chatContentList = (List<ChatContent>) session.getAttribute("chatContentList");
%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>コメント編集</title>
</head>
<body>
    <table>
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
                        <form style="display:inline-block;" action="ChatEditServlet" method="post">
                            <input type="hidden" name="boardId" value=%= chat.getBoardId() %>">
                            <input type="submit" name="flag" value="delete">
                        </form>
                        <form style="display:inline-block;" action="ChatEditServlet" method="post">
                            <input type="hidden" name="boardId" value="<%= chat.getBoardId() %>">
                            <input type="submit" name="flag" value="change">
                        </form>
                    </td>
                </tr>
            <% } %>
        </tbody>
    </table>
</body>
</html>
