
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Apple Community</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
    <script src="https://cdn.jsdelivr.net/npm/jquery@3.6.0/dist/jquery.slim.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
    <link href="css/board/main.css" rel="stylesheet">
</head>
<body>

<div class="jumbotron text-center">
    <h1 class="display-4">Apple Stories</h1>
    <p class="lead">"Let's go invent tomorrow rather than<br> worrying about what happened yesterday" – Steve Jobs</p>
</div>

<nav class="navbar navbar-expand-sm navbar-dark fixed-top">
    <div class="container">
        <a class="navbar-brand logo" href="http://localhost:8080">News Room</a>
        <a class="nav-link" href="/board.do?action=list">Community</a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#collapsibleNavbar">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="collapsibleNavbar">
            <ul class="navbar-nav ml-auto">
                <c:choose>
                    <c:when test="${not empty sessionScope.userid}">
                        <li class="nav-item">
                            <a class="nav-link" href="/member?action=myPage">MyPage</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member?action=signOut">Sign Out</a>
                        </li>
                    </c:when>
                    <c:otherwise>
                        <li class="nav-item">
                            <a class="nav-link" href="/member?action=signUpPage">Sign Up</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="/member?action=LoginPage">Sign In</a>
                        </li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>



<div class="container">
    <div class="row">
        <div class="col-md-4">
            <h2 class="section-title">About Apple</h2>
            <h5>Steve Jobs</h5>
                <img src="img_2.png" style="max-width: 100%; height: auto;">
            <p>Area codes 408 and 669</p>
            <h3>Some Links</h3>
            <p>If you want it, you can fly, <br> you just have to trust you a lot</p>
        </div>
        <div class="col-md-8">
            <h2 class="section-title">Plan to achieve carbon neutrality of all products</h2>
            <h5>Title description, Dec 7, 2024</h5>
            <div class="fakeimg">
                <img src="img.png" style="max-width: 100%; height: auto;">
            </div>
            <br>
            <p>To decarbonize products, Apple has introduced a clear and thorough approach that prioritizes reducing greenhouse gas emissions by power, materials, and transportation, the most important sources of emissions in the product's life cycle. First, product carbon emissions will be dramatically reduced, and then high-quality carbon credits obtained from nature-based projects will be applied to residual emissions that cannot be prevented or reduced by existing solutions.</p>
            <br>
            <h2 class="section-title">Apple and Google, Unwanted Tracking Issues</h2>
            <h5>Title description, Sep 2, 2014</h5>
            <div class="fakeimg">
                <img src="img_1.png" style="max-width: 100%; height: auto;">
            </div>
            <br>
            <p>Today, Apple and Google jointly submitted a proposed industry specification to combat the abuse of Bluetooth location tracking devices. The specification makes Bluetooth location tracking devices compatible with unauthorized tracking detection and makes them alert on iOS and Android platforms. Samsung, Tile, Chipolo, eufy Security, and Pebblebee have expressed support for the draft specification, which would provide best practices and guidelines for implementing these features in their products.</p>
        </div>
    </div>
</div>

<div class="jumbotron text-center" style="margin-top: 40px;">
    <p>March 25, 2024</p>
</div>

</body>
</html>
