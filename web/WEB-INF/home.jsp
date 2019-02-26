<%--
  Created by IntelliJ IDEA.
  User: roza
  Date: 2/25/19
  Time: 4:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Free CSS template by ChocoTemplates.com</title>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8"/>
    <link rel="stylesheet" href="/css/style.css" type="text/css" media="all"/>
    <!--[if IE 6]>
    <link rel="stylesheet" href="/css/ie6.css" type="text/css" media="all"/>
    <![endif]-->
    <script type="text/javascript" src="/js/jquery-1.4.2.min.js"></script>
    <script type="text/javascript" src="/js/jquery-func.js"></script>
</head>
<body>

<!-- Shell -->
<div id="shell">
    <!-- Header -->
    <div id="header">
        <h1 id="logo"><a href="/home">Movie World</a></h1>
        <div class="social">
            <span>FOLLOW US ON:</span>
            <ul>
                <li><a class="twitter" href="#">twitter</a></li>
                <li><a class="facebook" href="#">facebook</a></li>
                <li><a class="vimeo" href="#">vimeo</a></li>
                <li><a class="rss" href="#">rss</a></li>
            </ul>
        </div>

        <!-- Navigation -->
        <div id="navigation">
            <ul>
                <li><a class="active" href="home">HOME</a></li>
                <c:forEach var="genre" items="${requestScope.get('genres')}">
                    <li><a href="/home?genreId=${genre.id}">${genre.name}</a></li>
                </c:forEach>
                <li><a  href="/login.jsp">Login</a></li>
            </ul>
        </div>
        <!-- end Navigation -->

        <!-- Sub-menu -->
        <div id="sub-navigation">
            <ul>
<c:forEach var="genre" items="${requestScope.get('genres')}">
                <li><a href="/home?genreId=${genre.id}">${genre.name}</a></li>
                </c:forEach>

            </ul>
            <div id="search">
                <form action="/search" method="get" accept-charset="utf-8">
                    <label for="search-field">SEARCH</label>
                    <input type="text" name="search" value="Enter search here" id="search-field" title="Enter search here" class="blink search-field"  />
                    <input type="submit" value="GO!" class="search-button" />
                </form>
            </div>
        </div>
        <!-- end Sub-Menu -->

    </div>
    <!-- end Header -->

    <!-- Main -->
    <div id="main">
        <!-- Content -->
        <div id="content">

            <!-- Box -->
            <div class="box">
                <div class="head">
                    <h2>LATEST Movies</h2>
                    <p class="text-right"><a href="#">See all</a></p>
                </div>
<c:forEach var="mByLimit" items="${requestScope.get('movieByLimit')}">
                <!-- Movie -->

                <div class="movie">

                    <div class="movie-image">

                        <a href="/singlePost?id=${mByLimit.id}"><span class="play"><span class="name">${mByLimit.title}</span></span>
                            <img src="/getImage?picName=${mByLimit.picture}" width="60"/>
                        </a>
                    </div>

                    <div class="rating">
                        <p>Directed  </p>
                        <div class="stars">
                             ${mByLimit.director}
                        </div>

                    </div>
                </div>

                <!-- end Movie -->
</c:forEach>

</div>

            <!-- Box -->
            <div class="box" style="margin-top:50px;">
                <div class="head">
                    <h2>All Movies</h2>
                    <p class="text-right"><a href="#">See all</a></p>
                </div>
                <c:forEach var="allMovie" items="${requestScope.get('allMovies')}">
                    <!-- Movie -->

                    <div class="movie">

                        <div class="movie-image">

                            <a href="/singlePost?id=${allMovie.id}">
                                <span class="play"><span class="name">${allMovie.title}</span></span>
                                <img src="/getImage?picName=${allMovie.picture}" width="60"/>
                            </a>
                        </div>

                        <div class="rating">
                            <p>Directed  </p>
                            <div class="stars">
                                    ${allMovie.director}
                            </div>

                        </div>
                    </div>

                    <!-- end Movie -->
                </c:forEach>

 </div>

    </div>

</div>
</div>
<footer style="display:inline-block;text-align: center;    margin-top: 50px;
    width: 100%;    margin-bottom: 50px;">
    <p> © 2019 Movie Hunter, LLC. All Rights Reserved.  Designed by <a href="http://chocotemplates.com" target="_blank" title="The Sweetest CSS Templates WorldWide">ChocoTemplates.com</a></p>
</footer>

</body>
</html>
