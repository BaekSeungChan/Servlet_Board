<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 상세 페이지</title>
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
        .font-weight-bold {
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
        .btn-danger {
            background-color: #dc3545;
            border-color: #dc3545;
        }
        .btn-danger:hover {
            background-color: #c82333;
            border-color: #bd2130;
        }
        /* 추가된 스타일 */
        .section {
            border: 1px solid #dee2e6;
            border-radius: 5px;
            padding: 10px;
            margin-bottom: 15px;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">게시물 상세 페이지</h2>
    <c:if test="${not empty board}">
        <div class="row">
            <div class="col-md-12 section">
                <div class="font-weight-bold">제목:</div>
                <div>${board.title}</div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-12 section">
                <div class="font-weight-bold">내용:</div>
                <div>${board.content}</div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-12 section">
                <div class="font-weight-bold">작성자:</div>
                <div>${board.writer}</div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-12 section">
                <div class="font-weight-bold">작성일:</div>
                <div>${board.dueDate}</div>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-md-12 text-right">
                <a href="/board.do?action=updateForm&ID=${board.id}" class="btn btn-primary mr-2">수정</a>
                <a href="/board.do?action=delete&ID=${board.id}" class="btn btn-danger">삭제</a>
            </div>
        </div>
    </c:if>
</div>

</body>
</html>
