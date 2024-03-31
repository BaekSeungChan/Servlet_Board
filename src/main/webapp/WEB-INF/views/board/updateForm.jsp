<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>수정하기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link href="css/board/updateForm.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">수정하기</h2>
    <form action="/board.do" method="POST">
        <input type="hidden" name="action" value="update">

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
            <button onclick="updateButton()" class="btn btn-primary mr-2">수정</button>
            <a href="/board.do?action=list" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>

<script>

    const title = document.getElementById("title");
    const content = document.getElementById("content");

    function updateButton(){

        const param = {
            action: "update",
            id: ${board.id},
            title : title.value,
            content : content.value,
        }

        fetch(`/board.do`, {
            method: 'POST',
            body: JSON.stringify(param),
            headers: {
                'Content-Type': 'application/json; charset=utf-8'
            },
        }).then(res => res.json())
            .then(json => {
                if(json.status == 0){
                    alert("수정 완료")
                    location.href = "board.do?action=list"
                } else {
                    alert(json.statusMessage)
                }
            });
    }
</script>

</body>
</html>
