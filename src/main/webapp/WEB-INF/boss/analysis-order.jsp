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
            <div class="nav-wrapper"><a class="page-title">订单管理</a></div>
        </div>
    </nav>
    <%@ include file="../common/boss-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px; padding-bottom: 50px;">

        <blockquote><h5>每月订单笔数&总订单数</h5></blockquote>
        <div id="lineBar" style="width: 100%; height: 400px;"></div>

        <blockquote><h5>过去一年每日订单笔数</h5></blockquote>
        <div id="heatMap" style="width: 100%; height: 300px;"></div>

        <blockquote><h5>全国各地订单笔数</h5></blockquote>
        <div id="countryMap" style="width: 100%; height: 500px;"></div>

        <blockquote><h5>订单入住所占比例</h5></blockquote>
        <div id="pieChart" style="width: 100%; height: 500px;"></div>

        <blockquote><h5>各客栈订单数</h5></blockquote>
        <div id="bigBar" style="width: 100%; height: 500px;"></div>
    </div>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script src="/js/echarts.min.js"></script>
<script src="/js/charts/line-bar.js"></script>
<script src="/js/charts/calendar-heatmap.js"></script>
<script src="/js/charts/country-map.js"></script>
<script src="/js/charts/pie-chart.js"></script>
<script src="/js/charts/big-bar.js"></script>
<script src="/js/china.js"></script>
<script>
    $.ajax({
        url: "/analysis/order/lineBar",
        method: "POST",
        success: function (data) {
            getLineBar("lineBar", data, ["月订单数", "总订单数"]);
        },
        error: function () {
            console.log("fail to get line-bar-chart");
        }
    });

    $.ajax({
        url: "/analysis/order/calendar",
        method: "POST",
        success: function (data) {
            getHeatmap("heatMap", data);
        },
        error: function () {
            console.log("fail to get calendar-heat-map");
        }
    });

    $.ajax({
        url: "/analysis/order/countryMap",
        method: "POST",
        success: function (data) {
            getCountryMap("countryMap", data);
        },
        error: function () {
            console.log("fail to get country-map");
        }
    });

    $.ajax({
        url: "/analysis/order/pieChart",
        method: "POST",
        success: function (data) {
            console.log(data);
            getPieChart("pieChart", data, "订单入住所占比例");
        },
        error: function () {
            console.log("fail to get pie-chart");
        }
    });

    $.ajax({
        url: "/analysis/order/getBigBar",
        method: "POST",
        success: function (data) {
            getBigBar("bigBar", data, "各客栈订单数");
        },
        error: function () {
            console.log("fail to get big-bar");
        }
    });
</script>

</body>
</html>
