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
            <div class="nav-wrapper"><a class="page-title">入住管理</a></div>
        </div>
    </nav>
    <%@ include file="../common/boss-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px; padding-bottom: 50px;">

        <blockquote><h5>各房型入住所占比例</h5></blockquote>
        <div id="stylePieChart" style="width: 100%; height: 500px;"></div>

        <blockquote><h5>会员入住所占比例</h5></blockquote>
        <div id="pieChart" style="width: 100%; height: 500px;"></div>

        <blockquote><h5>全国各地入住人数</h5></blockquote>
        <div id="countryMap" style="width: 100%; height: 500px;"></div>

    </div>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/charts/country-map.js"></script>
<script src="/js/charts/pie-chart.js"></script>
<script src="/js/china.js"></script>
<script>
    $.ajax({
        url: "/analysis/checkIn/countryMap",
        method: "POST",
        success: function (data) {
            getCountryMap("countryMap", data);
        },
        error: function () {
            console.log("fail to get country-map");
        }
    });

    $.ajax({
        url: "/analysis/checkIn/stylePieChart",
        method: "POST",
        success: function (data) {
            console.log(data);
            getPieChart("stylePieChart", data, "各房型入住所占比例");
        },
        error: function () {
            console.log("fail to get pie-chart");
        }
    });

    $.ajax({
        url: "/analysis/order/pieChart",
        method: "POST",
        success: function (data) {
            console.log(data);
            getPieChart("pieChart", data, "会员入住所占比例");
        },
        error: function () {
            console.log("fail to get pie-chart");
        }
    });
</script>

</body>
</html>
