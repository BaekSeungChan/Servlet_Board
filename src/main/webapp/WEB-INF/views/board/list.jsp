<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>게시판</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="css/board/list.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark fixed-top">
    <a class="navbar-brand" href="http://localhost:8080">News Room</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar">
    </div>
    <form class="form-inline" action="/board.do" method="GET">
        <input type="hidden" name="action" value="list">
        <input id="searchKey" class="form-control mr-sm-2" type="text" name="searchKey" placeholder="Search">
        <button onclick="SearchButton()" class="search-button" type="submit">Search</button>
    </form>
</nav>

<div class="container mt-5">
    <div class="write-button text-right">
        <a href="/board.do?action=insertForm" class="btn btn-secondary" style="margin-bottom: 10px">글쓰기</a>
    </div>
    <div class="row" style="margin-bottom: 10px">
        <div class="col-2 bg-dark-gray text-center">ID</div>
        <div class="col-2 bg-dark-gray text-center">제목</div>
        <div class="col-4 bg-dark-gray text-center">내용</div>
        <div class="col-2 bg-dark-gray text-center">작성자</div>
        <div class="col-2 bg-dark-gray text-center">작성시간</div>
    </div>
    <form class="board-content">
        <c:forEach var="board" items="${list}">
            <div class="board-row row">
                <div class="col-2 text-center">
                    <a href="/board.do?action=view&id=${board.id}">${board.id}</a>
                </div>
                <div class="col-2 text-center">${board.title}</div>
                <div class="col-4 text-center">${board.content}</div>
                <div class="col-2 text-center">${board.writer}</div>
                <div class="col-2 text-center">${board.dueDate}</div>
            </div>
        </c:forEach>
    </form>
</div>

<script>

    const search = document.getElementById("search");

    function SearchButton(){

        const param = {
            action: "list",
            searchKey : searchKey.value,
        }

        fetch(`/board.do`, {
            method: 'POST',
            body: JSON.stringify(param),
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
        }).then(res => res.json())
            .then(json => {
                console.log("json", json);
                if(json.status == 0){
                    alert("글 등록 완료")
                    location = "board.do?action=list"
                } else {
                    alert(json.statusMessage)
                }
            });
    }
</script>
</body>
</html>