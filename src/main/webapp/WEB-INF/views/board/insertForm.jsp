<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>글쓰기</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <link href="css/board/list.css" rel="stylesheet">
</head>
<body>

<div class="container mt-5">
    <h2 class="text-center mb-4">글쓰기</h2>
    <form  action="/board.do" method="post">
        <div class="form-group">
            <label for="title">제목</label>
            <input type="text" class="form-control" id="title" name="title" required>
        </div>

        <div class="form-group">
            <label for="content">내용</label>
            <textarea class="form-control" id="content" name="content" rows="5" required></textarea>
        </div>

        <div class="form-group">
            <label for="writer">작성자</label>
            <input type="text" class="form-control" id="writer" name="writer" readonly>
        </div>

        <div class="text-right">
            <button onclick="insertButton()" class="btn btn-primary mr-2">등록</button>
            <a href="/board.do?action=list" class="btn btn-secondary">취소</a>
        </div>
    </form>
</div>
</body>

<script>

    const title = document.getElementById("title");
    const content = document.getElementById("content");
    const writer = document.getElementById("writer");

    function setUserId() {
        // 세션에 저장된 userid를 가져옴
        const userid = '<%= session.getAttribute("userid") %>';
        writer.value = userid;
    }

    // 페이지 로드 시 세션에 저장된 userid를 writer 필드에 넣음
    window.onload = setUserId;


    function insertButton(){

        const param = {
            action: "insert",
            title : title.value,
            content : content.value,
            writer : writer.value,
            userid : '<%= session.getAttribute("userid") %>'
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
                    alert("글 등록 완료")
                    location = "board.do?action=list"
                } else {
                    alert(json.statusMessage)
                }
            });
    }
</script>
</html>
