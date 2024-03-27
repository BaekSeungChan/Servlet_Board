<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <link href="css/member/SignUpPage.css" rel="stylesheet">
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
