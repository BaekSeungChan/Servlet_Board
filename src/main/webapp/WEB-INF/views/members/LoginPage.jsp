<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>로그인 페이지</title>
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
    }
    .login-box {
      width: 300px;
      margin: 100px auto;
      padding: 20px;
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    .login-box h2 {
      text-align: center;
      margin-bottom: 20px;
    }
    .login-box input[type="text"], .login-box input[type="password"], .login-box input[type="submit"] {
      width: calc(100% - 24px);
      padding: 12px;
      margin: 8px;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    .login-box input[type="checkbox"] {
      margin-top: 8px;
    }
    .login-box input[type="submit"] {
      background-color: #343a40;
      color: white;
      padding: 14px 20px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      width: calc(100% - 24px);
    }
    .login-box input[type="submit"]:hover {
      background-color: #555555;
    }
  </style>
</head>
<body>
<div class="login-box">
  <h2>로그인</h2>
  <form method="post" action="/member?action=Login">
    <input type="text" name="username" placeholder="아이디" required><br>
    <input type="password" name="password" placeholder="비밀번호" required><br>
    <input type="checkbox" id="autoLogin" name="autoLogin">
    <label for="autoLogin">자동 로그인</label><br>
    <input type="submit" value="로그인">
  </form>
</div>
</body>
</html>
