<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>게시물 상세 페이지</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link href="css/board/view.css" rel="stylesheet">
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
                <button onclick="updateBoard(${board.id})" class="btn btn-primary mr-2">수정</button>
                <button onclick="deleteBoard()" class="btn btn-danger">삭제</button>
            </div>
        </div>
    </c:if>
</div>
</body>

<script>

    function updateBoard(boardId) {
        location = '/board.do?action=updateForm&id=' + boardId;
    }

    function deleteBoard() {

        const param = {
            action : "delete",
            id : ${board.id}
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
                    alert("회원정보를 삭제 하였습니다.")
                    location.href = "board.do?action=list"
                } else {
                    alert(json.statusMessage)
                }
            });
    }
</script>
</html>
