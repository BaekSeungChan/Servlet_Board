<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <style>
    body {
      font-family: Arial, sans-serif;
      background-color: #f2f2f2;
    }
    h2 {
      text-align: center;
      margin-top: 50px;
    }
    table {
      margin: auto;
      border-collapse: collapse;
      width: 50%;
      background-color: #ffffff;
      border-radius: 10px;
      box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: center;
    }
    th {
      background-color: #f2f2f2;
    }
    input[type=text], input[type=password], input[type=tel], input[type=radio], input[type=checkbox] {
      width: calc(100% - 24px);
      padding: 12px;
      margin: 8px;
      display: inline-block;
      border: 1px solid #ccc;
      border-radius: 4px;
      box-sizing: border-box;
    }
    input[type=submit] {
      background-color: #4CAF50;
      color: white;
      padding: 14px 20px;
      margin: 8px;
      border: none;
      border-radius: 4px;
      cursor: pointer;
      width: calc(100% - 24px);
    }
    input[type=submit]:hover {
      background-color: #45a049;
    }

    input[type="checkbox"] {
      display: none;
    }

    label {
      display: inline-block;
      margin-right: 10px;
    }

    label input[type="checkbox"] {
      display: inline-block;
      margin-right: 5px;
    }

    input[type="radio"] {
      display: none;
    }

    label {
      display: inline-block;
      margin-right: 10px;
    }

    label input[type="radio"] {
      display: inline-block;
      margin-right: 5px;
    }


  </style>
</head>
<body>
<h2>회원가입</h2>
<form method="post" action="/member?action=membersignUp">
  <table border="0">
    <tr height="50">
      <td width="150" align="right">아이디:</td>
      <td width="250"><input type="text" name="userid" required></td>
    </tr>
    <tr height="50">
      <td width="150" align="right">패스워드:</td>
      <td width="250"><input type="password" name="userpassword1" required></td>
    </tr>
    <tr height="50">
      <td width="150" align="right">패스워드확인:</td>
      <td width="250"><input type="password" name="userpassword2" required></td>
    </tr>
    <tr height="50">
      <td width="150" align="right">이름:</td>
      <td width="250"><input type="text" name="username" required></td>
    </tr>
    <tr height="50">
      <td width="150" align="right">주소:</td>
      <td width="250"><input type="text" name="address" required></td>
    </tr>
    <tr height="50">
      <td width="150" align="right">전화번호:</td>
      <td width="250"><input type="tel" name="phone" required></td>
    </tr>
    <tr height="50">
      <td width="150" align="right">성별:</td>
      <td width="250">
        <label><input type="radio" value="남" name="gender" required>남</label>
        <label><input type="radio" value="여" name="gender" required>여</label>
      </td>
    </tr>
    <tr height="50">
      <td width="150" align="right">관심 분야:</td>
      <td width="250">
        <label><input type="checkbox" name="hobby" value="축구">축구</label>
        <label><input type="checkbox" name="hobby" value="농구">농구</label>
        <label><input type="checkbox" name="hobby" value="야구">야구</label>
        <label><input type="checkbox" name="hobby" value="피구">피구</label>
      </td>
    </tr>
    <tr height="50">
      <td colspan="2" width="150" align="center">
        <input type="submit" value="회원 가입">
      </td>
    </tr>
  </table>
</form>
</body>
</html>
