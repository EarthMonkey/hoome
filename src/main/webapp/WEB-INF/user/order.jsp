<%@ page import="entity.User" %>
<%@ page language="java" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
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
    <nav class="top-nav teal">
        <div class="container">
            <div class="nav-wrapper"><a class="page-title">我的订单</a></div>
        </div>
    </nav>

    <%@ include file="../common/user-nav.jsp" %>

</header>

<main>
    <div class="container" style="min-height: 500px;">
        <table class="highlight">
            <thead>
            <tr>
                <th>订单号</th>
                <th>酒店</th>
                <th>房间类型</th>
                <%--<th>数目</th>--%>
                <th>预定时间</th>
                <th>总价</th>
                <th>状态</th>
                <th>操作</th>
            </tr>
            </thead>
            <tbody id="record_container">
            <tr class="none" id="record_pattern">
                <td class="record_id">1</td>
                <td class="hotel_id none">老王客栈很长的</td>
                <td class="hotel_name">老王客栈很长的</td>
                <td class="room_style">单人房</td>
                <%--<td class="days">2</td>--%>
                <td class="book_time">2016-10-11 10:00:00</td>
                <td class="amount">400</td>
                <td class="status">已预定</td>
                <td><a class="btn teal" onclick="cancel(this)">退订</a></td>
            </tr>
            </tbody>
        </table>
    </div>
    <%--<%@ include file="../common/footer.jsp" %>--%>
</main>

<!--  Scripts-->
<script src="/js/jquery.min.js"></script>
<script src="/js/materialize.js"></script>
<script src="/js/init.js"></script>
<script>
    var id = ${sessionScope.get("user").getId()};
    $.ajax({
        method: "post",
        url: "/user/getMyOrder",
        async: false,
        dataType: "json",
        data: {
            "id": id,
        },
        success: function (result) {
            var recordHtml = $("#record_pattern").html();

            //all order record
            for (var i = 0; i < result.length; i++) {
                var tr = document.createElement("tr");
                tr.innerHTML = recordHtml;
                tr.getElementsByClassName("record_id")[0].innerHTML = result[i].id;
                tr.getElementsByClassName("hotel_id")[0].innerHTML = result[i].hotelId;
                tr.getElementsByClassName("hotel_name")[0].innerHTML = result[i].hotelName;
                tr.getElementsByClassName("room_style")[0].innerHTML = result[i].roomStyle == 0 ? "单人房" : "双人房";
                tr.getElementsByClassName("book_time")[0].innerHTML = new Date(result[i].bookTime).toLocaleString();
                tr.getElementsByClassName("amount")[0].innerHTML = result[i].amount;
                var status;
                switch (result[i].status) {
                    case 1:
                        status = "已入住";
                        tr.getElementsByClassName("btn")[0].className = "btn disabled";
                        tr.getElementsByClassName("btn")[0].innerHTML = "成交";
                        tr.getElementsByClassName("btn")[0].onclick = null;
                        break;
                    case 2:
                        status = "已退房";
                        tr.getElementsByClassName("btn")[0].className = "btn disabled";
                        tr.getElementsByClassName("btn")[0].innerHTML = "成交";
                        tr.getElementsByClassName("btn")[0].onclick = null;
                        break;
                    case -1:
                        status = "已退订";
                        tr.getElementsByClassName("btn")[0].className = "btn disabled";
                        tr.getElementsByClassName("btn")[0].innerHTML = "成交";
                        tr.getElementsByClassName("btn")[0].onclick = null;
                        break;
                    case 0:
                        status = "已预订";
                        break;
                }


                tr.getElementsByClassName("status")[0].innerHTML = status;
                $("#record_container").append(tr);
            }
        },
        error: function () {
            Materialize.toast('请求出错!', 1200);
        }
    });

    function cancel(obj) {
        var order_id = obj.parentNode.parentNode.getElementsByClassName("record_id")[0].innerHTML;

        $.ajax({
            method: "post",
            url: "/user/cancelOrder",
            async: false,
            data: {
                "order_id": order_id
            },
            success: function (result) {
                if (result == "success") {
                    Materialize.toast('tui成功!', 1200, (function () {
                        window.location.reload();
                    })());
                } else {
                    Materialize.toast('发布失败!', 1200);
                }
            },
            error: function () {
                Materialize.toast('请求出错!', 1200);
            }
        });


    }

</script>
</body>
</html>
