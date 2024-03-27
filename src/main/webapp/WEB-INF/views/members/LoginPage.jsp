<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>로그인 페이지</title>
  <link href="css/member/LoginPage.css" rel="stylesheet">
</head>
<body>
<div class="login-box">
  <h2>로그인</h2>
  <form method="post" action="/member?action=Login">
    <input type="text" name="userid" placeholder="아이디" required><br>
    <input type="password" name="userpassword" placeholder="비밀번호" required><br>
    <input type="checkbox" id="autoLogin" name="autoLogin">
    <label for="autoLogin">자동 로그인</label><br>
    <input type="submit" value="로그인">
  </form>
</div>
</body>
</html>
