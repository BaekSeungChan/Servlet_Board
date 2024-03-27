<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>마이페이지</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.7.1/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="css/member/MyPage.css" rel="stylesheet">
</head>
<body>

<nav class="navbar navbar-expand-sm navbar-dark fixed-top">
    <a class="navbar-brand" href="http://localhost:8080">Today Good</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="collapsibleNavbar"></div>
</nav>

<div class="container mt-5">
    <form action="/updateProfile" method="POST">
        <div class="card">
            <div class="card-header">
                <h2>Profile</h2>
            </div>
            <div class="card-body">
                <table class="table">
                    <tbody>
                    <tr>
                        <th scope="row" class="font-weight-bold">Username:</th>
                        <td><input type="text" name="username" value="${user.username}" class="form-control"/></td>
                    </tr>
                    <tr>
                        <th scope="row" class="font-weight-bold">Password:</th>
                        <td><input type="password" name="userpassword" class="form-control"/></td>
                    </tr>
                    <tr>
                        <th scope="row" class="font-weight-bold">Gender:</th>
                        <td>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="male" value="남" ${user.gender == '남' ? 'checked' : ''}>
                                <label class="form-check-label" for="male">남</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="radio" name="gender" id="female" value="여" ${user.gender == '여' ? 'checked' : ''}>
                                <label class="form-check-label" for="female">여</label>
                            </div>
                        </td>
                    </tr>
                    <tr>
                        <th scope="row" class="font-weight-bold">Hobby:</th>
                        <td>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" name="hobby" value="축구" id="hobby1" ${user.hobby.contains('축구') ? 'checked' : ''}>
                                <label class="form-check-label" for="hobby1">축구</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" name="hobby" value="농구" id="hobby2" ${user.hobby.contains('농구') ? 'checked' : ''}>
                                <label class="form-check-label" for="hobby2">농구</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" name="hobby" value="야구" id="hobby3" ${user.hobby.contains('야구') ? 'checked' : ''}>
                                <label class="form-check-label" for="hobby3">야구</label>
                            </div>
                            <div class="form-check form-check-inline">
                                <input class="form-check-input" type="checkbox" name="hobby" value="피구" id="hobby4" ${user.hobby.contains('피구') ? 'checked' : ''}>
                                <label class="form-check-label" for="hobby4">피구</label>
                            </div>
                        </td>
                    </tr>

                    <tr>
                        <th scope="row" class="font-weight-bold">Address:</th>
                        <td><input type="text" name="address" value="${user.address}" class="form-control"/></td>
                    </tr>
                    <tr>
                        <th scope="row" class="font-weight-bold">Phone:</th>
                        <td><input type="text" name="phone" value="${user.phone}" class="form-control"/></td>
                    </tr>
                    </tbody>
                </table>
                <div class="text-right">
                    <button type="submit" class="btn btn-primary">Update Profile</button>
                </div>
            </div>
        </div>
    </form>
</div>


</body>
</html>
