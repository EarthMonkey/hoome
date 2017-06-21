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
            <div class="nav-wrapper"><a class="page-title">会员管理</a></div>
        </div>
    </nav>
    <%@ include file="../common/boss-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px; padding-bottom: 50px;">

        <blockquote><h5>各地会员预订入住率&平均消费额&预订次数&总消费额</h5></blockquote>
        <div id="countryMap" style="width: 100%; height: 500px;"></div>

        <blockquote><h5>会员预订各房型所占比例</h5></blockquote>
        <div id="pieChart" style="width: 100%; height: 500px;"></div>
    </div>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script src="/js/echarts.min.js"></script>
<%--<script src="/js/charts/line-bar.js"></script>--%>
<%--<script src="/js/charts/calendar-heatmap.js"></script>--%>
<script src="/js/charts/country-map.js"></script>
<script src="/js/charts/pie-chart.js"></script>
<script src="/js/china.js"></script>
<script>
    //    $.ajax({
    //        url: "/analysis/order/lineBar",
    //        method: "POST",
    //        success: function (data) {
    //            getLineBar("lineBar", data);
    //        },
    //        error: function () {
    //            console.log("fail to get line-bar-chart");
    //        }
    //    });
    //
    //    $.ajax({
    //        url: "/analysis/order/calendar",
    //        method: "POST",
    //        success: function (data) {
    //            getHeatmap("heatMap", data);
    //        },
    //        error: function () {
    //            console.log("fail to get calendar-heat-map");
    //        }
    //    });

    $.ajax({
        url: "/analysis/member/countryMap",
        method: "POST",
        success: function (data) {
            getCountryMap("countryMap", data, ["预订入住率", "平均消费额", "预订次数", "总消费额"]);
        },
        error: function () {
            console.log("fail to get country-map");
        }
    });

    $.ajax({
        url: "/analysis/member/pieChart",
        method: "POST",
        success: function (data) {
            console.log(data);
            getPieChart("pieChart", data);
        },
        error: function () {
            console.log("fail to get pie-chart");
        }
    });
</script>

</body>
</html>
