<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수정하기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f8f9fa;
            padding-top: 60px;
        }
        .container {
            max-width: 600px;
            margin: 0 auto;
        }
        h2 {
            color: #343a40;
        }
        .form-group label {
            font-weight: bold;
            color: #343a40;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .btn-secondary {
            background-color: #6c757d;
            border-color: #6c757d;
        }
        .btn-secondary:hover {
            background-color: #5a6268;
            border-color: #545b62;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">수정하기</h2>
    <form action="/board.do" method="POST">
        <input type="hidden" name="action" value="update">
        <input type="hidden" name="ID" value="${board.id}">

        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" value="${board.title}" required>
        </div>

        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="5" required>${board.content}</textarea>
        </div>

        <div class="form-group">
            <label for="writer">작성자</label>
            <input type="text" class="form-control" id="writer" name="writer" value="${board.writer}" readonly>
        </div>

        <div class="text-right">
            <button type="submit" class="btn btn-primary mr-2">수정</button>
            <a href="/board.do?action=list" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

</body>
</html>