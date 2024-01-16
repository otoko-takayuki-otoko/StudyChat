<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>新規投稿</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"></script>
</head>

<body class="bg-light">

    <header>
        <button class="back-button" onclick="window.location.href='ChatControlServlet'">
            <a href="#" class="previous text-white">&laquo; 戻る</a>
        </button>
        <h2>新規投稿</h2>
    </header>

    <div class="container form-container">
        <form action="ChatControlServlet" method="get" class="bg-white p-4 rounded">
            <div class="form-group">
                <label for="title">タイトル:</label>
                <input type="text" id="title" name="title" class="form-control" required>
            </div>

            <div class="form-group">
                <label for="category">カテゴリー選択:</label>
                <select id="category" name="categoryId" class="form-control">
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

            <div class="form-group">
                <label for="comment">投稿内容:</label>
                <textarea id="comment" name="comment" rows="4" cols="50" class="form-control" required></textarea>
            </div>
            <input type="submit"  value="投稿する" class="btn btn-primary">
            <input type="hidden" name="flag" value="newChat">
        </form>
    </div>

    <footer>
        <p>web_app</p>
    </footer>
    
    <style>
        body {
            display: flex;
            flex-direction: column;
            min-height: 100vh;
            margin: 0;
        }

        .container {
            flex: 1;
            display: flex;
            flex-direction: column;
            justify-content: center;
            align-items: center;
            margin-top: 20px;
            margin-bottom: 60px; 
        }

        header {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            width: 100%;
            position: fixed;
            top: 0;
            z-index: 1000;
        }

        header h2 {
            text-align: center;
        }

        .back-button,
        a.back-button {
            color: #fff;
            background-color: #eb6100;
        }

        footer {
            background-color: #3498db;
            color: #fff;
            padding: 10px;
            width: 100%;
            position: fixed;
            bottom: 0;
            z-index: 1000;
            text-align: center;
        }
        
    </style>
</body>
</html>
