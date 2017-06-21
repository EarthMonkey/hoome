<%--
  Created by IntelliJ IDEA.
  User: L.H.S
  Date: 2017/6/13
  Time: 21:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1.0, user-scalable=no"/>
    <title>Welcome to Hoome!!!</title>
    <!-- CSS  -->
    <link href="/css/materialize.css" type="text/css" rel="stylesheet"
          media="screen,projection"/>
    <link href="/css/style.css" type="text/css" rel="stylesheet" media="screen,projection"/>
    <link href="/css/common.css" type="text/css" rel="stylesheet">
</head>
<body>

<header>
    <nav class="top-nav teal lighten-1">
        <div class="container">
            <div class="nav-wrapper"><a class="page-title">销售管理</a></div>
        </div>
    </nav>
    <%@ include file="../common/boss-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px; padding-bottom: 50px;">

        <blockquote><h5>市场计划前瞻</h5></blockquote>
        <div>
            <ul class="collection" id="plan">

            </ul>
        </div>
    </div>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script>
    $.ajax({
        url: "/analysis/market/plan",
        method: "POST",
        success: function (data) {
            for (var i=0; i<data.length; i++) {
                var li = $("<li class='collection-item'>"+ data[i] +"</li>");
                $("#plan").append(li);
            }
        },
        error: function () {
            console.log("fail to get plan");
        }
    });
</script>

</body>
</html>
